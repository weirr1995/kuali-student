// LU Type
INSERT INTO KSLU_LUTYPE (TYPE_KEY, TYPE_DESC, EFFECTIVEDATE, EXPIRATIONDATE, NAME) values ('luType.shell.course', 'A Shell Course', '2000-01-01 00:00:00.0', '2000-12-31 00:00:00.0', 'Course')

// CluInstructor
INSERT INTO KSLU_CLU_INSTR (ID, ORG_ID, PERS_ID) VALUES ('INSTR-1', 'ORG-1', 'PersonID')
/
INSERT INTO KSLU_CLU_INSTR (ID, ORG_ID, PERS_ID) VALUES ('INSTR-2', 'ORG-2', 'PersonID')
/

// CluPublishing
INSERT INTO KSLU_CLU_PUBL (ID, END_CYCLE, START_CYCLE, ST, TYPE, PRI_INSTR_ID) VALUES ('PUBL-1', 'Fall', 'Spring', 'State', 'Type', 'INSTR-1')
/
INSERT INTO KSLU_CLU_PUBL (ID, END_CYCLE, START_CYCLE, ST, TYPE, PRI_INSTR_ID) VALUES ('PUBL-2', 'Fall', 'Summer', 'State', 'Type', 'INSTR-2')
/

// RichText
INSERT INTO KS_RICH_TEXT_T (ID, FORMATTED, PLAIN) VALUES ('RICHTEXT-1', '<p>Desc</p>', 'Desc')
/
INSERT INTO KS_RICH_TEXT_T (ID, FORMATTED, PLAIN) VALUES ('RICHTEXT-2', '<p>Marketing Desc</p>', 'Marketing Desc')
/
INSERT INTO KS_RICH_TEXT_T (ID, FORMATTED, PLAIN) VALUES ('RICHTEXT-3', '<p>Desc2</p>', 'Desc2')
/
INSERT INTO KS_RICH_TEXT_T (ID, FORMATTED, PLAIN) VALUES ('RICHTEXT-4', '<p>Marketing Desc2</p>', 'Marketing Desc2')
/

// LuType
INSERT INTO KSLU_LUTYPE (TYPE_KEY, TYPE_DESC, EFFECTIVEDATE, EXPIRATIONDATE, NAME) VALUES ('LUTYPE-1', 'LuType desc', '2000-01-01 00:00:00.0', '2009-01-01 00:00:00.0', 'LUTYPE1')
/
INSERT INTO KSLU_LUTYPE (TYPE_KEY, TYPE_DESC, EFFECTIVEDATE, EXPIRATIONDATE, NAME) VALUES ('LUTYPE-2', 'LuType desc', '2000-01-01 00:00:00.0', '2009-01-01 00:00:00.0', 'LUTYPE2')
/

// CluIdentifier
INSERT INTO KSLU_CLU_IDENT (ID, CD, DIV, LVL, LNG_NAME, SHRT_NAME, ST, TYPE, VARTN) VALUES ('IDENT-1', 'Code', 'Division', 'Level', 'Long Name', 'Shortname', 'State', 'Type', 'Variation')
/
INSERT INTO KSLU_CLU_IDENT (ID, CD, DIV, LVL, LNG_NAME, SHRT_NAME, ST, TYPE, VARTN) VALUES ('IDENT-2', 'Code', 'Division', 'Level', 'Long Name', 'Shortname', 'State', 'Type', 'Variation')
/

// CluCredit
INSERT INTO KSLU_CLU_CR (ID, INSTR_UNIT, MAX_ALOW_INACV_ATP, MAX_ALOW_INACV_TMQ, MAX_TM_RSLT_RCGZ_ATP, MAX_TM_RSLT_RCGZ_TMQ, MAX_TM_TO_COMP_ATP, MAX_TM_TO_COMP_TMQ, MAX_TOT_UNIT, MIN_TM_TO_COMP_ATP, MIN_TM_TO_COMP_TMQ, MIN_TOT_UNIT, REPEAT_CNT, REPEAT_TM_ATP, REPEAT_TM_TMQ, REPEAT_UNIT) VALUES ('CR-1', 0, 'ATP-INACT-1', 0, 'ATP-RECOG-1', 0, 'ATP-MAXCOMPLETE-1', 0, 0, 'ATP-MINCOMPLETE-1', 0, 0, 'Repeat Count', 'ATP-REPEAT-1', 0, 'Repeat Units')
/
INSERT INTO KSLU_CLU_CR (ID, INSTR_UNIT, MAX_ALOW_INACV_ATP, MAX_ALOW_INACV_TMQ, MAX_TM_RSLT_RCGZ_ATP, MAX_TM_RSLT_RCGZ_TMQ, MAX_TM_TO_COMP_ATP, MAX_TM_TO_COMP_TMQ, MAX_TOT_UNIT, MIN_TM_TO_COMP_ATP, MIN_TM_TO_COMP_TMQ, MIN_TOT_UNIT, REPEAT_CNT, REPEAT_TM_ATP, REPEAT_TM_TMQ, REPEAT_UNIT) VALUES ('CR-2', 0, 'ATP-INACT-2', 0, 'ATP-RECOG-2', 0, 'ATP-MAXCOMPLETE-2', 0, 0, 'ATP-MINCOMPLETE-2', 0, 0, 'Repeat Count', 'ATP-REPEAT-2', 0, 'Repeat Units')
/

