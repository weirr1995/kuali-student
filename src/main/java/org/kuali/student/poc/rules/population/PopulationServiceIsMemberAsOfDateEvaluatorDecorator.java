/**
 * Copyright 2013 The Kuali Foundation Licensed under the
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
 *
 * Created by mahtabme on 12/13/13
 */
package org.kuali.student.poc.rules.population;

import org.kuali.student.enrollment.class2.population.service.decorators.PopulationServiceDecorator;
import org.kuali.student.r2.common.dto.ContextInfo;
import org.kuali.student.r2.common.exceptions.DoesNotExistException;
import org.kuali.student.r2.common.exceptions.InvalidParameterException;
import org.kuali.student.r2.common.exceptions.MissingParameterException;
import org.kuali.student.r2.common.exceptions.OperationFailedException;
import org.kuali.student.r2.common.exceptions.PermissionDeniedException;

import javax.jws.WebParam;
import java.util.Date;

/**
 * This class represents a PopulationService decorator which can apply a specific
 * IsMemberAsOfDateEvaluator to the population, as well as a specific YearOfStudyCalculator.
 *
 * @author Mezba Mahtab
 */
public class PopulationServiceIsMemberAsOfDateEvaluatorDecorator extends PopulationServiceDecorator {

    ////////////////////////
    // Data Variable
    ////////////////////////

    private IsMemberAsOfDateEvaluator isMemberAsOfDateEvaluator;

    /////////////////////////////
    // Getters and Setters
    /////////////////////////////

    public IsMemberAsOfDateEvaluator getMemberAsOfDateEvaluator() {
        return isMemberAsOfDateEvaluator;
    }

    public void setMemberAsOfDateEvaluator(IsMemberAsOfDateEvaluator memberAsOfDateEvaluator) {
        isMemberAsOfDateEvaluator = memberAsOfDateEvaluator;
    }

    /////////////////////////////
    // Functionals
    /////////////////////////////

    @Override
    public Boolean isMemberAsOfDate(@WebParam(name = "personId") String personId,
                                    @WebParam(name = "populationId") String populationId,
                                    @WebParam(name = "date") Date date,
                                    @WebParam(name = "contextInfo") ContextInfo contextInfo)
            throws DoesNotExistException,
                InvalidParameterException,
                MissingParameterException,
                OperationFailedException,
                PermissionDeniedException {
        return isMemberAsOfDateEvaluator.isMemberAsOfDate(personId, populationId, date, contextInfo);
    }


}