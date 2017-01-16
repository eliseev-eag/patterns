package Patterns;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Created by Елисеев on 08.01.2017.
 */
public class StatsAndCharsView {
    public StatsAndCharsView(String statsInfo) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("StatsAndChartsView.fxml"));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        StatsAndChartsViewController controller = loader.getController();
        controller.initialiseStatsInfo(statsInfo);
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public StatsAndCharsView(String oxAxis, String oyAxis, TableUserData table) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("StatsAndChartsView.fxml"));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        StatsAndChartsViewController controller = loader.getController();
        controller.initialiseCharts(oxAxis, oyAxis, table);
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
