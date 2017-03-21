
package model.sheet;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * <p>
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="firstComp" type="{http://example.org/moja}component"/>
 *         &lt;element name="operation12" type="{http://example.org/moja}graphMark"/>
 *         &lt;element name="secondComp" type="{http://example.org/moja}component"/>
 *         &lt;element name="operation23" type="{http://example.org/moja}graphMark"/>
 *         &lt;element name="thirdComp" type="{http://example.org/moja}component"/>
 *         &lt;element name="operation31" type="{http://example.org/moja}graphMark"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
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

    @XmlElement(namespace = "http://example.org/moja", required = true)
    protected String firstComp;
    @XmlElement(namespace = "http://example.org/moja", required = true)
    protected GraphMark operation12;
    @XmlElement(namespace = "http://example.org/moja", required = true)
    protected String secondComp;
    @XmlElement(namespace = "http://example.org/moja", required = true)
    protected GraphMark operation23;
    @XmlElement(namespace = "http://example.org/moja", required = true)
    protected String thirdComp;
    @XmlElement(namespace = "http://example.org/moja", required = true)
    protected GraphMark operation31;


    /**
     * Gets the value of the firstComp property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getFirstComp() {
        return firstComp;
    }

    /**
     * Sets the value of the firstComp property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setFirstComp(String value) {
        this.firstComp = value;
    }

    /**
     * Gets the value of the operation12 property.
     *
     * @return possible object is
     * {@link GraphMark }
     */
    public GraphMark getOperation12() {
        return operation12;
    }

    /**
     * Sets the value of the operation12 property.
     *
     * @param value allowed object is
     *              {@link GraphMark }
     */
    public void setOperation12(GraphMark value) {
        this.operation12 = value;
    }

    /**
     * Gets the value of the secondComp property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getSecondComp() {
        return secondComp;
    }

    /**
     * Sets the value of the secondComp property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setSecondComp(String value) {
        this.secondComp = value;
    }

    /**
     * Gets the value of the operation23 property.
     *
     * @return possible object is
     * {@link GraphMark }
     */
    public GraphMark getOperation23() {
        return operation23;
    }

    /**
     * Sets the value of the operation23 property.
     *
     * @param value allowed object is
     *              {@link GraphMark }
     */
    public void setOperation23(GraphMark value) {
        this.operation23 = value;
    }

    /**
     * Gets the value of the thirdComp property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getThirdComp() {
        return thirdComp;
    }

    /**
     * Sets the value of the thirdComp property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setThirdComp(String value) {
        this.thirdComp = value;
    }

    /**
     * Gets the value of the operation31 property.
     *
     * @return possible object is
     * {@link GraphMark }
     */
    public GraphMark getOperation31() {
        return operation31;
    }

    /**
     * Sets the value of the operation31 property.
     *
     * @param value allowed object is
     *              {@link GraphMark }
     */
    public void setOperation31(GraphMark value) {
        this.operation31 = value;
    }

    public String getOperation12String() {
        return operation12.toString();
    }

    public String getOperation23String() {
        return operation23.toString();
    }

    public String getOperation31String() {
        return operation31.toString();
    }

    public String toString() {
        return firstComp + " " + operation12 + " " + secondComp + " " + operation23 + " " + thirdComp + " " + operation31;
    }

    @Override
    public boolean equals(Object v) {
        boolean retVal = false;

        if (v instanceof Graph) {
            Graph toCheck = (Graph) v;
            if (this.getFirstComp().compareTo(toCheck.getFirstComp()) == 0
                && this.getOperation12String().compareTo(toCheck.getOperation12String()) == 0
                && this.getSecondComp().compareTo(toCheck.getSecondComp()) == 0
                && this.getOperation23String().compareTo(toCheck.getOperation23String()) == 0
                && this.getThirdComp().compareTo(toCheck.getThirdComp()) == 0
                && this.getOperation31String().compareTo(toCheck.getOperation31String()) == 0) {
                retVal = true;
            }
        }

        return retVal;
    }
}
