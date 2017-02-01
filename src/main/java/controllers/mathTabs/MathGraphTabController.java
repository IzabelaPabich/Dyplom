package controllers.mathTabs;

import controllers.NewSheetMathController;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import model.DAO.GraphsDAO;
import model.mathTables.EquationTable;
import model.mathTables.GraphTable;
import model.sheet.Graph;
import model.sheet.GraphMark;
import utils.SheetCommonUtils;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alebazi on 2017-01-28.
 */
public class MathGraphTabController {

    private NewSheetMathController newSheetMathController;

    @FXML
    private ToggleGroup gChoice = new ToggleGroup();

    @FXML
    RadioButton gFromDBRadioBtn, gManualRadioBtn;

    @FXML
    TextField gTxtField;

    @FXML
    Button showTableBtn;

    @FXML
    TableView graphsTable;

    @FXML TableColumn firstCol, firstCheckCol, ICol, ICheckCol, secondCol,secondCheckCol, IICol, IICheckCol, thirdCol, thirdCheckCol,
                        IIICol, IIICheckCol;

    @FXML protected void showTable() throws SQLException, ClassNotFoundException {
        graphsTable.setVisible(true);
        if(gFromDBRadioBtn.isSelected()) {
            getDataFromDB();
        }
    }

    @FXML protected void enableShowTableBtn() {
        if(!gTxtField.getText().isEmpty() && gTxtField.getText().compareTo("0") != 0
                && (gFromDBRadioBtn.isSelected() || gManualRadioBtn.isSelected())) {
            showTableBtn.setDisable(false);
        } else {
            showTableBtn.setDisable(true);
        }
    }

    public void getDataFromDB() throws SQLException, ClassNotFoundException {
        String range = newSheetMathController.getRangeString();
        List<Graph> graphs = GraphsDAO.searchGraphsWithAmount(Integer.parseInt(gTxtField.getText()), range);
        if(graphs.size() != 0) {
            List<GraphTable> tableGraphs = new ArrayList<>();
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
            finalEquations.add(eraseFieldsFromEquation((GraphTable) graphsTable.getItems().get(i)));
        }

        return finalEquations;
    }

    private Graph eraseFieldsFromEquation(GraphTable graphToErase) {
        Graph gr = new Graph();
        gr.setFirstComp(eraseComponent(graphToErase.getFirstColChecked(), graphToErase.getFirstComp()));
        gr.setOperation12(eraseGraphMark(graphToErase.getIColChecked(), graphToErase.getOperation12()));
        gr.setSecondComp(eraseComponent(graphToErase.getSecondColChecked(), graphToErase.getSecondComp()));
        gr.setOperation23(eraseGraphMark(graphToErase.getIIColChecked(), graphToErase.getOperation23()));
        gr.setThirdComp(eraseComponent(graphToErase.getThirdColChecked(), graphToErase.getThirdComp()));
        gr.setOperation31(eraseGraphMark(graphToErase.getIIIColChecked(), graphToErase.getOperation31()));

        return gr;
    }

    private GraphMark eraseGraphMark(boolean checkBoxValue, GraphMark graphMark) {
        if(checkBoxValue) {
            GraphMark tempMark = new GraphMark();
            tempMark.setOperation(SheetCommonUtils.replaceMathComponentWithDots());
            tempMark.setValue(SheetCommonUtils.replaceMathComponentWithDots());
            return tempMark;
        } else {
            return graphMark;
        }
    }

    private String eraseComponent(boolean checkBoxValue, String component) {
        if(checkBoxValue) {
            return SheetCommonUtils.replaceMathComponentWithDots();
        } else {
            return component;
        }
    }

    public List<GraphTable> getGraphsTable() {
        return graphsTable.getItems();
    }

    public void init() {
        graphsTable.setPlaceholder(new Label("Brak elementów"));

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
