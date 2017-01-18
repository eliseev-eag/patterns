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

    public void addData(TableUserData newData) {

        List<String> newHeaders = newData.getHeaders();
        List<String> headers = this.getHeaders();
        if(isDuplicateHeadersExist(headers) || isDuplicateHeadersExist(newHeaders))
            throw new IllegalStateException("В существующей таблице содержатся дублирующие заголовки. Слияние невозможно");
        if (newHeaders.containsAll(headers)) {
            int headersSize = headers.size();
            List<Integer> indexMappedProperty = new ArrayList<>();
            for (int i = 0; i < headersSize; i++) {
                int newIndex = newHeaders.indexOf(headers.get(i));
                indexMappedProperty.add(newIndex);
            }
            for (int i = 0; i < newData.size(); i++) {
                RowUserData newRow = new RowUserData(newData.get(i).getValues(), indexMappedProperty);
                userData.add(newRow);
            }
        } else throw new IllegalStateException("Не совпадающие заголовки в таблицах");
    }

    public List<String> getColumnValues(String columnHeader) {
        List<String> result = new ArrayList<>();
        int columnIndex = getHeaders().indexOf(columnHeader);
        for (RowUserData row : userData)
            result.add(row.getCellValue(columnIndex));
        return result;
    }

    private boolean isDuplicateHeadersExist(List<String> headers){
        Set<String> headersSet = new HashSet<String>(headers);
        return headersSet.size() < headers.size();
    }
}
