package Patterns;

import java.util.*;

/**
 * Created by Елисеев on 05.01.2017.
 */
public class TableUserData {

    private List<RowUserData> userData;
    private List<String> headers;

    public TableUserData(List<RowUserData> userData) {
        headers = userData.get(0).getValues();
        userData.remove(0);
        this.userData = userData;
    }

    public List<RowUserData> getUserData() {
        return userData;
    }

    public List<String> getHeaders() {
        return headers;
    }

    public RowUserData get(int index) {
        return userData.get(index);
    }

    public int size() {
        return userData.size();
    }

    public void addData(TableUserData newTable) {

        List<String> newHeaders = newTable.getHeaders();
        List<String> headers = this.getHeaders();
        MergeDataStrategy mergeStrategy;
        if (newHeaders.containsAll(headers)) {
            if (this.isDuplicateHeadersExist() || newTable.isDuplicateHeadersExist())
                mergeStrategy = new DuplicateMerge();
            else
                mergeStrategy = new NonDuplicateMerge();
            mergeStrategy.merge(this,newTable);
        } else throw new IllegalStateException("Не совпадающие заголовки в таблицах");
    }

    public List<String> getColumnValues(int columnIndex) {
        List<String> result = new ArrayList<>();
        for (RowUserData row : userData)
            result.add(row.getCellValue(columnIndex));
        return result;
    }

    public boolean isDuplicateHeadersExist() {
        Set<String> headersSet = new HashSet<String>(headers);
        return headersSet.size() < headers.size();
    }
}
