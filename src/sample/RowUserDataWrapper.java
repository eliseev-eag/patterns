package sample;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Елисеев on 05.01.2017.
 */
public class RowUserDataWrapper {
    private List<StringProperty> properties = new ArrayList<StringProperty>();
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
            };
            properties.add(simpleStringProperty);
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
