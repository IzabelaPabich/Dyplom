package utils;

import controllers.NewSheetPolishController;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.DAO.PolishWordsDAO;
import model.sheet.PolishWord;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

/**
 * Created by Alebazi on 2016-11-28.
 */
public class ViewUtils {

    private final static String ERROR_TITLE = "Błąd!";
    private final static String INFO_TITLE = "Informacja";
    private final static String ANSWER_YES = "Tak";
    private final static String ANSWER_NO = "Nie";

    public static boolean showClosingAlert(String title, String question) {
        Alert closeConfirmation = new Alert(Alert.AlertType.CONFIRMATION);
        closeConfirmation.setTitle(title);
        closeConfirmation.setHeaderText("");
        closeConfirmation.setContentText(question);

        ButtonType buttonYes = new ButtonType(ANSWER_YES);
        ButtonType buttonNo = new ButtonType(ANSWER_NO);
        closeConfirmation.getButtonTypes().setAll(buttonYes, buttonNo);

        Optional<ButtonType> result = closeConfirmation.showAndWait();

        if(result.get() == buttonYes) {
            return true;
        } else {
            return false;
        }
    }

    public static void showErrorAlert(String question) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(ERROR_TITLE);
        alert.setHeaderText(ERROR_TITLE);
        alert.setContentText(question);
        alert.showAndWait();
    }

    public static void showInfoAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(INFO_TITLE);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public static String makeSelectInFromList(List<String> list) {
        String listAsString = "(";
        for(int i = 0; i < list.size(); i++) {
            if(i == list.size() - 1) {
                listAsString += "'" + list.get(i) + "')";
            } else {
                listAsString += "'" + list.get(i) + "', ";
            }
        }
        return listAsString;
    }




}
