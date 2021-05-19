package classifier;

import moa.classifiers.Classifier;
import moa.evaluation.LearningCurve;

public class ClassifierHistory {
    private Classifier classifier;
    private LearningCurve learningCurve;

    public ClassifierHistory(Classifier classifier, LearningCurve learningCurve) {
        this.classifier = classifier;
        this.learningCurve = learningCurve;
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
}
