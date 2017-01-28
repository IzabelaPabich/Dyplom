package controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.DAO.LettersDAO;
import model.DAO.PolishWordsDAO;
import model.sheet.*;
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
 * Created by Alebazi on 2016-12-15.
 */
public class NewSheetPolishController implements IController {

    private Sheet newSheet = new Sheet();
    final ToggleGroup groupTop = new ToggleGroup();
    final ToggleGroup groupMid = new ToggleGroup();
    private ObservableList possibleLetters;
    private ObservableList selectedLetters = FXCollections.observableArrayList();
    private ObservableList addedWords = FXCollections.observableArrayList();
    private File directoryToSave, dictationFile;
    private Scene previousScene;
    private MainWindowController mainWindowController;

    @FXML
    Label namePolish;
    @FXML
    Button createPolishSheetBtn, closePolishSheetBtn;
    @FXML
    RadioButton dictationRadioBtn, wordsRadioBtn;
    @FXML
    Label loadFileLbl, howHaveWordsLbl, howManyLbl;
    @FXML
    TextField filePathTxtField, wordsAmountTxtField;
    @FXML
    Button loadFileBtn, saveTextBtn;
    @FXML
    RadioButton takeFromDBRadioBtn, writeBySelfRadioBtn;
    @FXML
    Label selectLettersLbl, allLettersLbl, selectedLettersLbl;
    @FXML
    ListView possibleLettersList, selectedLettersList;
    @FXML
    Button moveToSelectedBtn, removeFromSelectedBtn;
    @FXML
    Label manageWordsLbl;
    @FXML
    ListView wordsList;
    @FXML
    Button addWordsBtn, randomWordsBtn;
    @FXML
    Label newWordLbl, selectedWordLbl, wordsLbl, howManyColumnsLbl, fileTextLbl;
    @FXML
    TextField newWordTxtField, selectedWordTxtField;
    @FXML
    Button addWordBtn, deleteWordBtn;
    @FXML ChoiceBox columnNumberChBox;
    @FXML TextArea fileTextArea;
    @FXML CheckBox textNotFromFileCheckBox;

    public NewSheetPolishController() throws SQLException, ClassNotFoundException {
    }

    public void initialize() {
        System.out.println("Controller Polish");
    }


    @FXML
    protected void goBackToPart1(ActionEvent event) throws IOException {
        Stage stage = (Stage) closePolishSheetBtn.getScene().getWindow();
        if(ViewUtils.showClosingAlert("Anulowanie tworzenia", "Czy chcesz cofnąć drugi etap tworzenia arkusza?")) {
            stage.hide();
            stage.setScene(previousScene);
            stage.show();
        }
    }

    @FXML
    protected void createSheetPolish() throws JAXBException, IOException, SAXException, TransformerException, ConfigurationException {
        SheetCommonUtils.setSheetCommonData(newSheet);

        if(!selectedLetters.isEmpty()) {
            Letters letters = new Letters();
            letters.setLetter(selectedLetters);
            newSheet.setLetters(letters);
        } else {
            ViewUtils.showErrorAlert("Lista wybranych liter jest pusta");
            return;
        }

        if (wordsRadioBtn.isSelected()) {
//            polishWords.setColumns(columnNumberChBox.getSelectionModel().getSelectedItem().toString());
            if(wordsList.getItems().isEmpty()) {
                ViewUtils.showErrorAlert("Nie ma żadnych słów!");
                return;
            } else {
                newSheet.setPolishWords(SheetCommonUtils.replaceLetterWithDotsInList(wordsList.getItems(), selectedLettersList.getItems()));

                if (newSheet.getDictation() != null) {
                    newSheet.setDictation(null);
                }
            }
        } else if (dictationRadioBtn.isSelected()) {
            Dictation dictation = new Dictation();
            dictation.setLetters(selectedLetters);
            if(fileTextArea.getText().isEmpty()) {
                ViewUtils.showErrorAlert("Żaden plik z dyktandem nie został wczytany");
            } else {
                dictation.setFilepath(dictationFile.getPath());
                if (!selectedLetters.isEmpty()) {
                    dictation.setText(SheetCommonUtils.eraseLettersFromText(selectedLetters, fileTextArea.getText()));
                }
                newSheet.setDictation(dictation);
                if (newSheet.getPolishWords() != null) {
                    newSheet.setPolishWords(null);
                }
            }
        }
        buildAddInfo();

        SheetCommonUtils.replaceAllPolishCharacters(newSheet);

        directoryToSave = FileUtils.chooseDirectorForPDFFile((Stage) createPolishSheetBtn.getScene().getWindow());
        FileUtils.createSheet(newSheet, directoryToSave);
        ViewUtils.showInfoAlert("Utworzono plik: " + newSheet.getSheetName() + ".pdf w folderze: " + directoryToSave.toString());
        SheetCommonUtils.setNewSheetOnMainWindow(mainWindowController, directoryToSave, newSheet.getSheetName());
        ((Stage) createPolishSheetBtn.getScene().getWindow()).close();
    }

