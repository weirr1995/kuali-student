/*
 * Copyright 2011 The Kuali Foundation
 *
 * Licensed under the Educational Community License, Version 1.0 (the
 * "License"); you may not use this file except in compliance with the
 * License.  You may obtain a copy of the License at
 *
 * http://www.opensource.org/licenses/ecl1.php
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or
 * implied.  See the License for the specific language governing
 * permissions and limitations under the License.
 */
package org.kuali.student.lum.util.constants;

import org.kuali.student.common.util.constants.CommonServiceConstants;
// TODO kscm-149 import org.kuali.student.enrollment.grading.dto.GradeRosterEntryInfo;
// TODO kscm-149 import org.kuali.student.enrollment.grading.dto.GradeRosterInfo;
// TODO kscm-149 import org.kuali.student.enrollment.grading.dto.GradeValuesGroupInfo;

/**
 * Grading Service Constants
 */
public class GradingServiceConstants {

    public static final String NAMESPACE = CommonServiceConstants.REF_OBJECT_URI_GLOBAL_PREFIX + "grading";
    public static final String SERVICE_NAME_LOCAL_PART = "GradingService";
    public static final String REF_OBJECT_URI_GRADE_ROSTER = NAMESPACE + "/"; // TODO kscm-149
    // TODO kscm-149 // + GradeRosterInfo.class.getSimpleName();
    public static final String REF_OBJECT_URI_GRADE_ROSTER_ENTRY = NAMESPACE + "/"; // TODO kscm-149
    // TODO kscm-149 // + GradeRosterEntryInfo.class.getSimpleName();
    public static final String REF_OBJECT_URI_GRADE_VALUES_GROUP = NAMESPACE + "/"; // TODO kscm-149
    // TODO kscm-149 // + GradeValuesGroupInfo.class.getSimpleName();
    // grade values group type keys
    public static final String  RESULT_VALUE_NUMBER_GRADE_TYPE = "kuali.lrc.type.number.grade";
    
    public static final String RESULT_VALUE_LETTER_GRADE_TYPE  = "kuali.lrc.type.letter.grade";
    
    public static final String RESULT_VALUE_CREDIT_TYPE  = "kuali.lrc.type.credit";
}
