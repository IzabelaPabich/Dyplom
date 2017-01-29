
package model.sheet;

import java.util.ArrayList;
import java.util.List;
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
 *         &lt;element ref="{http://example.org/moja}equation" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element ref="{http://example.org/moja}equationM" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element ref="{http://example.org/moja}graph" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element ref="{http://example.org/moja}graphM" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element ref="{http://example.org/moja}textTask" maxOccurs="unbounded" minOccurs="0"/>
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
    "equation",
    "equationM",
    "graph",
    "graphM",
    "textTask"
})
@XmlRootElement(name = "mathTasks", namespace = "http://example.org/moja")
public class MathTasks {

    @XmlElement(namespace = "http://example.org/moja")
    protected List<Equation> equation;
    @XmlElement(namespace = "http://example.org/moja")
    protected List<EquationM> equationM;
    @XmlElement(namespace = "http://example.org/moja")
    protected List<Graph> graph;
    @XmlElement(namespace = "http://example.org/moja")
    protected List<GraphM> graphM;
    @XmlElement(namespace = "http://example.org/moja")
    protected List<TextTask> textTask;

    /**
     * Gets the value of the equation property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the equation property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getEquation().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Equation }
     * 
     * 
     */
    public List<Equation> getEquation() {
        if (equation == null) {
            equation = new ArrayList<Equation>();
        }
        return this.equation;
    }

    /**
     * Gets the value of the equationM property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the equationM property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getEquationM().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link EquationM }
     * 
     * 
     */
    public List<EquationM> getEquationM() {
        if (equationM == null) {
            equationM = new ArrayList<EquationM>();
        }
        return this.equationM;
    }

    /**
     * Gets the value of the graph property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the graph property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getGraph().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Graph }
     * 
     * 
     */
    public List<Graph> getGraph() {
        if (graph == null) {
            graph = new ArrayList<Graph>();
        }
        return this.graph;
    }

    /**
     * Gets the value of the graphM property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the graphM property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getGraphM().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link GraphM }
     * 
     * 
     */
    public List<GraphM> getGraphM() {
        if (graphM == null) {
            graphM = new ArrayList<GraphM>();
        }
        return this.graphM;
    }

    /**
     * Gets the value of the textTask property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the textTask property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getTextTask().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link TextTask }
     * 
     * 
     */
    public List<TextTask> getTextTask() {
        if (textTask == null) {
            textTask = new ArrayList<TextTask>();
        }
        return this.textTask;
    }

}
