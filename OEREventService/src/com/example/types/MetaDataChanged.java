
package com.example.types;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for MetaDataChanged complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="MetaDataChanged">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.bea.com/aler/events}MultiUse">
 *       &lt;sequence>
 *         &lt;element name="submittedBy" type="{http://www.bea.com/aler/events}User" minOccurs="0"/>
 *         &lt;element name="subscribedBy" type="{http://www.bea.com/aler/events}User" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "MetaDataChanged", propOrder = {
    "submittedBy",
    "subscribedBy"
})
public class MetaDataChanged
    extends MultiUse
{

    protected User submittedBy;
    protected List<User> subscribedBy;

    /**
     * Gets the value of the submittedBy property.
     * 
     * @return
     *     possible object is
     *     {@link User }
     *     
     */
    public User getSubmittedBy() {
        return submittedBy;
    }

    /**
     * Sets the value of the submittedBy property.
     * 
     * @param value
     *     allowed object is
     *     {@link User }
     *     
     */
    public void setSubmittedBy(User value) {
        this.submittedBy = value;
    }

    /**
     * Gets the value of the subscribedBy property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the subscribedBy property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSubscribedBy().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link User }
     * 
     * 
     */
    public List<User> getSubscribedBy() {
        if (subscribedBy == null) {
            subscribedBy = new ArrayList<User>();
        }
        return this.subscribedBy;
    }

}
