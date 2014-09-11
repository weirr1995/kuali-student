INSERT INTO KRIM_ROLE_RSP_ACTN_T (ACTN_PLCY_CD, ACTN_TYP_CD, FRC_ACTN, OBJ_ID, ROLE_MBR_ID, ROLE_RSP_ACTN_ID, ROLE_RSP_ID, VER_NBR)
  VALUES ('F', 'A', 'Y', 'A102F3FA08CF45CFAA404FBB89D831AA', '*', CONCAT('KS-', KS_RICE_ID_S.NEXTVAL), (SELECT ROLE_RSP_ID FROM KRIM_ROLE_RSP_T WHERE ROLE_ID = (SELECT ROLE_ID FROM KRIM_ROLE_T WHERE ROLE_NM = 'Technical Administrator' AND NMSPC_CD = 'KR-SYS') AND RSP_ID = (SELECT RSP_ID FROM KRIM_RSP_T WHERE NM = 'Resolve Exception' AND NMSPC_CD = 'KS-SYS')), 1)
/
INSERT INTO KRIM_ROLE_RSP_ACTN_T (ACTN_PLCY_CD, ACTN_TYP_CD, FRC_ACTN, OBJ_ID, ROLE_MBR_ID, ROLE_RSP_ACTN_ID, ROLE_RSP_ID, VER_NBR)
  VALUES ('F', 'A', 'Y', 'A102F3FA08CF45CFAA404FQAZ9D831AA', '*', CONCAT('KS-', KS_RICE_ID_S.NEXTVAL), (SELECT ROLE_RSP_ID FROM KRIM_ROLE_RSP_T WHERE ROLE_ID = (SELECT ROLE_ID FROM KRIM_ROLE_T WHERE ROLE_NM = 'Technical Administrator' AND NMSPC_CD = 'KR-SYS') AND RSP_ID = (SELECT RSP_ID FROM KRIM_RSP_T WHERE NM = 'Resolve Exception' AND NMSPC_CD = 'KR-SYS')), 1)
/
INSERT INTO KRIM_ROLE_RSP_ACTN_T (ACTN_PLCY_CD, ACTN_TYP_CD, FRC_ACTN, OBJ_ID, ROLE_MBR_ID, ROLE_RSP_ACTN_ID, ROLE_RSP_ID, VER_NBR)
  VALUES ('F', 'A', 'Y', 'DepartmentReview000ROLERSPACTN01', '*', CONCAT('KS-', KS_RICE_ID_S.NEXTVAL), (SELECT ROLE_RSP_ID FROM KRIM_ROLE_RSP_T WHERE ROLE_ID = (SELECT ROLE_ID FROM KRIM_ROLE_T WHERE ROLE_NM = 'Department Admin Reviewer' AND NMSPC_CD = 'KS-CM') AND RSP_ID = (SELECT RSP_ID FROM KRIM_RSP_T WHERE NM = 'DepartmentReview' AND NMSPC_CD = 'KS-CM')), 1)
/
INSERT INTO KRIM_ROLE_RSP_ACTN_T (ACTN_PLCY_CD, ACTN_TYP_CD, FRC_ACTN, OBJ_ID, ROLE_MBR_ID, ROLE_RSP_ACTN_ID, ROLE_RSP_ID, VER_NBR)
  VALUES ('F', 'A', 'Y', 'DepartmentReview000ROLERSPACTN02', '*', CONCAT('KS-', KS_RICE_ID_S.NEXTVAL), (SELECT ROLE_RSP_ID FROM KRIM_ROLE_RSP_T WHERE ROLE_ID = (SELECT ROLE_ID FROM KRIM_ROLE_T WHERE ROLE_NM = 'Org Admin Reviewer' AND NMSPC_CD = 'KS-CM') AND RSP_ID = (SELECT RSP_ID FROM KRIM_RSP_T WHERE NM = 'DepartmentReview' AND NMSPC_CD = 'KS-CM')), 1)
