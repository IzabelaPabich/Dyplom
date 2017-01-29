package controllers.mathTabs;

import controllers.NewSheetMathController;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;

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

    @FXML protected void showTable() {
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

    private void getDataFromDB() {
    }

    public void init() {
        equationsTable.setPlaceholder(new Label("Brak elementów"));
    }

    public void setMainController(NewSheetMathController controller) {
        this.newSheetMathController = controller;
    }
}
