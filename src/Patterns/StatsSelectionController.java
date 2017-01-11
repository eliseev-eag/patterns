package Patterns;

/**
 * Created by Елисеев on 08.01.2017.
 */
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.CheckBox;
import javafx.scene.control.CheckBoxTreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.control.cell.CheckBoxTreeCell;
import javafx.scene.layout.FlowPane;

import java.util.ArrayList;
import java.util.List;

public class StatsSelectionController {
    private TableUserData table;
/*
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
    private CheckBox kurtosis;*/

    @FXML
    private FlowPane columnNames;

    @FXML
    private TreeView tree;

    @FXML
    public void initialiseData(TableUserData table){
        final String[] checkboxContent =
                {"Минимум", "Максимум","Среднее","Сумма","Стандартное отклонение","Дисперсия","Коэф. ассиметрии","Медиана","Коэф. эксцесса"};
        this.table = table;

        CheckBoxTreeItem<String> rootItem =  new CheckBoxTreeItem<String>("Колонки");
        rootItem.setExpanded(true);
        //final TreeView tree = new TreeView(rootItem);
        //tree.setEditable(true);
        tree.setCellFactory(CheckBoxTreeCell.<String>forTreeView());
        //columnNames.getChildren().add(tree);

        for(String columnHeader : table.getSortHeaders()){

            CheckBoxTreeItem<String> checkBoxTreeItem =  new CheckBoxTreeItem<String>(columnHeader);
            rootItem.getChildren().add(checkBoxTreeItem);
            checkBoxTreeItem.setExpanded(true);
            for(String nestedValue : checkboxContent){
                CheckBoxTreeItem<String> nestedCheckbox =  new CheckBoxTreeItem<String>(nestedValue);
                checkBoxTreeItem.getChildren().add(nestedCheckbox);
            }
        }
        tree.setRoot(rootItem);
        tree.setShowRoot(true);
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