// CluAccounting
INSERT INTO KSLU_CLU_ACCT (ID) VALUES ('ACCT-1')
/
INSERT INTO KSLU_CLU_ACCT (ID) VALUES ('ACCT-2')
/

// CluFee
INSERT INTO KSLU_CLU_FEE (ID) VALUES ('FEE-1')
/
INSERT INTO KSLU_CLU_FEE (ID) VALUES ('FEE-2')
/

// Clu
insert into KSLU_CLU (ID, CREATEID, CREATETIME, UPDATEID, UPDATETIME, VERSIONIND, ACCRED_ORG_ID, ADMIN_ORG_ID, CAN_CREATE_LUI, DEF_ENRL_EST, DEF_MAX_ENRL, EFF_DT, EXPIR_DT, HAS_EARLY_DROP_DEDLN, IS_ENRL, IS_HAZR_DISBLD_STU, NEXT_REVIEW_PRD, REF_URL, ST, ATPDURATIONTYPEKEY, TIMEQUANTITY, STDY_SUBJ_AREA, ACCT_ID, CR_ID, RT_DESCR_ID, FEE_ID, LUTYPE_ID, RT_MKTG_DESCR_ID, OFFIC_CLU_ID, PRI_INSTR_ID, PUBL_ID) values ('CLU-1', 'CREATEID', '2000-01-01 00:00:00.0', 'UPDATEID', '2001-01-01 00:00:00.0', 1, 'ACCRED_ORG_ID', 'ADMIN_ORG_ID', 1, 1, 42, '2002-01-01 00:00:00.0', '2003-01-01 00:00:00.0', 1, 1, 0, 'NEXT_REVIEW_PRD', 'REF_URL', 'ST', 'ATPDURATIONTYPEKEY', 3, 'STDY_SUBJ_AREA', 'ACCT-1', 'CR-1', 'RICHTEXT-1', 'FEE-1', 'LUTYPE-1', 'RICHTEXT-2', 'IDENT-1', 'INSTR-1', 'PUBL-1')
/
insert into KSLU_CLU (ID, CREATEID, CREATETIME, UPDATEID, UPDATETIME, VERSIONIND, ACCRED_ORG_ID, ADMIN_ORG_ID, CAN_CREATE_LUI, DEF_ENRL_EST, DEF_MAX_ENRL, EFF_DT, EXPIR_DT, HAS_EARLY_DROP_DEDLN, IS_ENRL, IS_HAZR_DISBLD_STU, NEXT_REVIEW_PRD, REF_URL, ST, ATPDURATIONTYPEKEY, TIMEQUANTITY, STDY_SUBJ_AREA, ACCT_ID, CR_ID, RT_DESCR_ID, FEE_ID, LUTYPE_ID, RT_MKTG_DESCR_ID, OFFIC_CLU_ID, PRI_INSTR_ID, PUBL_ID) values ('CLU-2', 'CREATEID', '2000-01-01 00:00:00.0', 'UPDATEID', '2001-01-01 00:00:00.0', 1, 'ACCRED_ORG_ID', 'ADMIN_ORG_ID', 1, 1, 42, '2002-01-01 00:00:00.0', '2003-01-01 00:00:00.0', 1, 1, 0, 'NEXT_REVIEW_PRD', 'REF_URL', 'ST', 'ATPDURATIONTYPEKEY', 3, 'STDY_SUBJ_AREA', 'ACCT-2', 'CR-2', 'RICHTEXT-2', 'FEE-2', 'LUTYPE-2', 'RICHTEXT-2', 'IDENT-2', 'INSTR-2', 'PUBL-2')
/


//Lui
insert into KSLU_LUI (ID, CLU_ID, ATP_ID, LUI_CODE, MAX_SEATS, EFF_DT, EXP_DT, ST, VERSIONIND) values ('LUI-1', 'CLU-1', '1', 'MENG 329 section 101', 50, '2000-01-01 00:00:00.0', '2000-06-01 00:00:00.0', 'Active', 1)
/
insert into KSLU_LUI (ID, CLU_ID, ATP_ID, LUI_CODE, MAX_SEATS, EFF_DT, EXP_DT, ST, VERSIONIND) values ('LUI-2', 'CLU-1', '2', 'MENG 329 section 102', 75, '2000-08-25 00:00:00.0', '2000-12-31 00:00:00.0', 'Active', 1)
/
insert into KSLU_LUI (ID, CLU_ID, ATP_ID, LUI_CODE, MAX_SEATS, EFF_DT, EXP_DT, ST, VERSIONIND) values ('LUI-3', 'CLU-2', '1', 'BENG 471 section 101', 75, '2000-01-01 00:00:00.0', '2000-06-01 00:00:00.0', 'Active', 1)
/
