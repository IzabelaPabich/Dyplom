package controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.DAO.CategoriesDAO;
import model.DAO.EnglishWordsDao;
import model.sheet.EngCategories;
import model.sheet.EngpolWord;
import model.sheet.EngpolWords;
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

/**
 * Created by Alebazi on 2017-01-02.
 */
public class NewSheetEnglishController implements IController {

    private Sheet newSheet = new Sheet();
    private Scene previousScene;
    final ToggleGroup groupChoice = new ToggleGroup();
    final ToggleGroup groupTranslate = new ToggleGroup();
    private ObservableList possibleCategories = CategoriesDAO.searchCategories();
    private ObservableList selectedCategories = FXCollections.observableArrayList();
    private ObservableList<EngpolWord> wordsList = FXCollections.observableArrayList();
    private File directoryToSave;
    private MainWindowController mainWindowController;

    @FXML
    Label formName, howManyEPLbl, howManyPELbl, categoriesLbl, allCategoriesLbl, selectedCategoriesLbl,
            selectedWordsLbl, addWordLbl, polWordLbl, engWordLbl, catWordLbl;
    @FXML
    CheckBox polToEngCheckBox, engToPolCheckBox;
    @FXML
    TextField howManyEPTxtField, howManyPETxtField, newPolWordTxtField, newEngWordTxtField;
    @FXML
    ListView possibleCategoriesList, selectedCategoriesList;
    @FXML
    Button moveToSelectedBtn, removeFromSelectedBtn, addWordsBtn, randomWordsBtn, addWordBtn, deleteWordBtn,
            createEnglishSheetBtn, closeEnglishSheetBtn;
    @FXML
    RadioButton takeFromDBRadioBtn, writeByYourselfRadioBtn, polEngRadioBtn, engPolRadioBtn;
    @FXML
    TableView selectedWordsTable;
    @FXML
    TableColumn polWordCol, engWordCol, catWordCol, engPolFlagCol, polEngFlagCol;
    @FXML
    ComboBox catComboBox;

    public NewSheetEnglishController() throws SQLException, ClassNotFoundException {
    }

    @FXML protected  void moveToSelected(ActionEvent e) {
        selectedCategories.add(possibleCategoriesList.getSelectionModel().getSelectedItem());
        possibleCategories.remove(possibleCategoriesList.getSelectionModel().getSelectedItem());
        removeFromSelectedBtn.setDisable(false);
    }

    @FXML protected void removeFromSelected(ActionEvent e) {
        possibleCategories.add(selectedCategoriesList.getSelectionModel().getSelectedItem());
        selectedCategories.remove(selectedCategoriesList.getSelectionModel().getSelectedItem());
        if(selectedCategories.isEmpty()) {
            removeFromSelectedBtn.setDisable(true);
        }
    }

    @FXML protected void addWords(ActionEvent e) {
        initWordsManager();
    }

    @FXML protected void randomWords(ActionEvent e) throws SQLException, ClassNotFoundException {
        if(selectedCategories.isEmpty()) {
            ViewUtils.showErrorAlert("Lista wybranych kategorii jest pusta");
        } else if(howManyPETxtField.getText().isEmpty()) {
            ViewUtils.showErrorAlert("Wprowadź liczbę słów");
        } else {
            initWordsManager();
            selectedWordsTable.setItems(FXCollections.observableArrayList(
                    EnglishWordsDao.searchEnglishWordsWithAmount(Integer.parseInt(howManyEPTxtField.getText()),
                            Integer.parseInt(howManyPETxtField.getText()), selectedCategories)));
        }
    }

    @FXML protected void addWord(ActionEvent e) throws SQLException, ClassNotFoundException {
        if(!catComboBox.getSelectionModel().isEmpty()) {
            SheetCommonUtils.addWordFromTxtFieldsToTable(catComboBox, newPolWordTxtField,newEngWordTxtField,
                                                            polEngRadioBtn, engPolRadioBtn, selectedWordsTable);
        }
    }

    @FXML protected void deleteWord(ActionEvent e) {
        selectedWordsTable.getItems().remove(selectedWordsTable.getSelectionModel().getSelectedItem());
        if (selectedWordsTable.getItems().isEmpty()) {
            deleteWordBtn.setDisable(true);
        }
    }

    @FXML protected void goBackToPart1(ActionEvent e) {
        Stage stage = (Stage) closeEnglishSheetBtn.getScene().getWindow();
        if(ViewUtils.showClosingAlert("Anulowanie tworzenia", "Czy chcesz cofnąć drugi etap tworzenia arkusza?")) {
            stage.hide();
            stage.setScene(previousScene);
            stage.show();
        }
    }

