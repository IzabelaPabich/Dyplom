package controllers;

import controllers.mathTabs.*;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import model.sheet.Equation;
import model.sheet.Sheet;
import org.apache.avalon.framework.configuration.ConfigurationException;
import org.xml.sax.SAXException;
import utils.FileUtils;
import utils.SheetCommonUtils;
import utils.ViewUtils;

import javax.xml.bind.JAXBException;
import javax.xml.transform.TransformerException;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Alebazi on 2017-01-28.
 */
public class NewSheetMathController implements IController {

    private Sheet newSheet = new Sheet();
    private Scene previousScene;
    private MainWindowController mainWindowController;
    private File directoryToSave;

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

    @FXML Button createMathSheetBtn, closeMathSheetBtn, graphsOpExBtn, graphsOpMExBtn, equOpExBtn, equOpMExBtn, textTaskExBtn;

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

    @FXML protected void createSheet() throws IOException, TransformerException, SAXException, ConfigurationException, JAXBException {
        if(graphsOpCheckBox.isSelected()) {

        }
        if(graphsOpMCheckBox.isSelected()) {

        }
        if(equOpCheckBox.isSelected()) {
            if(mathEquationTabController.getEquationsTable().size() != 0) {
                List<Equation> finalEquations = mathEquationTabController.getFinalEquations();
            } else {
                ViewUtils.showErrorAlert("Checkbox 'Równania' zaznaczony. Lista równań pusta.");
                return;
            }
        }
        if(equOpMCheckBox.isSelected()) {

        }
        if(textTaskCheckBox.isSelected()) {

        }
        List<Equation> finalEquations = mathEquationTabController.getFinalEquations();

        newSheet.setAddInfo("Rozwiaz zadania"); //Rozwiąż zadania

        directoryToSave = FileUtils.chooseDirectorForPDFFile((Stage) createMathSheetBtn.getScene().getWindow());
        FileUtils.createSheet(newSheet, directoryToSave);
        ViewUtils.showInfoAlert("Utworzono plik: " + newSheet.getSheetName() + ".pdf w folderze:  " + directoryToSave.toString());
        SheetCommonUtils.setNewSheetOnMainWindow(mainWindowController, directoryToSave, newSheet.getSheetName());
        ((Stage) createMathSheetBtn.getScene().getWindow()).close();
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
