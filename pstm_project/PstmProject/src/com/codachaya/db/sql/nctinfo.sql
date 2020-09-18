DROP SEQUENCE PSTM_NCTINFOIDSEQ;
DROP TABLE PSTM_NCTINFO;

CREATE SEQUENCE PSTM_NCTINFOIDSEQ;

CREATE TABLE PSTM_NCTINFO(
	NCTINFOID NUMBER PRIMARY KEY,
	DIETID NUMBER NOT NULL,
	KCAL NUMBER NOT NULL,
	CARBOHYDRATE NUMBER,
	PROTEIN NUMBER,
	FAT NUMBER,
	NCTETC VARCHAR2(200),
	
	CONSTRAINT FK_NCTINFO_DIETID FOREIGN KEY(DIETID)
	REFERENCES PSTM_DIETINFO(DIETID)
);

SELECT * FROM PSTM_NCTINFO