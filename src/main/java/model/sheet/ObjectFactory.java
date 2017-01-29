
package model.sheet;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the model.sheet package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _Title_QNAME = new QName("http://example.org/moja", "title");
    private final static QName _Text_QNAME = new QName("http://example.org/moja", "text");
    private final static QName _NameAndSurname_QNAME = new QName("http://example.org/moja", "nameAndSurname");
    private final static QName _Grade_QNAME = new QName("http://example.org/moja", "grade");
    private final static QName _Date_QNAME = new QName("http://example.org/moja", "date");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: model.sheet
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Dictation }
     * 
     */
    public Dictation createDictation() {
        return new Dictation();
    }

    /**
     * Create an instance of {@link EngpolWord }
     * 
     */
    public EngpolWord createEngpolWord() {
        return new EngpolWord();
    }

    /**
     * Create an instance of {@link PolishWord }
     * 
     */
    public PolishWord createPolishWord() {
        return new PolishWord();
    }

    /**
     * Create an instance of {@link Equation }
     * 
     */
    public Equation createEquation() {
        return new Equation();
    }

    /**
     * Create an instance of {@link EngpolWords }
     * 
     */
    public EngpolWords createEngpolWords() {
        return new EngpolWords();
    }

    /**
     * Create an instance of {@link GraphM }
     * 
     */
    public GraphM createGraphM() {
        return new GraphM();
    }

    /**
     * Create an instance of {@link MathTasks }
     * 
     */
    public MathTasks createMathTasks() {
        return new MathTasks();
    }

    /**
     * Create an instance of {@link EquationM }
     * 
     */
    public EquationM createEquationM() {
        return new EquationM();
    }

    /**
     * Create an instance of {@link Graph }
     * 
     */
    public Graph createGraph() {
        return new Graph();
    }

    /**
     * Create an instance of {@link TextTask }
     * 
     */
    public TextTask createTextTask() {
        return new TextTask();
    }

    /**
     * Create an instance of {@link EngCategories }
     * 
     */
    public EngCategories createEngCategories() {
        return new EngCategories();
    }

    /**
     * Create an instance of {@link Sheet }
     * 
     */
    public Sheet createSheet() {
        return new Sheet();
    }

    /**
     * Create an instance of {@link Letters }
     * 
     */
    public Letters createLetters() {
        return new Letters();
    }

    /**
     * Create an instance of {@link PolishWords }
     * 
     */
    public PolishWords createPolishWords() {
        return new PolishWords();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://example.org/moja", name = "title")
    public JAXBElement<String> createTitle(String value) {
        return new JAXBElement<String>(_Title_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://example.org/moja", name = "text")
    public JAXBElement<String> createText(String value) {
        return new JAXBElement<String>(_Text_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://example.org/moja", name = "nameAndSurname")
    public JAXBElement<String> createNameAndSurname(String value) {
        return new JAXBElement<String>(_NameAndSurname_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://example.org/moja", name = "grade")
    public JAXBElement<String> createGrade(String value) {
        return new JAXBElement<String>(_Grade_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://example.org/moja", name = "date")
    public JAXBElement<String> createDate(String value) {
        return new JAXBElement<String>(_Date_QNAME, String.class, null, value);
    }

}
