DROP SEQUENCE WEIGHTIDSEQ;
DROP TABLE PSTM_WEIGHTINFO;
CREATE SEQUENCE WEIGHTIDSEQ;

CREATE TABLE PSTM_WEIGHTINFO(

	WEIGHTID NUMBER PRIMARY KEY NOT NULL,
	USERID NUMBER NOT NULL,
	MEASUREDATE DATE NOT NULL,
	WEIGHT NUMBER NOT NULL
	
);

