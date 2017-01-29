package controllers.mathTabs;

import controllers.NewSheetMathController;
import javafx.fxml.FXML;
import javafx.scene.control.*;

/**
 * Created by Alebazi on 2017-01-28.
 */
public class MathEquationController {

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
        if(eFromDBRadioBtn.isSelected()) {
            getDataFromDB();
        }
    }

    private void getDataFromDB() {
    }

    public void init() {
        equationsTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if(equationsTable.getSelectionModel().getSelectedItems().isEmpty()) {
                equationsTable.setPlaceholder(new Label("Brak elementów"));
            }
        });
    }
}
