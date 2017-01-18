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

    @FXML
    private TreeView tree;
    @FXML
    private ComboBox<String> OYAxisComboBox;
    @FXML
    private ComboBox<String> OXAxisComboBox;
    @FXML
    private Button createChartButton;
    private CheckBoxTreeItem<String> rootItem;
    private TableUserData table;
    private Statistics statistics = new Statistics();
    private final Map<String, DoubleSupplier> checkboxContent = new HashMap<String, DoubleSupplier>() {{
        put("Минимум", statistics::getMin);
        put("Максимум", statistics::getMax);
        put("Среднее", statistics::getMean);
        put("Стандартное отклонение", statistics::getStandardDeviation);
        put("Сумма", statistics::getSum);
        put("Дисперсия", statistics::getVariance);
        put("Коэф. ассиметрии", statistics::getSkewness);
        put("Коэф. эксцесса", statistics::getKurtosis);
    }};

    @FXML
    public void initialiseData(TableUserData table) {
        this.table = table;
        List<String> sortHeaders = table.getSortHeaders();
        generateCheckboxTreeView(sortHeaders);
        for (String columnHeader : sortHeaders) {
            OXAxisComboBox.getItems().add(columnHeader);
            OYAxisComboBox.getItems().add(columnHeader);
        }
        OXAxisComboBox.setValue(sortHeaders.get(0));
        if (sortHeaders.size() > 1)
            OYAxisComboBox.setValue(sortHeaders.get(1));
        else
            OYAxisComboBox.setValue(sortHeaders.get(0));
        createChartButton.setDisable(table.size() < 2);
    }

    private void generateCheckboxTreeView(List<String> columnHeaders) {
        rootItem = new CheckBoxTreeItem<String>("Колонки");
        rootItem.setExpanded(true);
        tree.setCellFactory(CheckBoxTreeCell.<String>forTreeView());

        for (String columnHeader : columnHeaders) {

            CheckBoxTreeItem<String> checkBoxTreeItem = new CheckBoxTreeItem<String>(columnHeader);
            rootItem.getChildren().add(checkBoxTreeItem);
            for (String nestedValue : checkboxContent.keySet()) {
                CheckBoxTreeItem<String> nestedCheckbox = new CheckBoxTreeItem<String>(nestedValue);
                checkBoxTreeItem.getChildren().add(nestedCheckbox);
            }
        }
        tree.setRoot(rootItem);
        tree.setShowRoot(true);
    }

    @FXML
    private void getSelectedColumnNamesAndGenerateStats() {
        StringBuilder statsStringBuilder = new StringBuilder();
        try {
            for (TreeItem<String> child : rootItem.getChildren()) {
                CheckBoxTreeItem<String> checkBoxTreeItem = (CheckBoxTreeItem<String>) child;
                if (checkBoxTreeItem.isSelected() || checkBoxTreeItem.isIndeterminate()) {
                    List<String> values = table.getColumnValues(checkBoxTreeItem.getValue());
                    List<Double> doubleValues = new ArrayList<>();
                    try {
                        for (String value : values)
                            doubleValues.add(Double.parseDouble(value));
                    } catch (NumberFormatException exception) {
                        throw new IllegalArgumentException("Не цифровые значения в столбце " + checkBoxTreeItem.getValue());
                    }
                    statsStringBuilder.append(generateStatsForColumnValues(doubleValues, checkBoxTreeItem));
                }
            }
            String stats = statsStringBuilder.toString();
            if (!stats.isEmpty())
                new StatsAndCharsView(stats);
            else
                new Alert(Alert.AlertType.INFORMATION, "Выберите хотя бы одну колонку").showAndWait();
        } catch (IllegalArgumentException exception) {
            new Alert(Alert.AlertType.ERROR, exception.getMessage()).showAndWait();
        }
    }

    private String generateStatsForColumnValues(List<Double> values, CheckBoxTreeItem<String> columnNameCheckBox) {
        statistics.setValues(values);
        StringBuilder statsStringBuilder = new StringBuilder();
        for (TreeItem<String> child : columnNameCheckBox.getChildren()) {
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

    @FXML
    private void generateChart() {
        if (OYAxisComboBox.getValue().equals(OXAxisComboBox.getValue()))
            new Alert(Alert.AlertType.INFORMATION, "Выберите разные колонки для осей").showAndWait();
        else
            try {
                new StatsAndCharsView(OXAxisComboBox.getValue(), OYAxisComboBox.getValue(), table);
            } catch (NumberFormatException exception) {
                new Alert(Alert.AlertType.INFORMATION, "Встретилось нечисловое значение в одной из колонок").showAndWait();
            }
    }
}
