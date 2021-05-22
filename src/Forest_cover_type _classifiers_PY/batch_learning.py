import concurrent.futures
import pandas as pd
import time
from sklearn import metrics, svm
from sklearn.ensemble import RandomForestClassifier
from sklearn.model_selection import train_test_split
from sklearn.naive_bayes import GaussianNB
from sklearn.preprocessing import StandardScaler


csv_path = 'Dataset\\covtype.csv'


def read_data():
    dataset = pd.read_csv(csv_path, header=None)
    x = dataset.iloc[:, dataset.columns != '54']
    y = dataset.iloc[:, -1]
    return train_test_split(x, y, test_size=0.2, random_state=0)


def queue_classifiers(classifiers, concurrency=1):
    concurrency = max(1, concurrency)
    with concurrent.futures.ProcessPoolExecutor(max_workers=concurrency) as executor:
        future_tasks = [executor.submit(classify, c) for c in classifiers]
        for task in concurrent.futures.as_completed(future_tasks):
            result = task.result()


def classify(classifier):
    x_train, x_test, y_train, y_test = read_data()
    sc = StandardScaler()
    x_train = sc.fit_transform(x_train)
    x_test = sc.fit_transform(x_test)

    classifier.fit(x_train, y_train)
    y_pred = classifier.predict(x_test)

    print('Mean Absolute Error:', metrics.mean_absolute_error(y_test, y_pred))
    print('Mean Squared Error:', metrics.mean_squared_error(y_test, y_pred))
    print('Root Mean Squared Error:', np.sqrt(metrics.mean_squared_error(y_test, y_pred)))

    print(metrics.f1_score(y_test, y_pred.round(), average="macro"))
    print(metrics.precision_score(y_test, y_pred.round(), average="macro"))
    print(metrics.recall_score(y_test, y_pred.round(), average="macro"))


if __name__ == '__main__':
    queue_classifiers([
        RandomForestClassifier(n_estimators=20, random_state=0),
        GaussianNB(),
        svm.SVC(kernel='linear')
    ], 3)