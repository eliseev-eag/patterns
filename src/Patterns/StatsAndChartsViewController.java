package Patterns;

/**
 * Created by Елисеев on 08.01.2017.
 */
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;

public class StatsAndChartsViewController {
    @FXML
    private AnchorPane anchorPane;

    @FXML
    public void initialiseStatsInfo(String statsInfo){
        TextArea textArea = new TextArea(statsInfo);
        textArea.setEditable(false);
        textArea.setWrapText(true);
        anchorPane.getChildren().add(textArea);
        AnchorPane.setTopAnchor(textArea,10.);
        AnchorPane.setRightAnchor(textArea,10.);
        AnchorPane.setLeftAnchor(textArea,10.);
        AnchorPane.setBottomAnchor(textArea,10.);
    }


}
