package controllers;

import com.sun.corba.se.impl.oa.toa.TOA;
import controllers.mathTabs.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.sheet.Sheet;
import utils.SheetCommonUtils;
import utils.ViewUtils;

import java.sql.SQLException;

/**
 * Created by Alebazi on 2017-01-28.
 */
public class NewSheetMathController implements IController {

    private Sheet newSheet = new Sheet();
    private Scene previousScene;
    private MainWindowController mainWindowController;

    @FXML private ToggleGroup range = new ToggleGroup();
    @FXML private MathGraphController mathGraphController;
    @FXML private MathGraphMController mathGraphMController;
    @FXML private MathEquationController mathEquationController;
    @FXML private MathEquationMController mathEquationMController;
    @FXML private MathTextTaskController mathTextTaskController;

    @FXML
    CheckBox graphsOpCheckBox, graphsOpMCheckBox, equOpCheckBox, equOpMCheckBox, textTaskCheckBox;

    @FXML
    RadioButton tenRadioBtn, twentyRadioBtn, fiftyRadioBtn, hundredRadioBtn;

    @FXML
    Label exerciseDescLbl, formName;

    @FXML
    ImageView exampleImView;

//    @FXML TabPane mathTabPane;

    @FXML Tab mainTab, graphsOpTab, graphsOpMTab, equOpTab, equOpMTab, textTaskTab;

    @FXML Button createSheetBtn, closeMathSheetBtn, graphsOpExBtn, graphsOpMExBtn, equOpExBtn, equOpMExBtn, textTaskExBtn;

    @FXML protected void goBackToPart1() {
        Stage stage = (Stage) closeMathSheetBtn.getScene().getWindow();
        if(ViewUtils.showClosingAlert("Anulowanie tworzenia", "Czy chcesz cofnąć drugi etap tworzenia arkusza?")) {
            stage.hide();
            stage.setScene(previousScene);
            stage.show();
        }
    }

    @FXML protected void createSheet() {

    }

    @FXML protected void showGraphsOpEx() {
        showExample("graphOp.png");
    }

    @FXML protected void showGraphsOpMEx() {
        showExample("graphOpM.png");
    }

    @FXML protected void showEquOpEx() {
        showExample("equOp.png");
    }

    @FXML protected void showEquOpMEx() {
        showExample("equOpM.png");
    }

    @FXML protected void showTextTaskEx() {
        showExample("textTask.png");
    }

    private void showExample(String imageName) {
        exerciseDescLbl.setVisible(true);
        exampleImView.setVisible(true);
//        Image exampleImage = new Image(getClass().getResourceAsStream(imageName));
//        exampleImView.setImage(exampleImage);
    }

    @Override
    public void init(String name, Sheet sheet, Scene scene, String sheetPath, MainWindowController controller) throws SQLException, ClassNotFoundException {
        mainWindowController = controller;
        formName.setText(name);
        newSheet = sheet;
        previousScene = scene;

        //checkbox listeners
        changeTabState(graphsOpCheckBox, graphsOpTab);
        changeTabState(graphsOpMCheckBox, graphsOpMTab);
        changeTabState(equOpCheckBox, equOpTab);
        changeTabState(equOpMCheckBox, equOpMTab);
        changeTabState(textTaskCheckBox, textTaskTab);
    }

    private void changeTabState(CheckBox checkBox, Tab tab) {
        checkBox.selectedProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue) {
                tab.setDisable(false);
            } else {
                tab.setDisable(true);
            }
        });
    }
}