    @FXML
    protected void showOptions() {
        if (dictationRadioBtn.isSelected()) {
            loadFileLbl.setVisible(true);
            filePathTxtField.setVisible(true);
            loadFileBtn.setVisible(true);
            takeFromDBRadioBtn.setVisible(false);
            writeBySelfRadioBtn.setVisible(false);
            howHaveWordsLbl.setVisible(false);
            manageWordsLbl.setVisible(false);
            addWordsBtn.setVisible(false);
            randomWordsBtn.setVisible(false);
            howManyLbl.setVisible(false);
            wordsAmountTxtField.setVisible(false);
            wordsList.setVisible(false);
            wordsLbl.setVisible(false);
//            howManyColumnsLbl.setVisible(false);
//            columnNumberChBox.setVisible(false);
            newWordLbl.setVisible(false);
            newWordTxtField.setVisible(false);
            addWordBtn.setVisible(false);
            selectedWordLbl.setVisible(false);
            selectedWordTxtField.setVisible(false);
            deleteWordBtn.setVisible(false);
            textNotFromFileCheckBox.setDisable(false);
        } else {
            loadFileLbl.setVisible(false);
            filePathTxtField.setVisible(false);
            loadFileBtn.setVisible(false);
            takeFromDBRadioBtn.setVisible(true);
            writeBySelfRadioBtn.setVisible(true);
            howHaveWordsLbl.setVisible(true);
            wordsList.setVisible(true);
            wordsLbl.setVisible(true);
//            howManyColumnsLbl.setVisible(true);
//            columnNumberChBox.setVisible(true);
            fileTextLbl.setVisible(false);
            fileTextArea.setVisible(false);
            textNotFromFileCheckBox.setDisable(true);
        }
    }

    @FXML
    protected void showWordsTable() {
        if (wordsRadioBtn.isSelected() && writeBySelfRadioBtn.isSelected()) {
            manageWordsLbl.setVisible(true);
            addWordsBtn.setVisible(true);
            randomWordsBtn.setVisible(false);
            howManyLbl.setVisible(false);
            wordsAmountTxtField.setVisible(false);
        } else {
            manageWordsLbl.setVisible(false);
            addWordsBtn.setVisible(false);
            randomWordsBtn.setVisible(true);
            howManyLbl.setVisible(true);
            wordsAmountTxtField.setVisible(true);
        }
    }

    @FXML
    protected void loadFile() {
        dictationFile = FileUtils.readFile((Stage) loadFileBtn.getScene().getWindow(), "TXT files (*.txt)", "*.txt");
        if(dictationFile.canRead()) {
            filePathTxtField.setText(dictationFile.getPath());
            fileTextLbl.setVisible(true);
            fileTextArea.setVisible(true);
            fileTextArea.setText(SheetCommonUtils.setFileToTextArea(dictationFile));
            ViewUtils.showInfoAlert("Wczytano tekst dyktanda");
        }
    }

    @FXML
    protected void moveToSelected(ActionEvent event) {
        selectedLetters.add(possibleLettersList.getSelectionModel().getSelectedItem());
        possibleLetters.remove(possibleLettersList.getSelectionModel().getSelectedItem());
        removeFromSelectedBtn.setDisable(false);
        addWordsBtn.setDisable(false);
        randomWordsBtn.setDisable(false);
    }

