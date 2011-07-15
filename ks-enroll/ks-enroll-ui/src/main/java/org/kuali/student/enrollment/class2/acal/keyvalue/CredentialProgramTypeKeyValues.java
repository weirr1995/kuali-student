package org.kuali.student.enrollment.class2.acal.keyvalue;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Iterator;
import java.io.Serializable;

import javax.xml.namespace.QName;

import org.kuali.rice.core.api.resourceloader.GlobalResourceLoader;
import org.kuali.rice.core.util.ConcreteKeyValue;
import org.kuali.rice.kns.lookup.keyvalues.KeyValuesBase;
import org.kuali.rice.kns.service.KNSServiceLocator;

import org.kuali.student.r2.common.dto.ContextInfo;
import org.kuali.student.r2.common.util.constants.LuServiceConstants;
import org.kuali.student.r2.lum.lu.service.LuService;
import org.kuali.student.common.exceptions.OperationFailedException;
import org.kuali.student.lum.lu.dto.LuTypeInfo;

public class CredentialProgramTypeKeyValues extends KeyValuesBase implements Serializable{
	public static final String CREDENTIAL_PROGRAM_TYPE_KEY_PREFIX = "kuali.lu.type.credential.";
	private static final long serialVersionUID = 1L;	
	
    private transient LuService luService;
    
    public List getKeyValues() {
        List keyValues = new ArrayList();
/*
        Collection<TravelAccountType> bos = KNSServiceLocator.getBusinessObjectService().findAll( TravelAccountType.class );
        
        keyValues.add(new ConcreteKeyValue("", ""));
        for ( TravelAccountType typ : bos ) {
            keyValues.add(new ConcreteKeyValue(typ.getAccountTypeCode(), typ.getName()));
        }
        
        
*/      /*
        try{
            List<LuTypeInfo> list = getLuService().getLuTypes(ContextInfo.newInstance());
            
        }catch (OperationFailedException ofe){
            
        }
        */
        keyValues.add(new ConcreteKeyValue("kuali.lu.type.credential.Baccalaureate", "Baccalaureate" ));
        keyValues.add(new ConcreteKeyValue("kuali.lu.type.credential.Masters", "Masters"));
/*
        ContextInfo context = ContextInfo.newInstance();
 
        try {
        	List<LuTypeInfo> luTypeInfoList = luService.getLuTypes(context);
        	
        	if (luTypeInfoList == null){
        		System.out.println(">>Didn't get luTypeInfoList, luTypeInfoList is null.");
        	}
        	else if (luTypeInfoList.size()== 0){
        		System.out.println(">>Didn't get luTypeInfoList, luTypeInfoList is zero.");
        	}
        	else if (luTypeInfoList.size()>= 0){
        		Iterator luTypeInfoIterator = luTypeInfoList.iterator();
        		while (luTypeInfoIterator.hasNext()){
        			LuTypeInfo luTypeInfo = (LuTypeInfo)luTypeInfoIterator.next();
        			String luTypeInfoKey = luTypeInfo.getId();
        			if (luTypeInfoKey.startsWith(CREDENTIAL_PROGRAM_TYPE_KEY_PREFIX)){
        				String name = luTypeInfo.getName();
        				keyValues.add(new ConcreteKeyValue(luTypeInfoKey,name));
        			}        			
        		}
        	}
        }catch (OperationFailedException ofe){
        	
        }
*/        
        return keyValues;
    }
    
    protected LuService getLuService() {
        if(luService == null) {
        	luService = (LuService)GlobalResourceLoader.getService(new QName(LuServiceConstants.LU_NAMESPACE,"LuService"));
        }
        return this.luService;
    }
}