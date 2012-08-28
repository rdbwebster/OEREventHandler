
package com.example.types;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ComplianceTemplateApplied complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ComplianceTemplateApplied">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.bea.com/aler/events}ALERExtendedData">
 *       &lt;sequence>
 *         &lt;element name="projectId" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="projectLeaders" type="{http://www.bea.com/aler/events}User" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ComplianceTemplateApplied", propOrder = {
    "projectId",
    "projectLeaders"
})
public class ComplianceTemplateApplied
    extends ALERExtendedData
{

    protected Long projectId;
    protected List<User> projectLeaders;

    /**
     * Gets the value of the projectId property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getProjectId() {
        return projectId;
    }

    /**
     * Sets the value of the projectId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setProjectId(Long value) {
        this.projectId = value;
    }

    /**
     * Gets the value of the projectLeaders property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the projectLeaders property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getProjectLeaders().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link User }
     * 
     * 
     */
    public List<User> getProjectLeaders() {
        if (projectLeaders == null) {
            projectLeaders = new ArrayList<User>();
        }
        return this.projectLeaders;
    }

}
