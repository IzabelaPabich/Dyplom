package controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.DAO.EnglishWordsDAO;
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
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alebazi on 2017-01-14.
 */
public class EditSheetWindowController implements IController {

    private ToggleGroup group = new ToggleGroup();
    private File newDictationFile;
    private Sheet currSheet = new Sheet();
    private String currSheetPath;
    private MainWindowController mainWindowController;

    @FXML
    private TextField sheetNameTxtField, titleSheetTxtField, selectedTxtField, addWordTxtField, deleteWordTxtField,
                      currFileTxField, polWordTxtField, engWordTxtField;

    @FXML private CheckBox ifNameCheckbox, ifDateCheckbox, ifGradeCheckbox;

    @FXML private Label lettersLbl, categoriesLbl, selectedLbl, wordsLbl, withWordsLbl, addWordLbl, deleteWordLbl,
                        dictationLbl, currFileLbl, addEngWordLbl, polLbl, engLbl, catLbl;

    @FXML private ListView thingsList, wordsList;

    @FXML private Button deleteBtn, addWordBtn, deleteWordBtn, saveBtn, cancelBtn, loadFileBtn, addEngBtn, deleteEngBtn;

    @FXML private TextArea dictationTxtArea;

    @FXML private TableView wordsTable;

    @FXML private TableColumn polWordCol, engWordCol, catWordCol, engPolFlagCol, polEngFlagCol;

    @FXML private ComboBox catCombobox;

    @FXML private RadioButton polEngRadioBtn, engPolRadioBtn;

    public EditSheetWindowController() {
    }

    @FXML protected void addWord(ActionEvent e) throws SQLException, ClassNotFoundException {
        SheetCommonUtils.addWordToListFromTxtField(wordsList, thingsList,addWordTxtField);
    }

    @FXML protected void deleteWord(ActionEvent e) {
        wordsList.getItems().remove(wordsList.getSelectionModel().getSelectedItem());
        if (wordsList.getItems().isEmpty()) {
            deleteWordBtn.setDisable(true);
        }
    }

    @FXML protected void save(ActionEvent e) throws IOException, TransformerException, SAXException, ConfigurationException, JAXBException {
        if((ifDateCheckbox.isSelected() && !currSheet.isIfDate()) || (!ifDateCheckbox.isSelected() && currSheet.isIfDate())) {
            currSheet.setIfDate(!currSheet.isIfDate());
        }
        if((ifNameCheckbox.isSelected() && !currSheet.isIfName()) || (!ifNameCheckbox.isSelected() && currSheet.isIfName())) {
            currSheet.setIfName(!currSheet.isIfName());
        }
        if((ifGradeCheckbox.isSelected() && !currSheet.isIfGrade()) || (!ifGradeCheckbox.isSelected() && currSheet.isIfGrade())) {
            currSheet.setIfGrade(!currSheet.isIfGrade());
        }
        SheetCommonUtils.setSheetCommonData(currSheet);

        if(currSheet.getSheetName().compareTo(sheetNameTxtField.getText()) != 0) {
            currSheet.setSheetName(sheetNameTxtField.getText());
        }
        if(currSheet.getTitle().compareTo(titleSheetTxtField.getText()) != 0) {
            currSheet.setTitle(titleSheetTxtField.getText());
        }
        switch (currSheet.getCategory()) {
            case "POLISH":
                if(currSheet.getDictation() == null) {
                    setChangedPolishWords();
                } else {
                    setChangedDictation();
                }
                break;
            case "ENGLISH":
                    setChangedEnglishWords();
                break;
            case "MATH":

                break;
        }

        File sheetDirectory = new File(currSheetPath);
        FileUtils.createSheet(currSheet, sheetDirectory);
        ViewUtils.showInfoAlert("Zapisano zmiany w pliku: " + currSheet.getSheetName() + ".pdf w folderze:  " + sheetDirectory.toString());
        ((Stage) saveBtn.getScene().getWindow()).close();
        mainWindowController.setSheetToOpen(new File(new String(currSheetPath + File.separator + currSheet.getSheetName() +".pdf")));
        mainWindowController.setOpenNewSheetFlag(true);
        mainWindowController.openExistingSheet();
    }

    @FXML protected void cancel(ActionEvent e) {
        Stage stage = (Stage) cancelBtn.getScene().getWindow();
        if(ViewUtils.showClosingAlert("Anulowanie edycji", "Czy na pewno chcesz zakończyć edycję?")) {
            stage.close();
        }
    }

    @FXML protected void loadFile(ActionEvent e) {
        newDictationFile = FileUtils.readFile((Stage) loadFileBtn.getScene().getWindow(), "TXT files (*.txt)", "*.txt");
        currFileTxField.setText(newDictationFile.getPath());
        dictationTxtArea.setText(SheetCommonUtils.setFileToTextArea(newDictationFile));
    }



