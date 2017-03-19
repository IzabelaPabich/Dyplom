package controllers;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.DAO.*;
import model.mathTables.EquationMTable;
import model.mathTables.EquationTable;
import model.mathTables.GraphTable;
import model.sheet.*;
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
    private List<Equation> equations;
    private List<EquationM> equationsM;
    private List<Graph> graphs;

    @FXML
    Tab polTab, engTab, catTab, mathTab;

    @FXML
    TabPane DBTabPane;

    @FXML
    Button closeBtn, deletePolWordBtn, addPolWordBtn, deleteEngWordBtn, addEngWordBtn, deleteCatBtn, addCatBtn,
            deleteLetterBtn, addLetterBtn, deleteEquationBtn, deleteEquationMBtn, deleteGraphBtn,
            addEquationBtn, addEquationMBtn, addGraphBtn;

    @FXML
    TextField selectedPolWordTxtField, selectedWordLetterTxtField, addPolWordTxtField, selectedOnPolTxtField, selectedOnEngTxtField,
            selectedCatTxtField, addOnPolTxtField, addOnEngTxtField, selectedCategoryTxtField, addCategoryTxtField, selectedLetterTxtField,
            addLetterTxtField;
    @FXML TextField firstCompTxtField, operationTxtField, secondCompTxtField, resultTxtField, equalTxtField;
    @FXML TextField firstComp1TxtField, firstOperationTxtField, firstComp2TxtField, equalsTxtField, secondComp1TxtField,
        secondOperationTxtField, secondComp2TxtField;
    @FXML TextField firstCompTxtFieldG, firstOperationTxtFieldG, secondCompTxtFieldG, secondOperationTxtFieldG,
            thirdCompTxtFieldG, thirdOperationTxtFieldG;

    @FXML
    TableView polWordsTable, engWordsTable, equationTable, equationMTable, graphTable;

    @FXML
    TableColumn polWordCol, letterCol, onPolCol, onEngCol, catCol, firstCompE, operationE, secondCompE, equationMarkE, resultE,
    firstComp1, firstOperation, firstComp2, equationMark, secondComp1, secondOperation, secondComp2,
    firstCompG, operation12, secondCompG, operation23, thirdCompG, operation31;

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

    @FXML protected void addEquation() {
        Equation newEquation = new Equation();
        newEquation.setFirstComp(firstCompTxtField.getText());
        newEquation.setOperation(operationTxtField.getText());
        newEquation.setSecondComp(secondCompTxtField.getText());
        newEquation.setEquationMark(equalTxtField.getText());
        newEquation.setResult(resultTxtField.getText());
        if(!equationTable.getItems().contains(newEquation)) {
            EquationsDAO.insertEquation(newEquation);
            ViewUtils.showInfoAlert("Równanie " + newEquation.toString() +  " zostało dodane do bazy");
            equationTable.getItems().add(newEquation);
        } else {
            ViewUtils.showInfoAlert("Równianie " + newEquation.toString() + " już istnieje w bazie");
        }
    }

    @FXML protected void deleteEquation() {
        EquationsDAO.deleteEquation((Equation) equationTable.getSelectionModel().getSelectedItem());
        ViewUtils.showInfoAlert("Równianie " + equationTable.getSelectionModel().getSelectedItem().toString() +
        " zostało usunięte z bazy");
        equationTable.getItems().remove(equationTable.getSelectionModel().getSelectedItem());
    }

    @FXML protected void addEquationM() {
        EquationM newEquation = new EquationM();
        newEquation.setFirstComp1(firstComp1TxtField.getText());
        newEquation.setFirstOperation(firstOperationTxtField.getText());
        newEquation.setFirstComp2(firstComp2TxtField.getText());
        newEquation.setEquationMark(equalsTxtField.getText());
        newEquation.setSecondComp1(secondComp1TxtField.getText());
        newEquation.setSecondOperation(secondOperationTxtField.getText());
        newEquation.setSecondComp2(secondComp2TxtField.getText());
        if(!equationMTable.getItems().contains(newEquation)) {
            EquationsMDAO.insertEquationM(newEquation);
            ViewUtils.showInfoAlert("Równanie " + newEquation.toString() +  " zostało dodane do bazy");
            equationMTable.getItems().add(newEquation);
        } else {
            ViewUtils.showInfoAlert("Równianie " + newEquation.toString() + " już istnieje w bazie");
        }
    }

    @FXML protected void deleteEquationM() {
        EquationsMDAO.deleteEquationM((EquationM) equationMTable.getSelectionModel().getSelectedItem());
        ViewUtils.showInfoAlert("Równianie " + equationMTable.getSelectionModel().getSelectedItem().toString() +
                " zostało usunięte z bazy");
        equationMTable.getItems().remove(equationMTable.getSelectionModel().getSelectedItem());
    }

    @FXML protected void addGraph() {
        Graph newGraph = new Graph();
        newGraph.setFirstComp(firstCompTxtFieldG.getText());
        newGraph.setOperation12(new GraphMark(firstOperationTxtFieldG.getText().substring(0, 1),
                firstOperationTxtFieldG.getText().substring(1)));
        newGraph.setSecondComp(secondCompTxtFieldG.getText());
        newGraph.setOperation23(new GraphMark(secondOperationTxtFieldG.getText().substring(0, 1),
                secondOperationTxtFieldG.getText().substring(1)));
        newGraph.setThirdComp(thirdCompTxtFieldG.getText());
        newGraph.setOperation31(new GraphMark(thirdOperationTxtFieldG.getText().substring(0, 1),
                thirdOperationTxtFieldG.getText().substring(1)));
        if(!graphTable.getItems().contains(newGraph)) {
            GraphsDAO.insertGraph(newGraph);
            ViewUtils.showInfoAlert("Graf został dodany do bazy");
            graphTable.getItems().add(newGraph);
        } else {
            ViewUtils.showInfoAlert("Graf już istnieje w bazie");
        }
    }

    @FXML protected void deleteGraph() {
        GraphsDAO.deleteGraph((Graph) graphTable.getSelectionModel().getSelectedItem());
        ViewUtils.showInfoAlert("Graf został usunięty z bazy");
        graphTable.getItems().remove(graphTable.getSelectionModel().getSelectedItem());
    }


    @Override
    public void init(String name, Sheet sheet, Scene scene, String sheetPath, MainWindowController mainWindowController)
            throws SQLException, ClassNotFoundException {
        configureTableColumns();
        setListeners();
        polishWords = PolishWordsDAO.getAllPolishWords();
        englishWords = EnglishWordsDAO.getAllEnglishWords();
        letters = LettersDAO.searchLetters();
        categories = CategoriesDAO.searchCategories();
        equations = EquationsDAO.getAllEquations();
        equationsM = EquationsMDAO.getAllEquationsM();
        graphs = GraphsDAO.getAllGraphs();

        polWordsTable.setItems(FXCollections.observableArrayList(polishWords));
        engWordsTable.setItems(FXCollections.observableArrayList(englishWords));
        categoriesListV.setItems(FXCollections.observableArrayList(categories));
        lettersListV.setItems(FXCollections.observableArrayList(letters));
        equationTable.setItems(FXCollections.observableArrayList(equations));
        equationMTable.setItems(FXCollections.observableArrayList(equationsM));
        graphTable.setItems(FXCollections.observableArrayList(graphs));


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

    private void configureTableColumns() {
        //table english
        onPolCol.setCellValueFactory(new PropertyValueFactory<EngpolWord, String>("polWord"));
        onEngCol.setCellValueFactory(new PropertyValueFactory<EngpolWord, String>("engWord"));
        catCol.setCellValueFactory(new PropertyValueFactory<EngpolWord, String>("engCategory"));
        catCol.setStyle("-fx-alignment: CENTER;");

        //table polish
        polWordCol.setCellValueFactory(new PropertyValueFactory<PolishWord, String>("word"));
        letterCol.setCellValueFactory(new PropertyValueFactory<PolishWord, String>("letter"));

        //equations
        firstCompE.setCellValueFactory(new PropertyValueFactory<Equation, String>("firstComp"));
        operationE.setCellValueFactory(new PropertyValueFactory<Equation, String>("operation"));
        secondCompE.setCellValueFactory(new PropertyValueFactory<Equation, String>("secondComp"));
        equationMarkE.setCellValueFactory(new PropertyValueFactory<Equation, String>("equationMark"));
        resultE.setCellValueFactory(new PropertyValueFactory<Equation, String>("result"));

        //equationsM
        firstComp1.setCellValueFactory(new PropertyValueFactory<EquationM, String>("firstComp1"));
        firstOperation.setCellValueFactory(new PropertyValueFactory<EquationM, String>("firstOperation"));
        firstComp2.setCellValueFactory(new PropertyValueFactory<EquationM, String>("firstComp2"));
        equationMark.setCellValueFactory(new PropertyValueFactory<EquationM, String>("equationMark"));
        secondComp1.setCellValueFactory(new PropertyValueFactory<EquationM, String>("secondComp1"));
        secondOperation.setCellValueFactory(new PropertyValueFactory<EquationM, String>("secondOperation"));
        secondComp2.setCellValueFactory(new PropertyValueFactory<EquationM, String>("secondComp2"));

        //graphs
        firstCompG.setCellValueFactory(new PropertyValueFactory<Graph, String>("firstComp"));
        operation12.setCellValueFactory(new PropertyValueFactory<Graph, String>("operation12String"));
        secondCompG.setCellValueFactory(new PropertyValueFactory<Graph, String>("secondComp"));
        operation23.setCellValueFactory(new PropertyValueFactory<Graph, String>("operation23String"));
        thirdCompG.setCellValueFactory(new PropertyValueFactory<Graph, String>("thirdComp"));
        operation31.setCellValueFactory(new PropertyValueFactory<Graph, String>("operation31String"));
    }
}
