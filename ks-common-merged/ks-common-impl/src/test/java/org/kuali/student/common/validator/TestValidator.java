package org.kuali.student.common.validator;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.kuali.student.common.olddictionary.dto.ObjectStructureDefinition;
import org.kuali.student.common.dictionary.service.impl.DictionaryServiceImpl;
import org.kuali.student.common.dto.ContextInfo;
import org.kuali.student.common.exceptions.DoesNotExistException;
import org.kuali.student.common.exceptions.InvalidParameterException;
import org.kuali.student.common.exceptions.MissingParameterException;
import org.kuali.student.common.exceptions.OperationFailedException;
import org.kuali.student.common.oldsearch.dto.SearchCriteriaTypeInfo;
import org.kuali.student.common.oldsearch.dto.SearchRequest;
import org.kuali.student.common.oldsearch.dto.SearchResult;
import org.kuali.student.common.oldsearch.dto.SearchResultRow;
import org.kuali.student.common.oldsearch.dto.SearchResultTypeInfo;
import org.kuali.student.common.oldsearch.dto.SearchTypeInfo;
import org.kuali.student.common.search.service.SearchDispatcher;
import org.kuali.student.common.oldsearch.service.SearchService;
import org.kuali.student.common.search.service.impl.SearchDispatcherImpl;
import org.kuali.student.common.oldvalidation.dto.ValidationResultInfo;

public class TestValidator {
	DefaultValidatorImpl val = null;
	ValidatorFactory valFactory = new ValidatorFactory();
	DictionaryServiceImpl dictionaryDelegate = new DictionaryServiceImpl("classpath:test-validator-context.xml");
	
	@Before
	public void init() {
		
		val = new DefaultValidatorImpl();
		val.setDateParser(new ServerDateParser());
		val.setMessageService(null);
		valFactory.setDefaultValidator(val);

	}
		
	@Test     
    public void testRequired(ContextInfo context) {
    	    	
    	List<ValidationResultInfo> results = val.validateObject( buildTestPerson1(), getSimpleStudentObjectStructure(), context);
    	assertEquals(results.size(), 1);

    	assertEquals(results.get(0).getErrorLevel(), ValidationResultInfo.ErrorLevel.ERROR);
    	assertEquals(results.get(0).getMessage(), "validation.required");
    }
    

    @Test     
    public void testLengthRange(ContextInfo context) {
    	
    	ConstraintMockPerson p = buildTestPerson1();
    	p.setFirstName("thisisaveryveryverylo");
    	
    	List<ValidationResultInfo> results = val.validateObject( p, getSimpleStudentObjectStructure(), context);
    	assertEquals(results.size(), 2);

    	assertEquals(results.get(0).getErrorLevel(), ValidationResultInfo.ErrorLevel.ERROR);
    	assertEquals(results.get(0).getMessage(), "validation.lengthOutOfRange");
    }
    
    @Test     
    public void testMinLength(ContextInfo context) {
    	
    	ConstraintMockPerson p = buildTestPerson1();
    	p.setFirstName("t");

    	ObjectStructureDefinition o1 = getSimpleStudentObjectStructure();
    	o1.getAttributes().get(0).setMaxLength(null);
    	
    	List<ValidationResultInfo> results = val.validateObject( p, o1, context);    
    	assertEquals(results.size(), 2);

    	assertEquals(results.get(0).getErrorLevel(), ValidationResultInfo.ErrorLevel.ERROR);
    	assertEquals(results.get(0).getMessage(), "validation.minLengthFailed");
    }
    

    @Test
    public void testMinDateValue(ContextInfo context) {
    	ConstraintMockPerson p = buildTestPerson1();
    	ServerDateParser sp = new ServerDateParser();
    	p.setDob(sp.parseDate("1960-01-01"));
    	ObjectStructureDefinition o1 = getSimpleStudentObjectStructure();
    	
    	List<ValidationResultInfo> results = val.validateObject( p, o1, context);    
    	assertEquals(results.size(), 1);

    	assertEquals(results.get(0).getErrorLevel(), ValidationResultInfo.ErrorLevel.ERROR);
    	assertEquals(results.get(0).getMessage(), "validation.minValueFailed");    	
    }
    
    @Test     
    public void testMaxLength(ContextInfo context) {
    	
    	ConstraintMockPerson p = buildTestPerson1();
    	p.setFirstName("thisisaveryveryverylo");

    	ObjectStructureDefinition o1 = getSimpleStudentObjectStructure();
    	o1.getAttributes().get(0).setMinLength(0);
    	
    	List<ValidationResultInfo> results = val.validateObject( p, o1, context);    
    	assertEquals(results.size(), 2);

    	assertEquals(results.get(0).getErrorLevel(), ValidationResultInfo.ErrorLevel.ERROR);
    	assertEquals(results.get(0).getMessage(), "validation.maxLengthFailed");
    }
    
    @Test     
    public void testValidChars(ContextInfo context) {
    	    	
    	ConstraintMockPerson p = buildTestPerson1();
    	p.setFirstName("in$#valid");

    	ObjectStructureDefinition o1 = getSimpleStudentObjectStructure();
    	
    	List<ValidationResultInfo> results = val.validateObject( p, o1, context);    
    	assertEquals(results.size(), 2);

    	assertEquals(results.get(0).getErrorLevel(), ValidationResultInfo.ErrorLevel.ERROR);
    	assertEquals(results.get(0).getMessage(), "validation.validCharsFailed");
    }


    @Test     
    public void testDoubleValueRange(ContextInfo context) {
    	
    	ConstraintMockPerson p = buildTestPerson2();
    	p.setGpa(5.0);

    	ObjectStructureDefinition o1 = getSimpleStudentObjectStructure();
    	
    	List<ValidationResultInfo> results = val.validateObject( p, o1, context);    
    	assertEquals(results.size(), 1);

    	assertEquals(results.get(0).getErrorLevel(), ValidationResultInfo.ErrorLevel.ERROR);
    	assertEquals(results.get(0).getMessage(), "validation.outOfRange");
    }
    
