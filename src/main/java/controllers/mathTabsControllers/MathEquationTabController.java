package controllers.mathTabsControllers;

import controllers.NewSheetMathController;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.CheckBoxListCell;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.DAO.EquationsDAO;
import model.mathTables.EquationTable;
import model.sheet.Equation;
import utils.SheetCommonUtils;
import utils.ViewUtils;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alebazi on 2017-01-28.
 */
public class MathEquationTabController {

    private NewSheetMathController newSheetMathController;
    List<EquationTable> tableEquations = new ArrayList<>();

    @FXML
    private ToggleGroup eChoice = new ToggleGroup();

    @FXML
    RadioButton eFromDBRadioBtn, eManualRadioBtn;

    @FXML
    TextField eTxtField, firstCompTxtField, operationTxtField, secondCompTxtField, resultTxtField, equalTxtField;

    @FXML
    Button showTableBtn, addEquationBtn;

    @FXML
    TableView equationsTable;

    @FXML
    TableColumn firstCol, firstCheckCol, ICol, ICheckCol, secondCol, secondCheckCol, IICol, IICheckCol, thirdCol, thirdCheckCol;

    @FXML
    ImageView equLegendImage;

    @FXML
    protected void showTable() throws SQLException, ClassNotFoundException {
        equationsTable.setVisible(true);
        if (eFromDBRadioBtn.isSelected()) {
            getDataFromDB();
            initManualTextFields(true);
        }
        if (eManualRadioBtn.isSelected()) {
            initManualTextFields(false);
        }
    }

    private void initManualTextFields(boolean b) {
        firstCompTxtField.setDisable(b);
        operationTxtField.setDisable(b);
        secondCompTxtField.setDisable(b);
        equalTxtField.setDisable(b);
        resultTxtField.setDisable(b);
        addEquationBtn.setDisable(b);
    }

    @FXML
    protected void enableShowTableBtn() {
        if (((!eTxtField.getText().isEmpty() && eTxtField.getText().compareTo("0") != 0)
                && eFromDBRadioBtn.isSelected()) || eManualRadioBtn.isSelected()) {
            showTableBtn.setDisable(false);
        } else {
            showTableBtn.setDisable(true);
        }
    }

    @FXML
    protected void addEquation() {
        if (txtFieldsAreEmpty()) {
            ViewUtils.showErrorAlert("Uzupełnij wszystkie komponenty równiania!");
        } else {
            EquationTable tempEquation;
            tempEquation = new EquationTable(firstCompTxtField.getText(), operationTxtField.getText(),
                    secondCompTxtField.getText(), equalTxtField.getText(), resultTxtField.getText(),
                    false, false, false, false, false);
            tableEquations.add(tempEquation);
            equationsTable.setItems(FXCollections.observableArrayList(tableEquations));
        }
    }

    private boolean txtFieldsAreEmpty() {
        return (firstCompTxtField.getText().isEmpty() || secondCompTxtField.getText().isEmpty() ||
                operationTxtField.getText().isEmpty() || equalTxtField.getText().isEmpty() ||
                resultTxtField.getText().isEmpty());
    }

    private void getDataFromDB() throws SQLException, ClassNotFoundException {
        String range = newSheetMathController.getRangeString();
        List<Equation> equations = EquationsDAO.searchEquationsWithAmount(Integer.parseInt(eTxtField.getText()), range);
        if (equations.size() != 0) {
            EquationTable tempEquation;
            for (int i = 0; i < equations.size(); i++) {
                tempEquation = new EquationTable(equations.get(i).getFirstComp(), equations.get(i).getOperation(),
                        equations.get(i).getSecondComp(), equations.get(i).getEquationMark(), equations.get(i).getResult(),
                        false, false, false, false, false);
                tableEquations.add(tempEquation);
            }
            equationsTable.setItems(FXCollections.observableArrayList(tableEquations));
        }
    }

    public void setMainController(NewSheetMathController controller) {
        this.newSheetMathController = controller;
    }

    public List<Equation> getFinalEquations() {
        List<Equation> finalEquations = new ArrayList<>();

        for (int i = 0; i < equationsTable.getItems().size(); i++) {
            finalEquations.add(SheetCommonUtils.eraseFieldsFromEquation((EquationTable) equationsTable.getItems().get(i)));
        }

        return finalEquations;
    }

    public List<EquationTable> getEquationsTable() {
        return equationsTable.getItems();
    }

    public void init() {
        equationsTable.setPlaceholder(new Label("Brak elementów"));
        equLegendImage.setImage(new Image("/mathExamples/equationLegend.png"));

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
}
