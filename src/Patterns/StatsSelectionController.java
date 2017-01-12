package Patterns;

/**
 * Created by Елисеев on 08.01.2017.
 */
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.CheckBoxTreeCell;
import javafx.scene.layout.FlowPane;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.DoubleSupplier;

public class StatsSelectionController {
    private TableUserData table;
    @FXML
    private FlowPane generateChart;
    @FXML
    private TreeView tree;
    private CheckBoxTreeItem<String> rootItem;
    private Statistics statistics = new Statistics();
    private final Map<String, DoubleSupplier> checkboxContent = new HashMap<String, DoubleSupplier>(){{
        put("Минимум",  statistics::getMin);
        put("Максимум", statistics::getMax);
        put("Среднее",   statistics::getMean);
        put("Стандартное отклонение",   statistics::getStandardDeviation);
        put("Сумма",  statistics::getSum);
        put("Дисперсия", statistics::getVariance);
        put("Коэф. ассиметрии",   statistics::getSkewness);
        put("Коэф. эксцесса",   statistics::getKurtosis);
    }};

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
        Button createChartButton = new Button();
    }

    private void generateCheckboxTreeView(List<String> columnHeaders) {
        rootItem =  new CheckBoxTreeItem<String>("Колонки");
        rootItem.setExpanded(true);
        tree.setCellFactory(CheckBoxTreeCell.<String>forTreeView());

        for(String columnHeader : columnHeaders){

            CheckBoxTreeItem<String> checkBoxTreeItem =  new CheckBoxTreeItem<String>(columnHeader);
            rootItem.getChildren().add(checkBoxTreeItem);
            for(String nestedValue : checkboxContent.keySet()){
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
        StringBuilder statsStringBuilder = new StringBuilder();
        for(TreeItem<String> child: rootItem.getChildren()){
            CheckBoxTreeItem<String> checkBoxTreeItem = (CheckBoxTreeItem<String>)child;
            if(checkBoxTreeItem.isSelected()) {
                List<String> values = table.getColumnValues(checkBoxTreeItem.getValue());
                List<Double> doubleValues = new ArrayList<>();
                for(String value :values)
                    doubleValues.add(Double.parseDouble(value));
                statsStringBuilder.append(generateStatsForColumnValues(doubleValues,checkBoxTreeItem));
            }
        }
        new StatsAndCharsView(statsStringBuilder.toString());
    }

    private String generateStatsForColumnValues(List<Double> values,CheckBoxTreeItem<String> columnNameCheckBox){
        statistics.setValues(values);
        StringBuilder statsStringBuilder = new StringBuilder();
        for(TreeItem<String> child: columnNameCheckBox.getChildren()) {
            CheckBoxTreeItem<String> checkBoxTreeItem = (CheckBoxTreeItem<String>) child;
            if (checkBoxTreeItem.isSelected()) {
                statsStringBuilder.append(columnNameCheckBox.getValue());
                statsStringBuilder.append(" ");
                statsStringBuilder.append(checkBoxTreeItem.getValue());
                statsStringBuilder.append(": ");
                statsStringBuilder.append(checkboxContent.get(checkBoxTreeItem.getValue()).getAsDouble());
                statsStringBuilder.append("\n");
            }
        }
        return statsStringBuilder.toString();
    }

}
