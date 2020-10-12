import React, {Component} from 'react';

import VideoPlayer from '../components/VideoPlayer';
import ChatBox from '../components/ChatBox';
import io from 'socket.io-client';
import {setDisplayType, setSpeakerMute, setMicMute, setOpponectSpeakerMute, setOpponectMicMute, setOpenChat, setChatAlert, addChat} from '../store/mainpage';
import { connect } from 'react-redux';

import IMG_DISCONNECT from '../resources/img/half.png';
import IMG_REMOTEINLOCAL from '../resources/img/remoteInLocal.png';
import IMG_LOCALINREMOTE from '../resources/img/localInRemote.png';
import IMG_MUTE_ON from '../resources/img/mute_on.png';
import IMG_MUTE_OFF from '../resources/img/mute_off.png';
import IMG_MIC_ON from '../resources/img/mic_on.png';
import IMG_MIC_OFF from '../resources/img/mic_off.png';
import IMG_CHAT from '../resources/img/chat.png';
import IMG_CHAT_ALERT  from '../resources/img/chat_alert.png';

const pc_config = {
    "iceServers":[{
        urls:'stun:stun.voipstunt.com'
    },
    {
        urls: 'turn:numb.viagenie.ca',
        credential: 'muazkh',
        username: 'webrtc@live.com'
    }
]};

class MainPage extends Component {
    localVideo = null;
    remoteVideo = null;

    localStream = null;

    socket = io.connect('https://pstm-webrtc.ml:7236', {transports:['websocket'], rejectUnauthorized:false});

    id = 0;
    opid = 0;
    name = '나';

    state = {
        width: 0,
        height: 0,
    }

    componentDidMount() {
        var search = window.location.search;

        search = search.slice(1, search.length).split('&');
        for(var i = 0; i < search.length; i++) {
            var result = search[i].split('=');
            if(result[0] === 'id') {
                this.id = result[1]*1;
            } else if(result[0] === 'opid') {
                this.opid = result[1]*1;
            } else if(result[0] === 'name') {
                this.name = decodeURI(result[1]);
            }
        }

        this.initSocket();
        this.initPeer();
        this.initUserMedia();

        this.updateWindowDimensions();
        window.addEventListener('resize', this.updateWindowDimensions);
    }

    componentWillUnmount() {
        alert(this.id);
        this.socket.emit('disconnected', this.id);
        window.removeEventListener('resize', this.updateWindowDimensions);
    }
    
    updateWindowDimensions = () => {
        this.setState({ width: window.innerWidth, height: window.innerHeight });
    }

