
package org.kuali.student.brms.statement.service.jaxws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * This class was generated by Apache CXF 2.2
 * Tue Jan 26 22:57:16 PST 2010
 * Generated source version: 2.2
 */

@XmlRootElement(name = "getReqComponentType", namespace = "http://student.kuali.org/wsdl/statement")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getReqComponentType", namespace = "http://student.kuali.org/wsdl/statement")

public class GetReqComponentType {

    @XmlElement(name = "reqComponentTypeKey")
    private java.lang.String reqComponentTypeKey;

    public java.lang.String getReqComponentTypeKey() {
        return this.reqComponentTypeKey;
    }

    public void setReqComponentTypeKey(java.lang.String newReqComponentTypeKey)  {
        this.reqComponentTypeKey = newReqComponentTypeKey;
    }

}

