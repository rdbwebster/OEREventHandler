
package com.example.types;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for AssetStatusChanged complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="AssetStatusChanged">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.bea.com/aler/events}ALERExtendedData">
 *       &lt;sequence>
 *         &lt;element name="comment" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="submittedBy" type="{http://www.bea.com/aler/events}User"/>
 *         &lt;element name="parameters" type="{http://www.bea.com/aler/events}MultiUseParam" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AssetStatusChanged", propOrder = {
    "comment",
    "submittedBy",
    "parameters"
})
public class AssetStatusChanged
    extends ALERExtendedData
{

    protected String comment;
    @XmlElement(required = true)
    protected User submittedBy;
    @XmlElement(required = true)
    protected List<MultiUseParam> parameters;

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
     * Gets the value of the parameters property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the parameters property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getParameters().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link MultiUseParam }
     * 
     * 
     */
    public List<MultiUseParam> getParameters() {
        if (parameters == null) {
            parameters = new ArrayList<MultiUseParam>();
        }
        return this.parameters;
    }

}
