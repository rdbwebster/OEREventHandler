
package com.example.types;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for AssetTabUnApprove complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="AssetTabUnApprove">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.bea.com/aler/events}ALERExtendedData">
 *       &lt;sequence>
 *         &lt;element name="tabName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="comment" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="assignedTo" type="{http://www.bea.com/aler/events}User" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AssetTabUnApprove", propOrder = {
    "tabName",
    "comment",
    "assignedTo"
})
public class AssetTabUnApprove
    extends ALERExtendedData
{

    protected String tabName;
    protected String comment;
    protected List<User> assignedTo;

    /**
     * Gets the value of the tabName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTabName() {
        return tabName;
    }

    /**
     * Sets the value of the tabName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTabName(String value) {
        this.tabName = value;
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

    /**
     * Gets the value of the assignedTo property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the assignedTo property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAssignedTo().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link User }
     * 
     * 
     */
    public List<User> getAssignedTo() {
        if (assignedTo == null) {
            assignedTo = new ArrayList<User>();
        }
        return this.assignedTo;
    }

}
