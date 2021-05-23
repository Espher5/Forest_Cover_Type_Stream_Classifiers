import concurrent.futures
import pandas as pd
import time
from sklearn import metrics
from sklearn.ensemble import RandomForestClassifier
from sklearn.model_selection import train_test_split
from sklearn.naive_bayes import GaussianNB
from sklearn.preprocessing import StandardScaler
from sklearn.tree import DecisionTreeClassifier


CSV_PATH = 'Dataset\\covtype.csv'


def read_data():
    dataset = pd.read_csv(CSV_PATH, header=None)
    x = dataset.iloc[:, dataset.columns != '54']
    y = dataset.iloc[:, -1]
    return train_test_split(x, y, test_size=0.2, random_state=0)


def queue_classifiers(classifiers, names, concurrency=1):
    concurrency = max(1, concurrency)
    with concurrent.futures.ProcessPoolExecutor(max_workers=concurrency) as executor:
        future_tasks = [executor.submit(classify, c, n) for c, n in zip(classifiers, names)]


def classify(classifier, name):
    log_file = open('Logging\\Batch learning\\' + name + '.log', 'a')
    x_train, x_test, y_train, y_test = read_data()
    start_time = time.time()
    sc = StandardScaler()
    x_train = sc.fit_transform(x_train)
    x_test = sc.fit_transform(x_test)

    classifier.fit(x_train, y_train)
    y_pred = classifier.predict(x_test)

    log_file.write('Classifier: {}\n'.format(name))
    log_file.write('Accuracy: {}\n'.format(metrics.accuracy_score(y_test, y_pred)))
    log_file.write('Precision: {}\n'.format(metrics.precision_score(y_test, y_pred.round(), average="macro")))
    log_file.write('Recall: {}\n'.format(metrics.recall_score(y_test, y_pred.round(), average="macro")))
    log_file.write('F1-score: {}\n'.format(metrics.f1_score(y_test, y_pred.round(), average="macro")))
    log_file.write('Kappa score: {}\n'.format(metrics.cohen_kappa_score(y_test, y_pred)))
    log_file.write('Execution time: {}\n'.format(time.time() - start_time))
    print('Done ' + name)



if __name__ == '__main__':
    queue_classifiers([
        RandomForestClassifier(n_estimators=200, random_state=0),
        GaussianNB(),
        DecisionTreeClassifier()
    ], [
        'Random Forest',
        'Naive Bayes',
        'Decision Tree'
    ], 3)
