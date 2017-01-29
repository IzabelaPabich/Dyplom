
package model.sheet;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
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
 *         &lt;element name="firstComp" type="{http://example.org/moja}component"/>
 *         &lt;element name="operation12" type="{http://example.org/moja}mark"/>
 *         &lt;element name="secondComp" type="{http://example.org/moja}component"/>
 *         &lt;element name="operation23" type="{http://example.org/moja}mark"/>
 *         &lt;element name="thirdComp" type="{http://example.org/moja}component"/>
 *         &lt;element name="operation31" type="{http://example.org/moja}mark"/>
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
    "firstComp",
    "operation12",
    "secondComp",
    "operation23",
    "thirdComp",
    "operation31"
})
@XmlRootElement(name = "graph", namespace = "http://example.org/moja")
public class Graph {

    @XmlElement(namespace = "http://example.org/moja")
    @XmlSchemaType(name = "nonNegativeInteger")
    protected int firstComp;
    @XmlElement(namespace = "http://example.org/moja", required = true)
    protected String operation12;
    @XmlElement(namespace = "http://example.org/moja")
    @XmlSchemaType(name = "nonNegativeInteger")
    protected int secondComp;
    @XmlElement(namespace = "http://example.org/moja", required = true)
    protected String operation23;
    @XmlElement(namespace = "http://example.org/moja")
    @XmlSchemaType(name = "nonNegativeInteger")
    protected int thirdComp;
    @XmlElement(namespace = "http://example.org/moja", required = true)
    protected String operation31;

    /**
     * Gets the value of the firstComp property.
     * 
     */
    public int getFirstComp() {
        return firstComp;
    }

    /**
     * Sets the value of the firstComp property.
     * 
     */
    public void setFirstComp(int value) {
        this.firstComp = value;
    }

    /**
     * Gets the value of the operation12 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOperation12() {
        return operation12;
    }

    /**
     * Sets the value of the operation12 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOperation12(String value) {
        this.operation12 = value;
    }

    /**
     * Gets the value of the secondComp property.
     * 
     */
    public int getSecondComp() {
        return secondComp;
    }

    /**
     * Sets the value of the secondComp property.
     * 
     */
    public void setSecondComp(int value) {
        this.secondComp = value;
    }

    /**
     * Gets the value of the operation23 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOperation23() {
        return operation23;
    }

    /**
     * Sets the value of the operation23 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOperation23(String value) {
        this.operation23 = value;
    }

    /**
     * Gets the value of the thirdComp property.
     * 
     */
    public int getThirdComp() {
        return thirdComp;
    }

    /**
     * Sets the value of the thirdComp property.
     * 
     */
    public void setThirdComp(int value) {
        this.thirdComp = value;
    }

    /**
     * Gets the value of the operation31 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOperation31() {
        return operation31;
    }

    /**
     * Sets the value of the operation31 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOperation31(String value) {
        this.operation31 = value;
    }

}
