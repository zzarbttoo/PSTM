var express = require('express')
  , app = express()
  , http = require('http')
  , server = http.createServer(app)
  , io = require('socket.io').listen(server);

server.listen(9999);

// routing 앞 이후의 url로 들어오면, res.sendfile의 위치로 이동하겠다 
app.get('/PstmProject/heyyowatssupp/hihi', function (req, res) {
  res.sendfile(__dirname + '/pstm_chatuser.html');
});

// usernames which are currently connected to the chat
var usernames = {};

// rooms which are currently available in chat
// 여기에 트레이너 번호를 넣으면 될 듯
var rooms = ['room1','room2','room3'];
var specificroom = 'room1';

io.sockets.on('connection', function (socket) {
	
	// when the client emits 'adduser', this listens and executes
	socket.on('adduser', function(username){
		// store the username in the socket session for this client
		socket.username = username;
		// store the room name in the socket session for this client
		//여기에도 톰캣에서 받은 값을 넣으면 될 듯
		socket.room = specificroom;
		// add the client's username to the global list
		usernames[username] = username;
		// send client to room 1
		socket.join(specificroom);
		// echo to client they've connected
		socket.emit('updatechat', 'SERVER', 'you have connected to' + specificroom);
		// echo to room 1 that a person has connected to their room
		socket.broadcast.to(specificroom).emit('updatechat', 'SERVER', username + ' has connected to this room');
		socket.emit('updaterooms', rooms,specificroom);
	});
	
	// when the client emits 'sendchat', this listens and executes
	socket.on('sendchat', function (data) {
		// we tell the client to execute 'updatechat' with 2 parameters
		io.sockets.in(socket.room).emit('updatechat', socket.username, data);
	});
	
	socket.on('switchRoom', function(newroom){
		socket.leave(socket.room);
		socket.join(newroom);
		socket.emit('updatechat', 'SERVER', 'you have connected to '+ newroom);
		// sent message to OLD room
		socket.broadcast.to(socket.room).emit('updatechat', 'SERVER', socket.username+' has left this room');
		// update socket session room title
		socket.room = newroom;
		socket.broadcast.to(newroom).emit('updatechat', 'SERVER', socket.username+' has joined this room');
		socket.emit('updaterooms', rooms, newroom);
	});
	

	// when the user disconnects.. perform this
	socket.on('disconnect', function(){
		// remove the username from global usernames list
		delete usernames[socket.username];
		// update list of users in chat, client-side
		io.sockets.emit('updateusers', usernames);
		// echo globally that this client has left
		socket.broadcast.emit('updatechat', 'SERVER', socket.username + ' has disconnected');
		socket.leave(socket.room);
	});
});
