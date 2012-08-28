
package com.example.types;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ExtractionIntialNotify complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ExtractionIntialNotify">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.bea.com/infra/events}ExtendedData">
 *       &lt;sequence>
 *         &lt;element name="extraction" type="{http://www.bea.com/aler/events}Extraction"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ExtractionIntialNotify", propOrder = {
    "extraction"
})
public class ExtractionIntialNotify
    extends ExtendedData
{

    @XmlElement(required = true)
    protected Extraction extraction;

    /**
     * Gets the value of the extraction property.
     * 
     * @return
     *     possible object is
     *     {@link Extraction }
     *     
     */
    public Extraction getExtraction() {
        return extraction;
    }

    /**
     * Sets the value of the extraction property.
     * 
     * @param value
     *     allowed object is
     *     {@link Extraction }
     *     
     */
    public void setExtraction(Extraction value) {
        this.extraction = value;
    }

}