    @FXML protected void addEng(ActionEvent e) throws SQLException, ClassNotFoundException {
        SheetCommonUtils.addWordFromTxtFieldsToTable(catCombobox, engWordTxtField, polWordTxtField, polEngRadioBtn,
                                                        engPolRadioBtn, wordsTable);
    }

    @FXML protected void deleteEng(ActionEvent e) {
        wordsTable.getItems().remove(wordsTable.getSelectionModel().getSelectedItem());
        if (wordsTable.getItems().isEmpty()) {
            deleteEngBtn.setDisable(true);
        }
    }

    @FXML protected void deleteWithWords(ActionEvent e) {
        switch(currSheet.getCategory()) {
            case "ENGLISH":
                EngpolWord engpolWord;
                List<EngpolWord> toRemoveEng = new ArrayList<>();
                for(int i = 0; i < wordsTable.getItems().size(); i++) {
                    engpolWord = (EngpolWord) wordsTable.getItems().get(i);
                    if(engpolWord.getEngCategory().trim().compareTo(selectedTxtField.getText().trim()) == 0) {
                        toRemoveEng.add((EngpolWord) wordsTable.getItems().get(i));
                    }
                }
                wordsTable.getItems().removeAll(toRemoveEng);
                break;
            case "POLISH":
                List<String> toRemovePol = new ArrayList<>();
                for(int i = 0; i < wordsList.getItems().size(); i++) {
                    if(SheetCommonUtils.checkIfContainsLetter(selectedTxtField.getText(), wordsList.getItems().get(i).toString())) {
                        toRemovePol.add((String) wordsList.getItems().get(i));
                    }
                }
                wordsList.getItems().removeAll(toRemovePol);
                break;
        }
        thingsList.getItems().remove(selectedTxtField.getText());
        selectedTxtField.setText("");
    }

