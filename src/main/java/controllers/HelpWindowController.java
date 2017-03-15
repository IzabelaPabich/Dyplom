package controllers;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import model.sheet.Sheet;
import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by Alebazi on 2017-02-27.
 */
public class HelpWindowController implements IController {

    private MainWindowController mainWindowController;


    @FXML
    ListView helpList;

    @FXML
    TextArea helpTxtArea;

    @FXML
    Button closeHelpBtn;


    public HelpWindowController() {

    }

    @FXML protected void closeHelpWindow() {
        Stage stage = (Stage) closeHelpBtn.getScene().getWindow();
        stage.close();
    }

    @Override
    public void init(String name, Sheet sheet, Scene scene, String sheetPath, MainWindowController controller) throws SQLException, ClassNotFoundException {
        mainWindowController = controller;
        helpList.setItems(FXCollections.observableArrayList("Tworzenie arkusza", "Otwieranie arkusza",
                "Edycja arkusza", "Opisy kategorii", "O programie"));
        helpList.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            switch((String) newValue) {
                case "Tworzenie arkusza":
                    try {
                        loadFile("create_sheet.txt");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                case "Otwieranie arkusza":
                    try {
                        loadFile("open_sheet.txt");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                case "Edycja arkusza":
                    try {
                        loadFile("edit_sheet.txt");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                case "Opisy kategorii":
                    try {
                        loadFile("sheet_categories.txt");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                case "O programie":
                    try {
                        loadFile("program_info.txt");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
            }
        });
    }

    private void loadFile(String filename) throws IOException {
        StringBuilder result = new StringBuilder("");
        ClassLoader classLoader = getClass().getClassLoader();
        String fileString = new String("helpWindowTexts/" + filename);
        File file = new File(classLoader.getResource(fileString).getFile());

        try (Scanner scanner = new Scanner(file)) {

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                result.append(line).append("\n");
            }

            scanner.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        helpTxtArea.setText(result.toString());
    }
}
