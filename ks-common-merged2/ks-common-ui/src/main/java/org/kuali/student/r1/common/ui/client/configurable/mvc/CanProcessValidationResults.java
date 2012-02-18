package org.kuali.student.r1.common.ui.client.configurable.mvc;

import java.util.List;

import org.kuali.student.r1.common.ui.client.event.ValidateRequestEvent;
import org.kuali.student.r1.common.validation.dto.ValidationResultInfo;
import org.kuali.student.r1.common.validation.dto.ValidationResultInfo.ErrorLevel;

public interface CanProcessValidationResults {

    public abstract ErrorLevel processValidationResults(FieldDescriptor fd, List<ValidationResultInfo> results);

    public abstract ErrorLevel processValidationResults(FieldDescriptor fd, List<ValidationResultInfo> results, boolean clearErrors);
    
    public boolean doesOnTheFlyValidation();
    
    public void Validate(ValidateRequestEvent event, List<ValidationResultInfo> result);

}