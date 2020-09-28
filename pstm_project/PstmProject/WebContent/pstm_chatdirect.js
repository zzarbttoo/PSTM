var express = require('express')
  , app = express()
  , http = require('http')
  , server = http.createServer(app)
  , io = require('socket.io').listen(server);

server.listen(9999);
var specificroom =null;
var trainernum = null;
var trainername = null;
var userid = null;
var istrainer = null;
var num = 1;

// routing 앞 이후의 url로 들어오면, res.sendfile의 위치로 이동하겠다
app.get('/PstmProject/trainerchating', function (req, res) {
  res.sendfile(__dirname + '/pstm_chatuser.html');
  trainernum = req.param('trainernum');
  trainername = req.param('trainername');
  userid = req.param('userid');
  istrainer = req.param('istrainer');
 initinform(trainernum, trainername, userid, istrainer);
 console.log(num);
});

function initinform(_trainernum, _trainername, _userid, _istrainer){
	
	trainernum = _trainernum;
	trainername = _trainername;
	userid = _userid;
	istrainer = _istrainer;
	console.log(istrainer);
	specificroom=trainernum + trainername;
	rooms.push(specificroom);
	console.log(specificroom);
}

// usernames which are currently connected to the chat
var usernames = {};

// rooms which are currently available in chat
// 여기에 트레이너 번호를 넣으면 될 듯
var rooms =[];

io.on('connection', (socket) => {
	
	console.log()
	// when the client emits 'adduser', this listens and executes
	socket.on('adduser', (username) =>{
		socket.room = specificroom;
		// store the username in the socket session for this client
		if(istrainer == 'true'){
			console.log("specificroom"  +specificroom);
		//specificroom  = trainername + trainernum이므로 이렇게 설정했음
		socket.username = specificroom  + "트레이너";
		console.log( "socket.username" + socket.username);
		usernames[username] = specificroom;
		}else{
		console.log("elseistrainer"  +istrainer);
		socket.username = username + num;
		num++;
		usernames[username] = username + num;
		}
		// send client to room 1
		socket.join(specificroom);
		socket.emit('updatechat', 'SERVER', 'you have connected to' + specificroom);
		socket.emit('updaterooms', rooms,specificroom);
	});
	// when the client emits 'sendchat', this listens and executes
	socket.on('sendchat', function (data) {
		// we tell the client to execute 'updatechat' with 2 parameters
		io.sockets.in(socket.room).emit('updatechat', socket.username, data);
	});


	// when the user disconnects.. perform this
	socket.on('disconnect', function(){
		delete usernames[socket.username];
		// update list of users in chat, client-side
		io.sockets.emit('updateusers', usernames);
		socket.emit('updatechat', 'SERVER', socket.username + 'leave the chat room');
		socket.leave(socket.room);
	});
});  




