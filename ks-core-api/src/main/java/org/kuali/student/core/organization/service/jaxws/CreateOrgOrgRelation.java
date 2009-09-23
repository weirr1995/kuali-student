
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

@XmlRootElement(name = "createOrgOrgRelation", namespace = "http://student.kuali.org/wsdl/organization")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "createOrgOrgRelation", namespace = "http://student.kuali.org/wsdl/organization", propOrder = {"orgId","relatedOrgId","orgOrgRelationTypeKey","orgOrgRelationInfo"})

public class CreateOrgOrgRelation {

    @XmlElement(name = "orgId")
    private java.lang.String orgId;
    @XmlElement(name = "relatedOrgId")
    private java.lang.String relatedOrgId;
    @XmlElement(name = "orgOrgRelationTypeKey")
    private java.lang.String orgOrgRelationTypeKey;
    @XmlElement(name = "orgOrgRelationInfo")
    private org.kuali.student.core.organization.dto.OrgOrgRelationInfo orgOrgRelationInfo;

    public java.lang.String getOrgId() {
        return this.orgId;
    }

    public void setOrgId(java.lang.String newOrgId)  {
        this.orgId = newOrgId;
    }

    public java.lang.String getRelatedOrgId() {
        return this.relatedOrgId;
    }

    public void setRelatedOrgId(java.lang.String newRelatedOrgId)  {
        this.relatedOrgId = newRelatedOrgId;
    }

    public java.lang.String getOrgOrgRelationTypeKey() {
        return this.orgOrgRelationTypeKey;
    }

    public void setOrgOrgRelationTypeKey(java.lang.String newOrgOrgRelationTypeKey)  {
        this.orgOrgRelationTypeKey = newOrgOrgRelationTypeKey;
    }

    public org.kuali.student.core.organization.dto.OrgOrgRelationInfo getOrgOrgRelationInfo() {
        return this.orgOrgRelationInfo;
    }

    public void setOrgOrgRelationInfo(org.kuali.student.core.organization.dto.OrgOrgRelationInfo newOrgOrgRelationInfo)  {
        this.orgOrgRelationInfo = newOrgOrgRelationInfo;
    }

}

