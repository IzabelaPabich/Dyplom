package model.DAO;

import javafx.scene.control.ListView;
import model.sheet.PolishWord;
import utils.DBUtils;
import utils.ViewUtils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Alebazi on 2016-12-18.
 */
public class PolishWordsDAO {

    public static void insertPolishWord(PolishWord newWord) {
        String deleteStmt = "INSERT INTO polish_words (LETTER, WORD_POL) " +
                "VALUES ('" + newWord.getLetter() + "', '" + newWord.getWord() + "');";

        try {
            DBUtils.dbExecuteUpdate(deleteStmt);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public static void deletePolishWord(PolishWord oldWord) {
        String insertStmt = "DELETE FROM polish_words WHERE word_pol='" + oldWord.getWord() + "' AND letter='" + oldWord.getLetter() + "'";

        try {
            DBUtils.dbExecuteUpdate(insertStmt);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public static PolishWord searchPolishWord (PolishWord word) throws SQLException, ClassNotFoundException {
        String selectStmt = "SELECT * FROM polish_words WHERE letter='"+word.getLetter()+"' AND word_pol='"+word.getWord()+"'";

        try {
            ResultSet rsWord = DBUtils.dbExecuteQuery(selectStmt);

            PolishWord polishWord = getPolishWordFromResultSet(rsWord);

            return polishWord;
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

    private static PolishWord getPolishWordFromResultSet(ResultSet rs) throws SQLException {
        PolishWord polWord = new PolishWord();
        if(rs.next()) {
            polWord.setLetter(rs.getString("LETTER"));
            polWord.setWord(rs.getString("WORD_POL"));
        }
        return polWord;
    }

    public static List<String> searchPolishWordsWithAmount (int amount, List<String> letters) throws ClassNotFoundException, SQLException {
        String lettersString = ViewUtils.makeSelectInFromList(letters);
        String selectStmt = "SELECT DISTINCT * FROM polish_words WHERE letter IN " + lettersString;

        try {
            ResultSet rsWords = DBUtils.dbExecuteQuery(selectStmt);

            List<String> polishWords = getPolishWordsAmountFromResultSet(rsWords, amount);

            return  polishWords;
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

    private static List<String> getPolishWordsAmountFromResultSet(ResultSet rsWords, int amount) throws SQLException {
        List<PolishWord> tempWords = new ArrayList<>();
        PolishWord tempWord;
        Random random = new Random();
        int currRandom;
        List<String> polWords = new ArrayList<>();
        while(rsWords.next()) {
            tempWord = new PolishWord();
            tempWord.setWord(rsWords.getString("WORD_POL").trim());
            tempWord.setLetter(rsWords.getString("WORD_ID").trim());
            tempWords.add(tempWord);
        }
        for(int i = 0; i < amount; i++) {
            currRandom = random.nextInt(tempWords.size());
            polWords.add(tempWords.get(currRandom).getWord());
            tempWords.remove(currRandom);
        }

        return polWords;
    }

    public static List<PolishWord> getAllPolishWords() throws ClassNotFoundException, SQLException {
        String selectStmt = "SELECT word_pol, letter FROM polish_words";

        try {
            ResultSet rsWord = DBUtils.dbExecuteQuery(selectStmt);

            List<PolishWord> polishWords = getAllPolishWordsFromResultSet(rsWord);

            return polishWords;
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

    private static List<PolishWord> getAllPolishWordsFromResultSet(ResultSet rs) throws SQLException {
        List<PolishWord> words = new ArrayList<>();
        PolishWord word;
        while(rs.next()) {
            word = new PolishWord();
            word.setLetter(rs.getString("LETTER").trim());
            word.setWord(rs.getString("WORD_POL").trim());
            words.add(word);
        }
        return words;
    }
}
