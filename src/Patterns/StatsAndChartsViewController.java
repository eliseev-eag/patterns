package Patterns;

/**
 * Created by Елисеев on 08.01.2017.
 */
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;

public class StatsAndChartsViewController {
    @FXML
    private AnchorPane anchorPane;

    public void initialiseStatsInfo(String statsInfo){
        TextArea textArea = new TextArea(statsInfo);
        textArea.setEditable(false);
        textArea.setWrapText(true);
        setChildrenElementForAhchorPane(textArea);
    }

    public void initialiseCharts(String oxAxis, String oyAxis,TableUserData table){
        final NumberAxis xAxis = new NumberAxis();
        final NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel(oxAxis);
        yAxis.setLabel(oyAxis);
        final LineChart<Number,Number> lineChart = new LineChart<>(xAxis, yAxis);
        XYChart.Series<Number,Number> series = new XYChart.Series();
        series.setName("Зависимость " + oxAxis + " от " + oyAxis);
        int oxIndex = table.getHeaderMap().get(oxAxis);
        int oyIndex = table.getHeaderMap().get(oyAxis);
        for (RowUserData row :table.getUserData())
            series.getData().add(new XYChart.Data<>(Double.parseDouble(row.getCellValue(oxIndex)), Double.parseDouble(row.getCellValue(oyIndex))));
        lineChart.getData().add(series);
        setChildrenElementForAhchorPane(lineChart);
    }

    private void setChildrenElementForAhchorPane(Node node) {
        anchorPane.getChildren().add(node);
        AnchorPane.setTopAnchor(node,10.);
        AnchorPane.setRightAnchor(node,10.);
        AnchorPane.setLeftAnchor(node,10.);
        AnchorPane.setBottomAnchor(node,10.);
    }
}
