package Patterns;

/**
 * Created by Елисеев on 08.01.2017.
 */
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.CheckBox;
import javafx.scene.control.CheckBoxTreeItem;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.control.cell.CheckBoxTreeCell;
import javafx.scene.layout.FlowPane;

import java.util.ArrayList;
import java.util.List;

public class StatsSelectionController {
    private TableUserData table;
    @FXML
    private FlowPane generateChart;
    @FXML
    private TreeView tree;
    private CheckBoxTreeItem<String> rootItem;

    @FXML
    public void initialiseData(TableUserData table){
        this.table = table;
        generateCheckboxTreeView(table.getSortHeaders());

        for(String columnHeader : table.getSortHeaders()){
            CheckBox checkBox = new CheckBox();
            checkBox.setText(columnHeader);
            checkBox.setPadding(new Insets(0,0,10,0));
            generateChart.getChildren().add(checkBox);
        }
    }

    private void generateCheckboxTreeView(List<String> columnHeaders) {
        final String[] checkboxContent =
                {"Минимум", "Максимум","Среднее","Сумма","Стандартное отклонение","Дисперсия","Коэф. ассиметрии","Коэф. эксцесса"};

        rootItem =  new CheckBoxTreeItem<String>("Колонки");
        rootItem.setExpanded(true);
        tree.setCellFactory(CheckBoxTreeCell.<String>forTreeView());

        for(String columnHeader : columnHeaders){

            CheckBoxTreeItem<String> checkBoxTreeItem =  new CheckBoxTreeItem<String>(columnHeader);
            rootItem.getChildren().add(checkBoxTreeItem);
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
        for(Node node: generateChart.getChildren())
            if(node instanceof CheckBox){
                CheckBox checkBox = (CheckBox)node;
                if(checkBox.isSelected())
                    checkedColumnsName.add((checkBox).getText());
            }
    }

    @FXML
    private void getSelectedColumnNamesAndGenerateStats(){
        for(TreeItem<String> child: rootItem.getChildren()){
            CheckBoxTreeItem<String> checkBoxTreeItem = (CheckBoxTreeItem<String>)child;
            if(checkBoxTreeItem.isSelected()) {
                List<String> values = table.getColumnValues(checkBoxTreeItem.getValue());
                List<Double> doubleValues = new ArrayList<>();
                for(String value :values)
                    doubleValues.add(Double.parseDouble(value));
                generateStatsForColumnValues(doubleValues,checkBoxTreeItem);
            }
        }
    }

    private void generateStatsForColumnValues(List<Double> values,CheckBoxTreeItem<String> checkedStats){
        Statistics statistics = new Statistics();
        statistics.setValues(values);
    }

}
