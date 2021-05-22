package history;

import moa.classifiers.Classifier;
import moa.evaluation.LearningCurve;

public class ClassifierHistory {
    private Classifier classifier;
    private LearningCurve learningCurve;
    private HistoryMetricsBean historyMetricsBean;

    public ClassifierHistory(Classifier classifier, LearningCurve learningCurve, HistoryMetricsBean historyMetricsBean) {
        this.classifier = classifier;
        this.learningCurve = learningCurve;
        this.historyMetricsBean = historyMetricsBean;
    }

    public Classifier getClassifier() {
        return classifier;
    }

    public void setClassifier(Classifier classifier) {
        this.classifier = classifier;
    }

    public LearningCurve getLearningCurve() {
        return learningCurve;
    }

    public void setLearningCurve(LearningCurve learningCurve) {
        this.learningCurve = learningCurve;
    }

    public HistoryMetricsBean getHistoryMetricsBean() {
        return historyMetricsBean;
    }

    public void setHistoryMetricsBean(HistoryMetricsBean historyMetricsBean) {
        this.historyMetricsBean = historyMetricsBean;
    }
}
