package classifier;

import moa.evaluation.BasicClassificationPerformanceEvaluator;
import moa.evaluation.LearningCurve;
import moa.streams.ArffFileStream;
import moa.tasks.EvaluatePrequential;

import java.util.ArrayList;
import java.util.List;


public class StreamClassifier {
    private final String streamPath;
    private final ArffFileStream dataStream;
    private final List<ClassifierHistory> evaluationHistory;

    public StreamClassifier(String streamPath) {
        this.streamPath = streamPath;
        this.dataStream = getStream();
        this.evaluationHistory = new ArrayList<>();
    }

    public void classify(moa.classifiers.Classifier classifier) {
        classifier.setModelContext(getStream().getHeader());
        classifier.prepareForUse();

        BasicClassificationPerformanceEvaluator evaluator = new BasicClassificationPerformanceEvaluator();
        EvaluatePrequential task = new EvaluatePrequential();
        task.learnerOption.setCurrentObject(classifier);
        task.streamOption.setCurrentObject(dataStream);
        task.evaluatorOption.setCurrentObject(evaluator);
        task.prepareForUse();

        LearningCurve learningCurve = (LearningCurve) task.doTask();
        ClassifierHistory newEntry = new ClassifierHistory(classifier, learningCurve);
        evaluationHistory.add(newEntry);
    }

    public void compareModels() {
        if(evaluationHistory.size() <= 1) {
            return;
        }
        for (ClassifierHistory entry : evaluationHistory) {
            System.out.println(entry.getLearningCurve());
        }
    }


    private ArffFileStream getStream() {
        ArffFileStream arffStream = new ArffFileStream();
        arffStream.arffFileOption.setValue(streamPath);
        arffStream.classIndexOption.setValue(55);
        arffStream.prepareForUse();
        return arffStream;
    }

    private List<ClassifierHistory> getEvaluationHistory() {
        return evaluationHistory;
    }
}