/
INSERT INTO KRIM_ROLE_RSP_ACTN_T (ACTN_PLCY_CD, ACTN_TYP_CD, FRC_ACTN, OBJ_ID, ROLE_MBR_ID, ROLE_RSP_ACTN_ID, ROLE_RSP_ID, VER_NBR)
  VALUES ('F', 'F', 'Y', 'DepartmentReview000ROLERSPACTN03', '*', CONCAT('KS-', KS_RICE_ID_S.NEXTVAL), (SELECT ROLE_RSP_ID FROM KRIM_ROLE_RSP_T WHERE ROLE_ID = (SELECT ROLE_ID FROM KRIM_ROLE_T WHERE ROLE_NM = 'Department Committee Reviewer' AND NMSPC_CD = 'KS-CM') AND RSP_ID = (SELECT RSP_ID FROM KRIM_RSP_T WHERE NM = 'DepartmentReview' AND NMSPC_CD = 'KS-CM')), 1)
/
INSERT INTO KRIM_ROLE_RSP_ACTN_T (ACTN_PLCY_CD, ACTN_TYP_CD, FRC_ACTN, OBJ_ID, ROLE_MBR_ID, ROLE_RSP_ACTN_ID, ROLE_RSP_ID, VER_NBR)
  VALUES ('F', 'F', 'Y', 'DepartmentReview000ROLERSPACTN04', '*', CONCAT('KS-', KS_RICE_ID_S.NEXTVAL), (SELECT ROLE_RSP_ID FROM KRIM_ROLE_RSP_T WHERE ROLE_ID = (SELECT ROLE_ID FROM KRIM_ROLE_T WHERE ROLE_NM = 'Org Committee Reviewer' AND NMSPC_CD = 'KS-CM') AND RSP_ID = (SELECT RSP_ID FROM KRIM_RSP_T WHERE NM = 'DepartmentReview' AND NMSPC_CD = 'KS-CM')), 1)
/
INSERT INTO KRIM_ROLE_RSP_ACTN_T (ACTN_PLCY_CD, ACTN_TYP_CD, FRC_ACTN, OBJ_ID, ROLE_MBR_ID, ROLE_RSP_ACTN_ID, ROLE_RSP_ID, VER_NBR)
  VALUES ('F', 'A', 'Y', 'CollegeReview000000ROLERSPACTN01', '*', CONCAT('KS-', KS_RICE_ID_S.NEXTVAL), (SELECT ROLE_RSP_ID FROM KRIM_ROLE_RSP_T WHERE ROLE_ID = (SELECT ROLE_ID FROM KRIM_ROLE_T WHERE ROLE_NM = 'College Admin Reviewer' AND NMSPC_CD = 'KS-CM') AND RSP_ID = (SELECT RSP_ID FROM KRIM_RSP_T WHERE NM = 'CollegeReview' AND NMSPC_CD = 'KS-CM')), 1)
/
INSERT INTO KRIM_ROLE_RSP_ACTN_T (ACTN_PLCY_CD, ACTN_TYP_CD, FRC_ACTN, OBJ_ID, ROLE_MBR_ID, ROLE_RSP_ACTN_ID, ROLE_RSP_ID, VER_NBR)
  VALUES ('F', 'A', 'Y', 'CollegeReview000000ROLERSPACTN02', '*', CONCAT('KS-', KS_RICE_ID_S.NEXTVAL), (SELECT ROLE_RSP_ID FROM KRIM_ROLE_RSP_T WHERE ROLE_ID = (SELECT ROLE_ID FROM KRIM_ROLE_T WHERE ROLE_NM = 'Org Admin Reviewer' AND NMSPC_CD = 'KS-CM') AND RSP_ID = (SELECT RSP_ID FROM KRIM_RSP_T WHERE NM = 'CollegeReview' AND NMSPC_CD = 'KS-CM')), 1)
/
INSERT INTO KRIM_ROLE_RSP_ACTN_T (ACTN_PLCY_CD, ACTN_TYP_CD, FRC_ACTN, OBJ_ID, ROLE_MBR_ID, ROLE_RSP_ACTN_ID, ROLE_RSP_ID, VER_NBR)
  VALUES ('F', 'F', 'Y', 'CollegeReview000000ROLERSPACTN03', '*', CONCAT('KS-', KS_RICE_ID_S.NEXTVAL), (SELECT ROLE_RSP_ID FROM KRIM_ROLE_RSP_T WHERE ROLE_ID = (SELECT ROLE_ID FROM KRIM_ROLE_T WHERE ROLE_NM = 'College Committee Reviewer' AND NMSPC_CD = 'KS-CM') AND RSP_ID = (SELECT RSP_ID FROM KRIM_RSP_T WHERE NM = 'CollegeReview' AND NMSPC_CD = 'KS-CM')), 1)
