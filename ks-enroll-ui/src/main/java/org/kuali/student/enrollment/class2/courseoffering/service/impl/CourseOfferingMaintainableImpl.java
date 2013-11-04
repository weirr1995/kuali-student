/**
 * Copyright 2012 The Kuali Foundation Licensed under the
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
 * @author Kuali Student Team
 */
package org.kuali.student.enrollment.class2.courseoffering.service.impl;


import org.apache.commons.lang.StringUtils;
import org.kuali.rice.core.api.util.ConcreteKeyValue;
import org.kuali.rice.core.api.util.KeyValue;
import org.kuali.rice.krad.maintenance.Maintainable;
import org.kuali.rice.krad.maintenance.MaintainableImpl;
import org.kuali.rice.krad.maintenance.MaintenanceDocument;
import org.kuali.rice.krad.uif.UifConstants;
import org.kuali.rice.krad.uif.container.CollectionGroup;
import org.kuali.rice.krad.uif.control.SelectControl;
import org.kuali.rice.krad.uif.field.InputField;
import org.kuali.rice.krad.uif.view.View;
import org.kuali.rice.krad.web.form.MaintenanceDocumentForm;
import org.kuali.student.enrollment.class2.courseoffering.dto.ActivityOfferingWrapper;
import org.kuali.student.enrollment.class2.courseoffering.dto.CourseOfferingCreateWrapper;
import org.kuali.student.enrollment.class2.courseoffering.dto.CourseOfferingEditWrapper;
import org.kuali.student.enrollment.class2.courseoffering.dto.CourseOfferingWrapper;
import org.kuali.student.enrollment.class2.courseoffering.dto.FormatOfferingWrapper;
import org.kuali.student.enrollment.class2.courseoffering.util.CourseOfferingResourceLoader;
import org.kuali.student.enrollment.courseoffering.dto.ActivityOfferingInfo;
import org.kuali.student.enrollment.courseoffering.dto.CourseOfferingCrossListingInfo;
import org.kuali.student.enrollment.courseoffering.dto.CourseOfferingInfo;
import org.kuali.student.enrollment.courseoffering.dto.FormatOfferingInfo;
import org.kuali.student.enrollment.courseoffering.service.CourseOfferingService;
import org.kuali.student.r2.common.dto.ContextInfo;
import org.kuali.student.r2.common.util.ContextUtils;
import org.kuali.student.r2.common.util.constants.LuiServiceConstants;
import org.kuali.student.r2.core.class1.state.dto.StateInfo;
import org.kuali.student.r2.core.class1.state.service.StateService;
import org.kuali.student.r2.core.class1.type.dto.TypeInfo;
import org.kuali.student.r2.core.class1.type.service.TypeService;
import org.kuali.student.r2.lum.course.dto.ActivityInfo;
import org.kuali.student.r2.lum.course.dto.CourseCrossListingInfo;
import org.kuali.student.r2.lum.course.dto.CourseInfo;
import org.kuali.student.r2.lum.course.dto.FormatInfo;
import org.kuali.student.r2.lum.course.service.CourseService;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Base view helper service for both create and edit course offering presentations.
 *
 */
public abstract class CourseOfferingMaintainableImpl extends MaintainableImpl implements Maintainable {

    private transient CourseOfferingService courseOfferingService;
    private transient TypeService typeService;
    private transient StateService stateService;
    private transient CourseService courseService;

