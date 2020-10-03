# PSTM (PerSonal Training at hoMe)


## 1. 프로젝트 개요  


 #### <b>개발 동기 및 필요성</b>

다이어트와 건강한 몸 유지를 위해서는 운동과 식단 관리 모두가 필요하다. 처음 헬스에 입문한 사람들은 식단 관리와 운동에 어려움을 겪는 경우가 많기 때문에 식단관리와 운동 관리가 모두 포함되어 있는 pt 서비스를 이용하는 경우가 많다. 하지만 헬스장을 따로 찾을 시간이 없는 경우, 또는 현 코로나 시국과 같이 헬스장 이용에 어려움을 겪을 경우 부득이하게 집에서 운동을 진행해야 한다. 이러한 “홈트레이닝” 족들을 위해 헬스장에 가지 않고도 pt 및 식단관리를 받을 수 있는 플랫폼을 만들고자 위 주제를 정하게 되었다.


#### <b>목표</b>
온라인 pt 서비스를 합리적인 가격으로 보급하고자 하며, 헬스장 이용이 어려운 사람들에게 서비스를 제공해 장소적인 제약에서 벗어날 수 있도록 한다.  

또한 식단 관리를 온라인으로 진행함으로써, 오프라인으로 진행할 시 발생할 불필요한 시간 낭비를 줄이도록 도와준다


####  <b>기대효과</b>
직장인, 주부, 학생 등 시간/공간에 제약을 받고 사는 사람들도 운동관리/식단 관리를 받을 수 있을 것이다



#### <b>기존 상품과의 차별점 </b>

 1) 식단 칼로리를 객체감지, 식품 api 를 이용해 계산해주어 회원들이 직관적으로 식단의 상태에 대해 알 수 있도록 하며, pt 트레이너의 피드백 제공에도 도움을 준다
2) 화상 채팅을 이용해 실시간으로 자세 교정을 진행할 수 있도록 한다

<br/>

## 2. 프로젝트 수행

####  <b>팀 구성 및 역할 분담</b>
  
배유진(팀장)  &nbsp;: &nbsp; 결제 페이지(import/iamporter), 챗봇(dialogFlow), 채팅(node.js socket.io 등), 페이징  
김선아 &nbsp;: &nbsp;후기 게시판(mybatis 이용), 후기 검색 기능, 글쓰기 api(썸머노트), 페이징   
김지훈 &nbsp;: &nbsp;식단/운동 확인 페이지(스크롤 페이징, Vision API/객체 감지)  
박민석 &nbsp;: &nbsp;비밀번호 암호화(해시 알고리즘), 화상 채팅(node.js socket.io, React Redux, https 등)
박준범 &nbsp;: &nbsp;지도 api(카카오 지도), 영양 성분 API(국가 표준 식품 성분 DB)  
조용승 &nbsp;: &nbsp;로그인 api(네이버/페이스북 로그인 API), 마이페이지(pt 강사용)

