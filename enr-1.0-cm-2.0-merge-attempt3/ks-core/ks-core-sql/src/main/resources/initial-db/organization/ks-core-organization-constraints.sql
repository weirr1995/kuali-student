
ALTER TABLE KSOR_ORG
    ADD CONSTRAINT KSOR_ORG_FK1 FOREIGN KEY (TYPE)
    REFERENCES KSOR_ORG_TYPE (TYPE_KEY)
/


ALTER TABLE KSOR_ORG_ATTR
    ADD CONSTRAINT KSOR_ORG_ATTR_FK1 FOREIGN KEY (OWNER)
    REFERENCES KSOR_ORG (ID)
/


ALTER TABLE KSOR_ORG_HIRCHY
    ADD CONSTRAINT KSOR_ORG_HIRCHY_FK1 FOREIGN KEY (ROOT_ORG)
    REFERENCES KSOR_ORG (ID)
/


ALTER TABLE KSOR_ORG_HIRCHY_ATTR
    ADD CONSTRAINT KSOR_ORG_HIRCHY_ATTR_FK1 FOREIGN KEY (OWNER)
    REFERENCES KSOR_ORG_HIRCHY (ID)
/


ALTER TABLE KSOR_ORG_HIRCHY_JN_ORG_TYPE
    ADD CONSTRAINT KSOR_ORG_HIRCHY_JN_ORG_TYP_FK1 FOREIGN KEY (ORG_HIRCHY_ID)
    REFERENCES KSOR_ORG_HIRCHY (ID)
/

ALTER TABLE KSOR_ORG_HIRCHY_JN_ORG_TYPE
    ADD CONSTRAINT KSOR_ORG_HIRCHY_JN_ORG_TYP_FK2 FOREIGN KEY (ORG_TYPE_ID)
    REFERENCES KSOR_ORG_TYPE (TYPE_KEY)
/


ALTER TABLE KSOR_ORG_JN_ORG_PERS_REL_TYPE
    ADD CONSTRAINT KSOR_ORG_JN_ORG_PERREL_TYP_FK1 FOREIGN KEY (ORG_PERS_RELTN_TYPE_ID)
    REFERENCES KSOR_ORG_PERS_RELTN_TYPE (TYPE_KEY)
/

ALTER TABLE KSOR_ORG_JN_ORG_PERS_REL_TYPE
    ADD CONSTRAINT KSOR_ORG_JN_ORG_PERREL_TYP_FK2 FOREIGN KEY (ORG_ID)
    REFERENCES KSOR_ORG (ID)
/


ALTER TABLE KSOR_ORG_ORG_RELTN
    ADD CONSTRAINT KSOR_ORG_ORG_RELTN_FK1 FOREIGN KEY (ORG)
    REFERENCES KSOR_ORG (ID)
/

ALTER TABLE KSOR_ORG_ORG_RELTN
    ADD CONSTRAINT KSOR_ORG_ORG_RELTN_FK2 FOREIGN KEY (RELATED_ORG)
    REFERENCES KSOR_ORG (ID)
/

ALTER TABLE KSOR_ORG_ORG_RELTN
    ADD CONSTRAINT KSOR_ORG_ORG_RELTN_FK3 FOREIGN KEY (TYPE)
    REFERENCES KSOR_ORG_ORG_RELTN_TYPE (TYPE_KEY)
/


ALTER TABLE KSOR_ORG_ORG_RELTN_ATTR
    ADD CONSTRAINT KSOR_ORG_ORG_RELTN_ATTR_FK1 FOREIGN KEY (OWNER)
    REFERENCES KSOR_ORG_ORG_RELTN (ID)
/


ALTER TABLE KSOR_ORG_ORG_RELTN_TYPE
    ADD CONSTRAINT KSOR_ORG_ORG_RELTN_TYPE_FK1 FOREIGN KEY (ORG_HIRCHY)
    REFERENCES KSOR_ORG_HIRCHY (ID)
/


ALTER TABLE KSOR_ORG_ORG_RELTN_TYPE_ATTR
    ADD CONSTRAINT KSOR_ORG_ORG_REL_TYP_ATTR_FK1 FOREIGN KEY (OWNER)
    REFERENCES KSOR_ORG_ORG_RELTN_TYPE (TYPE_KEY)
/


ALTER TABLE KSOR_ORG_PERS_RELTN
    ADD CONSTRAINT KSOR_ORG_PERS_RELTN_FK1 FOREIGN KEY (ORG)
    REFERENCES KSOR_ORG (ID)
/

ALTER TABLE KSOR_ORG_PERS_RELTN
    ADD CONSTRAINT KSOR_ORG_PERS_RELTN_FK2 FOREIGN KEY (ORG_PERS_RELTN_TYPE)
    REFERENCES KSOR_ORG_PERS_RELTN_TYPE (TYPE_KEY)
/


ALTER TABLE KSOR_ORG_PERS_RELTN_ATTR
    ADD CONSTRAINT KSOR_ORG_PERS_RELTN_ATTR_FK1 FOREIGN KEY (OWNER)
    REFERENCES KSOR_ORG_PERS_RELTN (ID)
/



ALTER TABLE KSOR_ORG_PERS_RELTN_TYPE_ATTR
    ADD CONSTRAINT KSOR_ORG_PERS_REL_TYP_ATTR_FK1 FOREIGN KEY (OWNER)
    REFERENCES KSOR_ORG_PERS_RELTN_TYPE (TYPE_KEY)
/


ALTER TABLE KSOR_ORG_POS_RESTR
    ADD CONSTRAINT KSOR_ORG_POS_RESTR_FK1 FOREIGN KEY (PERS_RELTN_TYPE)
    REFERENCES KSOR_ORG_PERS_RELTN_TYPE (TYPE_KEY)
/

ALTER TABLE KSOR_ORG_POS_RESTR
    ADD CONSTRAINT KSOR_ORG_POS_RESTR_FK2 FOREIGN KEY (ORG)
    REFERENCES KSOR_ORG (ID)
/


ALTER TABLE KSOR_ORG_POS_RESTR_ATTR
    ADD CONSTRAINT KSOR_ORG_POS_RESTR_ATTR_FK1 FOREIGN KEY (OWNER)
    REFERENCES KSOR_ORG_POS_RESTR (ID)
/



ALTER TABLE KSOR_ORG_TYPE_ATTR
    ADD CONSTRAINT KSOR_ORG_TYPE_ATTR_FK1 FOREIGN KEY (OWNER)
    REFERENCES KSOR_ORG_TYPE (TYPE_KEY)
/


ALTER TABLE KSOR_ORG_TYPE_JN_ORG_PERRL_TYP
    ADD CONSTRAINT KSOR_ORGTYP_JN_ORGPREL_TYP_FK1 FOREIGN KEY (ORG_PERS_RELTN_TYPE_ID)
    REFERENCES KSOR_ORG_PERS_RELTN_TYPE (TYPE_KEY)
/

ALTER TABLE KSOR_ORG_TYPE_JN_ORG_PERRL_TYP
    ADD CONSTRAINT KSOR_ORGTYP_JN_ORGPREL_TYP_FK2 FOREIGN KEY (ORG_TYPE_ID)
    REFERENCES KSOR_ORG_TYPE (TYPE_KEY)
/