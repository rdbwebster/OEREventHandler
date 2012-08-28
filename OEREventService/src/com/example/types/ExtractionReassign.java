
package com.example.types;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ExtractionReassign complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ExtractionReassign">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.bea.com/infra/events}ExtendedData">
 *       &lt;sequence>
 *         &lt;element name="extraction" type="{http://www.bea.com/aler/events}Extraction"/>
 *         &lt;element name="reassignedTo" type="{http://www.bea.com/aler/events}User"/>
 *         &lt;element name="invokedBy" type="{http://www.bea.com/aler/events}User"/>
 *         &lt;element name="comment" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ExtractionReassign", propOrder = {
    "extraction",
    "reassignedTo",
    "invokedBy",
    "comment"
})
public class ExtractionReassign
    extends ExtendedData
{

    @XmlElement(required = true)
    protected Extraction extraction;
    @XmlElement(required = true)
    protected User reassignedTo;
    @XmlElement(required = true)
    protected User invokedBy;
    @XmlElement(required = true)
    protected String comment;

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

    /**
     * Gets the value of the reassignedTo property.
     * 
     * @return
     *     possible object is
     *     {@link User }
     *     
     */
    public User getReassignedTo() {
        return reassignedTo;
    }

    /**
     * Sets the value of the reassignedTo property.
     * 
     * @param value
     *     allowed object is
     *     {@link User }
     *     
     */
    public void setReassignedTo(User value) {
        this.reassignedTo = value;
    }

    /**
     * Gets the value of the invokedBy property.
     * 
     * @return
     *     possible object is
     *     {@link User }
     *     
     */
    public User getInvokedBy() {
        return invokedBy;
    }

    /**
     * Sets the value of the invokedBy property.
     * 
     * @param value
     *     allowed object is
     *     {@link User }
     *     
     */
    public void setInvokedBy(User value) {
        this.invokedBy = value;
    }

    /**
     * Gets the value of the comment property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getComment() {
        return comment;
    }

    /**
     * Sets the value of the comment property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setComment(String value) {
        this.comment = value;
    }

}
