package classifier;

import history.ClassifierHistory;
import history.HistoryMetricsBean;
import moa.classifiers.Classifier;
import moa.evaluation.BasicClassificationPerformanceEvaluator;
import moa.evaluation.LearningCurve;
import moa.streams.ArffFileStream;
import moa.tasks.EvaluatePrequential;

import java.util.ArrayList;
import java.util.Arrays;
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
        compileModelHistory(classifier, learningCurve);
        System.out.println("Single model evaluation:");
        System.out.println(learningCurve);
    }

    public void compareModels() {
        if(evaluationHistory.size() <= 1) {
            return;
        }
        for (ClassifierHistory entry : evaluationHistory) {
            System.out.println("Accuracy: " + entry.getHistoryMetricsBean().getCorrectClassifications().get(5));
        }
    }


    private ArffFileStream getStream() {
        ArffFileStream arffStream = new ArffFileStream();
        arffStream.arffFileOption.setValue(streamPath);
        arffStream.classIndexOption.setValue(55);
        arffStream.prepareForUse();
        return arffStream;
    }


    private void compileModelHistory(Classifier classifier, LearningCurve learningCurve) {
        List<Double> learningEvaluationInstances = new ArrayList<>();
        List<Double> evaluationTime = new ArrayList<>();
        List<Double> modelCost = new ArrayList<>();
        List<Double> classifiedInstances = new ArrayList<>();
        List<Double> correctClassifications = new ArrayList<>();
        List<Double> kappaStatistic = new ArrayList<>();
        List<Double> kappaTemporalStatistic = new ArrayList<>();
        List<Double> kappaMStatistic = new ArrayList<>();
        List<Double> modelTrainingInstances = new ArrayList<>();
        List<Double> modelSerializedSize = new ArrayList<>();

        for(int i = 0; i < learningCurve.numEntries(); i++) {
            List<String> values = Arrays.asList(learningCurve.entryToString(i).split("\\s*,\\s*"));
            learningEvaluationInstances.add(Double.parseDouble(values.get(0)));
            evaluationTime.add(Double.parseDouble(values.get(1)));
            modelCost.add(Double.parseDouble(values.get(2)));
            classifiedInstances.add(Double.parseDouble(values.get(3)));
            correctClassifications.add(Double.parseDouble(values.get(4)));
            kappaStatistic.add(Double.parseDouble(values.get(5)));
            kappaTemporalStatistic.add(Double.parseDouble(values.get(6)));
            kappaMStatistic.add(Double.parseDouble(values.get(7)));
            modelTrainingInstances.add(Double.parseDouble(values.get(8)));
            modelSerializedSize.add(Double.parseDouble(values.get(9)));
        }

        HistoryMetricsBean hmb = new HistoryMetricsBean(
                learningEvaluationInstances,
                evaluationTime,
                modelCost,
                classifiedInstances,
                correctClassifications,
                kappaStatistic,
                kappaTemporalStatistic,
                kappaMStatistic,
                modelTrainingInstances,
                modelSerializedSize
        );
        ClassifierHistory newEntry = new ClassifierHistory(classifier, learningCurve, hmb);
        evaluationHistory.add(newEntry);
    }

    private List<ClassifierHistory> getEvaluationHistory() {
        return evaluationHistory;
    }
}