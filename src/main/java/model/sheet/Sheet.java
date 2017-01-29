
package model.sheet;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{http://example.org/moja}title" minOccurs="0"/>
 *         &lt;element ref="{http://example.org/moja}letters" minOccurs="0"/>
 *         &lt;element ref="{http://example.org/moja}date" minOccurs="0"/>
 *         &lt;element ref="{http://example.org/moja}nameAndSurname" minOccurs="0"/>
 *         &lt;element name="addInfo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element ref="{http://example.org/moja}dictation" minOccurs="0"/>
 *         &lt;element ref="{http://example.org/moja}polishWords" minOccurs="0"/>
 *         &lt;element ref="{http://example.org/moja}engCategories" minOccurs="0"/>
 *         &lt;element ref="{http://example.org/moja}engpolWords" minOccurs="0"/>
 *         &lt;element ref="{http://example.org/moja}mathTasks" minOccurs="0"/>
 *         &lt;element ref="{http://example.org/moja}grade" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="category" use="required">
 *         &lt;simpleType>
 *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *             &lt;enumeration value="POLISH"/>
 *             &lt;enumeration value="ENGLISH"/>
 *             &lt;enumeration value="MATH"/>
 *           &lt;/restriction>
 *         &lt;/simpleType>
 *       &lt;/attribute>
 *       &lt;attribute name="sheetName" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="ifDate" use="required" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;attribute name="ifName" use="required" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;attribute name="ifGrade" use="required" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "title",
    "letters",
    "date",
    "nameAndSurname",
    "addInfo",
    "dictation",
    "polishWords",
    "engCategories",
    "engpolWords",
    "mathTasks",
    "grade"
})
@XmlRootElement(name = "sheet", namespace = "http://example.org/moja")
public class Sheet {

    @XmlElement(namespace = "http://example.org/moja")
    protected String title;
    @XmlElement(namespace = "http://example.org/moja")
    protected Letters letters;
    @XmlElement(namespace = "http://example.org/moja")
    protected String date;
    @XmlElement(namespace = "http://example.org/moja")
    protected String nameAndSurname;
    @XmlElement(namespace = "http://example.org/moja")
    protected String addInfo;
    @XmlElement(namespace = "http://example.org/moja")
    protected Dictation dictation;
    @XmlElement(namespace = "http://example.org/moja")
    protected PolishWords polishWords;
    @XmlElement(namespace = "http://example.org/moja")
    protected EngCategories engCategories;
    @XmlElement(namespace = "http://example.org/moja")
    protected EngpolWords engpolWords;
    @XmlElement(namespace = "http://example.org/moja")
    protected MathTasks mathTasks;
    @XmlElement(namespace = "http://example.org/moja")
    protected String grade;
    @XmlAttribute(name = "category", required = true)
    protected String category;
    @XmlAttribute(name = "sheetName", required = true)
    protected String sheetName;
    @XmlAttribute(name = "ifDate", required = true)
    protected boolean ifDate;
    @XmlAttribute(name = "ifName", required = true)
    protected boolean ifName;
    @XmlAttribute(name = "ifGrade", required = true)
    protected boolean ifGrade;

    /**
     * Gets the value of the title property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets the value of the title property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTitle(String value) {
        this.title = value;
    }

    /**
     * Gets the value of the letters property.
     * 
     * @return
     *     possible object is
     *     {@link Letters }
     *     
     */
    public Letters getLetters() {
        return letters;
    }

    /**
     * Sets the value of the letters property.
     * 
     * @param value
     *     allowed object is
     *     {@link Letters }
     *     
     */
    public void setLetters(Letters value) {
        this.letters = value;
    }

