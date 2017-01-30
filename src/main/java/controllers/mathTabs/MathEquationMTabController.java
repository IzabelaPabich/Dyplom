package controllers.mathTabs;

import controllers.NewSheetMathController;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import model.DAO.EquationsMDAO;
import model.mathTables.EquationMTable;
import model.mathTables.EquationTable;
import model.sheet.Equation;
import model.sheet.EquationM;
import utils.SheetCommonUtils;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alebazi on 2017-01-28.
 */
public class MathEquationMTabController {

    private NewSheetMathController newSheetMathController;

    @FXML
    private ToggleGroup emChoice = new ToggleGroup();

    @FXML
    RadioButton emFromDBRadioBtn, emManualRadioBtn;

    @FXML
    TextField emTxtField;

    @FXML
    Button showTableBtn;

    @FXML
    TableView equationsMTable;

    @FXML TableColumn firstCol1, firstCheckCol1, ICol,ICheckCol, firstCol2, firstCheckCol2, IIICol, IIICheckCol,
            secondCol1, secondCheckCol1, IICol, IICheckCol, secondCol2, secondCheckCol2;

    @FXML protected void showTable() throws SQLException, ClassNotFoundException {
        equationsMTable.setVisible(true);
        if(emFromDBRadioBtn.isSelected()) {
            getDataFromDB();
        }
    }

    @FXML protected void enableShowTableBtn() {
        if(!emTxtField.getText().isEmpty() && emTxtField.getText().compareTo("0") != 0
                && (emFromDBRadioBtn.isSelected() || emManualRadioBtn.isSelected())) {
            showTableBtn.setDisable(false);
        } else {
            showTableBtn.setDisable(true);
        }
    }

    public void init() {
        equationsMTable.setPlaceholder(new Label("Brak elementów"));

        firstCol1.setCellValueFactory(new PropertyValueFactory<EquationMTable, String>("firstComp1"));
        firstCheckCol1.setCellValueFactory(new PropertyValueFactory<EquationMTable, Boolean>("firstCol1Checked"));
        firstCheckCol1.setCellFactory(column -> new CheckBoxTableCell<>());
        ICol.setCellValueFactory(new PropertyValueFactory<EquationMTable, String>("firstOperation"));
        ICheckCol.setCellValueFactory(new PropertyValueFactory<EquationMTable, Boolean>("IColChecked"));
        ICheckCol.setCellFactory(column -> new CheckBoxTableCell<>());
        firstCol2.setCellValueFactory(new PropertyValueFactory<EquationMTable, String>("firstComp2"));
        firstCheckCol2.setCellValueFactory(new PropertyValueFactory<EquationMTable, Boolean>("firstCol2Checked"));
        firstCheckCol2.setCellFactory(column -> new CheckBoxTableCell<>());

        IIICol.setCellValueFactory(new PropertyValueFactory<EquationTable, String>("equationMark"));
        IIICheckCol.setCellValueFactory(new PropertyValueFactory<EquationTable, Boolean>("IIIColChecked"));
        IIICheckCol.setCellFactory(column -> new CheckBoxTableCell<>());

        secondCol1.setCellValueFactory(new PropertyValueFactory<EquationTable, String>("secondComp1"));
        secondCheckCol1.setCellValueFactory(new PropertyValueFactory<EquationTable, Boolean>("secondCol1Checked"));
        secondCheckCol1.setCellFactory(column -> new CheckBoxTableCell<>());
        IICol.setCellValueFactory(new PropertyValueFactory<EquationTable, String>("secondOperation"));
        IICheckCol.setCellValueFactory(new PropertyValueFactory<EquationTable, Boolean>("IIColChecked"));
        IICheckCol.setCellFactory(column -> new CheckBoxTableCell<>());
        secondCol2.setCellValueFactory(new PropertyValueFactory<EquationTable, String>("secondComp2"));
        secondCheckCol2.setCellValueFactory(new PropertyValueFactory<EquationTable, Boolean>("secondCol2Checked"));
        secondCheckCol2.setCellFactory(column -> new CheckBoxTableCell<>());
    }

    public void getDataFromDB() throws SQLException, ClassNotFoundException {
        String range = newSheetMathController.getRangeString();
        List<EquationM> equations = EquationsMDAO.searchEquationsMWithAmount(Integer.parseInt(emTxtField.getText()), range);
        if(equations.size() != 0) {
            List<EquationMTable> tableEquations = new ArrayList<>();
            EquationMTable tempEquation;
            for(int i = 0; i < equations.size(); i++) {
                tempEquation = new EquationMTable(equations.get(i).getFirstComp1(), equations.get(i).getFirstOperation(),
                        equations.get(i).getFirstComp2(), equations.get(i).getEquationMark(), equations.get(i).getSecondComp1(),
                        equations.get(i).getSecondOperation(), equations.get(i).getSecondComp2(),
                        false, false, false, false, false, false, false);
                tableEquations.add(tempEquation);
            }
            equationsMTable.setItems(FXCollections.observableArrayList(tableEquations));
        }
    }

    public List<EquationM> getFinalEquationsM() {
        List<EquationM> finalEquations = new ArrayList<>();

        for(int i = 0; i < equationsMTable.getItems().size(); i++) {
            finalEquations.add(eraseFieldsFromEquation((EquationMTable) equationsMTable.getItems().get(i)));
        }

        return finalEquations;
    }

    private EquationM eraseFieldsFromEquation(EquationMTable equationToErase) {
        EquationM eq = new EquationM();
        eq.setFirstComp1(eraseComponent(equationToErase.getFirstCol1Checked(), equationToErase.getFirstComp1()));
        eq.setFirstOperation(eraseComponent(equationToErase.getIColChecked(), equationToErase.getFirstOperation()));
        eq.setFirstComp2(eraseComponent(equationToErase.getFirstCol2Checked(), equationToErase.getFirstComp2()));
        eq.setEquationMark(eraseComponent(equationToErase.getIIIColChecked(), equationToErase.getEquationMark()));
        eq.setSecondComp1(eraseComponent(equationToErase.getSecondCol1Checked(), equationToErase.getSecondComp1()));
        eq.setSecondOperation(eraseComponent(equationToErase.getIIColChecked(), equationToErase.getSecondOperation()));
        eq.setSecondComp2(eraseComponent(equationToErase.getSecondCol2Checked(), equationToErase.getSecondComp2()));

        return eq;
    }

    private String eraseComponent(boolean checkBoxValue, String component) {
        if(checkBoxValue) {
            return SheetCommonUtils.replaceMathComponentWithDots();
        } else {
            return component;
        }
    }

    public void setMainController(NewSheetMathController controller) {
        this.newSheetMathController = controller;
    }

    public List<EquationMTable> getEquationsMTable() {
        return equationsMTable.getItems();
    }
}
