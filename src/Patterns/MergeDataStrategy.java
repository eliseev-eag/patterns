package Patterns;

/**
 * Created by happy on 18.01.2017.
 */
public interface MergeDataStrategy {
    void merge(TableUserData existTable, TableUserData newTable);
}
