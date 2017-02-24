package utils;

import controllers.MainWindowController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.DAO.EnglishWordsDAO;
import model.DAO.PolishWordsDAO;
import model.sheet.*;
import org.apache.avalon.framework.configuration.ConfigurationException;
import org.xml.sax.SAXException;

import javax.xml.bind.JAXBException;
import javax.xml.transform.TransformerException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by Alebazi on 2017-01-15.
 */
public class SheetCommonUtils {


    private static String[] polishLetters = new String[]{"ą", "ę", "ł", "ż", "ź", "ń", "ś", "ć", "Ą", "Ę", "Ł", "Ż", "Ź", "Ń", "Ś", "Ć"};

    public static String setFileToTextArea(File dictationFile) {
        TextArea textArea = new TextArea();
        try {
            Scanner s = new Scanner(dictationFile);
            while (s.hasNext()) {
                if (s.hasNextInt()) { // check if next token is an int
                    textArea.appendText(s.nextInt() + " "); // display the found integer
                } else {
                    textArea.appendText(s.next() + " "); // else read the next token
                }
            }
        } catch (FileNotFoundException ex) {
            System.err.println(ex);
        }

        return textArea.getText();
    }

    public static void addWordToListFromTxtField(ListView words, ListView letters, TextField textField) throws SQLException, ClassNotFoundException {
        List<String> lettersInWord = new ArrayList<>();
        if (checkIfContainsSelectedLetters(lettersInWord, letters, textField)) {
            if (!words.getItems().contains(textField.getText())) {
                words.getItems().add(textField.getText());
                PolishWord newWord = new PolishWord();
                newWord.setWord(textField.getText());
                for (int i = 0; i < lettersInWord.size(); i++) {
                    newWord.setLetter(lettersInWord.get(i));
                    if (PolishWordsDAO.searchPolishWord(newWord).getWord() == null) {
                        PolishWordsDAO.insertPolishWord(newWord);
                    }
                }
                ViewUtils.showInfoAlert("Dodano słowo: " + textField.getText());
                textField.setText("");
            }
        } else {
            ViewUtils.showErrorAlert("Słowo już jest na liście");
        }
    }

    private static boolean checkIfContainsSelectedLetters(List<String> lettersInWord, ListView letters, TextField textField) {
        for (int i = 0; i < letters.getItems().size(); i++) {
            if (textField.getText().contains(letters.getItems().get(i).toString().trim())) {
                lettersInWord.add(letters.getItems().get(i).toString());
            }
        }
        if (lettersInWord.isEmpty()) {
            return false;
        } else {
            return true;
        }
    }

    public static void setSheetCommonData(Sheet sheet) {
        if(sheet.isIfDate()) {
            sheet.setDate("Data: " + "...................");
        } else {
            sheet.setDate(null);
        }
        if(sheet.isIfName()) {
            sheet.setNameAndSurname("Imię i nazwisko: " + "................................");
        } else {
            sheet.setNameAndSurname(null);
        }
        if(sheet.isIfGrade()) {
            sheet.setGrade("Ocena: ...................");
        } else {
            sheet.setGrade(null);
        }
    }

    public static void addWordFromTxtFieldsToTable(ComboBox comboBox, TextField engWord, TextField polWord, RadioButton toPol,
                                                   RadioButton toEng, TableView tableView) throws SQLException, ClassNotFoundException {
        EngpolWord newWord = new EngpolWord();
        newWord.setEngCategory(comboBox.getSelectionModel().getSelectedItem().toString().trim());
        newWord.setEngWord(engWord.getText());
        newWord.setPolWord(polWord.getText());
        if(toPol.isSelected()) {
            newWord.setIfToEnglish("X");
        } else if(toEng.isSelected()) {
            newWord.setIfToPolish("X");
        }
        if(!tableView.getItems().contains(newWord)) {
            tableView.getItems().add(newWord);
            ViewUtils.showInfoAlert("Dodano do listy słowo: " + newWord.getPolWord() + " - " + newWord.getPolWord());
            if(EnglishWordsDAO.searchEnglishWord(newWord).getEngWord() == null) {
                EnglishWordsDAO.insertEnglishWord(newWord);
            }
        } else {
            ViewUtils.showErrorAlert("Słowo już jest na liście");
        }
    }

    public static String eraseLettersFromText(ObservableList selectedLetters, String text) {
        String[] splited = text.split("\\s+");
        for(int i= 0; i < splited.length; i++) {
            for (int j = 0; j < selectedLetters.size(); j++) {
                if(splited[i].contains(selectedLetters.get(j).toString().trim())) {
                    splited[i] = eraseLetterFromWord(selectedLetters.get(j).toString(), splited[i]);
                }
            }
        }
        return String.join(" ", splited);
    }

    public static boolean checkIfContainsLetter(String s, String word) {
        return word.contains(s.trim());
    }

    public static String eraseLetterFromWord(String s, String word) {
        return word.replace(s.trim(), "....");
    }

