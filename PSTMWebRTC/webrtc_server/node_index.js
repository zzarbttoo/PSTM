const express = require('express');
const app = express();

const fs = require('fs');
const https = require('https');
const server = https.createServer({
    key: fs.readFileSync('./webrtc_back/private.key'),
    cert: fs.readFileSync('./webrtc_back/certificate.crt'),
    ca: fs.readFileSync('./webrtc_back/ca_bundle.crt'),
    requestCert: false,
    rejectUnauthorized: false
}, app);
const io = require('socket.io').listen(server);
let users = [];

io.on('connection', (socket) => {
    var id = 0;
    var opid = 0;

    socket.on('init', (res) => {
        console.log(res);
        id = res.id;
        opid = res.opid;
        users.push({id:res.id, opid:res.opid, socket:socket});

        for(var i = 0; i < users.length; i++) {
            if(users[i].opid == res.id) {
                users[0].socket.emit('start');
                console.log('started');
                console.log('0 : ', users[i].id);
                console.log('1 : ', res.id);
                break;
            }
        }
    });    

    socket.on('sendMessage', (res) => {
        console.log(res);
        for(var i = 0; i < users.length; i++) {
            if(users[i].id === res.id) {
                if(res.command === 'offer') {
                    users[i].socket.emit('receiveOffer', {sdp:res.sdp});
                    break;
                } else if(res.command === 'answer') {
                    users[i].socket.emit('receiveAnswer', {sdp:res.sdp});
                    break;
                } else if(res.command === 'candidate') {
                    users[i].socket.emit('receiveCandidate', {candidate:res.candidate});
                    console.log('receiveCandidate');
                    break;
                } else if(res.command === 'speakerMute'){
                    users[i].socket.emit('opponentSpeakerMuted', {mute:res.mute});
                    break;
                } else if(res.command === 'micMute') {
                    users[i].socket.emit('opponentMicMuted', {mute:res.mute});
                    break;
                } else if(res.command === 'sendMessage') {
                    users[i].socket.emit('receiveMessage', {chat:res.chat});
                }
            }
        }
    });

    socket.on('disconnect', (reason) => {
        console.log(reason);
        console.log(id);
        console.log(opid);
        var index = -1;
        for(var i = 0; i < users.length; i++) {
            if(users[i].opid === id) {
                oppoUser = users[i];
                users[i].socket.emit('opponectDisconnect');
                console.log('opponentDisconnect');
            } else if(users[i].id === id) {
                index = i;
            }
        }

        if(index !== -1) {
            users.splice(index, 1);
        }
        console.log(users);
    });
});

server.listen(3001, () => {
    console.log('server started at port 3001');
})