package controllers;

import javafx.scene.Scene;
import model.sheet.Sheet;

import java.sql.SQLException;

/**
 * Created by Alebazi on 2017-01-02.
 */
public interface IController {

    void init (String name, Sheet sheet, Scene scene, String sheetPath, MainWindowController controller) throws SQLException, ClassNotFoundException;
}
