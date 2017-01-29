package controllers;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.EnumSheetCategory;
import model.sheet.Sheet;
import utils.ViewUtils;

import java.io.IOException;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Alebazi on 2016-11-22.
 */
public class NewSheetFormController implements IController {

    private Sheet newSheet = new Sheet();

    private MainWindowController mainWindowController;

    @FXML ComboBox sheetCategory;
    @FXML TextField sheetName, titleTxtField;
    @FXML CheckBox ifDate, ifGrade, ifName;
    @FXML Button closeNewFormPart1Btn, makeNewFormPart1Btn;

    public void initialize() {
        sheetCategory.setItems(FXCollections.observableArrayList
                ("Język polski", "Matematyka", "Język angielski"));
        sheetCategory.setValue("Język polski");
    }

    @FXML protected void closeNewForm() {
        Stage stage = (Stage) closeNewFormPart1Btn.getScene().getWindow();
        if(ViewUtils.showClosingAlert("Anulowanie tworzenia", "Czy chcesz anulować tworzenia arkusza?")) {
            stage.close();
        }
    }

    @FXML protected void displayPart2Form() throws IOException, SQLException, ClassNotFoundException {
        switch (sheetCategory.getValue().toString()) {
            case ("Język polski"): {
                initSheet(EnumSheetCategory.POLISH);
                NewSheetPolishController newSheetPolishController = new NewSheetPolishController();
                openSheetFormX((Stage) makeNewFormPart1Btn.getScene().getWindow(), "/fxml/newSheetFormPolish.fxml",
                                    newSheetPolishController);
                break;
            }
            case ("Matematyka"): {
                initSheet(EnumSheetCategory.MATH);
                NewSheetMathController newSheetMathController = new NewSheetMathController();
                openSheetFormX((Stage) makeNewFormPart1Btn.getScene().getWindow(), "/fxml/newSheetFormMath.fxml",
                                    newSheetMathController);
                break;
            }
            case ("Język angielski"): {
                initSheet(EnumSheetCategory.ENGLISH);
                NewSheetEnglishController newSheetEnglishController = new NewSheetEnglishController();
                openSheetFormX((Stage) makeNewFormPart1Btn.getScene().getWindow(), "/fxml/newSheetFormEnglish.fxml",
                                    newSheetEnglishController);
                break;
            }
        }
    }

    public void openSheetFormX(Stage stage, String fxmlFile, IController controller) throws IOException, SQLException, ClassNotFoundException {
        if(!sheetName.getText().isEmpty() && isValidName(sheetName.getText())) {
            if(titleTxtField.getText().isEmpty()) {
                ViewUtils.showErrorAlert("Podaj tytuł arkusza");
            } else {
                FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
                Parent newFormPart2 = (Parent) loader.load();
                controller = loader.getController();
                controller.init(sheetName.getText(), newSheet, makeNewFormPart1Btn.getScene(), new String(), mainWindowController);
                Scene newForm2 = new Scene(newFormPart2);
                stage.hide();
                stage.setScene(newForm2);
                stage.show();
            }
        } else {
            ViewUtils.showErrorAlert("Błędna nazwa");
        }
    }

    protected void initSheet(EnumSheetCategory category) {
        if(sheetName != null) {
            newSheet.setSheetName(sheetName.getText());
        }
        if(titleTxtField != null) {
            newSheet.setTitle(titleTxtField.getText());
        }
        if(ifDate.isSelected()) {
            newSheet.setIfDate(true);
        } else {
            newSheet.setIfDate(false);
        }
        if(ifName.isSelected()) {
            newSheet.setIfName(true);
        } else {
            newSheet.setIfName(false);
        }
        if(ifGrade.isSelected()) {
            newSheet.setIfGrade(true);
        } else {
            newSheet.setIfGrade(false);
        }
        switch (category) {
            case POLISH:
                newSheet.setCategory("POLISH");
                break;
            case ENGLISH:
                newSheet.setCategory("ENGLISH");
                break;
            case MATH:
                newSheet.setCategory("MATH");
                break;
        }

    }

    public boolean isValidName(String text)
    {
        Pattern pattern = Pattern.compile(
                "# Match a valid Windows filename (unspecified file system).          \n" +
                        "^                                # Anchor to start of string.        \n" +
                        "(?!                              # Assert filename is not: CON, PRN, \n" +
                        "  (?:                            # AUX, NUL, COM1, COM2, COM3, COM4, \n" +
                        "    CON|PRN|AUX|NUL|             # COM5, COM6, COM7, COM8, COM9,     \n" +
                        "    COM[1-9]|LPT[1-9]            # LPT1, LPT2, LPT3, LPT4, LPT5,     \n" +
                        "  )                              # LPT6, LPT7, LPT8, and LPT9...     \n" +
                        "  (?:\\.[^.]*)?                  # followed by optional extension    \n" +
                        "  $                              # and end of string                 \n" +
                        ")                                # End negative lookahead assertion. \n" +
                        "[^<>:\"/\\\\|?*\\x00-\\x1F]*     # Zero or more valid filename chars.\n" +
                        "[^<>:\"/\\\\|?*\\x00-\\x1F\\ .]  # Last char is not a space or dot.  \n" +
                        "$                                # Anchor to end of string.            ",
                Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE | Pattern.COMMENTS);
        Matcher matcher = pattern.matcher(text);
        boolean isMatch = matcher.matches();
        return isMatch;
    }

    @Override
    public void init(String name, Sheet sheet, Scene scene, String sheetPath, MainWindowController controller) {
        mainWindowController = controller;
    }
}
