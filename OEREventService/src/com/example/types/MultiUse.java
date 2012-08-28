
package com.example.types;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for MultiUse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="MultiUse">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.bea.com/infra/events}ExtendedData">
 *       &lt;sequence>
 *         &lt;element name="asset" type="{http://www.bea.com/aler/events}Asset"/>
 *         &lt;element name="type" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
@XmlType(name = "MultiUse", propOrder = {
    "asset",
    "type",
    "parameters"
})
@XmlSeeAlso({
    RegistrationStatusChanged.class,
    PolicyAssertionChanged.class,
    MetaDataChanged.class,
    CategorizationStatusChanged.class
})
public class MultiUse
    extends ExtendedData
{

    @XmlElement(required = true)
    protected Asset asset;
    @XmlElement(required = true)
    protected String type;
    @XmlElement(required = true)
    protected List<MultiUseParam> parameters;

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
     * Gets the value of the type property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getType() {
        return type;
    }

    /**
     * Sets the value of the type property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setType(String value) {
        this.type = value;
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
