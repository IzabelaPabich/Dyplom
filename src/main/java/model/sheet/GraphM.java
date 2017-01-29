
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
 *         &lt;element name="firstComp1" type="{http://example.org/moja}component"/>
 *         &lt;element name="firstOperation" type="{http://example.org/moja}graphMark"/>
 *         &lt;element name="firstComp2" type="{http://example.org/moja}component"/>
 *         &lt;element name="operation12" type="{http://example.org/moja}graphMark"/>
 *         &lt;element name="secondComp1" type="{http://example.org/moja}component"/>
 *         &lt;element name="secondOperation" type="{http://example.org/moja}graphMark"/>
 *         &lt;element name="secondComp2" type="{http://example.org/moja}component"/>
 *         &lt;element name="operation23" type="{http://example.org/moja}graphMark"/>
 *         &lt;element name="thirdComp1" type="{http://example.org/moja}component"/>
 *         &lt;element name="thirdOperation" type="{http://example.org/moja}graphMark"/>
 *         &lt;element name="thirdComp2" type="{http://example.org/moja}component"/>
 *         &lt;element name="operation31" type="{http://example.org/moja}graphMark"/>
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
    "operation12",
    "secondComp1",
    "secondOperation",
    "secondComp2",
    "operation23",
    "thirdComp1",
    "thirdOperation",
    "thirdComp2",
    "operation31"
})
@XmlRootElement(name = "graphM", namespace = "http://example.org/moja")
public class GraphM {

    @XmlElement(namespace = "http://example.org/moja")
    @XmlSchemaType(name = "nonNegativeInteger")
    protected int firstComp1;
    @XmlElement(namespace = "http://example.org/moja", required = true)
    protected GraphMark firstOperation;
    @XmlElement(namespace = "http://example.org/moja")
    @XmlSchemaType(name = "nonNegativeInteger")
    protected int firstComp2;
    @XmlElement(namespace = "http://example.org/moja", required = true)
    protected GraphMark operation12;
    @XmlElement(namespace = "http://example.org/moja")
    @XmlSchemaType(name = "nonNegativeInteger")
    protected int secondComp1;
    @XmlElement(namespace = "http://example.org/moja", required = true)
    protected GraphMark secondOperation;
    @XmlElement(namespace = "http://example.org/moja")
    @XmlSchemaType(name = "nonNegativeInteger")
    protected int secondComp2;
    @XmlElement(namespace = "http://example.org/moja", required = true)
    protected GraphMark operation23;
    @XmlElement(namespace = "http://example.org/moja")
    @XmlSchemaType(name = "nonNegativeInteger")
    protected int thirdComp1;
    @XmlElement(namespace = "http://example.org/moja", required = true)
    protected GraphMark thirdOperation;
    @XmlElement(namespace = "http://example.org/moja")
    @XmlSchemaType(name = "nonNegativeInteger")
    protected int thirdComp2;
    @XmlElement(namespace = "http://example.org/moja", required = true)
    protected GraphMark operation31;

    /**
     * Gets the value of the firstComp1 property.
     * 
     */
    public int getFirstComp1() {
        return firstComp1;
    }

    /**
     * Sets the value of the firstComp1 property.
     * 
     */
    public void setFirstComp1(int value) {
        this.firstComp1 = value;
    }

    /**
     * Gets the value of the firstOperation property.
     * 
     * @return
     *     possible object is
     *     {@link GraphMark }
     *     
     */
    public GraphMark getFirstOperation() {
        return firstOperation;
    }

    /**
     * Sets the value of the firstOperation property.
     * 
     * @param value
     *     allowed object is
     *     {@link GraphMark }
     *     
     */
    public void setFirstOperation(GraphMark value) {
        this.firstOperation = value;
    }

    /**
     * Gets the value of the firstComp2 property.
     * 
     */
    public int getFirstComp2() {
        return firstComp2;
    }

    /**
     * Sets the value of the firstComp2 property.
     * 
     */
    public void setFirstComp2(int value) {
        this.firstComp2 = value;
    }

    /**
     * Gets the value of the operation12 property.
     * 
     * @return
     *     possible object is
     *     {@link GraphMark }
     *     
     */
    public GraphMark getOperation12() {
        return operation12;
    }

    /**
     * Sets the value of the operation12 property.
     * 
     * @param value
     *     allowed object is
     *     {@link GraphMark }
     *     
     */
    public void setOperation12(GraphMark value) {
        this.operation12 = value;
    }

    /**
     * Gets the value of the secondComp1 property.
     * 
     */
    public int getSecondComp1() {
        return secondComp1;
    }

    /**
     * Sets the value of the secondComp1 property.
     * 
     */
    public void setSecondComp1(int value) {
        this.secondComp1 = value;
    }

    /**
     * Gets the value of the secondOperation property.
     * 
     * @return
     *     possible object is
     *     {@link GraphMark }
     *     
     */
    public GraphMark getSecondOperation() {
        return secondOperation;
    }

    /**
     * Sets the value of the secondOperation property.
     * 
     * @param value
     *     allowed object is
     *     {@link GraphMark }
     *     
     */
    public void setSecondOperation(GraphMark value) {
        this.secondOperation = value;
    }

    /**
     * Gets the value of the secondComp2 property.
     * 
     */
    public int getSecondComp2() {
        return secondComp2;
    }

    /**
     * Sets the value of the secondComp2 property.
     * 
     */
    public void setSecondComp2(int value) {
        this.secondComp2 = value;
    }

    /**
     * Gets the value of the operation23 property.
     * 
     * @return
     *     possible object is
     *     {@link GraphMark }
     *     
     */
    public GraphMark getOperation23() {
        return operation23;
    }

    /**
     * Sets the value of the operation23 property.
     * 
     * @param value
     *     allowed object is
     *     {@link GraphMark }
     *     
     */
    public void setOperation23(GraphMark value) {
        this.operation23 = value;
    }

    /**
     * Gets the value of the thirdComp1 property.
     * 
     */
    public int getThirdComp1() {
        return thirdComp1;
    }

    /**
     * Sets the value of the thirdComp1 property.
     * 
     */
    public void setThirdComp1(int value) {
        this.thirdComp1 = value;
    }

    /**
     * Gets the value of the thirdOperation property.
     * 
     * @return
     *     possible object is
     *     {@link GraphMark }
     *     
     */
    public GraphMark getThirdOperation() {
        return thirdOperation;
    }

    /**
     * Sets the value of the thirdOperation property.
     * 
     * @param value
     *     allowed object is
     *     {@link GraphMark }
     *     
     */
    public void setThirdOperation(GraphMark value) {
        this.thirdOperation = value;
    }

    /**
     * Gets the value of the thirdComp2 property.
     * 
     */
    public int getThirdComp2() {
        return thirdComp2;
    }

    /**
     * Sets the value of the thirdComp2 property.
     * 
     */
    public void setThirdComp2(int value) {
        this.thirdComp2 = value;
    }

    /**
     * Gets the value of the operation31 property.
     * 
     * @return
     *     possible object is
     *     {@link GraphMark }
     *     
     */
    public GraphMark getOperation31() {
        return operation31;
    }

    /**
     * Sets the value of the operation31 property.
     * 
     * @param value
     *     allowed object is
     *     {@link GraphMark }
     *     
     */
    public void setOperation31(GraphMark value) {
        this.operation31 = value;
    }

}
