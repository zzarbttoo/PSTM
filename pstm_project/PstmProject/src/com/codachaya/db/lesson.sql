DROP TALBE PSTM_LESSON;

CREATE TABLE PSTM_LESSON(
	USERID NUMBER NOT NULL,
	CLASSCONTENT VARCHAR2(20),
	PRICEINFO VARCHAR2(20),
	STUDENTID VARCHAR2(100),
	
	CONSTRAINT FK_LESSON_USERID FOREIGN KEY(USERID)
	REFERENCES PSTM_USERINFO(USERID)
);

