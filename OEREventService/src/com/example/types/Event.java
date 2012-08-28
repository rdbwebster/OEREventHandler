
package com.example.types;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Event complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Event">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="eventData" type="{http://www.bea.com/infra/events}EventInfo"/>
 *         &lt;element name="eventSource" type="{http://www.bea.com/infra/events}EventSource"/>
 *         &lt;element ref="{http://www.bea.com/infra/events}ExtendedData" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Event", namespace = "http://www.bea.com/infra/events", propOrder = {
    "eventData",
    "eventSource",
    "extendedData"
})
public class Event {

    @XmlElement(required = true)
    protected EventInfo eventData;
    @XmlElement(required = true)
    protected EventSource eventSource;
    @XmlElementRef(name = "ExtendedData", namespace = "http://www.bea.com/infra/events", type = JAXBElement.class)
    protected JAXBElement<? extends ExtendedData> extendedData;

    /**
     * Gets the value of the eventData property.
     * 
     * @return
     *     possible object is
     *     {@link EventInfo }
     *     
     */
    public EventInfo getEventData() {
        return eventData;
    }

    /**
     * Sets the value of the eventData property.
     * 
     * @param value
     *     allowed object is
     *     {@link EventInfo }
     *     
     */
    public void setEventData(EventInfo value) {
        this.eventData = value;
    }

    /**
     * Gets the value of the eventSource property.
     * 
     * @return
     *     possible object is
     *     {@link EventSource }
     *     
     */
    public EventSource getEventSource() {
        return eventSource;
    }

    /**
     * Sets the value of the eventSource property.
     * 
     * @param value
     *     allowed object is
     *     {@link EventSource }
     *     
     */
    public void setEventSource(EventSource value) {
        this.eventSource = value;
    }

    /**
     * Gets the value of the extendedData property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link AssetAccepted }{@code >}
     *     {@link JAXBElement }{@code <}{@link ExtractionStatusChanged }{@code >}
     *     {@link JAXBElement }{@code <}{@link AssetSubmission }{@code >}
     *     {@link JAXBElement }{@code <}{@link AssetAllTabApproved }{@code >}
     *     {@link JAXBElement }{@code <}{@link ExtractionIntialNotify }{@code >}
     *     {@link JAXBElement }{@code <}{@link AssetTabApproved }{@code >}
     *     {@link JAXBElement }{@code <}{@link ALERExtendedData }{@code >}
     *     {@link JAXBElement }{@code <}{@link AssetUnregistered }{@code >}
     *     {@link JAXBElement }{@code <}{@link AssetTabUnApprove }{@code >}
     *     {@link JAXBElement }{@code <}{@link NewVersionRegistered }{@code >}
     *     {@link JAXBElement }{@code <}{@link RelatedAssetRegister }{@code >}
     *     {@link JAXBElement }{@code <}{@link ComplianceTemplateApplied }{@code >}
     *     {@link JAXBElement }{@code <}{@link MetaDataChanged }{@code >}
     *     {@link JAXBElement }{@code <}{@link AssetRegistered }{@code >}
     *     {@link JAXBElement }{@code <}{@link AssetUnsubmitted }{@code >}
     *     {@link JAXBElement }{@code <}{@link ExtractionReassign }{@code >}
     *     {@link JAXBElement }{@code <}{@link AssetSubmissionRejected }{@code >}
     *     {@link JAXBElement }{@code <}{@link PolicyAssertionChanged }{@code >}
     *     {@link JAXBElement }{@code <}{@link AssetUnassign }{@code >}
     *     {@link JAXBElement }{@code <}{@link MultiUse }{@code >}
     *     {@link JAXBElement }{@code <}{@link AssetAssign }{@code >}
     *     {@link JAXBElement }{@code <}{@link RegistrationStatusChanged }{@code >}
     *     {@link JAXBElement }{@code <}{@link ExtendedData }{@code >}
     *     {@link JAXBElement }{@code <}{@link AssetStatusChanged }{@code >}
     *     {@link JAXBElement }{@code <}{@link ExtractionSubsequentNotify }{@code >}
     *     {@link JAXBElement }{@code <}{@link CategorizationStatusChanged }{@code >}
     *     {@link JAXBElement }{@code <}{@link AssetUnaccepted }{@code >}
     *     
     */
    public JAXBElement<? extends ExtendedData> getExtendedData() {
        return extendedData;
    }

    /**
     * Sets the value of the extendedData property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link AssetAccepted }{@code >}
     *     {@link JAXBElement }{@code <}{@link ExtractionStatusChanged }{@code >}
     *     {@link JAXBElement }{@code <}{@link AssetSubmission }{@code >}
     *     {@link JAXBElement }{@code <}{@link AssetAllTabApproved }{@code >}
     *     {@link JAXBElement }{@code <}{@link ExtractionIntialNotify }{@code >}
     *     {@link JAXBElement }{@code <}{@link AssetTabApproved }{@code >}
     *     {@link JAXBElement }{@code <}{@link ALERExtendedData }{@code >}
     *     {@link JAXBElement }{@code <}{@link AssetUnregistered }{@code >}
     *     {@link JAXBElement }{@code <}{@link AssetTabUnApprove }{@code >}
     *     {@link JAXBElement }{@code <}{@link NewVersionRegistered }{@code >}
     *     {@link JAXBElement }{@code <}{@link RelatedAssetRegister }{@code >}
     *     {@link JAXBElement }{@code <}{@link ComplianceTemplateApplied }{@code >}
     *     {@link JAXBElement }{@code <}{@link MetaDataChanged }{@code >}
     *     {@link JAXBElement }{@code <}{@link AssetRegistered }{@code >}
     *     {@link JAXBElement }{@code <}{@link AssetUnsubmitted }{@code >}
     *     {@link JAXBElement }{@code <}{@link ExtractionReassign }{@code >}
     *     {@link JAXBElement }{@code <}{@link AssetSubmissionRejected }{@code >}
     *     {@link JAXBElement }{@code <}{@link PolicyAssertionChanged }{@code >}
     *     {@link JAXBElement }{@code <}{@link AssetUnassign }{@code >}
     *     {@link JAXBElement }{@code <}{@link MultiUse }{@code >}
     *     {@link JAXBElement }{@code <}{@link AssetAssign }{@code >}
     *     {@link JAXBElement }{@code <}{@link RegistrationStatusChanged }{@code >}
     *     {@link JAXBElement }{@code <}{@link ExtendedData }{@code >}
     *     {@link JAXBElement }{@code <}{@link AssetStatusChanged }{@code >}
     *     {@link JAXBElement }{@code <}{@link ExtractionSubsequentNotify }{@code >}
     *     {@link JAXBElement }{@code <}{@link CategorizationStatusChanged }{@code >}
     *     {@link JAXBElement }{@code <}{@link AssetUnaccepted }{@code >}
     *     
     */
    public void setExtendedData(JAXBElement<? extends ExtendedData> value) {
        this.extendedData = ((JAXBElement<? extends ExtendedData> ) value);
    }

}
