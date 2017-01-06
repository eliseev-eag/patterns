package sample;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.Set;

public class Controller {
    @FXML
    private Button button;

    @FXML
    private TableView<RowUserDataWrapper> tableView;

    @FXML
    public void onMouseClicked() throws IOException {
        OpenFileDialog();
    }


    private void OpenFileDialog() throws IOException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Document");
        FileChooser.ExtensionFilter extFilter =
                new FileChooser.ExtensionFilter("CSV files (*.csv)", "*.csv");//Расширение
        fileChooser.getExtensionFilters().add(extFilter);
        File file = fileChooser.showOpenDialog(button.getScene().getWindow());
        if (file != null) {
            InputDataConverter converter = new CSVDataConverter();
            TableUserData userData =  converter.Convert(file);
            CreateTableViewColumns(userData);
        }
    }

    private void CreateTableViewColumns(TableUserData userData){
        tableView.getColumns().clear();
        tableView.editableProperty().set(true);
        TableUserDataWrapper tableUserDataWrapper = new TableUserDataWrapper(userData);

        Map<String, Integer> headerMap = tableUserDataWrapper.getHeaderMap();

        for (Map.Entry<String, Integer> entry : headerMap.entrySet()) {
            String key = entry.getKey();
            TableColumn<RowUserDataWrapper, String> column = new TableColumn<>(key);
            column.setCellValueFactory(param -> (param.getValue().getProperty(entry.getValue())));
            tableView.getColumns().addAll(column);
        }
        tableView.setItems(tableUserDataWrapper.getUserData());
    }

}


