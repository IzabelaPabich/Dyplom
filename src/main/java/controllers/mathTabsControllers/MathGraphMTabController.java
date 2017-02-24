package controllers.mathTabsControllers;

import controllers.NewSheetMathController;
import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

/**
 * Created by Alebazi on 2017-01-28.
 */
public class MathGraphMTabController {

    private NewSheetMathController newSheetMathController;

    @FXML
    private ToggleGroup gmChoice = new ToggleGroup();

    @FXML
    RadioButton gmFromDBRadioBtn, gmManualRadioBtn;

    @FXML
    TextField gmTxtField;

    public void init() {

    }
}