    /**
     * Gets the value of the date property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDate() {
        return date;
    }

    /**
     * Sets the value of the date property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDate(String value) {
        this.date = value;
    }

    /**
     * Gets the value of the nameAndSurname property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNameAndSurname() {
        return nameAndSurname;
    }

    /**
     * Sets the value of the nameAndSurname property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNameAndSurname(String value) {
        this.nameAndSurname = value;
    }

    /**
     * Gets the value of the addInfo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAddInfo() {
        return addInfo;
    }

    /**
     * Sets the value of the addInfo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAddInfo(String value) {
        this.addInfo = value;
    }

    /**
     * Gets the value of the dictation property.
     * 
     * @return
     *     possible object is
     *     {@link Dictation }
     *     
     */
    public Dictation getDictation() {
        return dictation;
    }

    /**
     * Sets the value of the dictation property.
     * 
     * @param value
     *     allowed object is
     *     {@link Dictation }
     *     
     */
    public void setDictation(Dictation value) {
        this.dictation = value;
    }

    /**
     * Gets the value of the polishWords property.
     * 
     * @return
     *     possible object is
     *     {@link PolishWords }
     *     
     */
    public PolishWords getPolishWords() {
        return polishWords;
    }

    /**
     * Sets the value of the polishWords property.
     * 
     * @param value
     *     allowed object is
     *     {@link PolishWords }
     *     
     */
    public void setPolishWords(PolishWords value) {
        this.polishWords = value;
    }

    /**
     * Gets the value of the engCategories property.
     * 
     * @return
     *     possible object is
     *     {@link EngCategories }
     *     
     */
    public EngCategories getEngCategories() {
        return engCategories;
    }

    /**
     * Sets the value of the engCategories property.
     * 
     * @param value
     *     allowed object is
     *     {@link EngCategories }
     *     
     */
    public void setEngCategories(EngCategories value) {
        this.engCategories = value;
    }

    /**
     * Gets the value of the engpolWords property.
     * 
     * @return
     *     possible object is
     *     {@link EngpolWords }
     *     
     */
    public EngpolWords getEngpolWords() {
        return engpolWords;
    }

    /**
     * Sets the value of the engpolWords property.
     * 
     * @param value
     *     allowed object is
     *     {@link EngpolWords }
     *     
     */
    public void setEngpolWords(EngpolWords value) {
        this.engpolWords = value;
    }

    /**
     * Gets the value of the mathTasks property.
     * 
     * @return
     *     possible object is
     *     {@link MathTasks }
     *     
     */
    public MathTasks getMathTasks() {
        return mathTasks;
    }

    /**
     * Sets the value of the mathTasks property.
     * 
     * @param value
     *     allowed object is
     *     {@link MathTasks }
     *     
     */
    public void setMathTasks(MathTasks value) {
        this.mathTasks = value;
    }

    /**
     * Gets the value of the grade property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGrade() {
        return grade;
    }

    /**
     * Sets the value of the grade property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGrade(String value) {
        this.grade = value;
    }

    /**
     * Gets the value of the category property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCategory() {
        return category;
    }

    /**
     * Sets the value of the category property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCategory(String value) {
        this.category = value;
    }

    /**
     * Gets the value of the sheetName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSheetName() {
        return sheetName;
    }

    /**
     * Sets the value of the sheetName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSheetName(String value) {
        this.sheetName = value;
    }

    /**
     * Gets the value of the ifDate property.
     * 
     */
    public boolean isIfDate() {
        return ifDate;
    }

    /**
     * Sets the value of the ifDate property.
     * 
     */
    public void setIfDate(boolean value) {
        this.ifDate = value;
    }

    /**
     * Gets the value of the ifName property.
     * 
     */
    public boolean isIfName() {
        return ifName;
    }

    /**
     * Sets the value of the ifName property.
     * 
     */
    public void setIfName(boolean value) {
        this.ifName = value;
    }

    /**
     * Gets the value of the ifGrade property.
     * 
     */
    public boolean isIfGrade() {
        return ifGrade;
    }

    /**
     * Sets the value of the ifGrade property.
     * 
     */
    public void setIfGrade(boolean value) {
        this.ifGrade = value;
    }

}
