/*
 * Copyright 2009 The Kuali Foundation Licensed under the
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
package org.kuali.student.lum.lu.ui.course.client.configuration.course;

import java.util.ArrayList;
import java.util.List;

import org.kuali.student.common.assembly.client.QueryPath;
import org.kuali.student.common.ui.client.application.Application;
import org.kuali.student.common.ui.client.configurable.mvc.ConfigurableLayout;
import org.kuali.student.common.ui.client.configurable.mvc.CustomNestedSection;
import org.kuali.student.common.ui.client.configurable.mvc.FieldDescriptor;
import org.kuali.student.common.ui.client.configurable.mvc.SectionTitle;
import org.kuali.student.common.ui.client.configurable.mvc.SectionView;
import org.kuali.student.common.ui.client.configurable.mvc.ToolView;
import org.kuali.student.common.ui.client.configurable.mvc.VerticalSection;
import org.kuali.student.common.ui.client.configurable.mvc.Section.FieldLabelType;
import org.kuali.student.common.ui.client.configurable.mvc.binding.ModelWidgetBinding;
import org.kuali.student.common.ui.client.configurable.mvc.multiplicity.UpdatableMultiplicityComposite;
import org.kuali.student.common.ui.client.configurable.mvc.views.VerticalSectionView;
import org.kuali.student.common.ui.client.mvc.DataModel;
import org.kuali.student.common.ui.client.mvc.dto.ModelDTOValue.Type;
import org.kuali.student.common.ui.client.widgets.KSDatePicker;
import org.kuali.student.common.ui.client.widgets.KSDropDown;
import org.kuali.student.common.ui.client.widgets.KSLabel;
import org.kuali.student.common.ui.client.widgets.KSRichEditor;
import org.kuali.student.common.ui.client.widgets.KSTextArea;
import org.kuali.student.common.ui.client.widgets.commenttool.CommentPanel;
import org.kuali.student.common.ui.client.widgets.documenttool.DocumentTool;
import org.kuali.student.common.ui.client.widgets.list.KSCheckBoxList;
import org.kuali.student.common.ui.client.widgets.list.KSLabelList;
import org.kuali.student.common.ui.client.widgets.list.KSSelectItemWidgetAbstract;
import org.kuali.student.common.ui.client.widgets.list.impl.SimpleListItems;
import org.kuali.student.common.ui.client.widgets.selectors.KSSearchComponent;
import org.kuali.student.common.ui.client.widgets.selectors.SearchComponentConfiguration;
import org.kuali.student.common.ui.client.widgets.suggestbox.SearchSuggestOracle;
import org.kuali.student.common.ui.client.widgets.suggestbox.SuggestPicker;
import org.kuali.student.core.organization.ui.client.service.OrgRpcService;
import org.kuali.student.core.organization.ui.client.service.OrgRpcServiceAsync;
import org.kuali.student.core.search.dto.QueryParamValue;
import org.kuali.student.lum.lu.ui.course.client.configuration.CourseRequisitesSectionView;
import org.kuali.student.lum.lu.ui.course.client.configuration.LUConstants;
import org.kuali.student.lum.lu.ui.course.client.configuration.mvc.CluProposalModelDTO;
import org.kuali.student.lum.lu.ui.course.client.configuration.mvc.LuConfigurer.LuSections;
import org.kuali.student.lum.lu.ui.course.client.configuration.viewclu.ViewCluConfigurer;
import org.kuali.student.lum.lu.ui.course.client.widgets.AssemblerTestSection;
import org.kuali.student.lum.lu.ui.course.client.widgets.Collaborators;
import org.kuali.student.lum.lu.ui.course.client.widgets.OrgPicker;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.BlurHandler;
import com.google.gwt.event.dom.client.FocusHandler;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.Widget;
import org.kuali.student.lum.lu.assembly.data.client.refactorme.base.MetaInfoConstants;
import org.kuali.student.lum.lu.assembly.data.client.refactorme.base.RichTextInfoConstants;
import org.kuali.student.lum.lu.assembly.data.client.refactorme.orch.CreditCourseActivityConstants;
import org.kuali.student.lum.lu.assembly.data.client.refactorme.orch.CreditCourseConstants;
import org.kuali.student.lum.lu.assembly.data.client.refactorme.orch.CreditCourseDurationConstants;
import org.kuali.student.lum.lu.assembly.data.client.refactorme.orch.CreditCourseFormatConstants;
import org.kuali.student.lum.lu.assembly.data.client.refactorme.orch.CreditCourseProposalConstants;
import org.kuali.student.lum.lu.assembly.data.client.refactorme.orch.CreditCourseProposalInfoConstants;
import org.kuali.student.lum.lu.assembly.data.client.refactorme.orch.FeeInfoConstants;


/**
 * This is the configuration factory class for creating a proposal.
 *
 * @author Kuali Student Team
 *
 */
