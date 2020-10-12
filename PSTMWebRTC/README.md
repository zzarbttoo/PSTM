# WebRTC 시스템
## 1. 개요
프로젝트의 WebRTC 기능을 사용하기 위해 만들어진 프로젝트 파일입니다.    

## 2. 사용 기술   
- Backend - Node.js(Express)
    - socket.io - 클라이언트와 소켓통신을 하기 위해 사용
- Frontend - React.js
    - react-redux(+redux) - Redux 구조를 쓰기 위해 사용
    - socket.io-client - 서버와 소켓통신을 하기 위해 사용

## 3. 사용 방법
각 프로젝트 폴더 안에서 한 번씩 npm install을 실행시켜주셔야 실행됩니다.    

서버 실행 -     
(VSCode) Run - Start Debugging 시 바로 실행됩니다.  
(그 외) cmd에서 webrtc_server/node_index.js를 node로 실행합니다.

클라이언트 실행 -   
(VSCode) webrtc_client/package.json 파일 안에서 "scripts": 위의 Debug 버튼을 클릭 후 start를 눌러 실행해주시면 됩니다. 윈도우의 경우 start 안의 내용을   
(그 외) cmd에서 webrtc_clinet 안에서 npm start를 실행해주시면 됩니다.   

만약 Window 환경에서 실행시, webrtc_client/package.json 파일 안의 "scripts" 안의 "start"의 내용을 "set HTTPS=true&&npm start"로 바꿔주셔야 실행됩니다.

현재 깃허브에 올라가있는 상태로 바로 사용시, 컴퓨터에서 클라이언트를 두 번 사용하여 제대로 작동되는지 테스트 정도는 가능하지만, 다른 기기에서 사용하려 하면 사용할 수 없을겁니다.  
그러나 사용하기 위해서 해야하는 필수 작업이 몇가지 있어 해당 내용에 대한 이야기를 해보겠습니다.     

---
## 필수 작업 (localhost에서 사용시)  
- URL, 포트 번호 변경   
현재 URL이 기존에 사용하던 URL과 포트를 그대로 사용하고 있어, 이 부분을 바꿔주셔야 합니다.    
현재 백엔드, 프론트엔드 URL은 https://pstm-webrtc.ml 로 사용되고 있으며, URL 변경시 이 텍스트를 찾아서 바꿔주시면 됩니다.   
작성일자(2020/10/13) 기준으로 사용되고 있는 곳은 클라이언트의 src/pages/MainPage.js에 socket.io 초기화 부분밖에 없습니다.

---
## 다른 기기에서 사용하기 위한 준비
WebRTC를 이용하여 통신할 경우, 웹에서 반드시 https를 사용해야합니다. 이로 인해 도메인 생성, 인증서 생성 후 https 적용을 해야 사용을 할 수 있습니다.
- 도메인 생성 및 설정   
테스트용의 경우 freenom과 같이 무료 도메인 생성 사이트를 이용하여 도메인을 만들어 사용하시면 될 것 같습니다.    
도메인을 생성하신 후, 도메인에 집 IP에 연결해주시고, 만약 집에서 공유기 등을 사용하신다면 공유기 포트포워딩 설정도 해주셔야 합니다.
- 인증서 생성   
letsencrypt 등을 이용하여 인증받은 인증서를 받아야합니다. 인증받지 않을 경우 소켓통신이 제대로 되지 않으니 주의해주세요.    
- 인증서 사용 설정  
이제 인증서를 서버, 클라이언트 폴더에 각각 넣어줍니다. 사용하는 각 파일의 이름은 다음과 같습니다.   

    - key - private.key   
    - cert - certificate.crt  
    - ca - ca_bundle.crt  

---