    @Test
    public void testNestedStructures(ContextInfo context) {    	
    	ConstraintMockPerson p = buildTestPerson3();

    	ObjectStructureDefinition o = getStudentWithAddressObjectStructure();
    	
    	List<ValidationResultInfo> results = val.validateObject( p, o, context);
//     ERROR address/0/line1 validation.required
//     ERROR address/0/line2 validation.validCharsFailed
//     ERROR address/0/line2 validation.requiresField
//     ERROR address/0/province validation.required
//     ERROR address/0/postalCode validation.lengthOutOfRange
//     ERROR address/0/type validation.lengthOutOfRange
//     ERROR address/0/state validation.lengthOutOfRange
     System.out.println (results.size () + " errors found");
     for (ValidationResultInfo vri : results)
     {
      System.out.println (vri.getErrorLevel () + " " + vri.getElement () + " " + vri.getMessage ());
     }
    	assertEquals(7, results.size());

    	assertEquals(results.get(0).getErrorLevel(), ValidationResultInfo.ErrorLevel.ERROR);
    	assertEquals(results.get(0).getMessage(), "validation.required");

    	assertEquals(results.get(1).getErrorLevel(), ValidationResultInfo.ErrorLevel.ERROR);
    	assertEquals(results.get(1).getMessage(), "validation.validCharsFailed");    	
    	assertEquals(results.get(2).getMessage(), "validation.requiresField");
    	SearchDispatcher searchDispatcher= new SearchDispatcherImpl(new MockSearchService());
    	
    	val.setSearchDispatcher(searchDispatcher);
    	p.getAddress().get(0).setLine1("something");
    	results = val.validateObject( p, o, context);
     System.out.println (results.size () + " errors found");
     for (ValidationResultInfo vri : results)
     {
      System.out.println (vri.getErrorLevel () + " " + vri.getElement () + " " + vri.getMessage ());
     }
    	assertEquals(6, results.size());

    	p.getAddress().get(0).setLine2("notrightlookupvalue");
    	results = val.validateObject( p, o, context); 
     System.out.println (results.size () + " errors found");
     for (ValidationResultInfo vri : results)
     {
      System.out.println (vri.getErrorLevel () + " " + vri.getElement () + " " + vri.getMessage ());
     }
    	assertEquals(5, results.size());
    	assertEquals(results.get(0).getErrorLevel(), ValidationResultInfo.ErrorLevel.ERROR);
    	assertEquals(results.get(0).getMessage(), "validation.lookup");   
    }
    
    public class MockSearchService implements SearchService{

		@Override
		public SearchCriteriaTypeInfo getSearchCriteriaType(
				String searchCriteriaTypeKey, ContextInfo context) throws DoesNotExistException,
				InvalidParameterException, MissingParameterException,
				OperationFailedException {
			return null;
		}

		@Override
		public List<SearchCriteriaTypeInfo> getSearchCriteriaTypes(ContextInfo context)
				throws OperationFailedException {
			return null;
		}

		@Override
		public SearchResultTypeInfo getSearchResultType(
				String searchResultTypeKey, ContextInfo context) throws DoesNotExistException,
				InvalidParameterException, MissingParameterException,
				OperationFailedException {
			return null;
		}

		@Override
		public List<SearchResultTypeInfo> getSearchResultTypes(ContextInfo context)
				throws OperationFailedException {
			return null;
		}

		@Override
		public SearchTypeInfo getSearchType(String searchTypeKey, ContextInfo context)
				throws DoesNotExistException, InvalidParameterException,
				MissingParameterException, OperationFailedException {
			return null;
		}

		@Override
		public List<SearchTypeInfo> getSearchTypes(ContextInfo context)
				throws OperationFailedException {
			return null;
		}

		@Override
		public List<SearchTypeInfo> getSearchTypesByCriteria(
				String searchCriteriaTypeKey, ContextInfo context) throws DoesNotExistException,
				InvalidParameterException, MissingParameterException,
				OperationFailedException {
			return null;
		}

		@Override
		public List<SearchTypeInfo> getSearchTypesByResult(
				String searchResultTypeKey, ContextInfo context) throws DoesNotExistException,
				InvalidParameterException, MissingParameterException,
				OperationFailedException {
			return null;
		}

		@Override
		public SearchResult search(SearchRequest searchRequest, ContextInfo context)
				throws MissingParameterException {
			if(searchRequest!=null && searchRequest.getParams()!=null
					&&"param1".equals(searchRequest.getParams().get(0).getKey())
					&&"line2value".equals(searchRequest.getParams().get(0).getValue())
					){
				SearchResult result = new SearchResult();
				SearchResultRow row = new SearchResultRow();
				result.getRows().add(row);
				return result;
			}
			return null;
		}
    	
    }
    
    public ConstraintMockPerson buildTestPerson1() {
    	return ValidatorMockObjectGenerator.buildTestPerson1();
    }

    
    public ConstraintMockPerson buildTestPerson2() {
    	return ValidatorMockObjectGenerator.buildTestPerson2();
    }

    
    public ConstraintMockPerson buildTestPerson3() {
    	return ValidatorMockObjectGenerator.buildTestPerson3();
    }
    
    public ObjectStructureDefinition getSimpleStudentObjectStructure() {
    	return dictionaryDelegate.getObjectStructure("simpleStudent");
    }

    public ObjectStructureDefinition getStudentWithAddressObjectStructure() {
    	return dictionaryDelegate.getObjectStructure("studentWithAddress");
    }
}
