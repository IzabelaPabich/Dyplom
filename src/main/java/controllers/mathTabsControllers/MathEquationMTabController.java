package controllers.mathTabsControllers;

import controllers.NewSheetMathController;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.DAO.EquationsMDAO;
import model.mathTables.EquationMTable;
import model.mathTables.EquationTable;
import model.sheet.Equation;
import model.sheet.EquationM;
import utils.SheetCommonUtils;
import utils.ViewUtils;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alebazi on 2017-01-28.
 */
public class MathEquationMTabController {

    private NewSheetMathController newSheetMathController;
    List<EquationMTable> tableEquations = new ArrayList<>();

    @FXML
    private ToggleGroup emChoice = new ToggleGroup();

    @FXML
    RadioButton emFromDBRadioBtn, emManualRadioBtn;

    @FXML
    TextField emTxtField, firstComp1TxtField, firstOperationTxtField, firstComp2TxtField, equalsTxtField,
    secondComp1TxtField, secondComp2TxtField, secondOperationTxtField;

    @FXML
    Button showTableBtn, addEquationMBtn;

    @FXML
    TableView equationsMTable;

    @FXML TableColumn firstCol1, firstCheckCol1, ICol,ICheckCol, firstCol2, firstCheckCol2, IIICol, IIICheckCol,
            secondCol1, secondCheckCol1, IICol, IICheckCol, secondCol2, secondCheckCol2;

    @FXML
    ImageView equMLegendImage;

    @FXML protected void showTable() throws SQLException, ClassNotFoundException {
        equationsMTable.setVisible(true);
        if(emFromDBRadioBtn.isSelected()) {
            getDataFromDB();
            initManualTxtFields(true);
        }
        if(emManualRadioBtn.isSelected()) {
            initManualTxtFields(false);
        }
    }

    private void initManualTxtFields(boolean b) {
        firstComp1TxtField.setDisable(b);
        firstOperationTxtField.setDisable(b);
        firstComp2TxtField.setDisable(b);
        equalsTxtField.setDisable(b);
        secondComp1TxtField.setDisable(b);
        secondOperationTxtField.setDisable(b);
        secondComp2TxtField.setDisable(b);
        addEquationMBtn.setDisable(b);
    }

    @FXML protected void enableShowTableBtn() {
        if(((!emTxtField.getText().isEmpty() && emTxtField.getText().compareTo("0") != 0)
                && (emFromDBRadioBtn.isSelected()) || emManualRadioBtn.isSelected())) {
            showTableBtn.setDisable(false);
        } else {
            showTableBtn.setDisable(true);
        }
    }

    @FXML protected void addEquationM() {
        if (txtFieldsAreEmpty()) {
            ViewUtils.showErrorAlert("Uzupełnij wszystkie komponenty równiania!");
        } else {
            EquationMTable tempEquation;
            tempEquation = new EquationMTable(firstComp1TxtField.getText(), firstOperationTxtField.getText(),
                    firstComp2TxtField.getText(), equalsTxtField.getText(), secondComp1TxtField.getText(),
                    secondOperationTxtField.getText(), secondComp2TxtField.getText(),
                    false, false, false, false, false, false, false);
            tableEquations.add(tempEquation);
            equationsMTable.setItems(FXCollections.observableArrayList(tableEquations));
        }
    }

    private boolean txtFieldsAreEmpty() {
        return (firstComp1TxtField.getText().isEmpty() || firstComp2TxtField.getText().isEmpty() ||
                firstOperationTxtField.getText().isEmpty() || equalsTxtField.getText().isEmpty() ||
                secondComp1TxtField.getText().isEmpty() || secondComp2TxtField.getText().isEmpty() ||
                secondOperationTxtField.getText().isEmpty());
    }

    public void init() {
        equationsMTable.setPlaceholder(new Label("Brak elementów"));
        equMLegendImage.setImage(new Image("/mathExamples/equationMLegend.png"));

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
            finalEquations.add(SheetCommonUtils.eraseFieldsFromEquationM((EquationMTable) equationsMTable.getItems().get(i)));
        }

        return finalEquations;
    }

    public void setMainController(NewSheetMathController controller) {
        this.newSheetMathController = controller;
    }

    public List<EquationMTable> getEquationsMTable() {
        return equationsMTable.getItems();
    }
}
