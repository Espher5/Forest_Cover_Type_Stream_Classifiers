import concurrent.futures
import time
from skmultiflow.data.file_stream import FileStream
from skmultiflow.meta import AdaptiveRandomForestClassifier
from skmultiflow.bayes import NaiveBayes
from skmultiflow.trees import HoeffdingTreeClassifier


CSV_PATH = 'Dataset\\covtype.csv'
MAX_SAMPLES = 10000 #581811
logFile = open('Dataset\\results_online.log', 'a')


def queue_classifiers(classifiers, concurrency=1):
    concurrency = max(1, concurrency)
    with concurrent.futures.ProcessPoolExecutor(max_workers=concurrency) as executor:
        future_tasks = [executor.submit(classify, c) for c in classifiers]
        for task in concurrent.futures.as_completed(future_tasks):
            result = task.result()


def classify(stream_classifier):
    logFile.write('Classifier info: {}\n'.format(stream_classifier.get_info()))
    start_time = time.time()
    stream = FileStream(CSV_PATH)
    num_samples = 0
    correct_predictions = 0
    update_slots = 6
    checkpoint_flag = 0

    while num_samples < MAX_SAMPLES and stream.has_more_samples():
        if checkpoint_flag == MAX_SAMPLES // update_slots:
            checkpoint_flag = 0
            end_time = time.time() - start_time
            logFile.write('Processed {} samples\n'.format(num_samples))
            logFile.write('Accuracy: {}\n'.format(correct_predictions / num_samples))
            logFile.write('Total time: %s seconds\n' % end_time)
        checkpoint_flag += 1

        x, y = stream.next_sample()
        y_pred = stream_classifier.predict(x)
        if y[0] == y_pred[0]:
            correct_predictions += 1
        stream_classifier = stream_classifier.partial_fit(x, y)
        num_samples += 1
    end_time = time.time() - start_time
    logFile.write('Processed {} samples\n'.format(num_samples))
    logFile.write('Accuracy: {}\n'.format(correct_predictions / num_samples))
    logFile.write('Total time: %s seconds\n' % end_time)
    logFile.write('\n\n')


if __name__ == '__main__':
    queue_classifiers([
        AdaptiveRandomForestClassifier(),
        NaiveBayes(),
        HoeffdingTreeClassifier()
    ], 3)
