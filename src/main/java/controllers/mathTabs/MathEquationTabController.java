package controllers.mathTabs;

import controllers.NewSheetMathController;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.CheckBoxListCell;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import model.DAO.EquationsDAO;
import model.mathTables.EquationTable;
import model.sheet.Equation;
import utils.SheetCommonUtils;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alebazi on 2017-01-28.
 */
public class MathEquationTabController {

    private NewSheetMathController newSheetMathController;

    @FXML
    private ToggleGroup eChoice = new ToggleGroup();

    @FXML
    RadioButton eFromDBRadioBtn, eManualRadioBtn;

    @FXML
    TextField eTxtField;

    @FXML
    Button showTableBtn;

    @FXML
    TableView equationsTable;

    @FXML TableColumn firstCol, firstCheckCol, ICol,ICheckCol, secondCol,secondCheckCol, IICol, IICheckCol, thirdCol, thirdCheckCol;

    @FXML protected void showTable() throws SQLException, ClassNotFoundException {
        equationsTable.setVisible(true);
        if(eFromDBRadioBtn.isSelected()) {
            getDataFromDB();
        }
    }

    @FXML protected void enableShowTableBtn() {
        if(!eTxtField.getText().isEmpty() && eTxtField.getText().compareTo("0") != 0
                && (eFromDBRadioBtn.isSelected() || eManualRadioBtn.isSelected())) {
            showTableBtn.setDisable(false);
        } else {
            showTableBtn.setDisable(true);
        }
    }

    private void getDataFromDB() throws SQLException, ClassNotFoundException {
        String range = newSheetMathController.getRangeString();
        List<Equation> equations = EquationsDAO.searchEquationsWithAmount(Integer.parseInt(eTxtField.getText()), range);
        if(equations.size() != 0) {
            List<EquationTable> tableEquations = new ArrayList<>();
            EquationTable tempEquation;
            for(int i = 0; i < equations.size(); i++) {
                tempEquation = new EquationTable(equations.get(i).getFirstComp(), equations.get(i).getOperation(),
                        equations.get(i).getSecondComp(), equations.get(i).getEquationMark(), equations.get(i).getResult(),
                        false, false, false, false, false);
                tableEquations.add(tempEquation);
            }
            equationsTable.setItems(FXCollections.observableArrayList(tableEquations));
        }
    }

    public void init() {
        equationsTable.setPlaceholder(new Label("Brak elementów"));

        //firstCol, firstCheckCol, ICol, ICheckCol, secondCol, secondCheckCol, IICol, IICheckCol, thirdCol, thirdCheckCol
        firstCol.setCellValueFactory(new PropertyValueFactory<EquationTable, String>("firstComp"));
        firstCheckCol.setCellValueFactory(new PropertyValueFactory<EquationTable, Boolean>("firstColChecked"));
        firstCheckCol.setCellFactory(column -> new CheckBoxTableCell<>());
        ICol.setCellValueFactory(new PropertyValueFactory<EquationTable, String>("operation"));
        ICheckCol.setCellValueFactory(new PropertyValueFactory<EquationTable, Boolean>("IColChecked"));
        ICheckCol.setCellFactory(column -> new CheckBoxTableCell<>());
        secondCol.setCellValueFactory(new PropertyValueFactory<EquationTable, String>("secondComp"));
        secondCheckCol.setCellValueFactory(new PropertyValueFactory<EquationTable, Boolean>("secondColChecked"));
        secondCheckCol.setCellFactory(column -> new CheckBoxTableCell<>());
        IICol.setCellValueFactory(new PropertyValueFactory<EquationTable, String>("equationMark"));
        IICheckCol.setCellValueFactory(new PropertyValueFactory<EquationTable, Boolean>("IIColChecked"));
        IICheckCol.setCellFactory(column -> new CheckBoxTableCell<>());
        thirdCol.setCellValueFactory(new PropertyValueFactory<EquationTable, String>("result"));
        thirdCheckCol.setCellValueFactory(new PropertyValueFactory<EquationTable, Boolean>("thirdColChecked"));
        thirdCheckCol.setCellFactory(column -> new CheckBoxTableCell<>());

    }

    public void setMainController(NewSheetMathController controller) {
        this.newSheetMathController = controller;
    }

    public List<Equation> getFinalEquations() {
        List<Equation> finalEquations = new ArrayList<>();
        List<EquationTable> erasedEquations = new ArrayList<>();
        EquationTable tempEquation;
        for(int i = 0; i < equationsTable.getItems().size(); i++) {
            finalEquations.add(eraseFieldsFromEquation((EquationTable) equationsTable.getItems().get(i)));
        }

        return finalEquations;
    }

    private Equation eraseFieldsFromEquation(EquationTable equationToErase) {
        Equation eq = new Equation();
        eq.setFirstComp(eraseComponent(equationToErase.getFirstColChecked(), equationToErase.getFirstComp()));
        eq.setOperation(eraseComponent(equationToErase.getIColChecked(), equationToErase.getOperation()));
        eq.setSecondComp(eraseComponent(equationToErase.getSecondColChecked(), equationToErase.getSecondComp()));
        eq.setEquationMark(eraseComponent(equationToErase.getIIColChecked(), equationToErase.getEquationMark()));
        eq.setResult(eraseComponent(equationToErase.getThirdColChecked(), equationToErase.getResult()));

        return eq;
    }

    private String eraseComponent(boolean checkBoxValue, String component) {
        if(checkBoxValue) {
            return SheetCommonUtils.replaceMathComponentWithDots();
        } else {
            return component;
        }
    }

    public List<EquationTable> getEquationsTable() {
        return equationsTable.getItems();
    }
}
