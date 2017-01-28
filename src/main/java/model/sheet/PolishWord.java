
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
 *         &lt;element name="letter" type="{http://example.org/moja}letterType"/>
 *         &lt;element name="word" type="{http://example.org/moja}polishWordType"/>
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
    "letter",
    "word"
})
@XmlRootElement(name = "polishWord", namespace = "http://example.org/moja")
public class PolishWord {

    @XmlElement(namespace = "http://example.org/moja", required = true)
    protected String letter;
    @XmlElement(namespace = "http://example.org/moja", required = true)
    protected String word;

    /**
     * Gets the value of the letter property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLetter() {
        return letter;
    }

    /**
     * Sets the value of the letter property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLetter(String value) {
        this.letter = value;
    }

    /**
     * Gets the value of the word property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getWord() {
        return word;
    }

    /**
     * Sets the value of the word property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setWord(String value) {
        this.word = value;
    }

    @Override
    public boolean equals(Object v) {
        boolean retVal = false;

        if(v instanceof PolishWord) {
            PolishWord toCheck = (PolishWord) v;
            if(this.getLetter().compareTo(toCheck.getLetter()) == 0
                    && this.getWord().compareTo(toCheck.getWord()) == 0) {
                retVal = true;
            }
        }

        return retVal;
    }


}
