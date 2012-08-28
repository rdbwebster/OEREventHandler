
package com.example.types;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for NewVersionRegistered complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="NewVersionRegistered">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.bea.com/aler/events}ALERExtendedData">
 *       &lt;sequence>
 *         &lt;element name="newAsset" type="{http://www.bea.com/aler/events}Asset"/>
 *         &lt;element name="comment" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "NewVersionRegistered", propOrder = {
    "newAsset",
    "comment"
})
public class NewVersionRegistered
    extends ALERExtendedData
{

    @XmlElement(required = true)
    protected Asset newAsset;
    protected String comment;

    /**
     * Gets the value of the newAsset property.
     * 
     * @return
     *     possible object is
     *     {@link Asset }
     *     
     */
    public Asset getNewAsset() {
        return newAsset;
    }

    /**
     * Sets the value of the newAsset property.
     * 
     * @param value
     *     allowed object is
     *     {@link Asset }
     *     
     */
    public void setNewAsset(Asset value) {
        this.newAsset = value;
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
