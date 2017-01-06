package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.Map;

/**
 * Created by Елисеев on 05.01.2017.
 */
public class TableUserDataWrapper {
    private ObservableList<RowUserDataWrapper> userData = FXCollections.observableArrayList();
    private final Map<String, Integer> headerMap;

    public TableUserDataWrapper(TableUserData data){
        for (RowUserData row : data.getUserData()) {
            userData.add(new RowUserDataWrapper(row));
        }
        headerMap = data.getHeaderMap();
    }

    public ObservableList<RowUserDataWrapper> getUserData() {
        return userData;
    }

    public Map<String, Integer> getHeaderMap() {
        return headerMap;
    }

    public RowUserDataWrapper get(int index){
        return userData.get(index);
    }
}
