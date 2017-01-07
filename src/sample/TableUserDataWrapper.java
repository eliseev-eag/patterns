package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * Created by Елисеев on 05.01.2017.
 */
public class TableUserDataWrapper {
    private ObservableList<RowUserDataWrapper> observableTable = FXCollections.observableArrayList();
    private final Map<String, Integer> headerMap;
    private TableUserData nativeTable;

    //Хранить необернутый tableUserData

    public TableUserDataWrapper(TableUserData data){
        nativeTable = data;
        for (RowUserData row : data.getUserData()) {
            observableTable.add(new RowUserDataWrapper(row));
        }
        headerMap = data.getHeaderMap();
    }

    public ObservableList<RowUserDataWrapper> getUserData() {
        return observableTable;
    }

    public Map<String, Integer> getHeaderMap() {
        return headerMap;
    }

    public void addData(TableUserData newData){
        Map<String, Integer> newDataHeaderMap = newData.getHeaderMap();
        if(newDataHeaderMap.keySet().containsAll(this.headerMap.keySet())){
            List<Integer> indexMappedProperty = new ArrayList<>(Collections.nCopies(headerMap.size(),0));
            for(Map.Entry<String,Integer> srcHeader: headerMap.entrySet())
                indexMappedProperty.set(srcHeader.getValue(),newDataHeaderMap.get(srcHeader.getKey()));
            for(int i = 0;i<newData.getUserData().size();i++){
                RowUserData newRow = new RowUserData(newData.getUserData().get(i).getUserData(), indexMappedProperty);
                nativeTable.add(newRow);
                observableTable.add(new RowUserDataWrapper(newRow));
            }
        }

    }
}
