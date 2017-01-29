
package model.sheet;

import java.util.ArrayList;
import java.util.List;
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
 *         &lt;element ref="{http://example.org/moja}polishWord" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="columns" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="numberOfWords" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "polishWord"
})
@XmlRootElement(name = "polishWords", namespace = "http://example.org/moja")
public class PolishWords {

    @XmlElement(namespace = "http://example.org/moja")
    protected List<PolishWord> polishWord;
    @XmlAttribute(name = "columns")
    protected String columns;
    @XmlAttribute(name = "numberOfWords")
    protected String numberOfWords;

    /**
     * Gets the value of the polishWord property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the polishWord property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPolishWord().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PolishWord }
     * 
     * 
     */
    public List<PolishWord> getPolishWord() {
        if (polishWord == null) {
            polishWord = new ArrayList<PolishWord>();
        }
        return this.polishWord;
    }

    /**
     * Gets the value of the columns property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getColumns() {
        return columns;
    }

    /**
     * Sets the value of the columns property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setColumns(String value) {
        this.columns = value;
    }

    /**
     * Gets the value of the numberOfWords property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNumberOfWords() {
        return numberOfWords;
    }

    /**
     * Sets the value of the numberOfWords property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNumberOfWords(String value) {
        this.numberOfWords = value;
    }

}