    public static PolishWords replaceLetterWithDotsInList(ObservableList<String> words, ObservableList<String> letters) {
        PolishWords polishWords = new PolishWords();
        String wordWoLetters;
        PolishWord newWord;
        List<PolishWord> newWords = new ArrayList<>();
        for (int i = 0; i < words.size(); ++i) {
            for (int j = 0; j < letters.size(); ++j) {
                if (SheetCommonUtils.checkIfContainsLetter(letters.get(j), words.get(i))) {
                    if ((letters.get(j)).trim().compareTo("h") == 0) {
                        if (SheetCommonUtils.checkIfContainsLetter("ch", words.get(i))) {
                            continue;
                        }
                    }
                    wordWoLetters = SheetCommonUtils.eraseLetterFromWord(letters.get(j), words.get(i));
                    newWord = new PolishWord();
                    newWord.setLetter(letters.get(j).toString());
                    newWord.setWord(wordWoLetters);
                    newWords.add(newWord);
                }
            }
        }
        polishWords.setWords(newWords);
        polishWords.setNumberOfWords(Integer.toString(newWords.size()));

        return  polishWords;
    }

    public static EngpolWords replaceWordsWithDotsInList(ObservableList<EngpolWord> words) {
        EngpolWords engpolWords = new EngpolWords();
        ObservableList<EngpolWord> wordsList = FXCollections.observableArrayList();
        for (int i = 0; i < words.size(); i++) {
            wordsList.add(words.get(i));
            if (wordsList.get(i).getIfToPolish() != null) {
                wordsList.get(i).setPolWord(replaceStringWithDots(wordsList.get(i).getPolWord()));
            } else if (wordsList.get(i).getIfToEnglish() != null) {
                wordsList.get(i).setEngWord(replaceStringWithDots(wordsList.get(i).getEngWord()));
            }
        }
        engpolWords.setEngpolWord(wordsList);

        return engpolWords;
    }

    private static String replaceStringWithDots(String word) {
        StringBuilder sb = new StringBuilder(word.length());
        for(int j = 0; j < (word.length() * 3); j++) {
            sb.append(".");
        }
        return sb.toString();
    }

    public static void replaceAllPolishCharacters(Sheet sheet) {
        sheet.setTitle(replacePolishCharacters(sheet.getTitle()));
        if(sheet.getNameAndSurname() != null) {
            sheet.setNameAndSurname(replacePolishCharacters(sheet.getNameAndSurname()));
        }
        sheet.setAddInfo(replacePolishCharacters(sheet.getAddInfo()));
        if(sheet.getPolishWords() != null) {
            for(int i = 0; i < sheet.getPolishWords().getPolishWord().size(); i++) {
                sheet.getPolishWords().getPolishWord().get(i).setWord(
                        replacePolishCharacters(sheet.getPolishWords().getPolishWord().get(i).getWord()));
            }
        }
        if(sheet.getDictation() != null) {
            sheet.getDictation().setText(replacePolishCharacters(sheet.getDictation().getText()));
        }
        if(sheet.getEngpolWords() != null) {
            for(int i = 0; i < sheet.getEngpolWords().getEngpolWord().size(); i++) {
                if(sheet.getEngpolWords().getEngpolWord().get(i).getIfToEnglish() != null) {
                    sheet.getEngpolWords().getEngpolWord().get(i).setPolWord(replacePolishCharacters(
                            sheet.getEngpolWords().getEngpolWord().get(i).getPolWord()));
                }
            }
        }
    }

    private static String replacePolishCharacters(String text) {
        for(int i = 0; i < polishLetters.length; i++) {
            if(text.contains(polishLetters[i])) {
                switch (polishLetters[i]) {
                    case "ę":
                        text = text.replace("ę", "e");
                        break;
                    case "ą":
                        text = text.replace("ą", "a");
                        break;
                    case "ł":
                        text = text.replace("ł", "l");
                        break;
                    case "ż":
                        text = text.replace("ż", "z");
                        break;
                    case "ź":
                        text = text.replace("ź", "z");
                        break;
                    case "ń":
                        text = text.replace("ń", "n");
                        break;
                    case "ś":
                        text = text.replace("ś", "s");
                        break;
                    case "ć":
                        text = text.replace("ć", "c");
                        break;
                    case "Ę":
                        text = text.replace("Ę", "E");
                        break;
                    case "Ą":
                        text = text.replace("Ą", "A");
                        break;
                    case "Ł":
                        text = text.replace("Ł", "L");
                        break;
                    case "Ż":
                        text = text.replace("Ż", "Z");
                        break;
                    case "Ź":
                        text = text.replace("Ź", "Z");
                        break;
                    case "Ń":
                        text = text.replace("Ń", "N");
                        break;
                    case "Ś":
                        text = text.replace("Ś", "S");
                        break;
                    case "Ć":
                        text = text.replace("Ć", "C");
                        break;
                }
            }
        }
        return text;
    }

    public static void setNewSheetOnMainWindow(MainWindowController controller, File file, String sheetName)
            throws IOException, JAXBException {
        controller.setOpenNewSheetFlag(true);
        controller.setSheetToOpen(new File(file.toString() + "\\" + sheetName + ".pdf"));
        controller.openExistingSheet();
        controller.setOpenNewSheetFlag(false);
    }

    public static String replaceMathComponentWithDots() {
        return "........";
    }

    public static void saveSheetInDirectory(Sheet sheet, File directoryToSave, MainWindowController mainController, Button createButton)
            throws IOException, TransformerException, SAXException, ConfigurationException, JAXBException {
        FileUtils.createSheet(sheet, directoryToSave);
        ViewUtils.showInfoAlert("Utworzono plik: " + sheet.getSheetName() + ".pdf w folderze:  " + directoryToSave.toString());
        SheetCommonUtils.setNewSheetOnMainWindow(mainController, directoryToSave, sheet.getSheetName());
        ((Stage) createButton.getScene().getWindow()).close();
    }

}
