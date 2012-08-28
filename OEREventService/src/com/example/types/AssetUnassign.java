
package com.example.types;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for AssetUnassign complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="AssetUnassign">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.bea.com/aler/events}ALERExtendedData">
 *       &lt;sequence>
 *         &lt;element name="unassignedTo" type="{http://www.bea.com/aler/events}User" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AssetUnassign", propOrder = {
    "unassignedTo"
})
public class AssetUnassign
    extends ALERExtendedData
{

    protected List<User> unassignedTo;

    /**
     * Gets the value of the unassignedTo property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the unassignedTo property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getUnassignedTo().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link User }
     * 
     * 
     */
    public List<User> getUnassignedTo() {
        if (unassignedTo == null) {
            unassignedTo = new ArrayList<User>();
        }
        return this.unassignedTo;
    }

}
