package Patterns;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Created by Елисеев on 05.01.2017.
 */
public class RowUserDataWrapper {
    private ObservableList<StringProperty> properties = FXCollections.observableArrayList();
    private RowUserData rowUserData;

    public RowUserDataWrapper(RowUserData row){
        this.rowUserData = row;
        for(int i = 0;i<row.size();i++){
            int finalI = i;
            SimpleStringProperty simpleStringProperty = new SimpleStringProperty(row.getCellValue(finalI)){
                @Override
                public void set(String v){
                    super.set(v);
                    row.setCellValue(finalI,v);
                }

                @Override
                public void setValue(String value){
                    super.set(value);
                    row.setCellValue(finalI,value);
                }
            };

            properties.add(simpleStringProperty);
        }
    }

    public StringProperty getProperty(int i){
        return properties.get(i);
    }

}
