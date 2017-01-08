package Patterns;


import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.IOException;
import java.util.Map;

public class MainWindowController {
    @FXML
    private Button createNewTableButton;

    @FXML
    private Button addDataFromFileToTableButton;

    @FXML
    private TableView<RowUserDataWrapper> tableView;

    private  TableUserDataWrapper tableUserDataWrapper;

    @FXML
    public void createNewTableColumnsButtonClick() throws IOException {
        File file = OpenFileDialog();
        if(file!=null) {
            InputDataConverter converter = new CSVDataConverter();
            TableUserData userData = converter.Convert(file);
            CreateTableViewColumns(userData);
        }
    }

    @FXML
    public void addInfoFromFile() throws IOException {
        File file = OpenFileDialog();
        if(file!=null) {
            InputDataConverter converter = new CSVDataConverter();
            TableUserData userData = converter.Convert(file);
            addParsedInfo(userData);
        }
    }
    @FXML
    private void initialize() {

    }

    private void addParsedInfo(TableUserData userData) {
        tableUserDataWrapper.addData(userData);
    }

    private File saveFileDialog(){
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save Document");
        FileChooser.ExtensionFilter extFilter =
                new FileChooser.ExtensionFilter("CSV files (*.csv)", "*.csv");
        fileChooser.getExtensionFilters().add(extFilter);
        File file = fileChooser.showSaveDialog(createNewTableButton.getScene().getWindow());
        return file;
    }
    @FXML
    private void exportTableToFile() throws IOException {
        File file = saveFileDialog();
        if(file!=null){
            Exporter exporter = new CSVExporter();
            exporter.Export(file,tableUserDataWrapper.getNativeTable());
        }
    }

    private File OpenFileDialog() throws IOException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Document");
        FileChooser.ExtensionFilter extFilter =
                new FileChooser.ExtensionFilter("CSV files (*.csv)", "*.csv");
        fileChooser.getExtensionFilters().add(extFilter);
        File file = fileChooser.showOpenDialog(createNewTableButton.getScene().getWindow());
        return file;
    }

    private void CreateTableViewColumns(TableUserData userData){
        tableView.getColumns().clear();
        tableView.editableProperty().set(true);
        tableUserDataWrapper = new TableUserDataWrapper(userData);

        Map<String, Integer> headerMap = tableUserDataWrapper.getHeaderMap();

        for (Map.Entry<String, Integer> entry : headerMap.entrySet()) {
            String key = entry.getKey();
            TableColumn<RowUserDataWrapper, String> column = new TableColumn<>(key);
            column.setCellValueFactory(param -> (param.getValue().getProperty(entry.getValue())));
            column.editableProperty().set(true);
            tableView.getColumns().addAll(column);
        }
        tableView.setItems(tableUserDataWrapper.getTable());
    }

    public void statsDialogOpen(MouseEvent mouseEvent) throws Exception {
        new StatsSelectionWindow(tableUserDataWrapper.getNativeTable());
    }
}


