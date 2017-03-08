package model.DAO;

import model.sheet.Graph;
import model.sheet.GraphMark;
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
public class GraphsDAO {

    public static List<Graph> searchGraphsWithAmount(int amount, String range) throws ClassNotFoundException, SQLException {
        String selectStmt = "SELECT DISTINCT * FROM graphs";

        try {
            ResultSet rsWords = DBUtils.dbExecuteQuery(selectStmt);

            List<Graph> graphs = getGraphsAmountFromResultSet(rsWords, amount, range);

            return graphs;
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

    private static List<Graph> getGraphsAmountFromResultSet(ResultSet rsWords, int amount, String range) throws SQLException {
        List<Graph> tempGraphs = new ArrayList<>();
        Graph tempGraph;
        GraphMark tempGraphMark;
        Random random = new Random();
        int currRandom;
        List<Graph> graphs = new ArrayList<>();
        while(rsWords.next()) {
            if(DAOCommonUtils.checkIfInRange(rsWords, range)) {
                tempGraph = new Graph();
                tempGraphMark = new GraphMark();
                String operation;
                tempGraph.setFirstComp(String.valueOf(rsWords.getInt("FIRST_COMP")));
                tempGraph.setSecondComp(String.valueOf(rsWords.getInt("SECOND_COMP")));
                tempGraph.setThirdComp(String.valueOf(rsWords.getInt("THIRD_COMP")));

                operation = new String(rsWords.getString("OPERATION12"));
                tempGraph.setOperation12(new GraphMark(operation.substring(0,1), operation.substring(1)));

                operation = new String(rsWords.getString("OPERATION23"));
                tempGraph.setOperation23(new GraphMark(operation.substring(0,1), operation.substring(1)));

                operation = new String(rsWords.getString("OPERATION31"));
                tempGraph.setOperation31(new GraphMark(operation.substring(0,1), operation.substring(1)));;

                tempGraphs.add(tempGraph);
            }
        }
        if(tempGraphs.size() < amount) {
            ViewUtils.showErrorAlert("Nie ma tylu grafów w bazie");
        } else {
            for (int i = 0; i < amount; i++) {
                currRandom = random.nextInt(tempGraphs.size());
                graphs.add(tempGraphs.get(currRandom));
                tempGraphs.remove(currRandom);
            }
        }

        return graphs;
    }
}
