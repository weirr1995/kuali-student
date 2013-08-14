-- Blanket Approve Workflow Permission 
--- Permission
INSERT INTO KRIM_PERM_T (ACTV_IND,DESC_TXT,NM,NMSPC_CD,OBJ_ID,PERM_ID,PERM_TMPL_ID,VER_NBR)
  VALUES ('Y','Allows access to the Blanket Approval button on KS Documents.','Blanket Approve KS Document','KS-SYS',SYS_GUID(),KRIM_PERM_ID_S.NEXTVAL,'4',1)
/
--- Permission Detail
INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID,ATTR_VAL,KIM_ATTR_DEFN_ID,KIM_TYP_ID,OBJ_ID,PERM_ID,VER_NBR)
  VALUES (KRIM_ATTR_DATA_ID_S.NEXTVAL,'KualiStudentDocument','13','3',sys_guid(),KRIM_PERM_ID_S.CURRVAL,1)
/
--- Assign to KS Admin Role
INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND,OBJ_ID,PERM_ID,ROLE_ID,ROLE_PERM_ID,VER_NBR)
  VALUES ('Y',sys_guid(),KRIM_PERM_ID_S.CURRVAL,(SELECT ROLE_ID FROM KRIM_ROLE_T WHERE NMSPC_CD='KS-SYS' AND ROLE_NM='Kuali Student CM Admin'),KRIM_ROLE_PERM_ID_S.NEXTVAL,1)
/