    @FXML
    protected void removeFromSelected(ActionEvent event) {
        possibleLetters.add(selectedLettersList.getSelectionModel().getSelectedItem());
        selectedLetters.remove(selectedLettersList.getSelectionModel().getSelectedItem());
        if (selectedLetters.isEmpty()) {
            removeFromSelectedBtn.setDisable(true);
            addWordsBtn.setDisable(true);
            randomWordsBtn.setDisable(true);
        }
    }

    @FXML
    protected void addWords(ActionEvent event) throws SQLException, ClassNotFoundException, IOException {
        initWordsManagerLayout();
    }

    @FXML
    protected void randomWords(ActionEvent event) throws SQLException, ClassNotFoundException {
        if(wordsAmountTxtField.getText().isEmpty()) {
            ViewUtils.showErrorAlert("Wprowadź liczbę słów");
        } else {
            initWordsManagerLayout();
            wordsList.setItems(FXCollections.observableArrayList
                    (PolishWordsDAO.searchPolishWordsWithAmount(Integer.parseInt(wordsAmountTxtField.getText()), selectedLetters)));
        }
    }

    @FXML
    protected void addWord() throws SQLException, ClassNotFoundException {
        SheetCommonUtils.addWordToListFromTxtField(wordsList, selectedLettersList, newWordTxtField);
        deleteWordBtn.setDisable(false);
    }

    @FXML
    protected void deleteWord() {
        String temp = (String) wordsList.getSelectionModel().getSelectedItem();
        wordsList.getItems().remove(temp);
        ViewUtils.showInfoAlert("Usunięto z listy słowo " + temp);
        if (wordsList.getItems().isEmpty()) {
            deleteWordBtn.setDisable(true);
        }
    }

    @FXML protected void saveTextToFile() {
        if(!fileTextArea.getText().isEmpty()) {
            FileUtils.saveTxtFile((Stage) saveTextBtn.getScene().getWindow(), fileTextArea.getText());
        } else {
            ViewUtils.showErrorAlert("Treść dyktanda jest pusta. Nie można zapisać!");
        }
    }

    private void initWordsManagerLayout() {
        newWordLbl.setVisible(true);
        newWordTxtField.setVisible(true);
        newWordTxtField.setDisable(false);
        addWordBtn.setVisible(true);
        selectedWordLbl.setVisible(true);
        selectedWordTxtField.setVisible(true);
        deleteWordBtn.setVisible(true);
        wordsList.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (wordsList.getSelectionModel().getSelectedItem() != null) {
                selectedWordTxtField.setText(wordsList.getSelectionModel().getSelectedItem().toString());
            }
        });
    }

    private void buildAddInfo() {
        String addInfo = "Uzupełnij brakujące ";
        for(int i = 0; i < selectedLettersList.getItems().size(); i++) {
            if(i == selectedLettersList.getItems().size() - 1) {
                addInfo = addInfo + selectedLettersList.getItems().get(i);
            } else {
                addInfo = addInfo + selectedLettersList.getItems().get(i) + " / ";
            }
        }
        addInfo = addInfo + " w słowach";
        newSheet.setAddInfo(addInfo);
    }

    @Override
    public void init(String name, Sheet sheet, Scene scene, String sheetPath, MainWindowController controller) throws SQLException, ClassNotFoundException {
        mainWindowController = controller;
        namePolish.setText(name);
        possibleLetters = LettersDAO.searchLetters();
        possibleLettersList.setItems(possibleLetters);
        selectedLettersList.setItems(selectedLetters);
        wordsList.setItems(addedWords);
        this.newSheet = sheet;
        this.previousScene = scene;
        columnNumberChBox.setItems(FXCollections.observableArrayList("1", "2", "3"));
        textNotFromFileCheckBox.selectedProperty().addListener((observable, oldValue, newValue) -> {
                if (newValue) {
                    fileTextLbl.setVisible(true);
                    fileTextArea.setVisible(true);
                    fileTextArea.setEditable(true);
                    saveTextBtn.setVisible(true);
                    loadFileBtn.setDisable(true);
                } else {
                    fileTextLbl.setVisible(true);
                    fileTextArea.setVisible(false);
                    fileTextArea.setEditable(false);
                    saveTextBtn.setVisible(false);
                    loadFileBtn.setDisable(false);
                }
        });
    }

}