/
INSERT INTO KRIM_ROLE_RSP_ACTN_T (ACTN_PLCY_CD, ACTN_TYP_CD, FRC_ACTN, OBJ_ID, ROLE_MBR_ID, ROLE_RSP_ACTN_ID, ROLE_RSP_ID, VER_NBR)
  VALUES ('F', 'F', 'Y', 'CollegeReview000000ROLERSPACTN04', '*', CONCAT('KS-', KS_RICE_ID_S.NEXTVAL), (SELECT ROLE_RSP_ID FROM KRIM_ROLE_RSP_T WHERE ROLE_ID = (SELECT ROLE_ID FROM KRIM_ROLE_T WHERE ROLE_NM = 'Org Committee Reviewer' AND NMSPC_CD = 'KS-CM') AND RSP_ID = (SELECT RSP_ID FROM KRIM_RSP_T WHERE NM = 'CollegeReview' AND NMSPC_CD = 'KS-CM')), 1)
/
INSERT INTO KRIM_ROLE_RSP_ACTN_T (ACTN_PLCY_CD, ACTN_TYP_CD, FRC_ACTN, OBJ_ID, ROLE_MBR_ID, ROLE_RSP_ACTN_ID, ROLE_RSP_ID, VER_NBR)
  VALUES ('F', 'A', 'Y', 'DivisionReview00000ROLERSPACTN01', '*', CONCAT('KS-', KS_RICE_ID_S.NEXTVAL), (SELECT ROLE_RSP_ID FROM KRIM_ROLE_RSP_T WHERE ROLE_ID = (SELECT ROLE_ID FROM KRIM_ROLE_T WHERE ROLE_NM = 'Division Admin Reviewer' AND NMSPC_CD = 'KS-CM') AND RSP_ID = (SELECT RSP_ID FROM KRIM_RSP_T WHERE NM = 'DivisionReview' AND NMSPC_CD = 'KS-CM')), 1)
/
INSERT INTO KRIM_ROLE_RSP_ACTN_T (ACTN_PLCY_CD, ACTN_TYP_CD, FRC_ACTN, OBJ_ID, ROLE_MBR_ID, ROLE_RSP_ACTN_ID, ROLE_RSP_ID, VER_NBR)
  VALUES ('F', 'A', 'Y', 'DivisionReview00000ROLERSPACTN02', '*', CONCAT('KS-', KS_RICE_ID_S.NEXTVAL), (SELECT ROLE_RSP_ID FROM KRIM_ROLE_RSP_T WHERE ROLE_ID = (SELECT ROLE_ID FROM KRIM_ROLE_T WHERE ROLE_NM = 'Org Admin Reviewer' AND NMSPC_CD = 'KS-CM') AND RSP_ID = (SELECT RSP_ID FROM KRIM_RSP_T WHERE NM = 'DivisionReview' AND NMSPC_CD = 'KS-CM')), 1)
/
INSERT INTO KRIM_ROLE_RSP_ACTN_T (ACTN_PLCY_CD, ACTN_TYP_CD, FRC_ACTN, OBJ_ID, ROLE_MBR_ID, ROLE_RSP_ACTN_ID, ROLE_RSP_ID, VER_NBR)
  VALUES ('F', 'F', 'Y', 'DivisionReview00000ROLERSPACTN03', '*', CONCAT('KS-', KS_RICE_ID_S.NEXTVAL), (SELECT ROLE_RSP_ID FROM KRIM_ROLE_RSP_T WHERE ROLE_ID = (SELECT ROLE_ID FROM KRIM_ROLE_T WHERE ROLE_NM = 'Division Committee Reviewer' AND NMSPC_CD = 'KS-CM') AND RSP_ID = (SELECT RSP_ID FROM KRIM_RSP_T WHERE NM = 'DivisionReview' AND NMSPC_CD = 'KS-CM')), 1)
