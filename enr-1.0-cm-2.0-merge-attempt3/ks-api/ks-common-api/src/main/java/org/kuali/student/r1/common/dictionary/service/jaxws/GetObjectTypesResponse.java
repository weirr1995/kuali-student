
package org.kuali.student.r1.common.dictionary.service.jaxws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * This class was generated by Apache CXF 2.2
 * Fri May 14 11:26:06 PDT 2010
 * Generated source version: 2.2
 */

@Deprecated
@XmlRootElement(name = "getObjectTypesResponse", namespace = "http://student.kuali.org/wsdl/dictionary")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getObjectTypesResponse", namespace = "http://student.kuali.org/wsdl/dictionary")

public class GetObjectTypesResponse {

    @XmlElement(name = "return")
    private java.util.List<String> _return;

    public java.util.List<String> getReturn() {
        return this._return;
    }

    public void setReturn(java.util.List<String> new_return)  {
        this._return = new_return;
    }

}

