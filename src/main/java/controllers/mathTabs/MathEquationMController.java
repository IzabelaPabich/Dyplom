package controllers.mathTabs;

import controllers.NewSheetMathController;
import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

/**
 * Created by Alebazi on 2017-01-28.
 */
public class MathEquationMController {

    private NewSheetMathController newSheetMathController;

    @FXML
    private ToggleGroup emChoice = new ToggleGroup();

    @FXML
    RadioButton emFromDBRadioBtn, emManualRadioBtn;

    @FXML
    TextField emTxtField;

    public void init() {

    }
}
