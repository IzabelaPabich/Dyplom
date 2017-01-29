package controllers;

import controllers.mathTabs.*;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import model.sheet.Sheet;
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

    @FXML private MathGraphTabController mathGraphTabController;
    @FXML private MathGraphMTabController mathGraphMTabController;
    @FXML private MathEquationTabController mathEquationTabController;
    @FXML private MathEquationMTabController mathEquationMTabController;
    @FXML private MathTextTaskTabController mathTextTaskTabController;

    @FXML
    CheckBox graphsOpCheckBox, graphsOpMCheckBox, equOpCheckBox, equOpMCheckBox, textTaskCheckBox;

    @FXML
    RadioButton tenRadioBtn, twentyRadioBtn, fiftyRadioBtn, hundredRadioBtn;

    @FXML
    Label exerciseDescLbl, formName;

    @FXML
    ImageView exampleImView;

    @FXML Tab mainTab, graphsOpTab, graphsOpMTab, equOpTab, equOpMTab, textTaskTab;

    @FXML Button createSheetBtn, closeMathSheetBtn, graphsOpExBtn, graphsOpMExBtn, equOpExBtn, equOpMExBtn, textTaskExBtn;

    @FXML private void initialize() {
        mathEquationTabController.setMainController(this);
    }

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
        mathGraphTabController.init();
        mathGraphMTabController.init();
        mathEquationTabController.init();
        mathEquationMTabController.init();
        mathTextTaskTabController.init();

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
