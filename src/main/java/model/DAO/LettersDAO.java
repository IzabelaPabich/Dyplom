package model.DAO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.omg.CORBA.OBJ_ADAPTER;
import utils.DBUtils;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Alebazi on 2016-12-18.
 */
public class LettersDAO {

    public static void insertLetter(String newLetter) {
        String insertStmt = "INSERT INTO letters (LETTER) " +
                "VALUES ('" + newLetter + "');";

        try {
            DBUtils.dbExecuteUpdate(insertStmt);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public static ObservableList<String> searchLetters() throws ClassNotFoundException, SQLException {
        String selectStmt = "SELECT * FROM letters";

        try {
            ResultSet rsLetters = DBUtils.dbExecuteQuery(selectStmt);
            ObservableList<String> lettersList = getLettersList(rsLetters);

            return lettersList;
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

    private static ObservableList<String> getLettersList(ResultSet rsLetters) throws SQLException {
        ObservableList<String> letterList = FXCollections.observableArrayList();

        while (rsLetters.next()) {
            String letter = rsLetters.getString("LETTER").trim();
            letterList.add(letter);
        }

        return letterList;
    }

    public static void deleteLetter(String oldLetter) {
        String deleteStmt = "DELETE FROM letters WHERE letter ='" + oldLetter + "'";

        try {
            DBUtils.dbExecuteUpdate(deleteStmt);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
