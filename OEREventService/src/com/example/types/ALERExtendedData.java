
package com.example.types;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ALERExtendedData complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ALERExtendedData">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.bea.com/infra/events}ExtendedData">
 *       &lt;sequence>
 *         &lt;element name="asset" type="{http://www.bea.com/aler/events}Asset"/>
 *         &lt;element name="registrarEmail" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="updatedBy" type="{http://www.bea.com/aler/events}User"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ALERExtendedData", propOrder = {
    "asset",
    "registrarEmail",
    "updatedBy"
})
@XmlSeeAlso({
    AssetAllTabApproved.class,
    AssetTabApproved.class,
    ComplianceTemplateApplied.class,
    AssetUnassign.class,
    AssetStatusChanged.class,
    AssetAssign.class,
    AssetRegistered.class,
    AssetUnsubmitted.class,
    AssetSubmissionRejected.class,
    NewVersionRegistered.class,
    AssetUnaccepted.class,
    AssetTabUnApprove.class,
    AssetAccepted.class
})
public class ALERExtendedData
    extends ExtendedData
{

    @XmlElement(required = true)
    protected Asset asset;
    protected String registrarEmail;
    @XmlElement(required = true)
    protected User updatedBy;

    /**
     * Gets the value of the asset property.
     * 
     * @return
     *     possible object is
     *     {@link Asset }
     *     
     */
    public Asset getAsset() {
        return asset;
    }

    /**
     * Sets the value of the asset property.
     * 
     * @param value
     *     allowed object is
     *     {@link Asset }
     *     
     */
    public void setAsset(Asset value) {
        this.asset = value;
    }

    /**
     * Gets the value of the registrarEmail property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRegistrarEmail() {
        return registrarEmail;
    }

    /**
     * Sets the value of the registrarEmail property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRegistrarEmail(String value) {
        this.registrarEmail = value;
    }

    /**
     * Gets the value of the updatedBy property.
     * 
     * @return
     *     possible object is
     *     {@link User }
     *     
     */
    public User getUpdatedBy() {
        return updatedBy;
    }

    /**
     * Sets the value of the updatedBy property.
     * 
     * @param value
     *     allowed object is
     *     {@link User }
     *     
     */
    public void setUpdatedBy(User value) {
        this.updatedBy = value;
    }

}
