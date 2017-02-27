package controllers;

import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import model.sheet.Sheet;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import utils.FileUtils;
import utils.ViewUtils;
import org.apache.commons.io.FilenameUtils;

import javax.xml.bind.JAXBException;
import javafx.event.ActionEvent;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alebazi on 2016-11-22.
 */
public class MainWindowController {

    private NewSheetFormController newSheetFormController = new NewSheetFormController();
    public Stage mainStage;
    public Sheet currentSheet;
    private File sheetToOpen;
    private List<Image> pagesAsImages = new ArrayList<>();
    private int currPage;
    private boolean newSheetFlag;
    private boolean dbFlag = false;
    private boolean openNewSheetFlag = false;

    @FXML private MenuItem closeMenuItem, manageDBMenuItem, createSheetMenuItem, openSheetMenuItem, aboutProgramMenuItem;
    @FXML private MenuBar menuBar;
    @FXML private Label welcomeLbl1, welcomeLbl2, currSheetNameLbl, manageSheetLbl;
    @FXML private ScrollPane sheetScrollPane;
    @FXML private Button editSheetBtn, nextPageBtn, previousPageBtn, closeSheetBtn;

    public MainWindowController(){
    }

    public void initialize() {
        System.out.println("Start aplikacji");
        //newSheetFormController.init(this);
        sheetScrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        sheetScrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        sheetScrollPane.setFitToWidth(true);
        //this.mainStage = stage;
    }

    @FXML public void showClosePopup() {
        if(ViewUtils.showClosingAlert("Zamknięcie aplikacji", "Czy na pewno chcesz zakończyć?")) {
            mainStage.close();
        };
    }

    @FXML protected void showNewSheetForm() throws SQLException, ClassNotFoundException {
        newSheetFlag = true;
        openWindow("/fxml/newSheetFormPart1.fxml", new String());
    }

    @FXML
    public void openExistingSheet() throws IOException, JAXBException {
        if(!openNewSheetFlag) {
            sheetToOpen = FileUtils.readFile((Stage) menuBar.getScene().getWindow(), "PDF files( *.pdf)", "*.pdf");
        }
        if(sheetToOpen != null) {
            currPage = 0;
            welcomeLbl1.setVisible(false);
            welcomeLbl2.setVisible(false);
            sheetScrollPane.setVisible(true);
            manageSheetLbl.setVisible(true);
            editSheetBtn.setVisible(true);
            nextPageBtn.setVisible(true);
            previousPageBtn.setVisible(true);
            closeSheetBtn.setVisible(true);
            currentSheet = FileUtils.readXMLToSheet(FilenameUtils.removeExtension(sheetToOpen.getName()));
            currSheetNameLbl.setVisible(true);
            currSheetNameLbl.setText("Arkusz: " + currentSheet.getSheetName());
            readPDFToImages();
            setPageOnScrollPane();
        }
    }


    @FXML protected void manageDB() throws SQLException, ClassNotFoundException {
        dbFlag = true;
        openWindow("/fxml/DBWindow.fxml", new String());
    }

    private void setPageOnScrollPane() {
        ImageView imageView = new ImageView(pagesAsImages.get(currPage));
        imageView.setFitWidth(872);
        imageView.setPreserveRatio(true);
        sheetScrollPane.setContent(imageView);
    }

    private void readPDFToImages() throws IOException {
        PDDocument document = PDDocument.load(sheetToOpen);
        List<PDPage> list = document.getDocumentCatalog().getAllPages();
        pagesAsImages = new ArrayList<>();
        for(int i = 0; i < list.size(); i++) {
            PDPage page = list.get(i);
            pagesAsImages.add(SwingFXUtils.toFXImage(page.convertToImage(), null));
        }
        if(pagesAsImages.size() > 1) {
            nextPageBtn.setDisable(false);
        }
        document.close();
    }

    @FXML protected void editSheet(ActionEvent e) throws SQLException, ClassNotFoundException {
        newSheetFlag = false;
        openWindow("/fxml/editSheetWindow.fxml", sheetToOpen.getParent());
    }

    @FXML protected void closeSheet(ActionEvent e) {
        welcomeLbl1.setVisible(true);
        welcomeLbl2.setVisible(true);
        sheetScrollPane.setVisible(false);
        manageSheetLbl.setVisible(false);
        editSheetBtn.setVisible(false);
        nextPageBtn.setVisible(false);
        previousPageBtn.setVisible(false);
        closeSheetBtn.setVisible(false);
        currSheetNameLbl.setVisible(false);
    }

    @FXML protected void setPreviousPage(ActionEvent e) {
        nextPageBtn.setDisable(false);
        currPage = currPage - 1;
        setPageOnScrollPane();
        if(currPage == 0) {
            previousPageBtn.setDisable(true);
        }
    }

    @FXML protected void setNextPage(ActionEvent e) {
        previousPageBtn.setDisable(false);
        currPage = currPage + 1;
        setPageOnScrollPane();
        if(currPage == pagesAsImages.size() - 1) {
            nextPageBtn.setDisable(true);
        }
    }

    @FXML
    protected void showHelpWindow() throws SQLException, ClassNotFoundException {
        dbFlag = true;
        openWindow("/fxml/helpWindow.fxml", new String());

    }

    public void setMainStage(Stage stage) {
        this.mainStage = stage;
    }

    private void openWindow(String fxmlFile, String sheetPath) throws SQLException, ClassNotFoundException {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(fxmlFile));
            Parent rootNewForm = fxmlLoader.load();
            IController controller = fxmlLoader.getController();
            if(newSheetFlag) {
                controller.init(null, new Sheet(), editSheetBtn.getScene(), new String(), this);
            } else if(dbFlag) {
                controller.init(null, null, menuBar.getScene(), new String(), this);
            } else {
                controller.init(currentSheet.getSheetName(), currentSheet, editSheetBtn.getScene(), sheetPath, this);
            }
            Scene newForm1 = new Scene(rootNewForm);
            Stage stage = new Stage();
            stage.setScene(newForm1);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void init(Stage stage) {
        mainStage = stage;
    }

    public void setOpenNewSheetFlag(boolean openNewSheetFlag) {
        this.openNewSheetFlag = openNewSheetFlag;
    }

    public void setSheetToOpen(File sheetFile) {
        sheetToOpen = sheetFile;
    }
}
