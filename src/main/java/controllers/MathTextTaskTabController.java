package controllers;

import controllers.NewSheetMathController;
import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

/**
 * Created by Alebazi on 2017-01-28.
 */
public class MathTextTaskTabController {

    private NewSheetMathController newSheetMathController;

    @FXML
    private ToggleGroup ttChoice = new ToggleGroup();

    @FXML
    RadioButton ttFromDBRadioBtn, ttManualRadioBtn;

    @FXML
    TextField ttTxtField;

    public void init() {

    }
}
