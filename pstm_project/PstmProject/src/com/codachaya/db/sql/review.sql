DROP SEQUENCE PSTM_REVIEWIDSEQ;
DROP TABLE PSTM_REVIEWINFO;

CREATE SEQUENCE PSTM_REVIEWIDSEQ;

CREATE TABLE PSTM_REVIEWINFO (
	REVIEWID NUMBER PRIMARY KEY,
	USERID NUMBER,
	REVIEWTITLE VARCHAR2(50),
	REVIEWCONTENT VARCHAR2(500),
	UPLOADIMG VARCHAR2(100),
	ORIGINIMG VARCHAR2(100),
	REGDATE DATE,
	TRAINERID NUMBER,
	
	CONSTRAINT FK_REVIEWINFO_USERID FOREIGN KEY(USERID)
	REFERENCES PSTM_USERINFO(USERID)
);
	
SELECT REVIEWID, USERID, REVIEWTITLE, REVIEWCONTENT, UPLOADIMG, ORIGINIMG, REGDATE, TRINNERID
FROM REVIEW
ORDER BY REVIEWID DESC;