    render() {
        let footerSize = this.state.height / 12;
        let footerPos = this.state.height - footerSize;
        let iconSize = this.state.height / 15;
        let iconInterval = footerSize - iconSize;
        
        
        if(this.state.width > this.state.height) {
            footerSize = this.state.height / 7;
            footerPos = this.state.height - footerSize;
            iconSize = this.state.height / 10;
            iconInterval = footerSize - iconSize;
        } else {
            if(this.props.openChat) {
                footerPos = this.state.height / 10 * 7 - footerSize;
            }
        }

        return (
            <div style={{display:'inline-block', position:'absolute', width:this.state.width, height:this.state.height - footerSize, margin:0, left:0, top:0}}>
                <VideoPlayer style={{display:'inline-block', position:'absolute'}} ref={ ref => {
                    this.localVideo = ref;
                }} remote={false}/>
                <VideoPlayer style={{display:'inline-block', position:'absolute'}} ref = {ref => {
                    this.remoteVideo = ref;
                }} remote={true}/>
                <div style={{backgroundColor:'#444444', width:'100%', height:footerSize, position:'absolute', top:footerPos, verticalAlign:'middle'}}>
                    <div style={{float:'left'}}>
                        <img src={IMG_DISCONNECT} style={{width:iconSize, height:iconSize, marginLeft:iconInterval / 2, marginTop:iconInterval / 2, opacity:this.props.displayType === this.props.displayTypeEnum.half ? 1 : 0.5}} onClick={()=>{ this.handleSetDisplayType(this.props.displayTypeEnum.half); }} alt=''/>
                        <img src={IMG_REMOTEINLOCAL} style={{width:iconSize, height:iconSize, marginLeft:iconInterval / 2, marginTop:iconInterval / 2, opacity:this.props.displayType === this.props.displayTypeEnum.remoteInLocal ? 1 : 0.5}} onClick={()=>{ this.handleSetDisplayType(this.props.displayTypeEnum.remoteInLocal); }} alt=''/>
                        <img src={IMG_LOCALINREMOTE} style={{width:iconSize, height:iconSize, marginLeft:iconInterval / 2, marginTop:iconInterval / 2, opacity:this.props.displayType === this.props.displayTypeEnum.localInRemote ? 1 : 0.5}} onClick={()=>{ this.handleSetDisplayType(this.props.displayTypeEnum.localInRemote); }} alt=''/>
                    </div>
                    <div style={{float:'right'}}>
                        <img src={this.props.chatAlert ? IMG_CHAT_ALERT : IMG_CHAT} style={{width:iconSize, height:iconSize, marginRight:iconInterval / 2, marginTop:iconInterval / 2, opacity:this.props.openChat || this.props.chatAlert ? 1 : 0.5}} alt='' onClick={()=>{ this.handleSetOpenChat(!this.props.openChat); }}/>
                        <img src={this.props.speakerMute ? IMG_MUTE_ON : IMG_MUTE_OFF} style={{width:iconSize, height:iconSize, marginRight:iconInterval / 2, marginTop:iconInterval / 2}} alt='' onClick={()=>{ this.handleSetSpeakerMute(!this.props.speakerMute); }}/>
                        <img src={this.props.micMute ? IMG_MIC_OFF : IMG_MIC_ON} style={{width:iconSize, height:iconSize, marginRight:iconInterval / 2, marginTop:iconInterval / 2}} alt='' onClick={()=>{ this.handleSetMicMute(!this.props.micMute); }}/>
                    </div>
                </div>
                {this.props.openChat ? this.renderChatBox(footerSize) : ''}
            </div>
        );
    }

    renderChatBox(height) {
        return (<ChatBox footerHeight={height} onSendMessage={this.handleSendMessage}/>);
    }

    // init method

    initSocket() {
        this.socket.on('start', () => {
            console.log('start');
            this.createOffer();
        });

        this.socket.on('receiveOffer', (res) => {
            console.log('receiveOffer');
            console.log(res.sdp);
            this.setRemoteDesciprtion(res.sdp, true);
        });

        this.socket.on('receiveAnswer', (res) => {
            console.log('receive answer');
            this.setRemoteDesciprtion(res.sdp, false);
        });

        this.socket.on('receiveCandidate', (res) => {
            console.log(res.candidate);
            this.createCandidate(res.candidate);
        });

        this.socket.on('opponentSpeakerMuted', (res) => {
            console.log(res.mute);
            this.props.setOpponectSpeakerMute(res.mute);
        });

        this.socket.on('opponentMicMuted', (res) => {
            console.log(res.mute);
            this.props.setOpponectMicMute(res.mute);
        });

        this.socket.on('opponentDisconnect', () => {
            this.pc.close();
            this.pc = new RTCPeerConnection(pc_config);
            this.remoteVideo.reset();
        });

        this.socket.on('receiveMessage', (res) => {
            console.log('message received');
            this.props.addChat(res.chat);
            if(!this.props.chatOpen) {
                this.props.setChatAlert(true);
            }
        });
    }

    initPeer() {
        this.pc = new RTCPeerConnection(pc_config);

        this.pc.onicecandidate = (e) => {
            if(e.candidate)  {
                console.log(JSON.stringify(e.candidate));
                this.socket.emit('sendMessage', {command:'candidate', candidate:JSON.stringify(e.candidate), id:this.opid});
                console.log('send candidate');
            }
        }

        this.pc.oniceconnectionstatechange = (e) => {
            console.log(e);
        }

        this.pc.onaddstream = (e) => {
            this.remoteVideo.setStream(e.stream);
        }
    }

    initUserMedia() {

        if(navigator.mediaDevices === undefined) {
            navigator.mediaDevices = {};
        }

        if(navigator.mediaDevices.getUserMedia === undefined) {
            navigator.mediaDevices.getUserMedia = function(constraint) {
                var getUserMedia = navigator.webkitGetUserMedia || navigator.mozgetUserMedia;

                if(!getUserMedia) {
                    return Promise.reject(new Error('getUserMedia is not implemented in this browser'));
                }

                return new Promise(function(resolve, reject) {
                    getUserMedia.call(navigator, constraint, resolve, reject);
                });
            }
        }

        navigator.mediaDevices.getUserMedia({
            audio:true,
            video:true
        }).then(this.gotStream);
    }

