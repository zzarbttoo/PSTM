import cx_Oracle

connection = cx_Oracle.connect('master02_semi/semi@qclass.iptime.org:1521/xe')
cursor = connection.cursor()

cursor.execute('SELECT USERID, ID, PASSWORD, PASSWORD_KEY, NAME, PHONE, ADDR, DETAILADDR, USERTYPE, GENDER, HEIGHT, 	UPLOADIMG, CAREER, MYCOMMENT, ADMIT, SIGNOUT FROM PSTM_USERINFO ORDER BY USERID DESC')

for result in cursor:
    print(result)

cursor.close()
connection.close()
		
		
	
		
	
