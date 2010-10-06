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
package org.kuali.student.lum.lu.ui.course.client.requirements;

import java.util.*;

import org.kuali.student.common.ui.client.application.KSAsyncCallback;
import org.kuali.student.common.ui.client.mvc.*;
import org.kuali.student.core.assembly.data.Data;
import org.kuali.student.core.dto.StatusInfo;
import org.kuali.student.core.statement.dto.StatementTreeViewInfo;
import org.kuali.student.core.statement.dto.StatementTypeInfo;
import org.kuali.student.lum.lu.ui.course.client.service.CourseRpcService;
import org.kuali.student.lum.lu.ui.course.client.service.CourseRpcServiceAsync;
import org.kuali.student.lum.program.client.rpc.StatementRpcService;
import org.kuali.student.lum.program.client.rpc.StatementRpcServiceAsync;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;

public class CourseRequirementsDataModel {

    private final CourseRpcServiceAsync courseRemoteService = GWT.create(CourseRpcService.class);
    private StatementRpcServiceAsync statementRpcServiceAsync = GWT.create(StatementRpcService.class);
    private Controller parentController;
    private boolean isInitialized = false;

    private static int tempProgReqInfoID = 8888;   //TODO: remove after testing

    //keeping track of rules and rule state (UPDATED, ADDED, DELETED)
    public enum requirementState {STORED, ADDED, EDITED, DELETED;}
    private LinkedHashMap<StatementTypeInfo, LinkedHashMap<StatementTreeViewInfo, requirementState>> rules = new LinkedHashMap<StatementTypeInfo, LinkedHashMap<StatementTreeViewInfo, requirementState>>();

    public CourseRequirementsDataModel(Controller parentController) {
        this.parentController = parentController;
    }

    public Set<StatementTypeInfo> getStoredStatementTypes() {
        return rules.keySet();
    }

    public Set<StatementTreeViewInfo> getStoredProgRequirements(StatementTypeInfo statementTypeInfo) {
        return rules.get(statementTypeInfo).keySet();
    }

    public LinkedHashMap<StatementTreeViewInfo, requirementState> getStoredProgReqsAndStates(StatementTypeInfo statementTypeInfo) {
        return rules.get(statementTypeInfo);
    }

    //retrieve rules based on IDs associated with this course
    public void retrieveCourseRequirements(final Callback<Boolean> onReadyCallback) {
        parentController.requestModel("cluProposalModel", new ModelRequestCallback() {

            @Override
            public void onRequestFail(Throwable cause) {
                Window.alert(cause.getMessage());
                GWT.log("Unable to retrieve model for course requirements view", cause);
                onReadyCallback.exec(false);
            }

            @Override
            public void onModelReady(Model model) {
                String courseId = ((DataModel)model).getRoot().get("id");
                retrieveStatementTypes(courseId, onReadyCallback);
            }
        });    
    }

    private void retrieveStatementTypes(final String courseId, final Callback<Boolean> onReadyCallback) {

        //retrieve available course requirement types
        statementRpcServiceAsync.getStatementTypesForStatementTypeForCourse("kuali.statement.type.course", new KSAsyncCallback<List<StatementTypeInfo>>() {
            @Override
            public void handleFailure(Throwable caught) {
	            Window.alert(caught.getMessage());
	            GWT.log("getStatementTypes failed", caught);
                onReadyCallback.exec(false);
            }

            @Override
            public void onSuccess(List<StatementTypeInfo> stmtInfoTypes) {
                //store the statement types
                for (StatementTypeInfo stmtInfoType : stmtInfoTypes) {

                    //TODO remove after testing
                    if (stmtInfoType.getId().equals("kuali.statement.type.course.academicReadiness.prereq")) {
                        LinkedHashMap<StatementTreeViewInfo, requirementState> tempRulesList = new LinkedHashMap<StatementTreeViewInfo, requirementState>();
                        StatementTreeViewInfo tempRule = new StatementTreeViewInfo();
                        tempRule.setId("NEWCOURSEREQ" + Integer.toString(tempProgReqInfoID++));   //set unique id
                        tempRule = CourseRequirementsViewController.getTestStatement();
                        tempRulesList.put(tempRule, requirementState.ADDED);
                        rules.put(stmtInfoType, tempRulesList);
                        continue;
                    }

                    rules.put(stmtInfoType, new LinkedHashMap<StatementTreeViewInfo, requirementState>());
                }

                //now retrieve the actual rules
                retrieveRules(courseId, onReadyCallback);
            }
        });
    }

    private void retrieveRules(String courseId, final Callback<Boolean> onReadyCallback) {

        //true if no course exists yet
        if ((courseId == null) || courseId.isEmpty()) {
            isInitialized = true;
            onReadyCallback.exec(true);
            return;
        }

        courseRemoteService.getCourseStatements(courseId, CourseRequirementsViewController.RULEEDIT_TEMLATE, CourseRequirementsViewController.TEMLATE_LANGUAGE, new KSAsyncCallback<List<StatementTreeViewInfo>>() {
            @Override
            public void handleFailure(Throwable caught) {
                Window.alert(caught.getMessage());
                GWT.log("getRequirements failed", caught);
                onReadyCallback.exec(false);
            }

            @Override
            public void onSuccess(List<StatementTreeViewInfo> foundRules) {
                //update rules list with new course requirements
                for (StatementTreeViewInfo foundRule : foundRules) {

                    boolean stmtTypeFound = false;
                    for (StatementTypeInfo stmtInfo : rules.keySet()) {

                        if (stmtInfo.getId().equals(foundRule.getType())) {
                            stmtTypeFound = true;
                            LinkedHashMap<StatementTreeViewInfo, requirementState> tempRulesList;
                            if (rules.get(stmtInfo) != null) {
                                tempRulesList = rules.get(stmtInfo);
                            } else {
                                tempRulesList = new LinkedHashMap<StatementTreeViewInfo, requirementState>();
                            }

                            //add the rule
                            tempRulesList.put(foundRule, requirementState.STORED);
                            rules.put(stmtInfo, tempRulesList);
                            break;
                        }
                    }

                    if (!stmtTypeFound) {
                        Window.alert("Did not find corresponding statement type for course requirement of type: " + foundRule.getType());
                        GWT.log("Did not find corresponding statement type for course requirement of type: " + foundRule.getType(), null);
                    }
                }

                isInitialized = true;
                onReadyCallback.exec(true);
            }
        });     
    }

