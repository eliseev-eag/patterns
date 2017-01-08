package Patterns;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Created by Елисеев on 08.01.2017.
 */
public class StatsSelectionWindow {
    public StatsSelectionWindow(TableUserData table) throws Exception
    {
        FXMLLoader loader = new FXMLLoader(   getClass().getResource("StatsSelectionWindow.fxml"));
        Parent root = loader.load();
        StatsSelectionController controller =loader.<StatsSelectionController>getController();
        controller.initalizeData(table);
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
