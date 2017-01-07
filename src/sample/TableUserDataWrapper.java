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

    public void addData(TableUserData newData){
        Map<String, Integer> newDataHeaderMap = newData.getHeaderMap();
        //Менять ключи, чобы они шли в том же порядке, что и в первом файле
        if(newDataHeaderMap.keySet().containsAll(this.headerMap.keySet()))
        {
            for(int i = 0;i<newData.getUserData().size();i++){
                userData.add(new RowUserDataWrapper(newData.getUserData().get(i)));
            }
            System.out.println("Complete!");
        }

    }
}