    public Map<StatementTypeInfo, StatementTreeViewInfo> updateRules(StatementTreeViewInfo newTree, String originalProgramReqId, boolean isNewRule) {

        //find the affected course rule
        LinkedHashMap<StatementTreeViewInfo, requirementState> affectedRule = null;
        StatementTypeInfo affectedStmtTypeInfo = null;
        for(StatementTypeInfo stmtTypeInfo : rules.keySet()) {
            if (newTree.getType().equals(stmtTypeInfo.getId())) {
                affectedStmtTypeInfo = stmtTypeInfo;
                affectedRule = rules.get(stmtTypeInfo);
                break;
            }
        }

        if (affectedStmtTypeInfo == null) {
            Window.alert("Cannot find course requisite with a statement type that has id: '" + newTree.getType() + "'");
            GWT.log("Cannot find course requisite with a statement type that has id: '" + newTree.getType() + "'", null);
            return null;
        }

        if (affectedRule.get(affectedStmtTypeInfo) == CourseRequirementsDataModel.requirementState.STORED) {
            affectedRule.put(newTree, CourseRequirementsDataModel.requirementState.EDITED);
        }

        Map<StatementTypeInfo, StatementTreeViewInfo> result = new HashMap<StatementTypeInfo, StatementTreeViewInfo>();
        result.put(affectedStmtTypeInfo, newTree);
        return result;
    }

    public void updateModelFromLocalData(final Data dto) {

        String courseId = dto.get("id");

        for (final StatementTypeInfo stmtTypeInfo : getStoredStatementTypes()) {

            if (CourseRequirementsSummaryView.isTopStatement(stmtTypeInfo)) {
                continue;
            }

            for (final StatementTreeViewInfo rule : getStoredProgRequirements(stmtTypeInfo)) {

                final CourseRequirementsDataModel.requirementState ruleState = getStoredProgReqsAndStates(stmtTypeInfo).get(rule);
                switch (ruleState) {
                    case STORED:
                        //rule was not changed so continue
                        break;
                    case ADDED:
                        courseRemoteService.createCourseStatement(courseId, rule, new KSAsyncCallback<StatementTreeViewInfo>() {
                            @Override
                            public void handleFailure(Throwable caught) {
                                Window.alert(caught.getMessage());
                                GWT.log("createProgramRequirement failed", caught);
                            }
                            @Override
                            public void onSuccess(StatementTreeViewInfo rule) {
                                updateProgReqId(dto, rule.getId(), ruleState);
                                getStoredProgReqsAndStates(stmtTypeInfo).put(rule, CourseRequirementsDataModel.requirementState.STORED);
                            }
                        });
                        break;
                    case EDITED:
                        courseRemoteService.updateCourseStatement(courseId, rule, new KSAsyncCallback<StatementTreeViewInfo>() {
                            @Override
                            public void handleFailure(Throwable caught) {
                                Window.alert(caught.getMessage());
                                GWT.log("updateProgramRequirement failed", caught);
                            }
                            @Override
                            public void onSuccess(StatementTreeViewInfo rule) {
                                updateProgReqId(dto, rule.getId(), ruleState);
                                getStoredProgReqsAndStates(stmtTypeInfo).put(rule, CourseRequirementsDataModel.requirementState.STORED);
                            }
                        });
                        break;
                    case DELETED:
                        courseRemoteService.deleteCourseStatement(courseId, rule, new KSAsyncCallback<StatusInfo>() {
                            @Override
                            public void handleFailure(Throwable caught) {
                                Window.alert(caught.getMessage());
                                GWT.log("deleteProgramRequirement failed", caught);
                            }
                            @Override
                            public void onSuccess(StatusInfo statusInfo) {
                                updateProgReqId(dto, rule.getId(), ruleState);
                                getStoredProgRequirements(stmtTypeInfo).remove(rule);
                            }
                        });
                        break;
                    default:
                        break;
                }          
            }
        }
    }

    //now update the program this requirement belongs to
    private void updateProgReqId(Object dto, String progReqId, CourseRequirementsDataModel.requirementState op) {
        //TODO
        /*
        if (dto instanceof MajorDisciplineInfo) {
            MajorDisciplineInfo mdInfo = (MajorDisciplineInfo) dto;
            //mdInfo.getProgramRequirements().add(progReqId);
            updateProgramInfo(mdInfo.getProgramRequirements(), progReqId, op);
        } else {
            Window.alert("Only persistence of MajorDiscipline is currently implemented");
            GWT.log("Unable to retrieve model for course requirements view", null);
        } */
    }

    private void updateProgramInfo(List<String> requirements, String id, CourseRequirementsDataModel.requirementState op) {
        switch (op) {
            case ADDED:
                requirements.add(id);
                break;
            case DELETED:
                requirements.remove(id);
                break;
            default:
                break;
        }
    }    

    public boolean isInitialized() {
        return isInitialized;
    }

    public void setInitialized(boolean initialized) {
        isInitialized = initialized;
    }
}