    @Override
    public void init(String name, Sheet sheet, Scene scene, String sheetPath, MainWindowController controller) throws SQLException, ClassNotFoundException {
        currSheet = sheet;
        currSheetPath = sheetPath;
        mainWindowController = controller;
        sheetNameTxtField.setText(name);
        if(sheet.getTitle() != null) {
            titleSheetTxtField.setText(sheet.getTitle());
        }
        if(sheet.isIfDate()) {
            ifDateCheckbox.setSelected(true);
        }
        if(sheet.isIfName()) {
            ifNameCheckbox.setSelected(true);
        }
        if(sheet.isIfGrade()) {
            ifGradeCheckbox.setSelected(true);
        }
        switch (sheet.getCategory()) {
            case "POLISH":
                if(sheet.getDictation() == null) {
                    initPolishWords(sheet);
                } else {
                    initPolishDictation(sheet);
                }
                break;
            case "ENGLISH":
                initEnglishWords(sheet);
                break;
        }
        wordsList.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if(wordsList.getSelectionModel().getSelectedItem() != null) {
                deleteWordTxtField.setText(wordsList.getSelectionModel().getSelectedItem().toString());
            }
        });
        thingsList.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if(thingsList.getSelectionModel().getSelectedItem() != null) {
                selectedTxtField.setText(thingsList.getSelectionModel().getSelectedItem().toString());
            }
        });

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

    private void setChangedDictation() {
        Dictation dictation = currSheet.getDictation();
        if(dictation.getFilepath().compareTo(currFileTxField.getText()) != 0) {
            dictation.setFilepath(currFileTxField.getText());
            dictation.setText(SheetCommonUtils.eraseLettersFromText(
                    FXCollections.observableArrayList(dictation.getLetter()), dictationTxtArea.getText()));
        }
        currSheet.setDictation(dictation);
    }

    private void setChangedEnglishWords() {
        if(!wordsTable.getItems().isEmpty()) {
            currSheet.setEngpolWords(SheetCommonUtils.replaceWordsWithDotsInList(wordsTable.getItems()));
        } else {
            ViewUtils.showErrorAlert("Lista słów jest pusta");
            return;
        }
    }

    private void setChangedPolishWords() {
        if(!wordsList.getItems().isEmpty()) {
            currSheet.setPolishWords(SheetCommonUtils.replaceLetterWithDotsInList(wordsList.getItems(), thingsList.getItems()));
        } else {
            ViewUtils.showErrorAlert("Lista słów jest pusta");
            return;
        }

    }

    private void initEnglishWords(Sheet sheet) throws SQLException, ClassNotFoundException {
        categoriesLbl.setVisible(true);
        initSelected();
        wordsLbl.setVisible(true);
        wordsTable.setVisible(true);
        addEngWordLbl.setVisible(true);
        polLbl.setVisible(true);
        polWordTxtField.setVisible(true);
        engLbl.setVisible(true);
        engWordTxtField.setVisible(true);
        catLbl.setVisible(true);
        catCombobox.setVisible(true);
        polEngRadioBtn.setVisible(true);
        engPolRadioBtn.setVisible(true);
        addEngBtn.setVisible(true);
        deleteEngBtn.setVisible(true);
        thingsList.setItems(FXCollections.observableArrayList(sheet.getEngCategories().getEngCategory()));
        //todo - podmiana kropek na słowa
        wordsTable.setItems(replaceDotsWithWords(sheet));
        catCombobox.setItems(FXCollections.observableArrayList(sheet.getEngCategories().getEngCategory()));
    }

    private ObservableList<EngpolWord> replaceDotsWithWords(Sheet sheet) throws SQLException, ClassNotFoundException {
        List<EngpolWord> toPol = new ArrayList<>();
        List<EngpolWord> toEng = new ArrayList<>();
        String polWords = new String("('");
        String polCats = new String("('");
        String engWords = new String("('");
        String engCats = new String("('");
        for(int i = 0; i < sheet.getEngpolWords().getEngpolWord().size(); i++) {
            if(sheet.getEngpolWords().getEngpolWord().get(i).getIfToPolish() != null) {
                toPol.add(sheet.getEngpolWords().getEngpolWord().get(i));
                polWords = polWords + sheet.getEngpolWords().getEngpolWord().get(i).getEngWord() + "', '";
                if(!polCats.contains(sheet.getEngpolWords().getEngpolWord().get(i).getEngCategory())) {
                    polCats = polCats + sheet.getEngpolWords().getEngpolWord().get(i).getEngCategory() + "', '";
                }
            } else {
                toEng.add(sheet.getEngpolWords().getEngpolWord().get(i));
                engWords = engWords + sheet.getEngpolWords().getEngpolWord().get(i).getPolWord() + "', '";
                if(!engCats.contains(sheet.getEngpolWords().getEngpolWord().get(i).getEngCategory())) {
                    engCats = engCats + sheet.getEngpolWords().getEngpolWord().get(i).getEngCategory() + "', '";
                }
            }
        }
        polWords = polWords.substring(0, polWords.length() - 3) + ")";
        polCats = polCats.substring(0, polCats.length() - 3) + ")";
        engWords = engWords.substring(0, engWords.length() - 3) + ")";
        engCats = engCats.substring(0, engCats.length() - 3) + ")";
        ObservableList<EngpolWord> list = FXCollections.observableList(EnglishWordsDAO.
                searchEnglishWordsInTable(toPol, "word_eng", polWords, polCats));
        list.addAll(FXCollections.observableList(EnglishWordsDAO.
                searchEnglishWordsInTable(toEng, "word_pol", engWords, engCats)));
        return list;
    }

    private void initPolishDictation(Sheet sheet) {
        lettersLbl.setVisible(true);
        selectedLbl.setVisible(true);
        thingsList.setVisible(true);
        currFileLbl.setVisible(true);
        currFileTxField.setVisible(true);
        loadFileBtn.setVisible(true);
        dictationLbl.setVisible(true);
        dictationTxtArea.setVisible(true);
        currFileTxField.setText(sheet.getDictation().getFilepath());
        thingsList.setItems(FXCollections.observableArrayList(sheet.getLetters().getLetter()));
        dictationTxtArea.setText(sheet.getDictation().getText());
    }

    private void initPolishWords(Sheet sheet) {
        lettersLbl.setVisible(true);
        initSelected();
        wordsLbl.setVisible(true);
        wordsList.setVisible(true);
        addWordLbl.setVisible(true);
        addWordTxtField.setVisible(true);
        addWordBtn.setVisible(true);
        deleteWordLbl.setVisible(true);
        deleteWordTxtField.setVisible(true);
        deleteWordBtn.setVisible(true);
        thingsList.setItems(FXCollections.observableArrayList(sheet.getLetters().getLetter()));
        List<String> words = new ArrayList<>();
        for(int i = 0; i < sheet.getPolishWords().getPolishWord().size(); i++) {
            String tempWord = (sheet.getPolishWords().getPolishWord().get(i).getWord().trim()).replace("....",
                                        sheet.getPolishWords().getPolishWord().get(i).getLetter().trim());
            if(words.contains(tempWord)) {
                continue;
            }
            words.add(tempWord);
        }
        wordsList.setItems(FXCollections.observableArrayList(words));
    }

    private void initSelected() {
        selectedLbl.setVisible(true);
        thingsList.setVisible(true);
        selectedTxtField.setVisible(true);
        deleteBtn.setVisible(true);
        withWordsLbl.setVisible(true);
    }
}
