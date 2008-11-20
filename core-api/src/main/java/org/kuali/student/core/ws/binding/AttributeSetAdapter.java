package org.kuali.student.core.ws.binding;

import java.util.Map;

import javax.xml.bind.annotation.adapters.XmlAdapter;

import org.kuali.rice.kim.bo.types.dto.AttributeSet;

/**
 * This is jaxb adapter for org.kuali.rice.kim.bo.types.dto.AttributeSet. 
 * This class should not be required if we add getter/setter in the rice DTO.
 *
 */
public class AttributeSetAdapter extends
        XmlAdapter<JaxbAttributeList, AttributeSet> {
    public AttributeSet unmarshal(JaxbAttributeList value) {
        if(value == null) return null;
        AttributeSet result = new AttributeSet();
        for (JaxbAttribute a : value.getAttribute()) {
            result.put(a.type, a.value);
        }
        return result;
    }

    public JaxbAttributeList marshal(AttributeSet value) {
        if(value == null) return null;
        JaxbAttributeList attributes = new JaxbAttributeList();
        for (Map.Entry<String, String> e : value.entrySet()) {
            attributes.getAttribute().add(
                    new JaxbAttribute(e.getKey(), e.getValue()));
        }
        return attributes;
    }

}