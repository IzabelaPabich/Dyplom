
package model.sheet;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
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
 *         &lt;element name="firstComp1" type="{http://example.org/moja}component"/>
 *         &lt;element name="firstOperation" type="{http://example.org/moja}mark"/>
 *         &lt;element name="firstComp2" type="{http://example.org/moja}component"/>
 *         &lt;element name="equationMark" type="{http://example.org/moja}mark"/>
 *         &lt;element name="secondComp1" type="{http://example.org/moja}component"/>
 *         &lt;element name="secondOperation" type="{http://example.org/moja}mark"/>
 *         &lt;element name="secondComp2" type="{http://example.org/moja}component"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "firstComp1",
    "firstOperation",
    "firstComp2",
    "equationMark",
    "secondComp1",
    "secondOperation",
    "secondComp2"
})
@XmlRootElement(name = "equationM", namespace = "http://example.org/moja")
public class EquationM {

    @XmlElement(namespace = "http://example.org/moja", required = true)
    protected String firstComp1;
    @XmlElement(namespace = "http://example.org/moja", required = true)
    protected String firstOperation;
    @XmlElement(namespace = "http://example.org/moja", required = true)
    protected String firstComp2;
    @XmlElement(namespace = "http://example.org/moja", required = true)
    protected String equationMark;
    @XmlElement(namespace = "http://example.org/moja", required = true)
    protected String secondComp1;
    @XmlElement(namespace = "http://example.org/moja", required = true)
    protected String secondOperation;
    @XmlElement(namespace = "http://example.org/moja", required = true)
    protected String secondComp2;

    /**
     * Gets the value of the firstComp1 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFirstComp1() {
        return firstComp1;
    }

    /**
     * Sets the value of the firstComp1 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFirstComp1(String value) {
        this.firstComp1 = value;
    }

    /**
     * Gets the value of the firstOperation property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFirstOperation() {
        return firstOperation;
    }

    /**
     * Sets the value of the firstOperation property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFirstOperation(String value) {
        this.firstOperation = value;
    }

    /**
     * Gets the value of the firstComp2 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFirstComp2() {
        return firstComp2;
    }

    /**
     * Sets the value of the firstComp2 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFirstComp2(String value) {
        this.firstComp2 = value;
    }

    /**
     * Gets the value of the equationMark property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEquationMark() {
        return equationMark;
    }

    /**
     * Sets the value of the equationMark property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEquationMark(String value) {
        this.equationMark = value;
    }

    /**
     * Gets the value of the secondComp1 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSecondComp1() {
        return secondComp1;
    }

    /**
     * Sets the value of the secondComp1 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSecondComp1(String value) {
        this.secondComp1 = value;
    }

    /**
     * Gets the value of the secondOperation property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSecondOperation() {
        return secondOperation;
    }

    /**
     * Sets the value of the secondOperation property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSecondOperation(String value) {
        this.secondOperation = value;
    }

    /**
     * Gets the value of the secondComp2 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSecondComp2() {
        return secondComp2;
    }

    /**
     * Sets the value of the secondComp2 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSecondComp2(String value) {
        this.secondComp2 = value;
    }

}
