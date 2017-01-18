package Patterns;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by happy on 18.01.2017.
 */
public class NonDuplicateMerge implements MergeDataStrategy {
    @Override
    public void merge(TableUserData existTable, TableUserData newTable) {
        List<String> newHeaders = newTable.getHeaders();
        List<String> headers = existTable.getHeaders();
        int headersSize = headers.size();
        List<Integer> indexMappedProperty = new ArrayList<>();
        for (int i = 0; i < headersSize; i++) {
            int newIndex = newHeaders.indexOf(headers.get(i));
            indexMappedProperty.add(newIndex);
        }
        for (int i = 0; i < newTable.size(); i++) {
            RowUserData newRow = new RowUserData(newTable.get(i).getValues(), indexMappedProperty);
            existTable.getUserData().add(newRow);
        }
    }
}
