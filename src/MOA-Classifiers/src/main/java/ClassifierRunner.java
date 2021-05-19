import classifier.StreamClassifier;
import moa.classifiers.bayes.NaiveBayes;
import moa.classifiers.meta.AdaptiveRandomForest;
import moa.classifiers.trees.HoeffdingTree;

public class ClassifierRunner {
    public static void main(String[] args) {
        StreamClassifier classifier = new StreamClassifier("covertype_with_col.arff");
        classifier.classify(new AdaptiveRandomForest());
        classifier.classify(new NaiveBayes());
        classifier.classify(new HoeffdingTree());
        classifier.compareModels();
    }
}
