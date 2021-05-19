package history;

import java.util.ArrayList;
import java.util.List;

public class HistoryMetricsBean {
    List<Double> learningEvaluationInstances;
    List<Double> evaluationTime;
    List<Double> modelCost;
    List<Double> classifiedInstances;
    List<Double> correctClassifications;
    List<Double> kappaStatistic;
    List<Double> kappaTemporalStatistic;
    List<Double> kappaMStatistic;
    List<Double> modelTrainingInstances;
    List<Double> modelSerializedSize;

    public HistoryMetricsBean(List<Double> learningEvaluationInstances,List<Double> evaluationTime,
                              List<Double> modelCost, List<Double> classifiedInstances,
                              List<Double> correctClassifications, List<Double> kappaStatistic,
                              List<Double> kappaTemporalStatistic, List<Double> kappaMStatistic,
                              List<Double> modelTrainingInstances, List<Double> modelSerializedSize) {
        this.learningEvaluationInstances = learningEvaluationInstances;
        this.evaluationTime = evaluationTime;
        this.modelCost = modelCost;
        this.classifiedInstances = classifiedInstances;
        this.correctClassifications = correctClassifications;
        this.kappaStatistic = kappaStatistic;
        this.kappaTemporalStatistic = kappaTemporalStatistic;
        this.kappaMStatistic = kappaMStatistic;
        this.modelTrainingInstances = modelTrainingInstances;
        this.modelSerializedSize = modelSerializedSize;
    }

    public List<Double> getLearningEvaluationInstances() {
        return learningEvaluationInstances;
    }

    public void setLearningEvaluationInstances(List<Double> learningEvaluationInstances) {
        this.learningEvaluationInstances = learningEvaluationInstances;
    }

    public List<Double> getEvaluationTime() {
        return evaluationTime;
    }

    public void setEvaluationTime(List<Double> evaluationTime) {
        this.evaluationTime = evaluationTime;
    }

    public List<Double> getModelCost() {
        return modelCost;
    }

    public void setModelCost(List<Double> modelCost) {
        this.modelCost = modelCost;
    }

    public List<Double> getClassifiedInstances() {
        return classifiedInstances;
    }

    public void setClassifiedInstances(List<Double> classifiedInstances) {
        this.classifiedInstances = classifiedInstances;
    }

    public List<Double> getCorrectClassifications() {
        return correctClassifications;
    }

    public void setCorrectClassifications(List<Double> correctClassifications) {
        this.correctClassifications = correctClassifications;
    }

    public List<Double> getKappaStatistic() {
        return kappaStatistic;
    }

    public void setKappaStatistic(List<Double> kappaStatistic) {
        this.kappaStatistic = kappaStatistic;
    }

    public List<Double> getKappaTemporalStatistic() {
        return kappaTemporalStatistic;
    }

    public void setKappaTemporalStatistic(List<Double> kappaTemporalStatistic) {
        this.kappaTemporalStatistic = kappaTemporalStatistic;
    }

    public List<Double> getKappaMStatistic() {
        return kappaMStatistic;
    }

    public void setKappaMStatistic(List<Double> kappaMStatistic) {
        this.kappaMStatistic = kappaMStatistic;
    }

    public List<Double> getModelTrainingInstances() {
        return modelTrainingInstances;
    }

    public void setModelTrainingInstances(List<Double> modelTrainingInstances) {
        this.modelTrainingInstances = modelTrainingInstances;
    }

    public List<Double> getModelSerializedSize() {
        return modelSerializedSize;
    }

    public void setModelSerializedSize(List<Double> modelSerializedSize) {
        this.modelSerializedSize = modelSerializedSize;
    }
}
