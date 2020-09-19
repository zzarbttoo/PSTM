DROP TABLE PSTM_LESSON;

CREATE TABLE PSTM_LESSON(
	USERID NUMBER NOT NULL,
	CLASSCONTENT VARCHAR2(20),
	PRICEINFO VARCHAR2(20),
	STUDENTID VARCHAR2(100),
	STUDENTCOUNT NUMBER NOT NULL,
	
	CONSTRAINT FK_LESSON_USERID FOREIGN KEY(USERID)
	REFERENCES PSTM_USERINFO(USERID)
);

INSERT INTO PSTM_LESSON VALUES( 41, 'himam', '30000원/56000원' , '{}' , 0);

SELECT * FROM PSTM_LESSON;