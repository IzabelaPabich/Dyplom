package controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.DAO.EnglishWordsDAO;
import model.mathTables.EquationMTable;
import model.mathTables.EquationTable;
import model.mathTables.GraphTable;
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

    @FXML private CheckBox ifNameCheckbox, ifDateCheckbox, ifGradeCheckbox, equationCheckBox, equationMCheckBox,
                graphCheckBox;

    @FXML private Label lettersLbl, categoriesLbl, selectedLbl, wordsLbl, withWordsLbl, addWordLbl, deleteWordLbl,
                        dictationLbl, currFileLbl, addEngWordLbl, polLbl, engLbl, catLbl, mathTasksLbl;

    @FXML private ListView thingsList, wordsList;

    @FXML private Button deleteBtn, addWordBtn, deleteWordBtn, saveBtn, cancelBtn, loadFileBtn, addEngBtn, deleteEngBtn,
            equEditBtn, equMEditBtn, graphEditBtn;

    @FXML private TextArea dictationTxtArea;

    @FXML private TableView wordsTable, equationsTable, equationsMTable, graphsTable;

    @FXML private TableColumn polWordCol, engWordCol, catWordCol, engPolFlagCol, polEngFlagCol;
    @FXML private TableColumn firstColE, firstCheckColE, IColE, ICheckColE, secondColE, secondCheckColE, IIColE,
            IICheckColE, thirdColE, thirdCheckColE;
    @FXML private TableColumn firstCol1, firstCheckCol1, ICol,ICheckCol, firstCol2, firstCheckCol2, IIICol, IIICheckCol,
            secondCol1, secondCheckCol1, IICol, IICheckCol, secondCol2, secondCheckCol2;
    @FXML private TableColumn firstColG, firstCheckColG, IColG, ICheckColG, secondColG, secondCheckColG, IIColG, IICheckColG,
            thirdColG, thirdCheckColG, IIIColG, IIICheckColG;

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
                    setChangedMathTasks();
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

    @FXML protected void editEquations() {

    }

    @FXML protected void editEquationsM() {

    }

    @FXML protected void editGraphs() {

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
            case "MATH":
                initMathTables(sheet);
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

        //table -> English Sheet
        polWordCol.setCellValueFactory(new PropertyValueFactory<EngpolWord, String>("polWord"));
        engWordCol.setCellValueFactory(new PropertyValueFactory<EngpolWord, String>("engWord"));
        catWordCol.setCellValueFactory(new PropertyValueFactory<EngpolWord, String>("engCategory"));
        catWordCol.setStyle("-fx-alignment: CENTER;");
        polEngFlagCol.setCellValueFactory(new PropertyValueFactory<EngpolWord, String>("ifToEnglish"));
        polEngFlagCol.setStyle("-fx-alignment: CENTER;");
        engPolFlagCol.setCellValueFactory(new PropertyValueFactory<EngpolWord, String>("ifToPolish"));
        engPolFlagCol.setStyle("-fx-alignment: CENTER;");

        //table -> Math Sheet Equations
        equationsTable.setPlaceholder(new Label("Brak elementów"));

        firstColE.setCellValueFactory(new PropertyValueFactory<EquationTable, String>("firstComp"));
        firstCheckColE.setCellValueFactory(new PropertyValueFactory<EquationTable, Boolean>("firstColChecked"));
        firstCheckColE.setCellFactory(column -> new CheckBoxTableCell<>());
        firstCheckColE.setEditable(false);
        IColE.setCellValueFactory(new PropertyValueFactory<EquationTable, String>("operation"));
        ICheckColE.setCellValueFactory(new PropertyValueFactory<EquationTable, Boolean>("IColChecked"));
        ICheckColE.setCellFactory(column -> new CheckBoxTableCell<>());
        ICheckColE.setEditable(false);
        secondColE.setCellValueFactory(new PropertyValueFactory<EquationTable, String>("secondComp"));
        secondCheckColE.setCellValueFactory(new PropertyValueFactory<EquationTable, Boolean>("secondColChecked"));
        secondCheckColE.setCellFactory(column -> new CheckBoxTableCell<>());
        secondCheckColE.setEditable(false);
        IIColE.setCellValueFactory(new PropertyValueFactory<EquationTable, String>("equationMark"));
        IICheckColE.setCellValueFactory(new PropertyValueFactory<EquationTable, Boolean>("IIColChecked"));
        IICheckColE.setCellFactory(column -> new CheckBoxTableCell<>());
        IICheckColE.setEditable(false);
        thirdColE.setCellValueFactory(new PropertyValueFactory<EquationTable, String>("result"));
        thirdCheckColE.setCellValueFactory(new PropertyValueFactory<EquationTable, Boolean>("thirdColChecked"));
        thirdCheckColE.setCellFactory(column -> new CheckBoxTableCell<>());
        thirdCheckColE.setEditable(false);

        //table -> Math Sheet EquationsM
        equationsMTable.setPlaceholder(new Label("Brak elementów"));

        firstCol1.setCellValueFactory(new PropertyValueFactory<EquationMTable, String>("firstComp1"));
        firstCheckCol1.setCellValueFactory(new PropertyValueFactory<EquationMTable, Boolean>("firstCol1Checked"));
        firstCheckCol1.setCellFactory(column -> new CheckBoxTableCell<>());
        firstCheckCol1.setEditable(false);
        ICol.setCellValueFactory(new PropertyValueFactory<EquationMTable, String>("firstOperation"));
        ICheckCol.setCellValueFactory(new PropertyValueFactory<EquationMTable, Boolean>("IColChecked"));
        ICheckCol.setCellFactory(column -> new CheckBoxTableCell<>());
        ICheckCol.setEditable(false);
        firstCol2.setCellValueFactory(new PropertyValueFactory<EquationMTable, String>("firstComp2"));
        firstCheckCol2.setCellValueFactory(new PropertyValueFactory<EquationMTable, Boolean>("firstCol2Checked"));
        firstCheckCol2.setCellFactory(column -> new CheckBoxTableCell<>());
        firstCheckCol2.setEditable(false);

        IIICol.setCellValueFactory(new PropertyValueFactory<EquationTable, String>("equationMark"));
        IIICheckCol.setCellValueFactory(new PropertyValueFactory<EquationTable, Boolean>("IIIColChecked"));
        IIICheckCol.setCellFactory(column -> new CheckBoxTableCell<>());
        IIICheckCol.setEditable(false);

        secondCol1.setCellValueFactory(new PropertyValueFactory<EquationTable, String>("secondComp1"));
        secondCheckCol1.setCellValueFactory(new PropertyValueFactory<EquationTable, Boolean>("secondCol1Checked"));
        secondCheckCol1.setCellFactory(column -> new CheckBoxTableCell<>());
        secondCheckCol1.setEditable(false);
        IICol.setCellValueFactory(new PropertyValueFactory<EquationTable, String>("secondOperation"));
        IICheckCol.setCellValueFactory(new PropertyValueFactory<EquationTable, Boolean>("IIColChecked"));
        IICheckCol.setCellFactory(column -> new CheckBoxTableCell<>());
        IICheckCol.setEditable(false);
        secondCol2.setCellValueFactory(new PropertyValueFactory<EquationTable, String>("secondComp2"));
        secondCheckCol2.setCellValueFactory(new PropertyValueFactory<EquationTable, Boolean>("secondCol2Checked"));
        secondCheckCol2.setCellFactory(column -> new CheckBoxTableCell<>());
        secondCheckCol2.setEditable(false);

        //table -> Math Sheet Graphs
        graphsTable.setPlaceholder(new Label("Brak elementów"));

        firstColG.setCellValueFactory(new PropertyValueFactory<GraphTable, String>("firstComp"));
        firstCheckColG.setCellValueFactory(new PropertyValueFactory<GraphTable, Boolean>("firstColChecked"));
        firstCheckColG.setCellFactory(column -> new CheckBoxTableCell<>());
        firstCheckColG.setEditable(false);

        IColG.setCellValueFactory(new PropertyValueFactory<GraphTable, String>("operation12String"));
        ICheckColG.setCellValueFactory(new PropertyValueFactory<GraphTable, Boolean>("IColChecked"));
        ICheckColG.setCellFactory(column -> new CheckBoxTableCell<>());
        ICheckColG.setEditable(false);

        secondColG.setCellValueFactory(new PropertyValueFactory<GraphTable, String>("secondComp"));
        secondCheckColG.setCellValueFactory(new PropertyValueFactory<GraphTable, Boolean>("secondColChecked"));
        secondCheckColG.setCellFactory(column -> new CheckBoxTableCell<>());
        secondCheckCol2.setEditable(false);

        IIColG.setCellValueFactory(new PropertyValueFactory<GraphTable, String>("operation23String"));
        IICheckColG.setCellValueFactory(new PropertyValueFactory<GraphTable, Boolean>("IIColChecked"));
        IICheckColG.setCellFactory(column -> new CheckBoxTableCell<>());
        IICheckColG.setEditable(false);

        thirdColG.setCellValueFactory(new PropertyValueFactory<GraphTable, String>("thirdComp"));
        thirdCheckColG.setCellValueFactory(new PropertyValueFactory<GraphTable, Boolean>("thirdColChecked"));
        thirdCheckColG.setCellFactory(column -> new CheckBoxTableCell<>());
        thirdCheckColG.setEditable(false);

        IIIColG.setCellValueFactory(new PropertyValueFactory<GraphTable, String>("operation31String"));
        IIICheckColG.setCellValueFactory(new PropertyValueFactory<GraphTable, Boolean>("IIIColChecked"));
        IIICheckColG.setCellFactory(column -> new CheckBoxTableCell<>());
        IIICheckColG.setEditable(false);
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

    private void setChangedMathTasks() {
        if(!equationCheckBox.isSelected()) {
            currSheet.getMathTasks().setEquation(null);
        } else {
            List<Equation> finalEquations = new ArrayList<>();
            for (int i = 0; i < equationsTable.getItems().size(); i++) {
                finalEquations.add(SheetCommonUtils.eraseFieldsFromEquation((EquationTable) equationsTable.getItems().get(i)));
            }
            currSheet.getMathTasks().setEquation(finalEquations);
        }
        if(!equationMCheckBox.isSelected()) {
            currSheet.getMathTasks().setEquationM(null);
        } else {
            List<EquationM> finalEquationsM = new ArrayList<>();
            for(int i = 0; i < equationsMTable.getItems().size(); i++) {
                finalEquationsM.add(SheetCommonUtils.eraseFieldsFromEquationM((EquationMTable) equationsMTable.getItems().get(i)));
            }
            currSheet.getMathTasks().setEquationM(finalEquationsM);
        }
        if(!graphCheckBox.isSelected()) {
            currSheet.getMathTasks().setGraph(null);
        } else {
            List<Graph> finalGraphs = new ArrayList<>();
            for(int i = 0; i < graphsTable.getItems().size(); i++) {
                finalGraphs.add(SheetCommonUtils.eraseFieldsFromGraph((GraphTable) graphsTable.getItems().get(i)));
            }
            currSheet.getMathTasks().setGraph(finalGraphs);
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


    private void initMathTables(Sheet sheet) {
        mathTasksLbl.setVisible(true);
        equEditBtn.setVisible(true);
        equMEditBtn.setVisible(true);
        graphEditBtn.setVisible(true);
        equationCheckBox.setVisible(true);
        if(sheet.getMathTasks().getEquation().size() != 0) {
            equationCheckBox.setSelected(true);
            equEditBtn.setDisable(false);
            List<Equation> equations = sheet.getMathTasks().getEquation();
            List<EquationTable> equationsTemp = new ArrayList<>();
            for(int i = 0; i < equations.size(); i++) {
                EquationTable tempEquation = new EquationTable("","","","","",false,false,false,false,false);
                tempEquation.setFirstComp(equations.get(i).getFirstComp());
                if(equations.get(i).getFirstComp().compareTo("....") == 0) {
                    tempEquation.setFirstColChecked(true);
                }
                tempEquation.setOperation(equations.get(i).getOperation());
                if(equations.get(i).getOperation().compareTo("....") == 0) {
                    tempEquation.setIColChecked(true);
                }
                tempEquation.setSecondComp(equations.get(i).getSecondComp());
                if(equations.get(i).getSecondComp().compareTo("....") == 0) {
                    tempEquation.setSecondColChecked(true);
                }
                tempEquation.setEquationMark(equations.get(i).getEquationMark());
                if(equations.get(i).getEquationMark().compareTo("....") == 0) {
                    tempEquation.setIIColChecked(true);
                }
                tempEquation.setResult(equations.get(i).getResult());
                if(equations.get(i).getResult().compareTo("....") == 0) {
                    tempEquation.setThirdColChecked(true);
                }
                equationsTemp.add(tempEquation);
            }
            equationsTable.setItems(FXCollections.observableArrayList(equationsTemp));
        }
        equationMCheckBox.setVisible(true);
        if(sheet.getMathTasks().getEquationM().size() != 0) {
            equationMCheckBox.setSelected(true);
            equMEditBtn.setDisable(false);
            List<EquationM> equationsM = sheet.getMathTasks().getEquationM();
            List<EquationMTable> equationsMTemp = new ArrayList<>();
            for(int i = 0; i < equationsM.size(); i++) {
                EquationMTable tempEquation = new EquationMTable("","","","","","","",false,false,false,false,false,false,false);
                tempEquation.setFirstComp1(equationsM.get(i).getFirstComp1());
                if(equationsM.get(i).getFirstComp1().compareTo("....") == 0) {
                    tempEquation.setFirstCol1Checked(true);
                }
                tempEquation.setFirstOperation(equationsM.get(i).getFirstOperation());
                if(equationsM.get(i).getFirstOperation().compareTo("....") == 0) {
                    tempEquation.setIColChecked(true);
                }
                tempEquation.setFirstComp2(equationsM.get(i).getFirstComp2());
                if(equationsM.get(i).getFirstComp2().compareTo("....") == 0) {
                    tempEquation.setFirstCol2Checked(true);
                }
                tempEquation.setEquationMark(equationsM.get(i).getEquationMark());
                if(equationsM.get(i).getEquationMark().compareTo("....") == 0) {
                    tempEquation.setIIIColChecked(true);
                }
                tempEquation.setSecondComp1(equationsM.get(i).getSecondComp1());
                if(equationsM.get(i).getSecondComp1().compareTo("....") == 0) {
                    tempEquation.setSecondCol1Checked(true);
                }
                tempEquation.setSecondOperation(equationsM.get(i).getSecondOperation());
                if(equationsM.get(i).getSecondOperation().compareTo("....") == 0) {
                    tempEquation.setIIColChecked(true);
                }
                tempEquation.setSecondComp2(equationsM.get(i).getSecondComp2());
                if(equationsM.get(i).getSecondComp2().compareTo("....") == 0) {
                    tempEquation.setSecondCol2Checked(true);
                }
                equationsMTemp.add(tempEquation);
            }
            equationsMTable.setItems(FXCollections.observableArrayList(equationsMTemp));
        }
        graphCheckBox.setVisible(true);
        if(sheet.getMathTasks().getGraph().size() != 0) {
            graphCheckBox.setSelected(true);
            graphEditBtn.setDisable(false);
            List<Graph> graphs = sheet.getMathTasks().getGraph();
            List<GraphTable> graphsTemp = new ArrayList<>();
            for(int i = 0; i < graphs.size(); i++) {
                GraphTable tempGraph= new GraphTable("",new GraphMark(),"",new GraphMark(),"",new GraphMark(),
                        false,false,false,false,false,false);
                tempGraph.setFirstComp(graphs.get(i).getFirstComp());
                if(graphs.get(i).getFirstComp().compareTo("....") == 0) {
                    tempGraph.setFirstColChecked(true);
                }
                tempGraph.setOperation12(graphs.get(i).getOperation12());
                tempGraph.setOperation12String(graphs.get(i).getOperation12().toString());
                if(graphs.get(i).getOperation12().toString().compareTo("........") == 0) {
                    tempGraph.setIColChecked(true);
                }
                tempGraph.setSecondComp(graphs.get(i).getSecondComp());
                if(graphs.get(i).getSecondComp().compareTo("....") == 0) {
                    tempGraph.setSecondColChecked(true);
                }
                tempGraph.setOperation23(graphs.get(i).getOperation23());
                tempGraph.setOperation23String(graphs.get(i).getOperation23().toString());
                if(graphs.get(i).getOperation23().toString().compareTo("........") == 0) {
                    tempGraph.setIIColChecked(true);
                }
                tempGraph.setThirdComp(graphs.get(i).getThirdComp());
                if(graphs.get(i).getThirdComp().compareTo("....") == 0) {
                    tempGraph.setThirdColChecked(true);
                }
                tempGraph.setOperation31(graphs.get(i).getOperation31());
                tempGraph.setOperation31String(graphs.get(i).getOperation31().toString());
                if(graphs.get(i).getOperation31().toString().compareTo("........") == 0) {
                    tempGraph.setIIIColChecked(true);
                }
                graphsTemp.add(tempGraph);
            }
            graphsTable.setItems(FXCollections.observableArrayList(graphsTemp));
        }
        equationsTable.setVisible(true);
        equationsMTable.setVisible(true);
        graphsTable.setVisible(true);
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
