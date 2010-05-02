/**
 * Copyright 2010 The Kuali Foundation Licensed under the
 * Educational Community License, Version 2.0 (the "License"); you may
 * not use this file except in compliance with the License. You may
 * obtain a copy of the License at
 *
 * http://www.osedu.org/licenses/ECL-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an "AS IS"
 * BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing
 * permissions and limitations under the License.
 */

package org.kuali.student.core.messages.service.jaxws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import org.kuali.student.core.messages.dto.MessageList;

/**
 * This class was generated by Apache CXF 2.1.3
 * Fri Jan 09 10:52:53 PST 2009
 * Generated source version: 2.1.3
 */

@XmlRootElement(name = "getMessagesByGroupsResponse", namespace = "http://student.kuali.org/wsdl/messages")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getMessagesByGroupsResponse", namespace = "http://student.kuali.org/wsdl/messages")

public class GetMessagesByGroupsResponse {

    @XmlElement(name = "return")
    private MessageList _return;

    public MessageList getReturn() {
        return this._return;
    }

    public void setReturn(MessageList new_return)  {
        this._return = new_return;
    }

}

