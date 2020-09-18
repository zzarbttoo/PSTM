DROP SEQUENCE PSTM_RECEIPTIDSEQ;
DROP TABLE PSTM_RECEIPTINFO;

CREATE SEQUENCE PSTM_RECEIPTIDSEQ;

--RECEITID 에는 시퀀스 대신 결정된 uid 가 들어가야할 듯
CREATE TABLE PSTM_RECEIPTINFO(
	
	RECEIPTID NUMBER PRIMARY KEY,
	IMP_UID VARCHAR2(40) NOT NULL,
	USERID NUMBER NOT NULL,
	TRAINERID NUMBER NOT NULL,
	BUYDATE DATE NOT NULL,
	STARTDATE DATE NOT NULL,
	DURATION NUMBER NOT NULL,
	PURCHASETYPE VARCHAR2(20) NOT NULL,
	CHECKRIGHT VARCHAR2(2) NOT NULL CHECK(CHECKRIGHT IN ('R', 'W')),
	REFUND VARCHAR2(2) NOT NULL CHECK(REFUND IN ('N', 'Y')) ,
	PRICE NUMBER NOT NULL,
	
	CONSTRAINT FK_RECEIPTINFO_USERID FOREIGN KEY(USERID)
	REFERENCES PSTM_USERINFO(USERID)
);

SELECT * FROM PSTM_RECEIPTINFO;

