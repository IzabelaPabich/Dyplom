package controllers;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.DAO.CategoriesDAO;
import model.DAO.EnglishWordsDAO;
import model.DAO.LettersDAO;
import model.DAO.PolishWordsDAO;
import model.sheet.EngpolWord;
import model.sheet.PolishWord;
import model.sheet.Sheet;
import utils.ViewUtils;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alebazi on 2017-01-16.
 */
public class DBWindowController implements IController {

    private List<String> categories;
    private List<String> letters;
    private List<PolishWord> polishWords;
    private List<EngpolWord> englishWords;

    @FXML
    Tab polTab, engTab, catTab, mathTab;

    @FXML
    TabPane DBTabPane;

    @FXML
    Button closeBtn, deletePolWordBtn, addPolWordBtn, deleteEngWordBtn, addEngWordBtn, deleteCatBtn, addCatBtn,
            deleteLetterBtn, addLetterBtn;

    @FXML
    TextField selectedPolWordTxtField, selectedWordLetterTxtField, addPolWordTxtField, selectedOnPolTxtField, selectedOnEngTxtField,
            selectedCatTxtField, addOnPolTxtField, addOnEngTxtField, selectedCategoryTxtField, addCategoryTxtField, selectedLetterTxtField,
            addLetterTxtField;

    @FXML
    TableView polWordsTable, engWordsTable;

    @FXML
    TableColumn polWordCol, letterCol, onPolCol, onEngCol, catCol;

    @FXML
    ComboBox lettersCombobox, addCatCombobox;

    @FXML
    ListView categoriesListV, lettersListV;

    public DBWindowController(){
    }

    @FXML
    protected void close(ActionEvent e) {
        Stage stage = (Stage) closeBtn.getScene().getWindow();
        stage.close();
    }

    @FXML
    protected void addPolWord(ActionEvent e) {
        PolishWord newWord = new PolishWord();
        newWord.setWord(addPolWordTxtField.getText());
        newWord.setLetter(lettersCombobox.getSelectionModel().getSelectedItem().toString());
        if(!polWordsTable.getItems().contains(newWord)) {
            PolishWordsDAO.insertPolishWord(newWord);
            ViewUtils.showInfoAlert("Słowo " + newWord.getWord() + " z literą " + newWord.getLetter()
                    + " zostało dodane do bazy");
            polWordsTable.getItems().add(newWord);
        } else {
            ViewUtils.showInfoAlert("Słowo " + newWord.getWord() + " z literą " + newWord.getLetter()
                    + " już istnieje w bazie");
        }

    }

    @FXML
    protected void deletePolWord(ActionEvent e) {
        PolishWord oldWord = new PolishWord();
        oldWord.setWord(selectedPolWordTxtField.getText());
        oldWord.setLetter(selectedWordLetterTxtField.getText());
        PolishWordsDAO.deletePolishWord(oldWord);
        ViewUtils.showInfoAlert("Słowo " + oldWord.getWord() + " z literą " + oldWord.getLetter()
                + " zostało usunięte z bazy");
        polWordsTable.getItems().remove(oldWord);
        selectedPolWordTxtField.setText("");
        selectedWordLetterTxtField.setText("");
    }

    @FXML
    protected void addEngWord(ActionEvent e) {
        EngpolWord newWord = new EngpolWord();
        newWord.setPolWord(addOnPolTxtField.getText());
        newWord.setEngWord(addOnEngTxtField.getText());
        newWord.setEngCategory(addCatCombobox.getSelectionModel().getSelectedItem().toString());
        if(!engWordsTable.getItems().contains(newWord)) {
            EnglishWordsDAO.insertEnglishWord(newWord);
            ViewUtils.showInfoAlert("Słowo " + newWord.getPolWord() + " - " + newWord.getEngWord()
                    + " z kategorii " + newWord.getEngCategory() + " zostało dodane do bazy");
            engWordsTable.getItems().add(newWord);
        } else {
            ViewUtils.showInfoAlert("Słowo " + newWord.getPolWord() + " - " + newWord.getEngWord()
                    + " z kategorii " + newWord.getEngCategory() + " już istnieje w bazie");
        }

    }

    @FXML
    protected void deleteEngWord(ActionEvent e) {
        EngpolWord oldWord = new EngpolWord();
        oldWord.setPolWord(selectedOnPolTxtField.getText());
        oldWord.setEngWord(selectedOnEngTxtField.getText());
        oldWord.setEngCategory(selectedCatTxtField.getText());
        EnglishWordsDAO.deleteEnglishWord(oldWord);
        ViewUtils.showInfoAlert("Słowo " + oldWord.getPolWord() + " - " + oldWord.getEngWord()
                + " z kategorii " + oldWord.getEngCategory() + " zostało usunięte z bazy");
        engWordsTable.getItems().remove(oldWord);
        selectedOnPolTxtField.setText("");
        selectedOnEngTxtField.setText("");
        selectedCatTxtField.setText("");
    }

    @FXML
    protected void addCat(ActionEvent e) {
        if(!categoriesListV.getItems().contains(addCategoryTxtField.getText())) {
            CategoriesDAO.insertCategory(addCategoryTxtField.getText());
            ViewUtils.showInfoAlert("Kategoria " + addCategoryTxtField.getText() + " została dodana do bazy");
            categoriesListV.getItems().add(addCategoryTxtField.getText());
            addCategoryTxtField.setText("");
        } else {
            ViewUtils.showInfoAlert("Kategoria już istnieje w bazie");
        }
    }

