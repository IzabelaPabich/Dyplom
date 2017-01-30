package model.DAO;

import model.sheet.EngpolWord;
import model.sheet.PolishWord;
import utils.DBUtils;
import utils.ViewUtils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Alebazi on 2017-01-03.
 */
public class EnglishWordsDAO {

    public static void insertEnglishWord(EngpolWord newWord) {
        String insertStmt = "INSERT INTO english_words (WORD_ENG, CATEGORY, WORD_POL) " +
                "VALUES ('"+newWord.getEngWord()+"', '"+newWord.getEngCategory()+"', '"+newWord.getPolWord()+"');";

        try {
            DBUtils.dbExecuteUpdate(insertStmt);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public static void deleteEnglishWord(EngpolWord oldWord) {
        String deleteStmt = "DELETE FROM english_words WHERE word_eng='" + oldWord.getEngWord() + "' AND word_pol='" + oldWord.getPolWord()
                + "' AND category='" + oldWord.getEngCategory() + "'";

        try {
            DBUtils.dbExecuteUpdate(deleteStmt);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public static EngpolWord searchEnglishWord(EngpolWord word) throws ClassNotFoundException, SQLException {
        String selectStmt = "SELECT * FROM english_words WHERE word_eng='"+word.getEngWord()+
                "' AND word_pol='"+word.getPolWord()+"' AND category='"+word.getEngCategory()+"'";

        try {
            ResultSet rsWord = DBUtils.dbExecuteQuery(selectStmt);

            EngpolWord englishWord = getEnglishWordFromResultSet(rsWord);

            return englishWord;
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

    private static EngpolWord getEnglishWordFromResultSet(ResultSet rs) throws SQLException {
        EngpolWord engpolWord = new EngpolWord();
        if(rs.next()) {
            engpolWord.setEngWord(rs.getString("WORD_ENG"));
            engpolWord.setPolWord(rs.getString("WORD_POL"));
            engpolWord.setEngCategory(rs.getString("CATEGORY"));
        }
        return engpolWord;
    }

    public static List<EngpolWord> searchEnglishWordsWithAmount(int amountEngPol, int amountPolEng, List<String> categories)
            throws ClassNotFoundException, SQLException {
        String categoriesString = ViewUtils.makeSelectInFromList(categories);
        String selectStmt = "SELECT DISTINCT word_eng, word_pol, category FROM english_words WHERE category IN " + categoriesString;

        try {
            ResultSet rsWords = DBUtils.dbExecuteQuery(selectStmt);

            List<EngpolWord> englishWords = getEnglishWordsAmountFromResultSet(rsWords, amountEngPol, amountPolEng);

            return englishWords;
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

    private static List<EngpolWord> getEnglishWordsAmountFromResultSet(ResultSet rsWords, int amountEngPol, int amountPolEng)
            throws SQLException {
        List<EngpolWord> tempWords = getWordsFromResultSet(rsWords, null);
        Random random = new Random();
        int currRandom;
        List<EngpolWord> engWords = new ArrayList<>();
        for(int i = 0; i < amountEngPol; i++) {
            currRandom = random.nextInt(tempWords.size());
            tempWords.get(currRandom).setIfToPolish("X");
            engWords.add(tempWords.get(currRandom));
            tempWords.remove(currRandom);
        }
        for(int i = 0; i < amountPolEng; i++) {
            currRandom = random.nextInt(tempWords.size());
            tempWords.get(currRandom).setIfToEnglish("X");
            engWords.add(tempWords.get(currRandom));
            tempWords.remove(currRandom);
        }

        return engWords;
    }

    public static List<EngpolWord> searchEnglishWordsInTable(List<EngpolWord> words, String column, String inCondition,
                                                             String categories) throws ClassNotFoundException, SQLException {
        String selectStmt = "SELECT DISTINCT * FROM english_words WHERE " + column + " IN " + inCondition
                + " AND category IN " + categories;

        try {
            ResultSet rsWords = DBUtils.dbExecuteQuery(selectStmt);

            List<EngpolWord> searchedWords = getWordsFromResultSet(rsWords, words.get(0));

            return searchedWords;
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

    private static List<EngpolWord> getWordsFromResultSet(ResultSet rsWords, EngpolWord word)
            throws SQLException {
        EngpolWord tempWord;
        List<EngpolWord> engWords = new ArrayList<>();
        while(rsWords.next()) {
            tempWord = new EngpolWord();
            tempWord.setPolWord(rsWords.getString("WORD_POL").trim());
            tempWord.setEngWord(rsWords.getString("WORD_ENG").trim());
            tempWord.setEngCategory(rsWords.getString("CATEGORY").trim());
            if(word != null) {
                if (word.getIfToPolish() != null) {
                    tempWord.setIfToPolish("X");
                } else {
                    tempWord.setIfToEnglish("X");
                }
            }
            engWords.add(tempWord);
        }

        return engWords;
    }

    public static List<EngpolWord> getAllEnglishWords() throws ClassNotFoundException, SQLException {
        String selectStmt = "SELECT word_eng, word_pol, category FROM english_words";

        try {
            ResultSet rsWord = DBUtils.dbExecuteQuery(selectStmt);

            List<EngpolWord> englishWords = getAllEnglishWordsFromResultSet(rsWord);

            return englishWords;
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

    private static List<EngpolWord> getAllEnglishWordsFromResultSet(ResultSet rs) throws SQLException {
        List<EngpolWord> words = new ArrayList<>();
        EngpolWord word;
        while(rs.next()) {
            word = new EngpolWord();
            word.setEngWord(rs.getString("WORD_ENG").trim());
            word.setPolWord(rs.getString("WORD_POL").trim());
            word.setEngCategory(rs.getString("CATEGORY").trim());
            words.add(word);
        }
        return words;
    }

}