public class CourseConfigurer 
 implements CreditCourseProposalConstants,
 CreditCourseProposalInfoConstants,
 CreditCourseConstants,
 CreditCourseFormatConstants,
 CreditCourseActivityConstants,
 MetaInfoConstants,
 CreditCourseDurationConstants,
 FeeInfoConstants
{

    //FIXME:  Initialize type and state
    private static String groupName;

    private static boolean WITH_DIVIDER = true;
    private static boolean NO_DIVIDER = false;
    private static final int NUM_INITIAL_LOS = 3;

    public static final String CLU_PROPOSAL_MODEL = "cluProposalModel";

    public static void configureCourseProposal(ConfigurableLayout layout){
    	
    	groupName = LUConstants.COURSE_GROUP_NAME;
    	
        addCluStartSection(layout);

        layout.addSection(new String[] {"Edit Proposal", getLabel(LUConstants.PROPOSAL_INFORMATION_LABEL_KEY)}, generateGovernanceSection());
        layout.addSection(new String[] {"Edit Proposal", getLabel(LUConstants.PROPOSAL_INFORMATION_LABEL_KEY)}, generateCourseLogisticsSection());
        layout.addSection(new String[] {"Edit Proposal", getLabel(LUConstants.ACADEMIC_CONTENT_LABEL_KEY)}, generateCourseInfoSection());
//        layout.addSection(new String[] {"Edit Proposal", getLabel(LUConstants.ACADEMIC_CONTENT_LABEL_KEY)}, generateLearningObjectivesSection());
        layout.addSection(new String[] {"Edit Proposal", getLabel(LUConstants.STUDENT_ELIGIBILITY_LABEL_KEY)}, generateCourseRequisitesSection());
        layout.addSection(new String[] {"Edit Proposal", getLabel(LUConstants.ADMINISTRATION_LABEL_KEY)}, generateActiveDatesSection());
        layout.addSection(new String[] {"Edit Proposal", getLabel(LUConstants.ADMINISTRATION_LABEL_KEY)}, generateFinancialsSection());   
        layout.addSection(new String[] {getLabel(LUConstants.SUMMARY_LABEL_KEY)}, generateSummarySection());
        layout.addSection(new String[] {"Assembler Test"}, new AssemblerTestSection(LuSections.ASSEMBLER_TEST, "Assembler Test"));
        
        layout.addTool(new CollaboratorTool());
        layout.addTool(new CommentPanel(LuSections.COMMENTS, LUConstants.TOOL_COMMENTS));
        layout.addTool(new DocumentTool(LuSections.DOCUMENTS, LUConstants.TOOL_DOCUMENTS));
    }

    public static SectionView generateSummarySection(){
        VerticalSectionView section = initSectionView(LuSections.SUMMARY, LUConstants.SUMMARY_LABEL_KEY); 
            
        section.addSection(generateSummaryBrief(getH3Title(LUConstants.BRIEF_LABEL_KEY)));
        section.addSection(ViewCluConfigurer.generateSummaryDetails(getH3Title(LUConstants.FULL_VIEW_LABEL_KEY)));
        
        return section;
    }
    
    private static VerticalSection generateSummaryBrief(SectionTitle title) {
        VerticalSection section = new VerticalSection();
        section.addStyleName(LUConstants.STYLE_SECTION_DIVIDER);
        section.addStyleName(LUConstants.STYLE_SECTION);
        section.setSectionTitle(title);
        section.addField(new FieldDescriptor(PROPOSAL + "/" + TITLE, getLabel(LUConstants.TITLE_LABEL_KEY) +":    ", Type.STRING, new KSLabel()));
        section.addField(new FieldDescriptor(COURSE + "/" + SUBJECT_AREA, getLabel(LUConstants.DIVISION_LABEL_KEY), Type.STRING, new KSLabel()));
        section.addField(new FieldDescriptor(COURSE + "/" + COURSE_NUMBER_SUFFIX, getLabel(LUConstants.SUFFIX_CODE_LABEL_KEY), Type.STRING, new KSLabel()));
        
        // FIXME wilj: add proposal/delegate/collab person info to assembly
        section.addField(new FieldDescriptor(PROPOSAL + "/" + PROPOSER_PERSON, getLabel(LUConstants.PROPOSER_LABEL_KEY), Type.LIST, new ProposerPersonList()));
        section.addField(new FieldDescriptor("proposalInfo/todo", getLabel(LUConstants.DELEGATE_LABEL_KEY), Type.STRING, new KSLabel()));
        section.addField(new FieldDescriptor("proposalInfo/todo", getLabel(LUConstants.COLLABORATORS_LABEL_KEY), Type.STRING, new KSLabel()));
        
        // FIXME wilj: add create/update meta info to assembly
        section.addField(new FieldDescriptor(PROPOSAL + "/" + META_INFO + "/" + CREATE_TIME, getLabel(LUConstants.CREATED_DATE_LABEL_KEY), Type.STRING, new KSLabel()));
        section.addField(new FieldDescriptor(PROPOSAL + "/" + META_INFO + "/" + UPDATE_TIME, getLabel(LUConstants.LAST_CHANGED_DATE_LABEL_KEY), Type.STRING, new KSLabel()));
        
        section.addField(new FieldDescriptor(COURSE + "/" + DESCRIPTION + "/" + RichTextInfoConstants.PLAIN, getLabel(LUConstants.DESCRIPTION_LABEL_LABEL_KEY), Type.STRING, new KSLabel()));
       // TODO: Norm: find out why was this prefixed with proposal there is no state on proposal it is on the main object
        section.addField(new FieldDescriptor(CreditCourseProposalConstants.STATE, getLabel(LUConstants.STATUS_LABEL_KEY), Type.STRING, new KSLabel()));
        return section;
    } 
    
    public static void addCluStartSection(ConfigurableLayout layout){
        VerticalSectionView section = initSectionView(LuSections.CLU_BEGIN, LUConstants.START_LABEL_KEY); 

        section.addField(new FieldDescriptor(PROPOSAL + "/" + TITLE , getLabel(LUConstants.PROPOSAL_TITLE_LABEL_KEY), Type.STRING));
        //This will need to be a person picker
        // FIXME wilj: add proposal/delegate/collab person info to assembly
        section.addField(new FieldDescriptor(PROPOSAL + "/" + PROPOSER_PERSON, getLabel(LUConstants.PROPOSAL_PERSON_LABEL_KEY),Type.LIST, new PersonList())) ;
        layout.addStartSection(section);
    }

    /**
     * Adds a section for adding or modifying rules associated with a given course (program).
     *
     * @param layout - a content pane to which this section is added to
     * @return
     */
    private static SectionView generateCourseRequisitesSection() {
        CourseRequisitesSectionView section = new CourseRequisitesSectionView(LuSections.COURSE_REQUISITES, getLabel(LUConstants.REQUISITES_LABEL_KEY), CluProposalModelDTO.class);
        section.setSectionTitle(SectionTitle.generateH1Title(getLabel(LUConstants.REQUISITES_LABEL_KEY)));

		/* TODO: Once we have a section that allows for flow between rule screens
        VerticalSection enrollmentSection = new VerticalSection();
        enrollmentSection.setSectionTitle(SectionTitle.generateH2Title("Enrollment Restrictions"));
        enrollmentSection.addField(new FieldDescriptor("rationalle", "Rationalle", Type.STRING));
        enrollmentSection.addField(new FieldDescriptor("rules", "Rules", Type.STRING));
        enrollmentSection.addWidget(new KSButton());
        section.addSection(enrollmentSection);   */
        
        return section;

    }

    private static SectionView generateActiveDatesSection() {
        VerticalSectionView section = initSectionView(LuSections.ACTIVE_DATES, LUConstants.ACTIVE_DATES_LABEL_KEY); 

        VerticalSection startDate = initSection(getH3Title(LUConstants.START_DATE_LABEL_KEY), WITH_DIVIDER);
        startDate.addField(new FieldDescriptor(COURSE + "/" + EFFECTIVE_DATE, getLabel(LUConstants.EFFECTIVE_DATE_LABEL_KEY), Type.DATE, new KSDatePicker()));

        VerticalSection endDate = initSection(getH3Title(LUConstants.END_DATE_LABEL_KEY), WITH_DIVIDER);
        endDate.addField(new FieldDescriptor(COURSE + "/" + EXPIRATION_DATE, getLabel(LUConstants.EXPIRATION_DATE_LABEL_KEY), Type.DATE, new KSDatePicker()));

        
        section.addSection(startDate);
        section.addSection(endDate);
        
        return section;
    }

    private static SectionView generateFinancialsSection() {
        VerticalSectionView section = initSectionView(LuSections.FINANCIALS, LUConstants.FINANCIALS_LABEL_KEY); 

        //TODO ALL KEYS in this section are place holders until we know actual keys
        VerticalSection feeType = initSection(getH3Title(LUConstants.FEE_TYPE_LABEL_KEY), WITH_DIVIDER);
        feeType.addField(new FieldDescriptor("cluInfo/feeType", null, Type.STRING));

        VerticalSection feeAmount = initSection(getH3Title(LUConstants.FEE_DESC_LABEL_KEY), WITH_DIVIDER);
        feeAmount.addField(new FieldDescriptor(FEES +"/" + FEE_AMOUNT, getLabel(LUConstants.CURRENCY_SYMBOL_LABEL_KEY), Type.STRING));
        feeAmount.addField(new FieldDescriptor(FEES + "/" + TAXABLE, getLabel(LUConstants.TAXABLE_SYMBOL_LABEL_KEY), Type.STRING));//TODO checkboxes go here instead
        feeAmount.addField(new FieldDescriptor(FEES + "/" + FEE_DESC, getLabel(LUConstants.FEE_DESC_LABEL_KEY), Type.STRING, new KSTextArea()));
        feeAmount.addField(new FieldDescriptor(FEES + "/" + INTERNAL_NOTATION, getLabel(LUConstants.INTERNAL_FEE_NOTIFICATION_LABEL_KEY), Type.STRING, new KSTextArea()));

        section.addSection(feeType);
        section.addSection(feeAmount);
        
        return section;
    }

    public static SectionView generateGovernanceSection(){
        VerticalSectionView section = initSectionView(LuSections.GOVERNANCE, LUConstants.GOVERNANCE_LABEL_KEY); 

        VerticalSection oversight = initSection(getH3Title(LUConstants.CURRICULUM_OVERSIGHT_LABEL_KEY), WITH_DIVIDER);    
        oversight.addField(new FieldDescriptor(COURSE + "/" + ACADEMIC_SUBJECT_ORGS, null, Type.STRING, new OrgListPicker()));

        VerticalSection campus = initSection(getH3Title(LUConstants.CAMPUS_LOCATION_LABEL_KEY), WITH_DIVIDER);    
        campus.addField(new FieldDescriptor(COURSE + "/" + CAMPUS_LOCATIONS, null, Type.STRING, new CampusLocationList()));

        VerticalSection adminOrgs = initSection(getH3Title(LUConstants.ADMIN_ORG_LABEL_KEY), WITH_DIVIDER);    
        
        // FIXME the new OrgAdvSearchPicker doesn't fire selection/value change events correctly, won't work with binding
//        adminOrgs.addField(new FieldDescriptor("course/department", null, Type.STRING, configureAdminOrgSearch()));
        FieldDescriptor deptDescriptor = new FieldDescriptor(COURSE + "/" + DEPARTMENT, null, Type.STRING, new OrgListPicker());
        final QueryPath deptPath = QueryPath.parse(deptDescriptor.getFieldKey());
        deptDescriptor.setWidgetBinding(new ModelWidgetBinding<OrgListPicker>() {
        	// FIXME had to create custom binding because the OrgListPicker always returns a list, even of 1
			@Override
			public void setModelValue(OrgListPicker widget, DataModel model,
					String path) {
				model.set(deptPath, widget.getSelectedItem());
				
			}
			@Override
			public void setWidgetValue(OrgListPicker widget, DataModel model,
					String path) {
				widget.setValue((String) model.get(path));
			}
        });
        adminOrgs.addField(deptDescriptor);
        
//        adminOrgs.addField(new FieldDescriptor("cluInfo/primaryAdminOrg/orgId", null, Type.STRING, new OrgPicker()));
//        adminOrgs.addField(new FieldDescriptor("cluInfo/alternateAdminOrgs", null, Type.LIST, new AlternateAdminOrgList()));
        
        
        section.addSection(oversight);
        section.addSection(campus);
        section.addSection(adminOrgs);
        
        return section;

    }

    public static SectionView generateCourseInfoSection(){
        VerticalSectionView section = initSectionView(LuSections.COURSE_INFO, LUConstants.INFORMATION_LABEL_KEY); 

        //FIXME: Label should be key to messaging, field type should come from dictionary?

        //COURSE NUMBER
        CustomNestedSection courseNumber = new CustomNestedSection();
        courseNumber.addStyleName(LUConstants.STYLE_SECTION);
        courseNumber.addStyleName(LUConstants.STYLE_SECTION_DIVIDER);
        courseNumber.setSectionTitle(getH3Title(LUConstants.IDENTIFIER_LABEL_KEY)); 
        courseNumber.setCurrentFieldLabelType(FieldLabelType.LABEL_TOP);
        courseNumber.addField(new FieldDescriptor(COURSE + "/" + SUBJECT_AREA, null, Type.STRING)); //TODO OrgSearch goes here?
        courseNumber.addField(new FieldDescriptor(COURSE + "/" + COURSE_NUMBER_SUFFIX, null, Type.STRING));
        
        // TODO - hide cross-listed, offered jointly and version codes initially, with
        // clickable label to disclose them
        
        // Cross-listed
//        VerticalSection crossListed = new VerticalSection();
//        crossListed.setSectionTitle(getH3Title(LUConstants.CROSS_LISTED_ALT_LABEL_KEY));
//        // crossListed.setInstructions("Enter Department and/or Subject Code/Course Number.");
//        crossListed.addField(new FieldDescriptor("course/alternateIdentifiers", null, Type.LIST, new CrossListedList()));
//        crossListed.addStyleName("KS-LUM-Section-Divider");
//        courseNumber.addSection(crossListed);

        // Offered jointly
        // FIXME wilj: implement offered jointly in assembler 
        VerticalSection offeredJointly = new VerticalSection();
//        offeredJointly.setSectionTitle(getH3Title(LUConstants.JOINT_OFFERINGS_ALT_LABEL_KEY));
//        // offeredJointly.setInstructions("Enter an existing course or proposal.");
//        offeredJointly.addField(new FieldDescriptor("cluInfo/offeredJointly", null, Type.LIST, new OfferedJointlyList()));
//        offeredJointly.addStyleName("KS-LUM-Section-Divider");
//        courseNumber.addSection(offeredJointly);

        // FIXME wilj: version codes should be split out into a separate field on CreditCourse
        //Version Codes
//        VerticalSection versionCodes = new VerticalSection();
//        versionCodes.setSectionTitle(getH3Title(LUConstants.VERSION_CODES_LABEL_KEY));
//        versionCodes.addField(new FieldDescriptor("course/alternateIdentifiers", null, Type.LIST, new VersionCodeList()));
//        versionCodes.addStyleName("KS-LUM-Section-Divider");
//        courseNumber.addSection(versionCodes);

        VerticalSection longTitle = initSection(getH3Title(LUConstants.TITLE_LABEL_KEY), WITH_DIVIDER);
        KSTextArea textArea = new KSTextArea();
        textArea.setWidth("50");
        FieldDescriptor fd = new FieldDescriptor("course/courseTitle", null, Type.STRING);
//        Callback<Boolean> callback =  getSubjectValidationCallback(fd,objectKey);
  //      fd.setValidationCallBack(callback);
        longTitle.addField(fd);
        
        VerticalSection shortTitle = initSection(getH3Title(LUConstants.SHORT_TITLE_LABEL_KEY), WITH_DIVIDER);
        shortTitle.addField(new FieldDescriptor(COURSE + "/" + TRANSCRIPT_TITLE, null, Type.STRING));
        
        VerticalSection description = initSection(getH3Title(LUConstants.DESCRIPTION_LABEL_KEY), WITH_DIVIDER);
        description.addField(new FieldDescriptor(COURSE + "/" + DESCRIPTION, null, Type.MODELDTO, new KSRichEditor()));
        
        VerticalSection rationale = initSection(getH3Title(LUConstants.RATIONALE_LABEL_KEY), WITH_DIVIDER);
        rationale.addField(new FieldDescriptor(COURSE + "/" + RATIONALE, null, Type.MODELDTO, new KSRichEditor()));
        
        section.addSection(courseNumber);
        section.addSection(shortTitle);
        section.addSection(longTitle);
        section.addSection(description);
        section.addSection(rationale);

        return section;
    }

//    public static Callback<Boolean> getSubjectValidationCallback(final FieldDescriptor subjectFD, final String objectKey){
  //      return new Callback<Boolean>() {
    //        @Override
      //      public void exec(Boolean result) {
        //        ModelDTOConstraintSetupFactory bc = new ModelDTOConstraintSetupFactory();
          //      final Validator val = new Validator(bc, true);
            //    final ValidateResultEvent e = new ValidateResultEvent();
              //          ObjectStructure objStructure = Application.getApplicationContext().getDictionaryData(objectKey);
                //        if(objStructure == null){
                  //          Window.alert("Cannot load dictionary(object structure)");
                    //    }
                      //  List<ValidationResultContainer> results = val.validateTypeStateObject((ModelDTO) m.get(), objStructure);
                        //e.setValidationResult(results);// filled by calling the real validate code
                        //Controller.findController(subjectFD.getFieldWidget()).fireApplicationEvent(e);


//            }
  //      };
   // }
    

        
    public static SectionView generateCourseLogisticsSection() {
        VerticalSectionView section = initSectionView(LuSections.COURSE_LOGISTICS, LUConstants.LOGISTICS_LABEL_KEY); 

        VerticalSection instructors = initSection(getH3Title(LUConstants.INSTRUCTOR_LABEL_KEY), WITH_DIVIDER);
        // FIXME wilj: do we need to set the instructor's orgId? or can we default it at the assembler level?
        instructors.addField(new FieldDescriptor(COURSE + "/" + PRIMARY_INSTRUCTOR, null, Type.STRING));
//      instructors.addField(new FieldDescriptor("cluInfo/instructors", null, Type.LIST, new AlternateInstructorList()));

        //CREDITS
        //TODO: These needs to be mapped to learning results
//        VerticalSection credits = initSection(getH3Title(LUConstants.CREDITS_LABEL_KEY), WITH_DIVIDER);
//        credits.addField(new FieldDescriptor("cluInfo/creditType", getLabel(LUConstants.CREDIT_LABEL_KEY), Type.STRING));
//        credits.addField(new FieldDescriptor("cluInfo/creditValue", getLabel(LUConstants.CREDIT_VALUE_LABEL_KEY), Type.STRING));
//        credits.addField(new FieldDescriptor("cluInfo/maxCredits", getLabel(LUConstants.MAX_CREDITS_LABEL_KEY), Type.STRING));
//        VerticalSection learningResults = initSection(getH3Title(LUConstants.LEARNING_RESULTS_LABEL_KEY), WITH_DIVIDER);
//        learningResults.addField(new FieldDescriptor("cluInfo/evalType", getLabel(LUConstants.EVALUATION_TYPE_LABEL_KEY), Type.STRING)); //TODO EVAL TYPE ENUMERATION ????

        VerticalSection scheduling = initSection(getH3Title(LUConstants.SCHEDULING_LABEL_KEY), WITH_DIVIDER);
        // FIXME wilj: termsOffered not currently populated by assembler
        scheduling.addField(new FieldDescriptor(COURSE + "/" + DURATION + "/" + TERM_TYPE, getLabel(LUConstants.TERM_LITERAL_LABEL_KEY), Type.STRING, new AtpTypeList()));
        scheduling.addField(new FieldDescriptor(COURSE + "/" + DURATION + "/" + QUANTITY, getLabel(LUConstants.DURATION_LITERAL_LABEL_KEY), Type.INTEGER)); //TODO DURATION ENUMERATION


        //COURSE FORMATS
        VerticalSection courseFormats = initSection(getH3Title(LUConstants.FORMATS_LABEL_KEY), WITH_DIVIDER);
        courseFormats.addField(new FieldDescriptor(COURSE + "/" + FORMATS, null, Type.LIST, new CourseFormatList()));

        section.addSection(instructors);
//        section.addSection(credits);
//        section.addSection(learningResults);
        section.addSection(scheduling);
        section.addSection(courseFormats);

        
        return section;
    }

//    private static SectionView generateLearningObjectivesSection() {
//    	// FIXME wilj: assembler does not contain any LO stuff yet
//        VerticalSectionView section = initSectionView(LuSections.LEARNING_OBJECTIVES, LUConstants.LEARNING_OBJECTIVES_LABEL_KEY); 
//
//        VerticalSection los = initSection(null, NO_DIVIDER);    
//
//        los.addField(new FieldDescriptor("cluInfo/loInfos", null, Type.LIST, new LearningObjectiveList()));
//        los.addStyleName("KS-LUM-Section-Divider");
//        
//        section.addSection(los);
//        return section;        
//    }
    
    public static class CourseFormatList extends UpdatableMultiplicityComposite {

        {
            setAddItemLabel(getLabel(LUConstants.COURSE_ADD_FORMAT_LABEL_KEY));
            setItemLabel(getLabel(LUConstants.FORMAT_LABEL_KEY));
        }

        public Widget createItem() {
        	VerticalSection item = new VerticalSection();
            // TODO: NORM: find out why this was not prefixed with "course/formats/" so it is "course/formats/activities
            // item.addField(new FieldDescriptor("activities", null, Type.LIST, new CourseActivityList()));
            item.addField(new FieldDescriptor(ACTIVITIES, null, Type.LIST, new CourseActivityList()));
            return item;
        }
    }

    public static class CourseActivityList extends UpdatableMultiplicityComposite {

        {
            setAddItemLabel(getLabel(LUConstants.ADD_ACTIVITY_LABEL_KEY));
            setItemLabel(getLabel(LUConstants.ACTIVITY_LITERAL_LABEL_KEY));
        }

        public Widget createItem() {
            CustomNestedSection activity = new CustomNestedSection();
            activity.addField(new FieldDescriptor("activityType", getLabel(LUConstants.ACTIVITY_TYPE_LABEL_KEY), Type.STRING, new CluActivityType()));
            activity.nextRow();

            /* CreditInfo is deprecated, needs to be replaced with learning results
            activity.setCurrentFieldLabelType(FieldLabelType.LABEL_TOP);
            activity.addField(new FieldDescriptor("creditInfo", "Credit Value", Type.STRING));
            activity.nextRow();
            activity.setCurrentFieldLabelType(FieldLabelType.LABEL_TOP);
            activity.addField(new FieldDescriptor("evalType", "Learning Result", Type.STRING));
            activity.nextRow();
            */

            activity.setCurrentFieldLabelType(FieldLabelType.LABEL_TOP);
            // FIXME need to get the term offered added to the activity metadata?
//            activity.addField(new FieldDescriptor("term", getLabel(LUConstants.TERM_LITERAL_LABEL_KEY), Type.STRING, new AtpTypeList()));
            // FIXME last I checked, the activity had an intensity but not a duration, need to recheck.  either add it to the assembler, or remove it from here  
//            activity.addField(new FieldDescriptor("stdDuration/timeQuantity", getLabel(LUConstants.DURATION_LITERAL_LABEL_KEY), Type.INTEGER)); //TODO dropdown need here?

            // FIXME contact hours needs to be reworked
//            activity.nextRow();
//            activity.setCurrentFieldLabelType(FieldLabelType.LABEL_TOP);
//            activity.addField(new FieldDescriptor("contactHours/hrs", getLabel(LUConstants.CONTACT_HOURS_LABEL_KEY), Type.INTEGER));
//            // FIXME look up what the label and implement as dropdown
//            activity.addField(new FieldDescriptor("contactHours/per", getLabel(label goes here), Type.STRING));
//            // FIXME add defaultEnrollmentEstimate to assembler
//            activity.addField(new FieldDescriptor("defaultEnrollmentEstimate", getLabel(LUConstants.CLASS_SIZE_LABEL_KEY), Type.INTEGER));

            return activity;
        }

    }

    // TODO look up field label and type from dictionary & messages

//    private static Type translateDictType(String dictType) {
//        if (dictType.equalsIgnoreCase("String"))
//            return Type.STRING;
//        else
//            return null;
//    }
//
//    private static FieldDescriptor createMVCFieldDescriptor(String fieldName,
//            String objectKey, String type, String state  ) {
//
//        Field f = LUDictionaryManager.getInstance().getField(objectKey, type, state, fieldName);
//
//        FieldDescriptor fd =
//            new FieldDescriptor(f.getKey(),
//                    getLabel(type, state, f.getKey() ),
//                    translateDictType(f.getFieldDescriptor().getDataType())
//            );
//        return fd;
//    }
//
//
//    private static String getLabel(String type, String state, String fieldId) {
//        String labelKey = type + ":" + state + ":" + fieldId;
//        System.out.println(labelKey);
//        return context.getMessage(labelKey);
//    }
//

    //FIXME: This is a temp widget impl for the Curriculum Oversight field. Don't yet know if this
    //will be a multiple org select field, in which case we need a multiple org select picker widget.
    //Otherwise if it's single org picker, need a way to bind a HasText widget to ModelDTOList
    public static class OrgListPicker extends KSSelectItemWidgetAbstract implements SuggestPicker{
        private OrgPicker orgPicker;

        public OrgListPicker(){
            orgPicker = new OrgPicker();
            initWidget(orgPicker);
        }

        public void deSelectItem(String id) {
            throw new UnsupportedOperationException();
        }

        public List<String> getSelectedItems() {
            ArrayList<String> selectedItems = new ArrayList<String>();
            selectedItems.add(orgPicker.getValue());
            return selectedItems;
        }

        public boolean isEnabled() {
            return true;
        }

        public void onLoad() {
        }

        public void redraw() {
            throw new UnsupportedOperationException();
        }

        public void selectItem(String id) {
            orgPicker.setValue(id);
        }

        public void setEnabled(boolean b) {
            throw new UnsupportedOperationException();
        }

        public boolean isMultipleSelect(){
            return true;
        }
        
        public void clear(){
            orgPicker.clear();
        }

		@Override
		public HandlerRegistration addFocusHandler(FocusHandler handler) {
			return orgPicker.addFocusHandler(handler);
		}

		@Override
		public HandlerRegistration addBlurHandler(BlurHandler handler) {
			return orgPicker.addBlurHandler(handler);
		}

		@Override
		public String getValue() {
			return orgPicker.getValue();
		}

		@Override
		public void setValue(String value) {
			orgPicker.setValue(value);
			
		}

		@Override
		public void setValue(String value, boolean fireEvents) {
			orgPicker.setValue(value, fireEvents);
			
		}

		@Override
		public HandlerRegistration addValueChangeHandler(
				ValueChangeHandler<String> handler) {
			return orgPicker.addValueChangeHandler(handler);
		}
    }
    
    // FIXME uncomment and fix AlternateAdminOrgList and AlternateInstructorList
//    public static class AlternateAdminOrgList extends MultiplicityCompositeWithLabels {
//        {
//            setAddItemLabel("Add an Alternate Admin Organization");
//            setItemLabel("Organization ID ");
//        }
//
//        @Override
//        public Widget createItem() {
//            MultiplicitySection multi = new MultiplicitySection("");
//            
//            CustomNestedSection ns = new CustomNestedSection();
//            ns.setCurrentFieldLabelType(FieldLabelType.LABEL_TOP);
//            ns.addField(new FieldDescriptor("orgId", "Organization ID", Type.STRING, new OrgPicker() ));
//            multi.addSection(ns);
//            
//            return multi;
//        }
//    }
//    
//    public static class AlternateInstructorList extends MultiplicityCompositeWithLabels {
//        {
//            setAddItemLabel("Add an Alternate Instructor.");
//            setItemLabel("Instructor ID");
//        }
//
//        @Override
//        public Widget createItem() {
//            MultiplicitySection multi = new MultiplicitySection("");
//            
//            CustomNestedSection ns = new CustomNestedSection();
//            ns.setCurrentFieldLabelType(FieldLabelType.LABEL_TOP);
//            ns.addField(new FieldDescriptor("personId", "Instructor ID", Type.STRING /*, new InstructorPicker() */ ));
//            multi.addSection(ns);
//            
//            return multi;
//        }
//    }

    //FIXME: Create a configurable checkbox list which can obtain values via RPC calls
    public static class CampusLocationList extends KSCheckBoxList{
        public CampusLocationList(){
            SimpleListItems campusLocations = new SimpleListItems();

            campusLocations.addItem("NorthCountyCampus", "North County Campus");
            campusLocations.addItem("SouthCountyCampus", "South County Campus");
            campusLocations.addItem("ExtendedStudiesCampus", "Extended Studies Campus");
            campusLocations.addItem("AllCampuses", "All Campuses");

            super.setListItems(campusLocations);
        }
    }

    //FIXME: Create a configurable drop down list which can obtain values via RPC calls
    public static class CluActivityType extends KSDropDown{
        public CluActivityType(){
            SimpleListItems activityTypes = new SimpleListItems();

            activityTypes.addItem("kuali.lu.type.activity.Tutorial", "Tutorial");
            activityTypes.addItem("kuali.lu.type.activity.Lecture", "Lecture");
            activityTypes.addItem("kuali.lu.type.activity.WebLecture", "WebLecture");
            activityTypes.addItem("kuali.lu.type.activity.Discussion", "Discussion");
            activityTypes.addItem("kuali.lu.type.activity.Lab", "Lab");
            activityTypes.addItem("kuali.lu.type.activity.Directed", "Directed");
            activityTypes.addItem("kuali.lu.type.activity.WebDiscuss", "WebDiscuss");

            super.setListItems(activityTypes);
            this.selectItem("kuali.lu.type.activity.Lecture");
        }
    }

    //FIXME: Is this what should be part of the term field
    public static class AtpTypeList extends KSDropDown{
        public AtpTypeList(){
            SimpleListItems activityTypes = new SimpleListItems();

            activityTypes.addItem("atpType.semester.fall", "Fall");
            activityTypes.addItem("atpType.semester.spring", "Spring");
            activityTypes.addItem("atpType.semester.summer", "Summer");
            activityTypes.addItem("atpType.semester.winter", "Winter");

            super.setListItems(activityTypes);
        }

        //FIXME: This is a hack, since field is a list, but wireframe allows only single select
        public boolean isMultipleSelect(){
            return false;
        }
    }

    //FIXME: This needs to be a proper person picker (or some other widget person entry widget)
    public static class PersonList extends KSDropDown{
        public PersonList(){
            SimpleListItems people = new SimpleListItems();

            String userId = Application.getApplicationContext().getUserId();
            people.addItem(userId, userId);

            super.setListItems(people);
            this.selectItem(userId);
        }

        //FIXME: This is a hack, since field is a list, but wireframe allows only single select
        public boolean isMultipleSelect(){
            return true;
        }
    }

    public static class ProposerPersonList extends KSLabelList {
        public ProposerPersonList(){
            SimpleListItems list = new SimpleListItems();

            super.setListItems(list);
        }
    }
    // FIXME uncomment and fix CrossListedList, LearningObjectiveList, OfferedJointlyList, and VersionCodeList
//    public static class CrossListedList extends MultiplicityCompositeWithLabels {        
//        {
//            setAddItemLabel(getLabel(LUConstants.ADD_CROSS_LISTED_LABEL_KEY));
//            setItemLabel(getLabel(LUConstants.CROSS_LISTED_ITEM_LABEL_KEY));
//        }
//        
//        @Override
//        public Widget createItem() {
//            MultiplicitySection multi = new MultiplicitySection(CluDictionaryClassNameHelper.CLU_IDENTIFIER_INFO_CLASS,
//                                                                "kuali.lu.type.CreditCourse.identifier.cross-listed",
//                                                                "active");
//            CustomNestedSection ns = new CustomNestedSection();
//            ns.setCurrentFieldLabelType(FieldLabelType.LABEL_TOP);
//            ns.addField(new FieldDescriptor("orgId", getLabel(LUConstants.DEPT_LABEL_KEY), Type.STRING, new OrgPicker()));
//            ns.nextRow();
//            ns.setCurrentFieldLabelType(FieldLabelType.LABEL_TOP);
//            ns.addField(new FieldDescriptor("code", getLabel(LUConstants.SUBJECT_CODE_LABEL_KEY), Type.STRING));//TODO OrgSearch goes here?
//            ns.addField(new FieldDescriptor("suffixCode", getLabel(LUConstants.COURSE_NUMBER_LABEL_KEY), Type.STRING));
//            
//            multi.addSection(ns);
//            
//            return multi;
//        }
//    }
//    
//    public static class LearningObjectiveList extends SimpleMultiplicityComposite {        
//		{
//            setAddItemLabel(getLabel(LUConstants.LEARNING_OBJECTIVE_ADD_LABEL_KEY));
//        }
//
//		
//		@Override
//	    public void onLoad() {
//	        super.onLoad();
//	        if (!loaded) {
//	            loaded = true;
//
//	            // TODO - what do we do when they delete an LO?
//	            // (as far as updating the model). If they clear
//	            // an LO textfield, I think we delete the item from
//	            // the multiplicity, and delete the LO via the service
//	            // if it exists
//	            
//				// populate with at least NUM_INITIAL_LOS items,
//				// even if there aren't that many defined yet
//				int startIdx = null == modelDTOList ? 0 : modelDTOList.get().size();
//	            for (int i = startIdx; i < NUM_INITIAL_LOS; i++) {
//	            	addItem();
//	            }
//	        }
//	    }
//
//		@Override
//		public void redraw() {
//			super.redraw();
//			// populate with at least NUM_INITIAL_LOS items,
//			// even if there aren't that many defined yet
//			int startIdx = null == modelDTOList ? 0 : modelDTOList.get().size();
//            for (int i = startIdx; i < NUM_INITIAL_LOS; i++) {
//            	addItem();
//            }
//		}
//        
//        @Override
//        public Widget createItem() {
//            MultiplicitySection multi = new MultiplicitySection(CluDictionaryClassNameHelper.LO_INFO_CLASS,
//                                                                "kuali.lo.type.singleUse", "draft");
//            CustomNestedSection ns = new CustomNestedSection();
//            ns.setCurrentFieldLabelType(FieldLabelType.LABEL_TOP);
//            LOPicker picker = new LOPicker();
//            picker.addValueChangeHandler(new ValueChangeHandler<String>() {
//				@Override
//				public void onValueChange(ValueChangeEvent<String> event) {
//					// System.out.println("Value changed");
//				}
//			});
//            // FIXME wilj: definitely wrong path for LO picker
//            FieldDescriptor fd = new FieldDescriptor("desc", null/* getLabel(LUConstants.LEARNING_OBJECTIVE_LO_NAME_KEY)*/, Type.STRING, picker);
//            ns.addField(fd);
//            multi.addSection(ns);
//            
//            return multi;
//        }
//    }
//    
//    public static class OfferedJointlyList extends MultiplicityCompositeWithLabels {
//        {
//            setAddItemLabel(getLabel(LUConstants.ADD_EXISTING_LABEL_KEY));
//            setItemLabel(getLabel(LUConstants.JOINT_OFFER_ITEM_LABEL_KEY));
//        }
//
//        @Override
//        public Widget createItem() {
//            MultiplicitySection multi = new MultiplicitySection("");
//            
//            CustomNestedSection ns = new CustomNestedSection();
//            ns.setCurrentFieldLabelType(FieldLabelType.LABEL_TOP);
//            ns.addField(new FieldDescriptor("id", getLabel(LUConstants.COURSE_NUMBER_OR_TITLE_LABEL_KEY), Type.STRING /*, new CluPicker() */ ));
//            multi.addSection(ns);
//            
//            return multi;
//        }
//    }
//    
//    public static class VersionCodeList extends MultiplicityCompositeWithLabels {
//        {
//            setAddItemLabel(getLabel(LUConstants.ADD_VERSION_CODE_LABEL_KEY));
//            setItemLabel(getLabel(LUConstants.VERSION_CODE_LABEL_KEY));
//        }
//
//        @Override
//        public Widget createItem() {
//            MultiplicitySection multi = new MultiplicitySection(CluDictionaryClassNameHelper.CLU_IDENTIFIER_INFO_CLASS,
//                                                                "kuali.lu.type.CreditCourse.identifier.version",
//                                                                "active");
//            CustomNestedSection ns = new CustomNestedSection();
//            ns.setCurrentFieldLabelType(FieldLabelType.LABEL_TOP);
//            ns.addField(new FieldDescriptor("variation", getLabel(LUConstants.CODE_LABEL_KEY), Type.STRING));//TODO wrong key
//            ns.addField(new FieldDescriptor("shortName", getLabel(LUConstants.TITLE_LITERAL_LABEL_KEY), Type.STRING));//TODO wrong key
//            multi.addSection(ns);
//            
//            return multi;
//        }
//    }

    private static KSSearchComponent configureAdminOrgSearch() {
    	   
    	OrgRpcServiceAsync orgRpcServiceAsync = GWT.create(OrgRpcService.class);
    	
    	List<String> basicCriteria = new ArrayList<String>() {
  		   {
  		      add("org.queryParam.orgOptionalLongName");
  		   }
  		};
  	
  		List<String> advancedCriteria = new ArrayList<String>() {
   		   {
   			   add("org.queryParam.orgOptionalLongName");
   			   add("org.queryParam.orgOptionalLocation");
   			   add("org.queryParam.orgOptionalId");
   		   }
   		};    
   		
        //set context criteria
   		List<QueryParamValue> contextCriteria = new ArrayList<QueryParamValue>();   		
		QueryParamValue orgOptionalTypeParam = new QueryParamValue();
		orgOptionalTypeParam.setKey("org.queryParam.orgOptionalType");
		orgOptionalTypeParam.setValue("kuali.org.Department");   
		contextCriteria.add(orgOptionalTypeParam);      		
    	
    	SearchComponentConfiguration searchConfig = new SearchComponentConfiguration(contextCriteria, basicCriteria, advancedCriteria);
    	
    	searchConfig.setSearchDialogTitle("Find Organization");
    	searchConfig.setSearchService(orgRpcServiceAsync);
    	searchConfig.setSearchTypeKey("org.search.advanced");
    	searchConfig.setResultIdKey("org.resultColumn.orgId");
    	searchConfig.setRetrievedColumnKey("org.resultColumn.orgOptionalLongName");    	
    	
    	//TODO: following code should be in KSSearchComponent with config parameters set within SearchComponentConfiguration class
    	final SearchSuggestOracle orgSearchOracle = new SearchSuggestOracle(searchConfig.getSearchService(),
    	        "org.search.orgByShortNameAndType", 
    	        "org.queryParam.orgShortName", //field user is entering and we search on... add '%' the parameter
    	        "org.queryParam.orgId", 		//if one wants to search by ID rather than by name
    	        "org.resultColumn.orgId", 		
    	        "org.resultColumn.orgShortName");
    	  			
		//Restrict searches to Department Types
		ArrayList<QueryParamValue> params = new ArrayList<QueryParamValue>();
		QueryParamValue orgTypeParam = new QueryParamValue();
		orgTypeParam.setKey("org.queryParam.orgType");
		orgTypeParam.setValue("kuali.org.Department");
		params.add(orgTypeParam);
		orgSearchOracle.setAdditionalQueryParams(params);	
    	
    	return new KSSearchComponent(searchConfig, orgSearchOracle);
    }   

    /*
     * Configuring Program specific screens.
     */
    public static void configureProgramProposal(ConfigurableLayout layout, String objectKey, String typeKey, String stateKey) {
    	
    	groupName = LUConstants.PROGRAM_GROUP_NAME;
    	
        addCluStartSection(layout);

        layout.addSection(new String[] {getLabel(LUConstants.ACADEMIC_CONTENT_LABEL_KEY)}, generateProgramInfoSection());       
        
        layout.addTool(new CollaboratorTool());
        layout.addTool(new CommentPanel(LuSections.COMMENTS, LUConstants.TOOL_COMMENTS));
        layout.addTool(new DocumentTool(LuSections.DOCUMENTS, LUConstants.TOOL_DOCUMENTS));
    }
    
    
    public static SectionView generateProgramInfoSection(){
        VerticalSectionView section = initSectionView(LuSections.PROGRAM_INFO, LUConstants.INFORMATION_LABEL_KEY); 

        VerticalSection shortTitle = initSection(getH3Title(LUConstants.SHORT_TITLE_LABEL_KEY), WITH_DIVIDER);
        shortTitle.addField(new FieldDescriptor("cluInfo/officialIdentifier/shortName", null, Type.STRING));       
        
        section.addSection(shortTitle);

        return section;
    }    
    
    public static class CollaboratorTool extends ToolView{
        public CollaboratorTool(){
            super(LuSections.AUTHOR, LUConstants.SECTION_AUTHORS_AND_COLLABORATORS);
        }

        @Override
        protected Widget createWidget() {
            return new Collaborators();
        }

    }
    
    private static VerticalSectionView initSectionView (Enum<?> viewEnum, String labelKey) {
        VerticalSectionView section = new VerticalSectionView(viewEnum, getLabel(labelKey), CLU_PROPOSAL_MODEL);
        section.addStyleName(LUConstants.STYLE_SECTION);
        section.setSectionTitle(getH1Title(labelKey));
        
        return section;
    }
    
    
    private static VerticalSection initSection(SectionTitle title, boolean withDivider) {
        VerticalSection section = new VerticalSection();
        if (title !=  null) {
          section.setSectionTitle(title);
        }
        section.addStyleName(LUConstants.STYLE_SECTION);
        if (withDivider)
            section.addStyleName(LUConstants.STYLE_SECTION_DIVIDER);
        return section;
    }
    
    private static String getLabel(String labelKey) {
        return Application.getApplicationContext().getUILabel(groupName, "course", "draft", labelKey);
    }
    
    private static SectionTitle getH1Title(String labelKey) {
        return SectionTitle.generateH1Title(getLabel(labelKey));
    } 
    
    private static SectionTitle getH2Title(String labelKey) {
        return SectionTitle.generateH2Title(getLabel(labelKey));
    } 
    
    private static SectionTitle getH3Title(String labelKey) {
        return SectionTitle.generateH3Title(getLabel(labelKey));
    } 
    
    private static SectionTitle getH4Title(String labelKey) {
        return SectionTitle.generateH4Title(getLabel(labelKey));
    } 
    
    private static SectionTitle getH5Title(String labelKey) {
        return SectionTitle.generateH5Title(getLabel(labelKey));
    }
    
    
}