
package com.example.types;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for InstanceStatusEnumeration.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="InstanceStatusEnumeration">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="RUNNING"/>
 *     &lt;enumeration value="EXCEPTION"/>
 *     &lt;enumeration value="SUSPENDED"/>
 *     &lt;enumeration value="GRABBED"/>
 *     &lt;enumeration value="COMPLETED"/>
 *     &lt;enumeration value="ABORTED"/>
 *     &lt;enumeration value="ACTIVITY_COMPLETED"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "InstanceStatusEnumeration", namespace = "http://www.bea.com/infra/events/workflow/status")
@XmlEnum
public enum InstanceStatusEnumeration {

    RUNNING,
    EXCEPTION,
    SUSPENDED,
    GRABBED,
    COMPLETED,
    ABORTED,
    ACTIVITY_COMPLETED;

    public String value() {
        return name();
    }

    public static InstanceStatusEnumeration fromValue(String v) {
        return valueOf(v);
    }

}