    @FXML protected void createSheetEnglish(ActionEvent e) throws IOException, TransformerException, SAXException, ConfigurationException, JAXBException {
        SheetCommonUtils.setSheetCommonData(newSheet);

        if(!selectedCategories.isEmpty()) {
            EngCategories categories = new EngCategories();
            categories.setCategories(selectedCategories);
            newSheet.setEngCategories(categories);
        } else {
            ViewUtils.showErrorAlert("Lista wybranych kategorii jest pusta");
            return;
        }

        if(selectedWordsTable.getItems().isEmpty()) {
            ViewUtils.showErrorAlert("Lista słów jest pusta");
            return;
        } else {
            newSheet.setEngpolWords(SheetCommonUtils.replaceWordsWithDotsInList(selectedWordsTable.getItems()));
        }
        newSheet.setAddInfo("Przetlumacz!"); //Przetłumacz

        SheetCommonUtils.replaceAllPolishCharacters(newSheet);

        directoryToSave = FileUtils.chooseDirectorForPDFFile((Stage) createEnglishSheetBtn.getScene().getWindow());
        if(directoryToSave != null) {
            SheetCommonUtils.saveSheetInDirectory(newSheet, directoryToSave, mainWindowController, createEnglishSheetBtn);
        }
    }

    @FXML protected void showOptions(ActionEvent e) {
        if(!selectedCategories.isEmpty()) {
            if (takeFromDBRadioBtn.isSelected()) {
                randomWordsBtn.setVisible(true);
                addWordsBtn.setVisible(false);
            } else if (writeByYourselfRadioBtn.isSelected()) {
                randomWordsBtn.setVisible(false);
                addWordsBtn.setVisible(true);
            }
        }
    }

    @FXML protected void unableHowManyPE(ActionEvent e) {
        changeDisableValueTextField(polToEngCheckBox, howManyPETxtField);
    }

    @FXML protected void unableHowManyEP(ActionEvent e) {
        changeDisableValueTextField(engToPolCheckBox, howManyEPTxtField);
    }

    private void changeDisableValueTextField(CheckBox checkBox, TextField textField) {
        if(checkBox.isSelected()) {
            textField.setDisable(false);
        }
        else {
            textField.setDisable(true);
        }
    }

    private void initWordsManager() {
        selectedWordsTable.getItems().removeAll(wordsList);
        selectedWordsLbl.setVisible(true);
        selectedWordsTable.setVisible(true);
        addWordLbl.setVisible(true);
        polWordLbl.setVisible(true);
        newPolWordTxtField.setVisible(true);
        engWordLbl.setVisible(true);
        newEngWordTxtField.setVisible(true);
        catWordLbl.setVisible(true);
        catComboBox.setVisible(true);
        catComboBox.setItems(selectedCategories);
        addWordBtn.setVisible(true);
        deleteWordBtn.setVisible(true);
        selectedWordsTable.setPlaceholder(new Label("Brak elementów"));
        selectedWordsTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if(selectedWordsTable.getSelectionModel().getSelectedItems().isEmpty()) {
                deleteWordBtn.setDisable(true);
            }
            else {
                deleteWordBtn.setDisable(false);
            }
        });
        engPolRadioBtn.setVisible(true);
        polEngRadioBtn.setVisible(true);
    }

    @Override
    public void init(String name, Sheet sheet, Scene scene, String sheetPath, MainWindowController controller) {
        mainWindowController = controller;
        formName.setText(name);
        possibleCategoriesList.setItems(possibleCategories);
        selectedCategoriesList.setItems(selectedCategories);
        this.newSheet = sheet;
        this.previousScene = scene;

        //table
        polWordCol.setCellValueFactory(new PropertyValueFactory<EngpolWord, String>("polWord"));
        engWordCol.setCellValueFactory(new PropertyValueFactory<EngpolWord, String>("engWord"));
        catWordCol.setCellValueFactory(new PropertyValueFactory<EngpolWord, String>("engCategory"));
        catWordCol.setStyle("-fx-alignment: CENTER;");
        polEngFlagCol.setCellValueFactory(new PropertyValueFactory<EngpolWord, String>("ifToEnglish"));
        polEngFlagCol.setStyle("-fx-alignment: CENTER;");
        engPolFlagCol.setCellValueFactory(new PropertyValueFactory<EngpolWord, String>("ifToPolish"));
        engPolFlagCol.setStyle("-fx-alignment: CENTER;");

    }
}
