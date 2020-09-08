DROP SEQUENCE PSTM_RECEIPTIDSEQ;
DROP TABLE PSTM_RECEIPTINFO;

CREATE SEQUENCE PSTM_RECEIPTIDSEQ;


CREATE TABLE PSTM_RECEIPTINFO(
	
	RECEIPTID NUMBER PRIMARY KEY,
	USERID NUMBER NOT NULL,
	TRAINERID NUMBER NOT NULL,
	BUYDATE DATE NOT NULL,
	STARTDATE DATE NOT NULL,
	DURATION NUMBER NOT NULL,
	PURCHASETYPE VARCHAR2(20) NOT NULL,
	PRICE NUMBER NOT NULL,
	
	CONSTRAINT FK_RECEIPTINFO_USERID FOREIGN KEY(USERID)
	REFERENCES PSTM_USERINFO(USERID)
);


