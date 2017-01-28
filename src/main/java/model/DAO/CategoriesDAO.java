package model.DAO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import utils.DBUtils;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Alebazi on 2017-01-03.
 */
public class CategoriesDAO {

    public static void insertCategory(String newCategory) {
        String insertStmt = "INSERT INTO categories (category) " +
                "VALUES ('" + newCategory + "');";

        try {
            DBUtils.dbExecuteUpdate(insertStmt);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static ObservableList<String> searchCategories() throws ClassNotFoundException, SQLException {
        String selectStmt = "SELECT * FROM categories";

        try {
            ResultSet rsCategories = DBUtils.dbExecuteQuery(selectStmt);
            ObservableList<String> categoriesList = getCategoriesList(rsCategories);

            return categoriesList;
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

    private static ObservableList<String> getCategoriesList(ResultSet rsLetters) throws SQLException {
        ObservableList<String> categoryList = FXCollections.observableArrayList();

        while(rsLetters.next()) {
            String letter = rsLetters.getString("CATEGORY").trim();
            categoryList.add(letter);
        }

        return categoryList;
    }

    public static void deleteCategory(String oldCategory) {
        String deleteStmt = "DELETE FROM categories WHERE category ='" + oldCategory + "'";

        try {
            DBUtils.dbExecuteUpdate(deleteStmt);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
