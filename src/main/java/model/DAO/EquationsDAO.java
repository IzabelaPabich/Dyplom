package model.DAO;

import model.sheet.Equation;
import model.sheet.PolishWord;
import utils.DAOCommonUtils;
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

    private final static String ONE_TEN = "1 - 10";
    private final static String ONE_TWENTY = "1 - 20";
    private final static String ONE_FIFTY = "1 - 50";
    private final static String ONE_HUNDRED = "1 - 100";

    public static List<Equation> searchEquationsWithAmount(int amount, String range) throws ClassNotFoundException, SQLException {
        String selectStmt = "SELECT DISTINCT * FROM equations";

        try {
            ResultSet rsWords = DBUtils.dbExecuteQuery(selectStmt);

            List<Equation> equations = getEquationAmountFromResultSet(rsWords, amount, range);

            return equations;
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

    private static List<Equation> getEquationAmountFromResultSet(ResultSet rsWords, int amount, String range) throws SQLException {
        List<Equation> tempEquations = new ArrayList<>();
        Equation tempEquation;
        Random random = new Random();
        int currRandom;
        List<Equation> equations = new ArrayList<>();
        while(rsWords.next()) {
            if(DAOCommonUtils.checkIfInRange(rsWords, range)) {
                tempEquation = new Equation();
                tempEquation.setFirstComp(String.valueOf(rsWords.getInt("FIRST_COMP")));
                tempEquation.setOperation(rsWords.getString("OPERATION"));
                tempEquation.setSecondComp(String.valueOf(rsWords.getInt("SECOND_COMP")));
                tempEquation.setEquationMark(rsWords.getString("EQUATION_MARK"));
                tempEquation.setResult(String.valueOf(rsWords.getInt("RESULT")));
                tempEquations.add(tempEquation);
            }
        }
        if(tempEquations.size() < amount) {
            ViewUtils.showErrorAlert("Nie ma tylu działań w bazie");
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
