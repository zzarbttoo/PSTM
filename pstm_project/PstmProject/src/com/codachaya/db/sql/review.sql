DROP SEQUENCE PSTM_REVIEWIDSEQ;
DROP TABLE PSTM_REVIEWINFO;

CREATE SEQUENCE PSTM_REVIEWIDSEQ;

CREATE TABLE PSTM_REVIEWINFO (
	REVIEWID NUMBER PRIMARY KEY,
	USERID NUMBER,
	REVIEWTITLE VARCHAR2(1000) NOT NULL,
	REVIEWCONTENT VARCHAR2(2000) NOT NULL,
	UPLOADIMG VARCHAR2(1000) ,
	REVIEWSTAR VARCHAR2(200) ,
	REGDATE DATE,
	TRAINERID NUMBER,
	TRAINER VARCHAR2(2000) NOT NULL,
	
	CONSTRAINT FK_REVIEWINFO_USERID FOREIGN KEY(USERID)
	REFERENCES PSTM_USERINFO(USERID)
);

INSERT INTO PSTM_REVIEWINFO
VALUES (PSTM_REVIEWIDSEQ.NEXTVAL,001,'오늘도 테스트', '테스트글입니다.','메인 이미지가 없습니다', '★★★★★' , SYSDATE,0,'김선아 강사님');

SELECT REVIEWID, USERID, REVIEWTITLE, REVIEWCONTENT, UPLOADIMG, REVIEWSTAR , REGDATE, TRAINERID, TRAINER 
FROM PSTM_REVIEWINFO
ORDER BY REVIEWID DESC;

SELECT REVIEWID, USERID, REVIEWTITLE, REVIEWCONTENT, UPLOADIMG, REVIEWSTAR, REGDATE, TRAINERID, TRAINER
			FROM (SELECT SEQ, REVIEWID, USERID, REVIEWTITLE, REVIEWCONTENT, UPLOADIMG, REVIEWSTAR, REGDATE, TRAINERID, TRAINER
			FROM (SELECT ROWNUM AS SEQ, REVIEWID, USERID, REVIEWTITLE, REVIEWCONTENT, UPLOADIMG, REVIEWSTAR, REGDATE, TRAINERID, TRAINER
			FROM (SELECT * FROM PSTM_REVIEWINFO 
			 WHERE
				REVIEWTITLE LIKE '%' || '오늘도 테스트'|| '%' OR
				TRAINER LIKE '%' || 'null'|| '%'))
			WHERE SEQ > 0)
			WHERE ROWNUM <= 6;
			