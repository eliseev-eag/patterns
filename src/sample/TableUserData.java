package sample;

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
}