    @FXML
    protected void deleteCat(ActionEvent e) {
        CategoriesDAO.deleteCategory(selectedCategoryTxtField.getText());
        ViewUtils.showInfoAlert("Kategoria " + selectedCategoryTxtField.getText() + " została usunięta z bazy");
        categoriesListV.getItems().remove(selectedCategoryTxtField.getText());
        selectedCategoryTxtField.setText("");
    }

    @FXML
    protected void addLetter(ActionEvent e) {
        if(!lettersListV.getItems().contains(addLetterTxtField.getText())) {
            LettersDAO.insertLetter(addLetterTxtField.getText());
            ViewUtils.showInfoAlert("Litera " + addLetterTxtField.getText() + " została dodana do bazy");
            lettersListV.getItems().add(addLetterTxtField.getText());
            addLetterTxtField.setText("");
        } else {
            ViewUtils.showInfoAlert("Litera już istnieje w bazie");
        }
    }

    @FXML
    protected void deleteLetter(ActionEvent e) {
        LettersDAO.deleteLetter(selectedLetterTxtField.getText());
        ViewUtils.showInfoAlert("Litera " + selectedLetterTxtField.getText() + " została usunięta z bazy");
        lettersListV.getItems().remove(selectedLetterTxtField.getText());
        selectedLetterTxtField.setText("");
    }

    @FXML
    protected void initEngWords(ActionEvent e) {
        engWordsTable.setItems(FXCollections.observableArrayList(englishWords));
    }

    @FXML
    protected void initCategories(ActionEvent e) {
        categoriesListV.setItems(FXCollections.observableArrayList(categories));
    }

    @FXML
    protected void initLetters(ActionEvent e) {
        lettersListV.setItems(FXCollections.observableArrayList(letters));
    }

    @FXML
    protected void initPolWords(ActionEvent e) {
        polWordsTable.setItems(FXCollections.observableArrayList(polishWords));
    }

    @Override
    public void init(String name, Sheet sheet, Scene scene, String sheetPath, MainWindowController mainWindowController)
            throws SQLException, ClassNotFoundException {
        configureTableColums();
        setListeners();
        //todo-pobranie wszystkiego z bazy
        polishWords = PolishWordsDAO.getAllPolishWords();
        englishWords = EnglishWordsDAO.getAllEnglishWords();
        letters = LettersDAO.searchLetters();
        categories = CategoriesDAO.searchCategories();
        List<String> ewe = new ArrayList<>();

        polWordsTable.setItems(FXCollections.observableArrayList(polishWords));
        engWordsTable.setItems(FXCollections.observableArrayList(englishWords));
        categoriesListV.setItems(FXCollections.observableArrayList(categories));
        lettersListV.setItems(FXCollections.observableArrayList(letters));


        if (polWordsTable.getSelectionModel().getSelectedItems().isEmpty()) {
            polWordsTable.setPlaceholder(new Label("Brak elementów"));
        }
        if (engWordsTable.getSelectionModel().getSelectedItems().isEmpty()) {
            engWordsTable.setPlaceholder(new Label("Brak elementów"));
        }

        lettersCombobox.setItems(FXCollections.observableArrayList(letters));
        addCatCombobox.setItems(FXCollections.observableArrayList(categories));
    }

    private void setListeners() {
        lettersListV.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (lettersListV.getSelectionModel().getSelectedItem() != null) {
                selectedLetterTxtField.setText(lettersListV.getSelectionModel().getSelectedItem().toString());
            }
        });
        categoriesListV.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (categoriesListV.getSelectionModel().getSelectedItem() != null) {
                selectedCategoryTxtField.setText(categoriesListV.getSelectionModel().getSelectedItem().toString());
            }
        });
        polWordsTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (polWordsTable.getSelectionModel().getSelectedItem() != null) {
                PolishWord polishWord = (PolishWord) polWordsTable.getSelectionModel().getSelectedItem();
                selectedPolWordTxtField.setText(polishWord.getWord());
                selectedWordLetterTxtField.setText(polishWord.getLetter());
            }
        });
        engWordsTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (engWordsTable.getSelectionModel().getSelectedItem() != null) {
                EngpolWord engpolWord = (EngpolWord) engWordsTable.getSelectionModel().getSelectedItem();
                selectedOnPolTxtField.setText(engpolWord.getPolWord());
                selectedOnEngTxtField.setText(engpolWord.getEngWord());
                selectedCatTxtField.setText(engpolWord.getEngCategory());
            }
        });
    }

    private void configureTableColums() {
        //table english
        onPolCol.setCellValueFactory(new PropertyValueFactory<EngpolWord, String>("polWord"));
        onEngCol.setCellValueFactory(new PropertyValueFactory<EngpolWord, String>("engWord"));
        catCol.setCellValueFactory(new PropertyValueFactory<EngpolWord, String>("engCategory"));
        catCol.setStyle("-fx-alignment: CENTER;");

        //table polish
        polWordCol.setCellValueFactory(new PropertyValueFactory<PolishWord, String>("word"));
        letterCol.setCellValueFactory(new PropertyValueFactory<PolishWord, String>("letter"));
    }
}