/
INSERT INTO KRIM_ROLE_RSP_ACTN_T (ACTN_PLCY_CD, ACTN_TYP_CD, FRC_ACTN, OBJ_ID, ROLE_MBR_ID, ROLE_RSP_ACTN_ID, ROLE_RSP_ID, VER_NBR)
  VALUES ('F', 'F', 'Y', 'DivisionReview00000ROLERSPACTN04', '*', CONCAT('KS-', KS_RICE_ID_S.NEXTVAL), (SELECT ROLE_RSP_ID FROM KRIM_ROLE_RSP_T WHERE ROLE_ID = (SELECT ROLE_ID FROM KRIM_ROLE_T WHERE ROLE_NM = 'Org Committee Reviewer' AND NMSPC_CD = 'KS-CM') AND RSP_ID = (SELECT RSP_ID FROM KRIM_RSP_T WHERE NM = 'DivisionReview' AND NMSPC_CD = 'KS-CM')), 1)
/
INSERT INTO KRIM_ROLE_RSP_ACTN_T (ACTN_PLCY_CD, ACTN_TYP_CD, FRC_ACTN, OBJ_ID, ROLE_MBR_ID, ROLE_RSP_ACTN_ID, ROLE_RSP_ID, VER_NBR)
  VALUES ('F', 'A', 'Y', 'SenateReview0000000ROLERSPACTN01', '*', CONCAT('KS-', KS_RICE_ID_S.NEXTVAL), (SELECT ROLE_RSP_ID FROM KRIM_ROLE_RSP_T WHERE ROLE_ID = (SELECT ROLE_ID FROM KRIM_ROLE_T WHERE ROLE_NM = 'Senate Admin Reviewer' AND NMSPC_CD = 'KS-CM') AND RSP_ID = (SELECT RSP_ID FROM KRIM_RSP_T WHERE NM = 'SenateReview' AND NMSPC_CD = 'KS-CM')), 1)
/
INSERT INTO KRIM_ROLE_RSP_ACTN_T (ACTN_PLCY_CD, ACTN_TYP_CD, FRC_ACTN, OBJ_ID, ROLE_MBR_ID, ROLE_RSP_ACTN_ID, ROLE_RSP_ID, VER_NBR)
  VALUES ('F', 'A', 'Y', 'SenateReview0000000ROLERSPACTN02', '*', CONCAT('KS-', KS_RICE_ID_S.NEXTVAL), (SELECT ROLE_RSP_ID FROM KRIM_ROLE_RSP_T WHERE ROLE_ID = (SELECT ROLE_ID FROM KRIM_ROLE_T WHERE ROLE_NM = 'Org Admin Reviewer' AND NMSPC_CD = 'KS-CM') AND RSP_ID = (SELECT RSP_ID FROM KRIM_RSP_T WHERE NM = 'SenateReview' AND NMSPC_CD = 'KS-CM')), 1)
/
INSERT INTO KRIM_ROLE_RSP_ACTN_T (ACTN_PLCY_CD, ACTN_TYP_CD, FRC_ACTN, OBJ_ID, ROLE_MBR_ID, ROLE_RSP_ACTN_ID, ROLE_RSP_ID, VER_NBR)
  VALUES ('F', 'F', 'Y', 'SenateReview0000000ROLERSPACTN03', '*', CONCAT('KS-', KS_RICE_ID_S.NEXTVAL), (SELECT ROLE_RSP_ID FROM KRIM_ROLE_RSP_T WHERE ROLE_ID = (SELECT ROLE_ID FROM KRIM_ROLE_T WHERE ROLE_NM = 'Senate Committee Reviewer' AND NMSPC_CD = 'KS-CM') AND RSP_ID = (SELECT RSP_ID FROM KRIM_RSP_T WHERE NM = 'SenateReview' AND NMSPC_CD = 'KS-CM')), 1)
/
INSERT INTO KRIM_ROLE_RSP_ACTN_T (ACTN_PLCY_CD, ACTN_TYP_CD, FRC_ACTN, OBJ_ID, ROLE_MBR_ID, ROLE_RSP_ACTN_ID, ROLE_RSP_ID, VER_NBR)
  VALUES ('F', 'F', 'Y', 'SenateReview0000000ROLERSPACTN04', '*', CONCAT('KS-', KS_RICE_ID_S.NEXTVAL), (SELECT ROLE_RSP_ID FROM KRIM_ROLE_RSP_T WHERE ROLE_ID = (SELECT ROLE_ID FROM KRIM_ROLE_T WHERE ROLE_NM = 'Org Committee Reviewer' AND NMSPC_CD = 'KS-CM') AND RSP_ID = (SELECT RSP_ID FROM KRIM_RSP_T WHERE NM = 'SenateReview' AND NMSPC_CD = 'KS-CM')), 1)