    // methods

    gotStream = (stream) => {
        console.log(stream);
        this.localStream = stream;
        this.localVideo.setStream(stream);
        this.localVideo.setVideoMute(true);
        if(this.pc.addStream === undefined) {
            stream.getTracks().forEach(track => this.pc.addTrack(track, stream));
        } else {
            this.pc.addStream(stream);
        }


        console.log('init start');
        this.socket.emit('init', {id:this.id, opid:this.opid});
    }

    createOffer() {
        console.log('Offer');

        this.pc.createOffer({offerToReceiveVideo:1, offerToReceiveAudio:1}).then(sdp => {
            console.log(JSON.stringify(sdp));
            this.pc.setLocalDescription(sdp);
            this.socket.emit('sendMessage', {command:'offer', sdp:JSON.stringify(sdp), id:this.opid});
            console.log('send Offer');
        }, e => {})
    }

    createAnswer() {
        console.log('Answer');
        this.pc.createAnswer({offerToReceiveVideo: 1, offerToReceiveAudio:1}).then(sdp => {
            console.log(JSON.stringify(sdp));
            this.pc.setLocalDescription(sdp);
            console.log(this.opid);
            this.socket.emit('sendMessage', {command:'answer', sdp:JSON.stringify(sdp), id:this.opid});
            console.log('sendAnswer');
        }, e => {});
    }

    createCandidate() {
        const candidate = JSON.parse(this.textref.value);
        console.log('Adding candidate:', candidate);

        this.pc.addIceCandidate(new RTCIceCandidate(candidate));
    }

    createCandidate = (candidate) => {

        const parsed = JSON.parse(candidate);

        console.log('Adding candidate:', parsed);

        this.pc.addIceCandidate(new RTCIceCandidate(parsed));
    }

    setRemoteDesciprtion(sdp, answer) {
        this.pc.setRemoteDescription(new RTCSessionDescription(JSON.parse(sdp)));
        if(answer) {
            this.createAnswer();
        }
    }
    
    // events

    handleSetDisplayType = (type) => {
        this.props.setDisplayType(type);
    }

    handleSetSpeakerMute = (mute) => {
        this.props.setSpeakerMute(mute);
        this.remoteVideo.setVideoMute(mute);
        this.socket.emit('sendMessage', {command:'speakerMute', id:this.opid, mute});     
    }
    handleSetMicMute = (mute) => {
        this.props.setMicMute(mute);
        if(this.localStream.getAudioTracks().length > 0) {
            this.localStream.getAudioTracks()[0].enabled = !mute;
        }
        this.socket.emit('sendMessage', {command:'micMute', id:this.opid, mute});
    }

    handleSetOpenChat = (open) => {
        this.props.setOpenChat(open);

        if(this.props.chatAlert) {
            this.props.setChatAlert(false);
        }
    }

    handleSendMessage = (message) => {
        const current = new Date();
        let hour = current.getHours();
        let min = current.getMinutes();

        let timeString = "";

        if(hour < 10) {
            timeString += '0';
        }
        timeString += hour + ":";

        if(min < 10) {
            timeString += '0';
        }
        timeString += min;

        const chat = {name:this.name, content:message, time:timeString};

        this.props.addChat(chat);

        this.socket.emit('sendMessage', {command:'sendMessage', id:this.opid, chat:chat});
    }
} 

const mapStateToProps = ({mainPage}) => ({
    displayType: mainPage.displayType,
    speakerMute: mainPage.speakerMute,
    micMute: mainPage.micMute,
    displayTypeEnum: mainPage.displayTypeEnum,
    openChat: mainPage.openChat,
    chatAlert: mainPage.chatAlert,
});

const mapDispatchToProps = {setDisplayType, setSpeakerMute, setMicMute, setOpponectSpeakerMute, setOpponectMicMute, setOpenChat, setChatAlert, addChat};

export default connect(mapStateToProps, mapDispatchToProps)(MainPage);