
package com.example.types;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for AssetAssign complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="AssetAssign">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.bea.com/aler/events}ALERExtendedData">
 *       &lt;sequence>
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
@XmlType(name = "AssetAssign", propOrder = {
    "assignedTo"
})
public class AssetAssign
    extends ALERExtendedData
{

    protected List<User> assignedTo;

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
