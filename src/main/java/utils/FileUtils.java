package utils;

import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.sheet.ObjectFactory;
import model.sheet.Sheet;
import org.apache.avalon.framework.configuration.Configuration;
import org.apache.avalon.framework.configuration.ConfigurationException;
import org.apache.avalon.framework.configuration.DefaultConfigurationBuilder;
import org.apache.commons.io.IOUtils;
import org.apache.commons.io.input.ClassLoaderObjectInputStream;
import org.apache.fop.apps.*;
import org.apache.fop.apps.FopFactory;
import org.xml.sax.SAXException;

import javax.xml.bind.*;
import javax.xml.transform.*;
import javax.xml.transform.sax.SAXResult;
import javax.xml.transform.stream.StreamSource;
import java.io.*;

/**
 * Created by Alebazi on 2016-12-17.
 */
public class FileUtils {

    public static void createSheet(Sheet newSheet, File directoryForPDF) throws JAXBException, IOException, SAXException, TransformerException, ConfigurationException {

        File file = new File("sheets" + File.separator + newSheet.getSheetName() + ".xml");
        file.getParentFile().mkdirs();
        file.createNewFile();
        FileOutputStream fout = new FileOutputStream(file, false);
        readSheetToXML(newSheet, fout);
        IOUtils.closeQuietly(fout);

        String xslFile = new String();
        switch(newSheet.getCategory()) {
            case "POLISH":
                xslFile = "sheetPolishT.xsl";
                break;
            case "ENGLISH":
                xslFile = "sheetEnglishT.xsl";
                break;
            case "MATH":
                xslFile = "sheetMathT.xsl";
                break;
        }
        createPDFFromXML("sheets" + File.separator + newSheet.getSheetName() + ".xml", xslFile,
                directoryForPDF + File.separator + newSheet.getSheetName() + ".pdf");

    }

    public static void createPDFFromXML(String xml, String xsl, String pdf) throws IOException, SAXException, TransformerException, ConfigurationException {

        File xsltFile = new File(xsl);
        StreamSource xmlSource = new StreamSource(new File(xml));
        FopFactory fopFactory = FopFactory.newInstance(new File(".").toURI());


        DefaultConfigurationBuilder cfgBuilder = new DefaultConfigurationBuilder();
        Configuration cfg = cfgBuilder.buildFromFile(new File("src/main/resources/font/mycfg.xml"));
        FopFactoryBuilder fopFactoryBuilder = new FopFactoryBuilder(new File(".").toURI()).setConfiguration(cfg);
        FopFactory fopFactory1 = fopFactoryBuilder.build();


        FOUserAgent foUserAgent = fopFactory.newFOUserAgent();
        OutputStream out = new FileOutputStream(pdf);

        try {
            Fop fop = fopFactory1.newFop(MimeConstants.MIME_PDF, foUserAgent, out);

            TransformerFactory factory = TransformerFactory.newInstance();
            Transformer transformer = factory.newTransformer(new StreamSource(xsltFile));
            transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");

            Result res = new SAXResult(fop.getDefaultHandler());
            transformer.transform(xmlSource, res);
        } finally {
            out.close();
        }
    }

    public static File chooseDirectorForPDFFile(Stage stage) {
        DirectoryChooser chooser = new DirectoryChooser();
        chooser.setTitle("Wybierz folder do zapisu");
        chooser.setInitialDirectory(new File("."));
        File selectedDirectory = chooser.showDialog(stage);
        return selectedDirectory;
    }

    public static File readFile(Stage stage, String ext1, String ext2) {
        FileChooser fc = new FileChooser();
        fc.setInitialDirectory(new File("."));
        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter(ext1, ext2));
        File file = fc.showOpenDialog(stage);
        return file;
    }

    public static Sheet readXMLToSheet(String sheetName) throws JAXBException {
        JAXBContext jc = JAXBContext.newInstance(Sheet.class);
        Unmarshaller unmarshaller = jc.createUnmarshaller();
        File file = new File("sheets" + File.separator + sheetName + ".xml");
        return (Sheet) unmarshaller.unmarshal(file);
    }

    private static void readSheetToXML(Sheet newSheet, FileOutputStream fout) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(newSheet.getClass().getPackage().getName());
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
        marshaller.marshal(newSheet, fout);
    }

    public static void saveTxtFile(Stage stage, String text) {
        FileChooser fc = new FileChooser();
        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt"));
        fc.setTitle("Zapisz treść dyktanda");
        File file = fc.showSaveDialog(stage);
        if (file != null) {
            try{
                PrintWriter pw = new PrintWriter(file, "UTF-8");
                pw.println(text);
                pw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
