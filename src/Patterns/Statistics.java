package Patterns;

import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;

/**
 * Created by Елисеев on 08.01.2017.
 */
public class Statistics {

    private DescriptiveStatistics stats = new DescriptiveStatistics();

    public Statistics(TableUserData table){
        for (RowUserData row:table.getUserData()) {

        }
        //stats.
    }
}
