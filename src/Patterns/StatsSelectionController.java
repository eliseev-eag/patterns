package Patterns;

/**
 * Created by Елисеев on 08.01.2017.
 */
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.CheckBox;
import javafx.scene.control.CheckBoxTreeItem;
import javafx.scene.layout.FlowPane;

import java.util.ArrayList;
import java.util.List;

public class StatsSelectionController {
    private TableUserData table;

    @FXML
    private CheckBox min;

    @FXML
    private CheckBox max;

    @FXML
    private CheckBox mean;

    @FXML
    private CheckBox sum;

    @FXML
    private CheckBox standard_deviation;

    @FXML
    private CheckBox variance;

    @FXML
    private CheckBox skewness;

    @FXML
    private CheckBox median;

    @FXML
    private CheckBox kurtosis;

    @FXML
    private FlowPane columnNames;

    @FXML
    public void initialiseData(TableUserData table){
        this.table = table;
        for(String columnHeader : table.getSortHeaders()){
            CheckBox checkBox = new CheckBox();
            checkBox.setText(columnHeader);
            checkBox.setPadding(new Insets(0,0,10,0));
            columnNames.getChildren().add(checkBox);
        }
    }

    @FXML
    private void generateStats(){
        List<String> checkedColumnsName = new ArrayList<>();
        for(Node node: columnNames.getChildren())
            if(node instanceof CheckBox){
                CheckBox checkBox = (CheckBox)node;
                if(checkBox.isSelected())
                    checkedColumnsName.add((checkBox).getText());
        }

    }

}
