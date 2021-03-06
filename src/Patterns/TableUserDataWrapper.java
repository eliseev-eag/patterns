package Patterns;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;
import java.util.Map;

/**
 * Created by Елисеев on 05.01.2017.
 */
public class TableUserDataWrapper {
    private ObservableList<RowUserDataWrapper> observableTable = FXCollections.observableArrayList();
    private TableUserData nativeTable;

    public TableUserDataWrapper(TableUserData data) {
        nativeTable = data;
        for (RowUserData row : data.getUserData()) {
            observableTable.add(new RowUserDataWrapper(row));
        }
    }

    public void addData(TableUserData newData) {
        int sizeBeforeAddingRows = nativeTable.size();
        nativeTable.addData(newData);
        int sizeAfterAddingRows = nativeTable.size();
        for (int index = sizeBeforeAddingRows; index < sizeAfterAddingRows; index++)
            observableTable.add(new RowUserDataWrapper(nativeTable.get(index)));
    }

    public ObservableList<RowUserDataWrapper> getTable() {
        return observableTable;
    }

    public List<String> getHeaders() {
        return nativeTable.getHeaders();
    }

    public TableUserData getUserData() {
        return nativeTable;
    }
}
