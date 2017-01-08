package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.Map;

/**
 * Created by Елисеев on 05.01.2017.
 */
public class TableUserDataWrapper {
    private ObservableList<RowUserDataWrapper> observableTable = FXCollections.observableArrayList();
    private final Map<String, Integer> headerMap;

    private TableUserData nativeTable;

    public TableUserDataWrapper(TableUserData data){
        nativeTable = data;
        for (RowUserData row : data.getUserData()) {
            observableTable.add(new RowUserDataWrapper(row));
        }
        headerMap = data.getHeaderMap();
    }

    public void addData(TableUserData newData){
        Map<String, Integer> newDataHeaderMap = newData.getHeaderMap();
        if(newDataHeaderMap.keySet().containsAll(this.headerMap.keySet())){
            int sizeBeforeAddingRows = nativeTable.size();
            nativeTable.addData(newData);
            int sizeAfterAddingRows = nativeTable.size();
            for (int index = sizeBeforeAddingRows; index < sizeAfterAddingRows; index++)
                observableTable.add(new RowUserDataWrapper(nativeTable.get(index)));
        }
    }

    public ObservableList<RowUserDataWrapper> getTable() {
        return observableTable;
    }

    public Map<String, Integer> getHeaderMap() {
        return headerMap;
    }

    public TableUserData getNativeTable() {
        return nativeTable;
    }


}
