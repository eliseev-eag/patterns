package Patterns;


import javafx.collections.ListChangeListener;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.TextFieldTableCell;
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
    private Button exportTableButton;

    @FXML
    private Button statsDialogOpenButton;

    @FXML
    private TableView<RowUserDataWrapper> tableView;

    private  TableUserDataWrapper tableUserDataWrapper;

    @FXML
    public void createNewTable() throws IOException {
        File file = OpenFileDialog();
        if(file!=null) {
            InputDataConverter converter = InputDataFlyweight.getInstance().getInputConverter(file);
            TableUserData userData = converter.Convert(file);
            CreateTableViewColumns(userData);
        }
    }

    @FXML
    private void addInfoFromFile() throws IOException {
        File file = OpenFileDialog();
        if(file!=null) {
            InputDataConverter converter = InputDataFlyweight.getInstance().getInputConverter(file);
            TableUserData userData = converter.Convert(file);
            try {
                addParsedInfo(userData);
            }
            catch (IllegalStateException exception){
                new Alert(Alert.AlertType.ERROR,exception.getMessage()).showAndWait();
            }
        }
    }
    @FXML
    private void initialize() {
        tableView.getColumns().addListener(
                (ListChangeListener.Change<? extends TableColumn<? extends RowUserDataWrapper,?>> c) ->
                {addDataFromFileToTableButton.setDisable(tableView.getColumns().isEmpty());
                statsDialogOpenButton.setDisable(tableView.getColumns().isEmpty());
                exportTableButton.setDisable(tableView.getColumns().isEmpty());
                });
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
            Exporter exporter = ExporterFlyweight.getInstance().getExporter(file);
            exporter.Export(file,tableUserDataWrapper.getUserData());
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
            column.setCellFactory(TextFieldTableCell.forTableColumn());

            column.setOnEditCommit(event -> onEditCommitSelectedTableCommit(event));
            column.editableProperty().set(true);
            tableView.getColumns().addAll(column);
        }
        tableView.setItems(tableUserDataWrapper.getTable());
        tableView.setEditable(true);
    }

    private void onEditCommitSelectedTableCommit(TableColumn.CellEditEvent<RowUserDataWrapper,String> event){
        int columnIndex = event.getTablePosition().getColumn();
        String newValue = event.getNewValue();
        event.getRowValue().getProperty(columnIndex).setValue(newValue);
    }
    public void statsDialogOpen(MouseEvent mouseEvent) throws Exception {
        new StatsSelectionWindow(tableUserDataWrapper.getUserData());
    }
}


