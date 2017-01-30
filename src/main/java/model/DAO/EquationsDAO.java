package model.DAO;

import model.sheet.Equation;
import model.sheet.PolishWord;
import utils.DBUtils;
import utils.ViewUtils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Alebazi on 2017-01-29.
 */
public class EquationsDAO {

    public static List<Equation> searchEquationsWithAmount (int amount) throws ClassNotFoundException, SQLException {
        String selectStmt = "SELECT DISTINCT * FROM equations";

        try {
            ResultSet rsWords = DBUtils.dbExecuteQuery(selectStmt);

            List<Equation> equations = getEquationAmountFromResultSet(rsWords, amount);

            return equations;
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

    private static List<Equation> getEquationAmountFromResultSet(ResultSet rsWords, int amount) throws SQLException {
        List<Equation> tempEquations = new ArrayList<>();
        Equation tempEquation;
        Random random = new Random();
        int currRandom;
        List<Equation> equations = new ArrayList<>();
        while(rsWords.next()) {
            tempEquation = new Equation();
            tempEquation.setFirstComp(rsWords.getInt("FIRST_COMP"));
            tempEquation.setOperation(rsWords.getString("OPERATION"));
            tempEquation.setSecondComp(rsWords.getInt("SECOND_COMP"));
            tempEquation.setEquationMark(rsWords.getString("EQUATION_MARK"));
            tempEquation.setResult(rsWords.getInt("RESULT"));
            tempEquations.add(tempEquation);
        }
        if(tempEquations.size() < amount) {
            ViewUtils.showErrorAlert("Nie ma tylu słów w bazie");
        } else {
            for (int i = 0; i < amount; i++) {
                currRandom = random.nextInt(tempEquations.size());
                equations.add(tempEquations.get(currRandom));
                tempEquations.remove(currRandom);
            }
        }

        return equations;
    }
}
