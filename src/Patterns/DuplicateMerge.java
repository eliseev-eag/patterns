package Patterns;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by happy on 18.01.2017.
 */
public class DuplicateMerge implements MergeDataStrategy {
    @Override
    public void merge(TableUserData existTable, TableUserData newTable) {
        List<String> newHeaders = newTable.getHeaders();
        List<String> headers = existTable.getHeaders();
        int headersSize = headers.size();
        List<Integer> indexMappedProperty = new ArrayList<>();
        for (int i = 0; i < headersSize; i++) {
            int newIndex = newHeaders.indexOf(headers.get(i));
            if (indexMappedProperty.contains(newIndex)) {
                int numberOfRepetionOfElement = 0;
                for (int j = 0; j < i; j++)
                    if (headers.get(j).equals(headers.get(i)))
                        numberOfRepetionOfElement++;
                for (int currentRepetition = 0; newIndex < newHeaders.size(); newIndex++)
                    if (newHeaders.get(newIndex).equals(headers.get(i)))
                        if (currentRepetition < numberOfRepetionOfElement)
                            currentRepetition++;
                        else
                            break;
            }
            indexMappedProperty.add(newIndex);
        }
        for (int i = 0; i < newTable.size(); i++) {
            RowUserData newRow = new RowUserData(newTable.get(i).getValues(), indexMappedProperty);
            existTable.getUserData().add(newRow);
        }
    }
}
