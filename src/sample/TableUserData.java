package sample;

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

    public void add(RowUserData row){
        userData.add(row);
    }

    public void addData(TableUserData newData){
        Map<String, Integer> newDataHeaderMap = newData.getHeaderMap();
        if(newDataHeaderMap.keySet().containsAll(this.headerMap.keySet())){
            List<Integer> indexMappedProperty = new ArrayList<>(Collections.nCopies(headerMap.size(),0));
            for(Map.Entry<String,Integer> srcHeader: headerMap.entrySet())
                indexMappedProperty.set(srcHeader.getValue(),newDataHeaderMap.get(srcHeader.getKey()));
            for(int i = 0;i<newData.getUserData().size();i++){
                RowUserData newRow = new RowUserData(newData.getUserData().get(i).getUserData(), indexMappedProperty);
            }
        }

    }
}