    /**
     * Returns the format name by concatenation all the activity names with / seperated
     *
     * @param foWrapper
     * @param course
     * @return
     */
    public String getFormatName(FormatOfferingWrapper foWrapper,CourseInfo course){
        for (FormatInfo format : course.getFormats()) {
            if (StringUtils.equals(format.getId(),foWrapper.getFormatId())){
                StringBuilder activityName = new StringBuilder();
                for (ActivityInfo activityInfo : format.getActivities()) {
                    TypeInfo activityType = null;
                    try {
                        activityType = getTypeService().getType(activityInfo.getTypeKey(), ContextUtils.createDefaultContextInfo());
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                    activityName.append(activityType.getName()+"/");
                }
                if (format.getActivities().size() == 1){
                    return StringUtils.removeEnd(activityName.toString(),"/") + " Only";
                } else {
                    return StringUtils.removeEnd(activityName.toString(),"/");
                }
            }
        }
        return StringUtils.EMPTY;
    }

    /**
     * Returns the format long name short name as a string array  by concatenation all the shortened activity names with / seperated
     *
     * @param foWrapper
     * @param course
     * @return   String[]
     */
    public String[] getFormatShortAndLongNames(FormatOfferingWrapper foWrapper,CourseInfo course){
        String[] formatNames = new String[2];
        for (FormatInfo format : course.getFormats()) {
            if (StringUtils.equals(format.getId(),foWrapper.getFormatId())){
                StringBuilder longName = new StringBuilder();
                StringBuilder shortName = new StringBuilder();
                for (ActivityInfo activityInfo : format.getActivities()) {
                    TypeInfo activityType = null;
                    try {
                        activityType = getTypeService().getType(activityInfo.getTypeKey(), ContextUtils.createDefaultContextInfo());
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                    longName.append(activityType.getName()+"/");
                    shortName.append(activityType.getName().toUpperCase().substring(0,3)+"/");
                }
                if (format.getActivities().size() == 1){
                    formatNames[0] = StringUtils.removeEnd(longName.toString(),"/") + " Only";
                } else {
                    formatNames[0] = StringUtils.removeEnd(longName.toString(),"/");
                }
                formatNames[1] = StringUtils.removeEnd(shortName.toString(),"/");
                return formatNames;
            }
        }
        formatNames[0] = StringUtils.EMPTY;
        formatNames[1] = StringUtils.EMPTY;
        return formatNames;
    }

    @Override
    public void processCollectionAddBlankLine(View view, Object model, String collectionPath) {

        if (StringUtils.endsWith(collectionPath,"formatOfferingList")){
            MaintenanceDocumentForm maintenanceForm = (MaintenanceDocumentForm) model;
            MaintenanceDocument document = maintenanceForm.getDocument();

            if (document.getNewMaintainableObject().getDataObject() instanceof CourseOfferingEditWrapper){
                CourseOfferingEditWrapper wrapper = (CourseOfferingEditWrapper)document.getNewMaintainableObject().getDataObject();

                populateFormatNames(wrapper);

                FormatOfferingWrapper newFoWrapper = new FormatOfferingWrapper();
                newFoWrapper.getRenderHelper().setNewRow(true);

                wrapper.getFormatOfferingList().add(newFoWrapper);

                return;
            }
        }

        super.processCollectionAddBlankLine(view,model,collectionPath);
    }

    @Override
    protected void processAfterDeleteLine(View view, CollectionGroup collectionGroup, Object model, int lineIndex) {
        if (StringUtils.equals(collectionGroup.getPropertyName(),"formatOfferingList")){
            MaintenanceDocumentForm maintenanceForm = (MaintenanceDocumentForm) model;
            MaintenanceDocument document = maintenanceForm.getDocument();

            if (document.getNewMaintainableObject().getDataObject() instanceof CourseOfferingEditWrapper){
                CourseOfferingEditWrapper wrapper = (CourseOfferingEditWrapper)document.getNewMaintainableObject().getDataObject();

                for (FormatOfferingWrapper foWrapper : wrapper.getFormatOfferingList()){
                    if (StringUtils.isNotBlank(foWrapper.getFormatId())){
                        foWrapper.getRenderHelper().setNewRow(false);
                    }
                }
            }
        }
    }

    /**
     * This method is being called by KRAD to populate grade roster level types drop down.
     *
     * <p>
     * In 'Create CO', we use a wrapper around <code>FormatOfferingInfo</code> to handle joint formats.
     * In 'Edit CO', it's just <code>FormatOfferingInfo</code> at the collection
     * </p>
     *
     * <p>There would be no reference for this method in the code as it has it's references at the following view xmls</p>
     *      <ul>
     *          <li>CourseOfferingCreateMaintenanceView.xml</li>
     *          <li>CourseOfferingEditMaintenanceView.xml</li>
     *      </ul>
     *
     * @param field grade roster level field
     * @param form maintenace form
     * @see #populateFinalExamDriverTypes
     */
    @SuppressWarnings("unused")
    public void populateGradeRosterLevelTypes(InputField field, MaintenanceDocumentForm form){

        if (field.isReadOnly()){
            return;
        }

        FormatOfferingInfo formatOfferingInfo;
        CourseOfferingWrapper wrapper = (CourseOfferingWrapper)form.getDocument().getNewMaintainableObject().getDataObject();
        CourseInfo courseInfo = wrapper.getCourse();

        if (wrapper instanceof CourseOfferingCreateWrapper) {
            /**
             * If the call is from create co, then there are two places from where this method is being called. From the 'Add format' section
             * and from the format offering collections. For the 'add format' section, we're checking the property name to get the FO Wrapper
             */
            if (StringUtils.equals(field.getPropertyName(),"addLineFormatWrapper.gradeRosterLevelTypeKey")){
                formatOfferingInfo = ((CourseOfferingCreateWrapper)wrapper).getAddLineFormatWrapper().getFormatOfferingInfo();
            } else {
                //This else is for the format offering collection.
                FormatOfferingWrapper foWrapper = (FormatOfferingWrapper)field.getContext().get(UifConstants.ContextVariableNames.LINE);
                formatOfferingInfo = foWrapper.getFormatOfferingInfo();
                if (foWrapper.isJointOffering()){
                    courseInfo = foWrapper.getJointCreateWrapper().getCourseInfo();
                }
            }
        } else {
            formatOfferingInfo = ((FormatOfferingWrapper)field.getContext().get(UifConstants.ContextVariableNames.LINE)).getFormatOfferingInfo();
        }

        SelectControl control = (SelectControl)field.getControl();

        List<KeyValue> gradeKeyValues = new ArrayList<KeyValue>();

        if (StringUtils.isNotBlank(formatOfferingInfo.getFormatId())){
            // Always include an option for Course
            gradeKeyValues.add(new ConcreteKeyValue(LuiServiceConstants.COURSE_OFFERING_TYPE_KEY, getTypeName(LuiServiceConstants.COURSE_OFFERING_TYPE_KEY)));
            gradeKeyValues.addAll(collectActivityTypeKeyValues(courseInfo, formatOfferingInfo.getFormatId(), getTypeService(), ContextUtils.createDefaultContextInfo()));
            control.setDisabled(false);
        } else {
            control.setDisabled(true);
        }

        control.setOptions(gradeKeyValues);

    }

    /**
     * This method is being called by KRAD to populate final exam driver types drop down.
     *
     * <p>
     * In 'Create CO', we use a wrapper around <code>FormatOfferingInfo</code> to handle joint formats.
     * In 'Edit CO', it's just <code>FormatOfferingInfo</code> at the collection
     * </p>
     *
     * <p>There would be no reference for this method in the code as it has it's references at the following view xmls</p>
     *      <ul>
     *          <li>CourseOfferingCreateMaintenanceView.xml</li>
     *          <li>CourseOfferingEditMaintenanceView.xml</li>
     *      </ul>
     *
     * @param field grade roster level field
     * @param form maintenace form
     * @see #populateGradeRosterLevelTypes
     */
    @SuppressWarnings("unused")
    public void populateFinalExamDriverTypes(InputField field, MaintenanceDocumentForm form) throws Exception {

        if (field.isReadOnly()) {
            return;
        }

        FormatOfferingInfo formatOfferingInfo;
        CourseOfferingWrapper wrapper = (CourseOfferingWrapper) form.getDocument().getNewMaintainableObject().getDataObject();
        CourseInfo courseInfo = wrapper.getCourse();

        if (wrapper instanceof CourseOfferingCreateWrapper) {
            /**
             * If the call is from create co, then there are two places from where this method is being called. From the 'Add format' section
             * and from the format offering collections. For the 'add format' section, we're checking the property name to get the FO Wrapper
             */
            if (StringUtils.equals(field.getPropertyName(), "addLineFormatWrapper.finalExamLevelTypeKey")) {
                formatOfferingInfo = ((CourseOfferingCreateWrapper) wrapper).getAddLineFormatWrapper().getFormatOfferingInfo();
            } else {
                //This else is for the format offering collection.
                FormatOfferingWrapper foWrapper = (FormatOfferingWrapper) field.getContext().get(UifConstants.ContextVariableNames.LINE);
                formatOfferingInfo = foWrapper.getFormatOfferingInfo();
                if (foWrapper.isJointOffering()) {
                    courseInfo = foWrapper.getJointCreateWrapper().getCourseInfo();
                }
            }
        } else {
            formatOfferingInfo = ((FormatOfferingWrapper) field.getContext().get(UifConstants.ContextVariableNames.LINE)).getFormatOfferingInfo();
        }

        SelectControl control = (SelectControl) field.getControl();

        List<KeyValue> keyValues = new ArrayList<KeyValue>();
        CourseOfferingEditWrapper courseOfferingEditWrapper;
        if (StringUtils.isNotBlank(formatOfferingInfo.getFormatId()) && courseInfo != null) {
            keyValues.addAll(collectActivityTypeKeyValues(courseInfo, formatOfferingInfo.getFormatId(), getTypeService(), ContextUtils.createDefaultContextInfo()));
            if (wrapper instanceof CourseOfferingEditWrapper) {
                courseOfferingEditWrapper = (CourseOfferingEditWrapper) form.getDocument().getNewMaintainableObject().getDataObject();
                if (wrapper.getCourseOfferingInfo().getId() != null) {
                    loadActivityOfferingsByCourseOffering(wrapper.getCourseOfferingInfo(), courseOfferingEditWrapper);
                    if (courseOfferingEditWrapper.getAoWrapperList() != null) {
                        FormatOfferingInfo aoformatOfferingInfo = courseOfferingEditWrapper.getAoWrapperList().get(0).getFormatOffering();
                        if (aoformatOfferingInfo.getFinalExamLevelTypeKey() != keyValues.get(0).getKey()) {
                            List<KeyValue> newKeyValues = new ArrayList<KeyValue>();
                            newKeyValues.add(keyValues.get(0));
                            keyValues.remove(0);
                            keyValues.add(newKeyValues.get(0));

                        }
                    }
                }

            }
            control.setDisabled(false);
        } else {
            control.setDisabled(true);
        }

        control.setOptions(keyValues);

    }

    protected List<KeyValue> collectActivityTypeKeyValues(CourseInfo course, String formatId, TypeService typeService, ContextInfo contextInfo) {

        List<KeyValue> results = new ArrayList<KeyValue>();

        Set<String> activityTypes = new HashSet<String>();
        for(FormatInfo format : course.getFormats()) {
            if (StringUtils.isBlank(formatId) || (StringUtils.isNotBlank(formatId) && StringUtils.equals(format.getId(),formatId))){
                for (ActivityInfo activity : format.getActivities()) {
                    // if we haven't added a value for this activity type yet
                    if(activityTypes.add(activity.getTypeKey())) {
                        try {
                            TypeInfo type = typeService.getType(activity.getTypeKey(), contextInfo);
                            results.add(new ConcreteKeyValue(type.getKey(), type.getName()));
                        } catch (Exception e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
            }
        }

        return results;
    }

    /**
     * This method populates {@link CourseCrossListingInfo} in to the {@link CourseOfferingInfo}
     * based on the user selection at create or edit CO. Note: If the <code>kuali.ks.enrollment.options.selective-crosslisting-allowed</code>
     * property is enabled, this method creates cross listing dtos for all the alternate course codes.
     *
     * @param wrapper either {@link CourseOfferingCreateWrapper} or {@link org.kuali.student.enrollment.class2.courseoffering.dto.CourseOfferingEditWrapper}
     * @param coInfo course offering dto
     */
    protected void loadCrossListedCOs(CourseOfferingWrapper wrapper, CourseOfferingInfo coInfo) {
        coInfo.getCrossListings().clear();
        if (wrapper.isSelectCrossListingAllowed()) {
            List<String> alternateCodes = null;
            if (wrapper instanceof CourseOfferingCreateWrapper){
                alternateCodes = wrapper.getAlternateCOCodes();
            } else if (wrapper instanceof CourseOfferingEditWrapper){
                alternateCodes = ((CourseOfferingEditWrapper)wrapper).getAlternateCourseCodesSuffixStripped();
            }
            for (String alternateCode : alternateCodes) {
                String alternateCourseCodesSuffixStripped = StringUtils.stripEnd(alternateCode,coInfo.getCourseNumberSuffix());
                for (CourseCrossListingInfo crossInfo : wrapper.getCourse().getCrossListings()) {
                    if (StringUtils.equals(crossInfo.getCode(),alternateCourseCodesSuffixStripped)) {
                        CourseOfferingCrossListingInfo crossListingInfo = new CourseOfferingCrossListingInfo();
                        crossListingInfo.setCode(crossInfo.getCode());
                        crossListingInfo.setCourseNumberSuffix(crossInfo.getCourseNumberSuffix());
                        crossListingInfo.setSubjectOrgId(crossInfo.getSubjectOrgId());
                        crossListingInfo.setSubjectArea(crossInfo.getSubjectArea());
                        crossListingInfo.setStateKey(LuiServiceConstants.LUI_CO_STATE_DRAFT_KEY);
                        crossListingInfo.setTypeKey(LuiServiceConstants.LUI_IDENTIFIER_CROSSLISTED_TYPE_KEY);
                        coInfo.getCrossListings().add(crossListingInfo);
                    }
                }
            }
        } else {
            // get all the crosslisted COs
            CourseInfo courseInfo = wrapper.getCourse();
            for (CourseCrossListingInfo crossInfo : courseInfo.getCrossListings()) {
                CourseOfferingCrossListingInfo crossListingInfo = new CourseOfferingCrossListingInfo();
                crossListingInfo.setCode(crossInfo.getCode());
                crossListingInfo.setCourseNumberSuffix(crossInfo.getCourseNumberSuffix());
                crossListingInfo.setSubjectOrgId(crossInfo.getSubjectOrgId());
                crossListingInfo.setSubjectArea(crossInfo.getSubjectArea());
                crossListingInfo.setStateKey(LuiServiceConstants.LUI_CO_STATE_DRAFT_KEY);
                crossListingInfo.setTypeKey(LuiServiceConstants.LUI_IDENTIFIER_CROSSLISTED_TYPE_KEY);
                coInfo.getCrossListings().add(crossListingInfo);
            }
        }
    }

    public void populateFormatNames(CourseOfferingWrapper coWrapper){

        if (!(coWrapper instanceof CourseOfferingEditWrapper)){
            throw new RuntimeException("Invalid CourseOffering wrapper.");
        }

        CourseOfferingEditWrapper editWrapper = (CourseOfferingEditWrapper)coWrapper;
        for (FormatOfferingWrapper foWrapper : editWrapper.getFormatOfferingList()){
            if (StringUtils.isBlank(foWrapper.getFormatOfferingInfo().getName())){
                foWrapper.getFormatOfferingInfo().setName(getFormatName(foWrapper,editWrapper.getCourse()));
            }
            if (StringUtils.isNotBlank(foWrapper.getFormatId())){
                foWrapper.getRenderHelper().setNewRow(false);
            }
        }

    }

    private void loadActivityOfferingsByCourseOffering(CourseOfferingInfo theCourseOfferingInfo, CourseOfferingEditWrapper formObject) throws Exception {
        String courseOfferingId = theCourseOfferingInfo.getId();
        List<ActivityOfferingInfo> activityOfferingInfoList;
        List<ActivityOfferingWrapper> activityOfferingWrapperList;
            try {

                activityOfferingInfoList = getCourseOfferingService().getActivityOfferingsByCourseOffering(courseOfferingId, ContextUtils.createDefaultContextInfo());
                activityOfferingWrapperList = new ArrayList<ActivityOfferingWrapper>(activityOfferingInfoList.size());

                for (ActivityOfferingInfo info : activityOfferingInfoList) {
                    ActivityOfferingWrapper aoWrapper = convertAOInfoToWrapper_Simple(info);
                    activityOfferingWrapperList.add(aoWrapper);
                }
            } catch (Exception e) {
                throw new RuntimeException(String.format("Could not load AOs for course offering [%s].", courseOfferingId), e);
            }
            formObject.setAoWrapperList(activityOfferingWrapperList);
    }

    private ActivityOfferingWrapper convertAOInfoToWrapper_Simple(ActivityOfferingInfo aoInfo) throws Exception{

        ActivityOfferingWrapper aoWrapper = new ActivityOfferingWrapper(aoInfo);

        ContextInfo contextInfo = ContextUtils.createDefaultContextInfo();

        StateInfo state = getStateService().getState(aoInfo.getStateKey(), contextInfo);
        aoWrapper.setStateName(state.getName());

        TypeInfo typeInfo = getTypeService().getType(aoInfo.getTypeKey(), contextInfo);
        aoWrapper.setTypeName(typeInfo.getName());

        FormatOfferingInfo fo = getCourseOfferingService().getFormatOffering(aoInfo.getFormatOfferingId(), contextInfo);
        aoWrapper.setFormatOffering(fo);
        return aoWrapper;
    }

    protected TypeService getTypeService() {
        if(typeService == null) {
            typeService = CourseOfferingResourceLoader.loadTypeService();
        }
        return this.typeService;
    }

    protected StateService getStateService() {
        if(stateService == null) {
            stateService = CourseOfferingResourceLoader.loadStateService();
        }
        return stateService;
    }

    protected CourseOfferingService getCourseOfferingService() {
        if (courseOfferingService == null) {
            courseOfferingService = CourseOfferingResourceLoader.loadCourseOfferingService();
        }
        return courseOfferingService;
    }

    protected CourseService getCourseService() {
        if(courseService == null) {
            courseService = CourseOfferingResourceLoader.loadCourseService();
        }
        return this.courseService;
    }

    /**
     * Returns the Name for a type key.
     *
     * @param typeKey
     * @return
     */
    protected String getTypeName(String typeKey){
        try{
            TypeInfo typeInfo = getTypeService().getType(typeKey,ContextUtils.createDefaultContextInfo());
            return typeInfo.getName();
        } catch (Exception e){
            //Throwing a runtime as we use this method to get the type name only for the ui purpose..
            throw new RuntimeException(e);
        }
    }


}
