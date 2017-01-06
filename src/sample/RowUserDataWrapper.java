package sample;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Елисеев on 05.01.2017.
 */
public class RowUserDataWrapper {
    List<StringProperty> properties = new ArrayList<StringProperty>() {
    };

    public RowUserDataWrapper(RowUserData row){
        for(String cell: row.getUserData()){
            properties.add(new SimpleStringProperty(cell));
        }
    }

    public StringProperty getProperty(int i){
        return properties.get(i);
    }

    public String getPropertyValue(int i){
        return properties.get(i).get();
    }

    public void setPropertyValue(int i,String value){
        properties.get(i).set(value);
    }
}
