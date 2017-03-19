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

    public static List<Equation> getAllEquations() throws ClassNotFoundException, SQLException {
        String selectStmt = "SELECT DISTINCT * FROM equations";

        try {
            ResultSet rsWords = DBUtils.dbExecuteQuery(selectStmt);

            List<Equation> equations = getEquationsFromResultSet(rsWords);

            return equations;
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

    private static List<Equation> getEquationsFromResultSet(ResultSet rsWords) throws SQLException {
        List<Equation> tempEquations = new ArrayList<>();
        Equation tempEquation;
        List<Equation> equations = new ArrayList<>();
        while(rsWords.next()) {
           tempEquation = new Equation();
           tempEquation.setFirstComp(String.valueOf(rsWords.getInt("FIRST_COMP")));
           tempEquation.setOperation(rsWords.getString("OPERATION"));
           tempEquation.setSecondComp(String.valueOf(rsWords.getInt("SECOND_COMP")));
           tempEquation.setEquationMark(rsWords.getString("EQUATION_MARK"));
           tempEquation.setResult(String.valueOf(rsWords.getInt("RESULT")));
           equations.add(tempEquation);
        }

        return equations;
    }

    public static void deleteEquation(Equation equation) {
        String deleteStmt = "DELETE FROM equations WHERE first_comp='" + equation.getFirstComp() +
                "' AND operation='" + equation.getOperation() + "'AND second_comp='" + equation.getSecondComp()
                + "' AND equation_mark='" + equation.getEquationMark() + "' AND result='" + equation.getResult()
                + "'";

        try {
            DBUtils.dbExecuteUpdate(deleteStmt);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void insertEquation(Equation newEquation) {
        String insertStmt = "INSERT INTO equations (FIRST_COMP, OPERATION, SECOND_COMP, EQUATION_MARK, RESULT) " +
                "VALUES ('"+newEquation.getFirstComp()+"', '"+newEquation.getOperation()+"', '"
                +newEquation.getSecondComp()+"', '"+newEquation.getEquationMark()+"', '"+newEquation.getResult()+"');";

        try {
            DBUtils.dbExecuteUpdate(insertStmt);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
