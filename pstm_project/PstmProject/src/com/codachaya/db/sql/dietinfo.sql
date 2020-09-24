DROP SEQUENCE PSTM_DIETIDSEQ;
DROP TABLE PSTM_DIETINFO;

CREATE SEQUENCE PSTM_DIETIDSEQ;
CREATE TABLE PSTM_DIETINFO(
	DIETID NUMBER PRIMARY KEY,
	DAILYINFOID NUMBER NOT NULL,
	UPLOADIMG VARCHAR2(100) NOT NULL,
	ORIGINIMG VARCHAR2(100) NOT NULL,
	RESULT VARCHAR2(30) NOT NULL,
	EATENCOUNT NUMBER NOT NULL,
	TIMEEAT VARCHAR2(500) NOT NULL,
	
	CONSTRAINT FK_DIETINFO_DAILYINFOID FOREIGN KEY(DAILYINFOID)
	REFERENCES PSTM_DAILYINFO(DAILYINFOID)
);


INSERT INTO PSTM_DIETINFO VALUES(DIETIDSEQ.NEXTVAL, 1, 'diet04.png', 'diet04.png', 'Hi', 0, '12:30')

INSERT INTO PSTM_DIETINFO VALUES(DIETIDSEQ.NEXVAL, 1, 'pizza.jpg', 'pizza.jpg', 'Hello', 2, '01:30')

SELECT DIETID, DAILYINFOID, UPLOADIMG, ORIGINIMG, RESULT, EATENCOUNT, TIMEEAT
FROM PSTM_DIETINFO
