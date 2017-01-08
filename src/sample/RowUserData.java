package sample;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by Елисеев on 05.01.2017.
 */
public class RowUserData {
    private List<String> userData;

    public RowUserData(List<String> userData) {
        this.userData = userData;
    }

    public RowUserData(List<String> inputRow,List<Integer> mappedProperty){
        userData = new ArrayList<>();
        for(int i = 0;i< mappedProperty.size();i++) {
            userData.add(inputRow.get(mappedProperty.get(i)));
        }
    }
    public List<String> getValues() {
        return userData;
    }

    public String getCellValue(int index){
        return userData.get(index);
    }

    public void setCellValue(int index,String value){
        userData.set(index,value);
    }

    public int size(){
        return userData.size();
    }
}