/
INSERT INTO KRIM_ROLE_RSP_ACTN_T (ACTN_PLCY_CD, ACTN_TYP_CD, FRC_ACTN, OBJ_ID, ROLE_MBR_ID, ROLE_RSP_ACTN_ID, ROLE_RSP_ID, VER_NBR)
  VALUES ('F', 'A', 'Y', 'PublicationReview00ROLERSPACTN01', '*', CONCAT('KS-', KS_RICE_ID_S.NEXTVAL), (SELECT ROLE_RSP_ID FROM KRIM_ROLE_RSP_T WHERE ROLE_ID = (SELECT ROLE_ID FROM KRIM_ROLE_T WHERE ROLE_NM = 'Publication Reviewer' AND NMSPC_CD = 'KS-CM') AND RSP_ID = (SELECT RSP_ID FROM KRIM_RSP_T WHERE NM = 'PublicationReview' AND NMSPC_CD = 'KS-CM')), 1)
/
INSERT INTO KRIM_ROLE_RSP_ACTN_T (ACTN_PLCY_CD, ACTN_TYP_CD, FRC_ACTN, OBJ_ID, ROLE_MBR_ID, ROLE_RSP_ACTN_ID, ROLE_RSP_ID, VER_NBR)
  VALUES ('F', 'A', 'Y', 'PublicationReview00ROLERSPACTN02', '*', CONCAT('KS-', KS_RICE_ID_S.NEXTVAL), (SELECT ROLE_RSP_ID FROM KRIM_ROLE_RSP_T WHERE ROLE_ID = (SELECT ROLE_ID FROM KRIM_ROLE_T WHERE ROLE_NM = 'Org Admin Reviewer' AND NMSPC_CD = 'KS-CM') AND RSP_ID = (SELECT RSP_ID FROM KRIM_RSP_T WHERE NM = 'PublicationReview' AND NMSPC_CD = 'KS-CM')), 1)
/
INSERT INTO KRIM_ROLE_RSP_ACTN_T (ACTN_PLCY_CD, ACTN_TYP_CD, FRC_ACTN, OBJ_ID, ROLE_MBR_ID, ROLE_RSP_ACTN_ID, ROLE_RSP_ID, VER_NBR)
  VALUES ('F', 'A', 'Y', 'PublicationReview00SOLERSPACTN02', '*', CONCAT('KS-', KS_RICE_ID_S.NEXTVAL), (SELECT ROLE_RSP_ID FROM KRIM_ROLE_RSP_T WHERE ROLE_ID = (SELECT ROLE_ID FROM KRIM_ROLE_T WHERE ROLE_NM = 'Org Admin Reviewer' AND NMSPC_CD = 'KS-CM') AND RSP_ID = (SELECT RSP_ID FROM KRIM_RSP_T WHERE NM = 'PublicationDecisionReview' AND NMSPC_CD = 'KS-CM')), 1)
/
INSERT INTO KRIM_ROLE_RSP_ACTN_T (ACTN_PLCY_CD, ACTN_TYP_CD, FRC_ACTN, OBJ_ID, ROLE_MBR_ID, ROLE_RSP_ACTN_ID, ROLE_RSP_ID, VER_NBR)
  VALUES ('F', 'A', 'Y', 'PublicationReview00TOLERSPACTN02', '*', CONCAT('KS-', KS_RICE_ID_S.NEXTVAL), (SELECT ROLE_RSP_ID FROM KRIM_ROLE_RSP_T WHERE ROLE_ID = (SELECT ROLE_ID FROM KRIM_ROLE_T WHERE ROLE_NM = 'Org Admin Reviewer' AND NMSPC_CD = 'KS-CM') AND RSP_ID = (SELECT RSP_ID FROM KRIM_RSP_T WHERE NM = 'DocOrgReview' AND NMSPC_CD = 'KS-CM')), 1)
/