package model.DAO;

import model.sheet.EquationM;
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
public class EquationsMDAO {

    public static List<EquationM> searchEquationsMWithAmount(int amount, String range) throws ClassNotFoundException, SQLException {
        String selectStmt = "SELECT DISTINCT * FROM equations_m";

        try {
            ResultSet rsWords = DBUtils.dbExecuteQuery(selectStmt);

            List<EquationM> equations = getEquationAmountFromResultSet(rsWords, amount, range);

            return equations;
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

    private static List<EquationM> getEquationAmountFromResultSet(ResultSet rsWords, int amount, String range) throws SQLException {
        List<EquationM> tempEquations = new ArrayList<>();
        EquationM tempEquation;
        Random random = new Random();
        int currRandom;
        List<EquationM> equations = new ArrayList<>();
        while (rsWords.next()) {
            if (DAOCommonUtils.checkIfInRange(rsWords, range)) {
                tempEquation = new EquationM();
                tempEquation.setFirstComp1(String.valueOf(rsWords.getInt("FIRST_COMP1")));
                tempEquation.setFirstOperation(rsWords.getString("FIRST_OPERATION"));
                tempEquation.setFirstComp2(String.valueOf(rsWords.getInt("FIRST_COMP2")));
                tempEquation.setEquationMark(rsWords.getString("EQUATION_MARK"));
                tempEquation.setSecondComp1(String.valueOf(rsWords.getInt("SECOND_COMP1")));
                tempEquation.setSecondOperation(rsWords.getString("SECOND_OPERATION"));
                tempEquation.setSecondComp2(String.valueOf(rsWords.getInt("SECOND_COMP2")));
                tempEquations.add(tempEquation);
            }
        }
        if (tempEquations.size() < amount) {
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


