
package com.example.types;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for RelatedAssetRegister complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="RelatedAssetRegister">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.bea.com/infra/events}ExtendedData">
 *       &lt;sequence>
 *         &lt;element name="originalAsset" type="{http://www.bea.com/aler/events}Asset"/>
 *         &lt;element name="relatedAsset" type="{http://www.bea.com/aler/events}Asset"/>
 *         &lt;element name="relation" type="{http://www.bea.com/aler/events}Relationship"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RelatedAssetRegister", propOrder = {
    "originalAsset",
    "relatedAsset",
    "relation"
})
public class RelatedAssetRegister
    extends ExtendedData
{

    @XmlElement(required = true)
    protected Asset originalAsset;
    @XmlElement(required = true)
    protected Asset relatedAsset;
    @XmlElement(required = true)
    protected Relationship relation;

    /**
     * Gets the value of the originalAsset property.
     * 
     * @return
     *     possible object is
     *     {@link Asset }
     *     
     */
    public Asset getOriginalAsset() {
        return originalAsset;
    }

    /**
     * Sets the value of the originalAsset property.
     * 
     * @param value
     *     allowed object is
     *     {@link Asset }
     *     
     */
    public void setOriginalAsset(Asset value) {
        this.originalAsset = value;
    }

    /**
     * Gets the value of the relatedAsset property.
     * 
     * @return
     *     possible object is
     *     {@link Asset }
     *     
     */
    public Asset getRelatedAsset() {
        return relatedAsset;
    }

    /**
     * Sets the value of the relatedAsset property.
     * 
     * @param value
     *     allowed object is
     *     {@link Asset }
     *     
     */
    public void setRelatedAsset(Asset value) {
        this.relatedAsset = value;
    }

    /**
     * Gets the value of the relation property.
     * 
     * @return
     *     possible object is
     *     {@link Relationship }
     *     
     */
    public Relationship getRelation() {
        return relation;
    }

    /**
     * Sets the value of the relation property.
     * 
     * @param value
     *     allowed object is
     *     {@link Relationship }
     *     
     */
    public void setRelation(Relationship value) {
        this.relation = value;
    }

}
