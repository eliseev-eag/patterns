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

    public void initialiseStatsInfo(String statsInfo) {
        TextArea textArea = new TextArea(statsInfo);
        textArea.setEditable(false);
        textArea.setWrapText(true);
        setChildrenElementForAhchorPane(textArea);
    }

    public void initialiseCharts(String oxAxis, String oyAxis, TableUserData table) {
        final LineChart<Number, Number> lineChart = createChart(oxAxis, oyAxis, table);

        final double SCALE_DELTA = 1.1;
        lineChart.setOnScroll(event -> {
            event.consume();
            if (event.getDeltaY() == 0) {
                return;
            }
            double scaleFactor = (event.getDeltaY() > 0) ? SCALE_DELTA : 1 / SCALE_DELTA;
            lineChart.setScaleX(lineChart.getScaleX() * scaleFactor);
            lineChart.setScaleY(lineChart.getScaleY() * scaleFactor);
        });

        class Delta {
            double x, y;
        }
        final Delta dragDelta = new Delta();
        lineChart.setOnMousePressed(event -> {
            if (event.getClickCount() == 2) {
                lineChart.setScaleX(1.0);
                lineChart.setScaleY(1.0);
                lineChart.setTranslateX(0);
                lineChart.setTranslateY(0);
            } else {
                dragDelta.x = lineChart.getTranslateX() - event.getScreenX();
                dragDelta.y = lineChart.getTranslateY() - event.getScreenY();
            }
        });

        lineChart.setOnMouseDragged(event -> {
            lineChart.setTranslateX(event.getScreenX() + dragDelta.x);
            lineChart.setTranslateY(event.getScreenY() + dragDelta.y);
        });

        setChildrenElementForAhchorPane(lineChart);
    }

    private LineChart<Number, Number> createChart(String oxAxis, String oyAxis, TableUserData table) {
        final NumberAxis xAxis = new NumberAxis();
        final NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel(oxAxis);
        yAxis.setLabel(oyAxis);
        final LineChart<Number, Number> lineChart = new LineChart<>(xAxis, yAxis);
        XYChart.Series<Number, Number> series = new XYChart.Series();
        series.setName("Зависимость " + oxAxis + " от " + oyAxis);
        int oxIndex = table.getHeaderMap().get(oxAxis);
        int oyIndex = table.getHeaderMap().get(oyAxis);
        for (RowUserData row : table.getUserData())
            series.getData().add(new XYChart.Data<>(Double.parseDouble(row.getCellValue(oxIndex)), Double.parseDouble(row.getCellValue(oyIndex))));
        lineChart.getData().add(series);
        return lineChart;
    }

    private void setChildrenElementForAhchorPane(Node node) {
        anchorPane.getChildren().add(node);
        AnchorPane.setTopAnchor(node, 10.);
        AnchorPane.setRightAnchor(node, 10.);
        AnchorPane.setLeftAnchor(node, 10.);
        AnchorPane.setBottomAnchor(node, 10.);
    }
}
