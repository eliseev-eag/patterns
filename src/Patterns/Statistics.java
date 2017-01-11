package Patterns;

import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;

import java.util.List;

/**
 * Created by Елисеев on 08.01.2017.
 */
public class Statistics {
    private DescriptiveStatistics stats = new DescriptiveStatistics();

    public void setValues(List<Double> values){
        stats.clear();
        for (double value:values)
            stats.addValue(value);
    }

    public double getMin(){
        return stats.getMin();
    }

    public double getMax(){
        return stats.getMax();
    }

    public double getMean(){
        return stats.getMean();
    }

    public double getSum(){
        return stats.getSum();

    }
    public double getStandardDeviation(){
        return stats.getStandardDeviation();
    }

    public double getVariance(){
        return stats.getVariance();
    }

    public double getSkewness(){
        return stats.getSkewness();
    }

    public double getKurtosis(){
        return stats.getKurtosis();
    }
}
