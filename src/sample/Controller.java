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
        FileChooser fileChooser = new FileChooser();//Класс работы с диалогом выборки и сохранения
        fileChooser.setTitle("Open Document");//Заголовок диалога
        FileChooser.ExtensionFilter extFilter =
                new FileChooser.ExtensionFilter("CSV files (*.csv)", "*.csv");//Расширение
        fileChooser.getExtensionFilters().add(extFilter);
        File file = fileChooser.showOpenDialog(button.getScene().getWindow());//Указываем текущую сцену CodeNote.mainStage
        if (file != null) {
//Open
            System.out.println("Процесс открытия файла");
            InputDataConverter converter = new CSVDataConverter();
            TableUserData userData =  converter.Convert(file);
            CreateTableViewColumns(userData);
        }
    }

    private void CreateTableViewColumns(TableUserData userData){
        TableUserDataWrapper tableUserDataWrapper = new TableUserDataWrapper(userData);
/*
        TableView<RowUserDataWrapper> tableView = new TableView<>();

        Map<String, Integer> headerMap = tableUserDataWrapper.getHeaderMap();

        for (Map.Entry<String, Integer> entry : headerMap.entrySet()) {
            String key = entry.getKey();
            TableColumn<RowUserDataWrapper, String> column = new TableColumn<>(key);
            //column.setCellValueFactory(param -> new ReadOnlyObjectWrapper(param.getValue().getProperty(entry.getValue())));
            tableView.getColumns().addAll(column);
        }
        tableView.setItems(tableUserDataWrapper.getUserData());*/

        final Set<String> strings = tableUserDataWrapper.getHeaderMap().keySet();
        for(String header : strings) {
            TableColumn tableColumn = new TableColumn(header);

            tableView.getColumns().add(tableColumn);
        }
        tableView.setItems(tableUserDataWrapper.getUserData());
    }
/*
    private void Test(){
        TableView tableView;
        String headers[] = null;
        String items[] = null;

        Employee ee;
        List<String> columns = new ArrayList<String>();
        List<String> rows = new ArrayList<String>();
        ObservableList<ObservableList> csvData = FXCollections.observableArrayList();

            try {
                int columnIndex = 0;
                TableColumn[] tableColumns;
                File f = new File("C:\\Users\\admin\\Desktop\\Project\\shipforecast\\Data\\Recieve\\ShipId-1432530905282-1.csv");
                if (f.exists() && !f.isDirectory()) {
                    FileReader fin = new FileReader(f);
                    BufferedReader in = new BufferedReader(fin);
                    String l;
                    int i = 0;

                    while ((l = in.readLine()) != null) {

                        if (i < 1) {
                            headers = l.split(",");

                            for (String w : headers) {
                                columns.add(w);

                            }
                            for (int ii = 0; ii < columns.size(); ii++) {
                                final int finalIdx = ii;
                                TableColumn<ObservableList<String>, String> column = new TableColumn<>(
                                        columns.get(ii)
                                );

                                // column.setText("hghjghjg");
                                column.setCellValueFactory(param ->
                                        new ReadOnlyObjectWrapper<>(param.getValue().get(finalIdx))
                                );

                             /*System.out.println(new ReadOnlyObjectWrapper<>(param.getValue().get(finalIdx)));*/

                              //  tableView.getColumns().addAll(column);
                           // }

                            //tableView.getColumns().addAll(tableColumns);
                    /*    } else {
                            ObservableList<String> row = FXCollections.observableArrayList();
                            row.clear();
                            items = l.split(",");
                            for (String item : items) {
                                System.out.println(item);
                                row.add(item);
                            }
                            csvData.add(row);
                        }
                        i++;

                        tableView.getItems().add(csvData);

                    }
                } else {
                    System.out.println("File Not Found");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }*/
}


