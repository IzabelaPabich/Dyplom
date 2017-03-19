
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
 *         &lt;element name="firstComp" type="{http://example.org/moja}component"/>
 *         &lt;element name="operation" type="{http://example.org/moja}mark"/>
 *         &lt;element name="secondComp" type="{http://example.org/moja}component"/>
 *         &lt;element name="equationMark" type="{http://example.org/moja}mark"/>
 *         &lt;element name="result" type="{http://example.org/moja}component"/>
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
    "operation",
    "secondComp",
    "equationMark",
    "result"
})
@XmlRootElement(name = "equation", namespace = "http://example.org/moja")
public class Equation {

    @XmlElement(namespace = "http://example.org/moja", required = true)
    protected String firstComp;
    @XmlElement(namespace = "http://example.org/moja", required = true)
    protected String operation;
    @XmlElement(namespace = "http://example.org/moja", required = true)
    protected String secondComp;
    @XmlElement(namespace = "http://example.org/moja", required = true)
    protected String equationMark;
    @XmlElement(namespace = "http://example.org/moja", required = true)
    protected String result;

    /**
     * Gets the value of the firstComp property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFirstComp() {
        return firstComp;
    }

    /**
     * Sets the value of the firstComp property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFirstComp(String value) {
        this.firstComp = value;
    }

    /**
     * Gets the value of the operation property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOperation() {
        return operation;
    }

    /**
     * Sets the value of the operation property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOperation(String value) {
        this.operation = value;
    }

    /**
     * Gets the value of the secondComp property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSecondComp() {
        return secondComp;
    }

    /**
     * Sets the value of the secondComp property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSecondComp(String value) {
        this.secondComp = value;
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
     * Gets the value of the result property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getResult() {
        return result;
    }

    /**
     * Sets the value of the result property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setResult(String value) {
        this.result = value;
    }

    public String toString() {
        return firstComp + " " + operation + " " + secondComp + " " + equationMark + " " + result;
    }

    @Override
    public boolean equals(Object v) {
        boolean retVal = false;

        if(v instanceof Equation) {
            Equation toCheck = (Equation) v;
            if(this.getFirstComp().compareTo(toCheck.getFirstComp()) == 0
                && this.getOperation().compareTo(toCheck.getOperation()) == 0
                && this.getSecondComp().compareTo(toCheck.getSecondComp()) == 0
                && this.getEquationMark().compareTo(toCheck.getEquationMark()) == 0
                && this.getResult().compareTo(toCheck.getResult()) == 0){
                retVal = true;
            }
        }

        return retVal;
    }

}
