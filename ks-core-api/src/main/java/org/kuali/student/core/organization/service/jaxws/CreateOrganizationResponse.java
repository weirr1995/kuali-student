
package org.kuali.student.core.organization.service.jaxws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * This class was generated by Apache CXF 2.1.3
 * Wed Jan 21 13:23:58 EST 2009
 * Generated source version: 2.1.3
 */

@XmlRootElement(name = "createOrganizationResponse", namespace = "http://student.kuali.org/wsdl/organization")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "createOrganizationResponse", namespace = "http://student.kuali.org/wsdl/organization")

public class CreateOrganizationResponse {

    @XmlElement(name = "return")
    private org.kuali.student.core.organization.dto.OrgInfo _return;

    public org.kuali.student.core.organization.dto.OrgInfo getReturn() {
        return this._return;
    }

    public void setReturn(org.kuali.student.core.organization.dto.OrgInfo new_return)  {
        this._return = new_return;
    }

}

