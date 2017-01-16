package Patterns;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * Created by Елисеев on 05.01.2017.
 */
public class TableUserData {

    private final Map<String, Integer> headerMap;
    private List<RowUserData> userData;

    public TableUserData(Map<String, Integer> headerMap, List<RowUserData> userData) {
        this.headerMap = headerMap;
        this.userData = userData;
    }

    public Map<String, Integer> getHeaderMap() {
        return headerMap;
    }

    public List<RowUserData> getUserData() {
        return userData;
    }

    public RowUserData get(int index) {
        return userData.get(index);
    }

    public int size() {
        return userData.size();
    }

    public void addData(TableUserData newData) {
        Map<String, Integer> newDataHeaderMap = newData.getHeaderMap();
        if (newDataHeaderMap.keySet().containsAll(this.headerMap.keySet())) {
            List<Integer> indexMappedProperty = new ArrayList<>(Collections.nCopies(headerMap.size(), 0));
            for (Map.Entry<String, Integer> srcHeader : headerMap.entrySet())
                indexMappedProperty.set(srcHeader.getValue(), newDataHeaderMap.get(srcHeader.getKey()));
            for (int i = 0; i < newData.size(); i++) {
                RowUserData newRow = new RowUserData(newData.get(i).getValues(), indexMappedProperty);
                userData.add(newRow);
            }
        } else throw new IllegalStateException("Не совпадающие заголовки в таблицах");
    }

    public List<String> getSortHeaders() {
        List<String> result = new ArrayList<String>(Collections.nCopies(headerMap.size(), ""));
        for (Map.Entry<String, Integer> srcHeader : headerMap.entrySet())
            result.set(srcHeader.getValue(), srcHeader.getKey());
        return result;
    }

    public List<String> getColumnValues(String columnHeader) {
        List<String> result = new ArrayList<>();
        int columnIndex = headerMap.get(columnHeader);
        for (RowUserData row : userData)
            result.add(row.getCellValue(columnIndex));
        return result;
    }
}
