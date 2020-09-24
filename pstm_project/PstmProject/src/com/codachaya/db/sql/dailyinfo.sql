DROP SEQUENCE PSTM_DAILYINFOIDSEQ;
DROP TABLE PSTM_DAILYINFO;

CREATE SEQUENCE PSTM_DAILYINFOIDSEQ;

CREATE TABLE PSTM_DAILYINFO(
	DAILYINFOID NUMBER PRIMARY KEY,
	USERID NUMBER NOT NULL,
	REGDATE DATE NOT NULL,
	EXERCISE VARCHAR2(300) NOT NULL,
	CONSUMEKCAL NUMBER NOT NULL,
	FEEDBACK VARCHAR2(500),
	
	
	CONSTRAINT FK_DAILYINFO_USERID FOREIGN KEY(USERID)
	REFERENCES PSTM_USERINFO(USERID)
);


INSERT INTO PSTM_DAILYINFO 
VALUES(PSTM_DAILYINFOIDSEQ.NEXTVAL, 1, SYSDATE, '스쿼트', 150, '살빼세요');

INSERT INTO PSTM_DAILYINFO
VALUES(PSTM_DAILYINFOIDSEQ.NEXTVAL, 1, SYSDATE, '팔굽혀펴기', 160, '살 더빼세요');

SELECT DAILYINFOID, USERID, REGDATE, EXERCISE, CONSUMEKCAL, FEEDBACK
FROM PSTM_DAILYINFO

select * from pstm_dailyinfo




