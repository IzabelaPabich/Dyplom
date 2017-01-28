
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
 *         &lt;element name="engCategory" type="{http://example.org/moja}engCategory"/>
 *         &lt;element name="engWord" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="polWord" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="imagePath" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *       &lt;attribute name="ifToPolish" use="required">
 *         &lt;simpleType>
 *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *             &lt;maxLength value="1"/>
 *           &lt;/restriction>
 *         &lt;/simpleType>
 *       &lt;/attribute>
 *       &lt;attribute name="ifToEnglish" use="required">
 *         &lt;simpleType>
 *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *             &lt;maxLength value="1"/>
 *           &lt;/restriction>
 *         &lt;/simpleType>
 *       &lt;/attribute>
 *       &lt;attribute name="ifImage" use="required" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "engCategory",
    "engWord",
    "polWord",
    "imagePath"
})
@XmlRootElement(name = "engpolWord", namespace = "http://example.org/moja")
public class EngpolWord {

    @XmlElement(namespace = "http://example.org/moja", required = true)
    protected String engCategory;
    @XmlElement(namespace = "http://example.org/moja", required = true)
    protected String engWord;
    @XmlElement(namespace = "http://example.org/moja", required = true)
    protected String polWord;
    @XmlElement(namespace = "http://example.org/moja", required = true)
    protected String imagePath;
    @XmlAttribute(name = "ifToPolish", required = true)
    protected String ifToPolish;
    @XmlAttribute(name = "ifToEnglish", required = true)
    protected String ifToEnglish;
    @XmlAttribute(name = "ifImage", required = true)
    protected boolean ifImage;

    /**
     * Gets the value of the engCategory property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEngCategory() {
        return engCategory;
    }

    /**
     * Sets the value of the engCategory property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEngCategory(String value) {
        this.engCategory = value;
    }

    /**
     * Gets the value of the engWord property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEngWord() {
        return engWord;
    }

    /**
     * Sets the value of the engWord property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEngWord(String value) {
        this.engWord = value;
    }

    /**
     * Gets the value of the polWord property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPolWord() {
        return polWord;
    }

    /**
     * Sets the value of the polWord property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPolWord(String value) {
        this.polWord = value;
    }

    /**
     * Gets the value of the imagePath property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getImagePath() {
        return imagePath;
    }

    /**
     * Sets the value of the imagePath property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setImagePath(String value) {
        this.imagePath = value;
    }

    /**
     * Gets the value of the ifToPolish property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIfToPolish() {
        return ifToPolish;
    }

    /**
     * Sets the value of the ifToPolish property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIfToPolish(String value) {
        this.ifToPolish = value;
    }

    /**
     * Gets the value of the ifToEnglish property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIfToEnglish() {
        return ifToEnglish;
    }

    /**
     * Sets the value of the ifToEnglish property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIfToEnglish(String value) {
        this.ifToEnglish = value;
    }

    /**
     * Gets the value of the ifImage property.
     * 
     */
    public boolean isIfImage() {
        return ifImage;
    }

    /**
     * Sets the value of the ifImage property.
     * 
     */
    public void setIfImage(boolean value) {
        this.ifImage = value;
    }

    @Override
    public boolean equals(Object v) {
        boolean retVal = false;

        if(v instanceof EngpolWord) {
            EngpolWord toCheck = (EngpolWord) v;
            if(this.getPolWord().compareTo(toCheck.getPolWord()) == 0
                    && this.getEngWord().compareTo(toCheck.getEngWord()) == 0
                    && this.getEngCategory().compareTo(toCheck.engCategory) == 0) {
                retVal = true;
            }
        }

        return retVal;
    }

}
