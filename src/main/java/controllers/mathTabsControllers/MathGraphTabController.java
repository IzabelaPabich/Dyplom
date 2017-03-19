package controllers.mathTabsControllers;

import controllers.NewSheetMathController;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.DAO.GraphsDAO;
import model.mathTables.EquationTable;
import model.mathTables.GraphTable;
import model.sheet.Graph;
import model.sheet.GraphMark;
import utils.SheetCommonUtils;
import utils.ViewUtils;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alebazi on 2017-01-28.
 */
public class MathGraphTabController {

    private NewSheetMathController newSheetMathController;
    List<GraphTable> tableGraphs = new ArrayList<>();

    @FXML
    private ToggleGroup gChoice = new ToggleGroup();

    @FXML
    RadioButton gFromDBRadioBtn, gManualRadioBtn;

    @FXML
    TextField gTxtField, firstCompTxtField, secondCompTxtField, thirdCompTxtField, firstOperationTxtField,
            secondOperationTxtField, thirdOperationTxtField;

    @FXML
    Button showTableBtn, addGraphBtn;

    @FXML
    TableView graphsTable;

    @FXML TableColumn firstCol, firstCheckCol, ICol, ICheckCol, secondCol,secondCheckCol, IICol, IICheckCol, thirdCol, thirdCheckCol,
                        IIICol, IIICheckCol;

    @FXML
    ImageView graphLegendImage;

    @FXML protected void showTable() throws SQLException, ClassNotFoundException {
        graphsTable.setVisible(true);
        if(gFromDBRadioBtn.isSelected()) {
            getDataFromDB();
            initManualTxtFields(true);
        }
        if(gManualRadioBtn.isSelected()) {
            initManualTxtFields(false);
        }
    }

    private void initManualTxtFields(boolean b) {
        firstCompTxtField.setDisable(b);
        firstOperationTxtField.setDisable(b);
        secondCompTxtField.setDisable(b);
        secondOperationTxtField.setDisable(b);
        thirdCompTxtField.setDisable(b);
        thirdOperationTxtField.setDisable(b);
        addGraphBtn.setDisable(b);
    }

    @FXML protected void enableShowTableBtn() {
        if(((!gTxtField.getText().isEmpty() && gTxtField.getText().compareTo("0") != 0)
                && (gFromDBRadioBtn.isSelected()) || gManualRadioBtn.isSelected())) {
            showTableBtn.setDisable(false);
        } else {
            showTableBtn.setDisable(true);
        }
    }

    @FXML protected void addGraph() {
        if (txtFieldsAreEmpty()) {
            ViewUtils.showErrorAlert("Uzupełnij wszystkie komponenty równiania!");
        } else {
            GraphTable tempGraph;
            GraphMark operation12 = new GraphMark(firstOperationTxtField.getText().substring(0,1),
                        firstOperationTxtField.getText().substring(1));
            GraphMark operation23 = new GraphMark(secondOperationTxtField.getText().substring(0,1),
                    secondOperationTxtField.getText().substring(1));
            GraphMark operation31 = new GraphMark(thirdOperationTxtField.getText().substring(0,1),
                    thirdOperationTxtField.getText().substring(1));

            tempGraph = new GraphTable(firstCompTxtField.getText(), operation12,
                    secondCompTxtField.getText(), operation23,
                    thirdCompTxtField.getText(), operation31,
                    false, false, false, false, false, false);
            tableGraphs.add(tempGraph);
            graphsTable.setItems(FXCollections.observableArrayList(tableGraphs));
        }
    }

    private boolean txtFieldsAreEmpty() {
        return (firstCompTxtField.getText().isEmpty() || secondCompTxtField.getText().isEmpty() ||
                thirdCompTxtField.getText().isEmpty() || firstOperationTxtField.getText().isEmpty() ||
                secondOperationTxtField.getText().isEmpty() || thirdOperationTxtField.getText().isEmpty());
    }

    public void getDataFromDB() throws SQLException, ClassNotFoundException {
        String range = newSheetMathController.getRangeString();
        List<Graph> graphs = GraphsDAO.searchGraphsWithAmount(Integer.parseInt(gTxtField.getText()), range);
        if(graphs.size() != 0) {
            GraphTable tempGraph;
            for(int i = 0; i < graphs.size(); i++) {
                tempGraph = new GraphTable(graphs.get(i).getFirstComp(), graphs.get(i).getOperation12(),
                        graphs.get(i).getSecondComp(), graphs.get(i).getOperation23(), graphs.get(i).getThirdComp(),
                        graphs.get(i).getOperation31(), false, false, false, false, false, false);
                tableGraphs.add(tempGraph);
            }
            graphsTable.setItems(FXCollections.observableArrayList(tableGraphs));
        }
    }

    public void setMainController(NewSheetMathController mainController) {
        this.newSheetMathController = mainController;
    }

    public List<Graph> getFinalGraphs() {
        List<Graph> finalEquations = new ArrayList<>();

        for(int i = 0; i < graphsTable.getItems().size(); i++) {
            finalEquations.add(SheetCommonUtils.eraseFieldsFromGraph((GraphTable) graphsTable.getItems().get(i)));
        }

        return finalEquations;
    }

    public List<GraphTable> getGraphsTable() {
        return graphsTable.getItems();
    }

    public void init() {
        graphsTable.setPlaceholder(new Label("Brak elementów"));
        graphLegendImage.setImage(new Image("/mathExamples/graphLegend.png"));

        firstCol.setCellValueFactory(new PropertyValueFactory<GraphTable, String>("firstComp"));
        firstCheckCol.setCellValueFactory(new PropertyValueFactory<GraphTable, Boolean>("firstColChecked"));
        firstCheckCol.setCellFactory(column -> new CheckBoxTableCell<>());

        ICol.setCellValueFactory(new PropertyValueFactory<GraphTable, String>("operation12String"));
        ICheckCol.setCellValueFactory(new PropertyValueFactory<GraphTable, Boolean>("IColChecked"));
        ICheckCol.setCellFactory(column -> new CheckBoxTableCell<>());

        secondCol.setCellValueFactory(new PropertyValueFactory<GraphTable, String>("secondComp"));
        secondCheckCol.setCellValueFactory(new PropertyValueFactory<GraphTable, Boolean>("secondColChecked"));
        secondCheckCol.setCellFactory(column -> new CheckBoxTableCell<>());

        IICol.setCellValueFactory(new PropertyValueFactory<GraphTable, String>("operation23String"));
        IICheckCol.setCellValueFactory(new PropertyValueFactory<GraphTable, Boolean>("IIColChecked"));
        IICheckCol.setCellFactory(column -> new CheckBoxTableCell<>());

        thirdCol.setCellValueFactory(new PropertyValueFactory<GraphTable, String>("thirdComp"));
        thirdCheckCol.setCellValueFactory(new PropertyValueFactory<GraphTable, Boolean>("thirdColChecked"));
        thirdCheckCol.setCellFactory(column -> new CheckBoxTableCell<>());

        IIICol.setCellValueFactory(new PropertyValueFactory<GraphTable, String>("operation31String"));
        IIICheckCol.setCellValueFactory(new PropertyValueFactory<GraphTable, Boolean>("IIIColChecked"));
        IIICheckCol.setCellFactory(column -> new CheckBoxTableCell<>());
    }

}
