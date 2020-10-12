import React, {Component} from 'react';
import {connect} from 'react-redux';

import IMG_MICMUTED from '../resources/img/mic_off.png';

class VideoPlayer extends Component {

    state = {
        width: 0,
        height: 0,
        status: 1,
    }

    localVideo = null;

    componentDidMount() {
        this.setState({ width: window.innerWidth, height: window.innerHeight });
        this.updateWindowDimensions = this.updateWindowDimensions.bind(this);
        window.addEventListener('resize', this.updateWindowDimensions);
        this.localVideo.controls = false;
    }

    componentWillUnmount() {
        window.removeEventListener('resize', this.updateWindowDimensions);
    }

    updateWindowDimensions() {
        this.setState({ width: window.innerWidth, height: window.innerHeight });
    }

    setStream = (stream) => {
        this.localVideo.srcObject = stream;
        this.setState({status:2});
    }

    setStreamURL = (url) => {
        console.log(url);
        this.localVideo.src = window.URL.createObjectURL(url);
    }

    setVideoMute(mute) {
        this.localVideo.muted = mute;
    }

    play = () => {
        this.localVideo.play();
    }

    reset = () => {
        this.localVideo.src = "";
        this.localVideo.stop();
        this.setState({status:1});
    }

    makeVideoStyle() {

        var videoStyle = {
            width: 100,
            height: 100,
            margin: '0px',
            backgroundColor: 'black',
            display: 'inline-block',
            position:'absolute',
            left: this.props.left,
            zindex:0,
        }

        var width = 0;
        var height = 0;
        if(this.localVideo !== null) {
            if(this.state.width > this.state.height) {
                width = this.state.width / 8;
                if(this.localVideo.videoWidth !== 0) {
                    let ratio = width / this.localVideo.videoWidth;
                    height = this.localVideo.videoHeight * ratio;
                } else {
                    height = width / 16 * 9;
                }
            } else {
                height = this.state.height / 8;
                if(this.localVideo.videoHeight !== 0) {
                    let ratio = height / this.localVideo.videoHeight;
                    width = this.localVideo.videoWidth * ratio;
                } else {
                    width = height / 9 * 16;
                }
            }
        }
        
        if(this.props.displayType === this.props.displayTypeEnum.half) {
            if(this.state.width > this.state.height) {
                videoStyle.width = '50%';
                videoStyle.height = '100%';

                
                if(this.props.openChat) {
                    videoStyle.width = '35%';
                }

                if(this.props.remote) {
                    videoStyle.left = '50%';

                    if(this.props.openChat) {
                        videoStyle.left = '35%';
                    }
                }
            } else {
                videoStyle.width = '100%';
                videoStyle.height = '50%';

                if(this.props.openChat) {
                    videoStyle.height = '35%';
                }

                if(this.props.remote) {
                    videoStyle.top = '50%';
                    
                    if(this.props.openChat) {
                        videoStyle.top = '35%';
                    }
                }
            }
        } else if(this.props.displayType === this.props.displayTypeEnum.remoteInLocal) {
            if(this.props.remote) {
                videoStyle.width = width;
                videoStyle.height = height;
            } else {
                videoStyle.width = '100%';
                videoStyle.height = '100%';

                if(this.props.openChat) {
                    if(this.state.width < this.state.height) {
                        videoStyle.height = '70%';
                    } else {
                        videoStyle.width = '70%';
                    }
                }
            }

        } else if(this.props.displayType === this.props.displayTypeEnum.localInRemote) {
            if(this.props.remote) {
                videoStyle.width = '100%';
                videoStyle.height = '100%';
                videoStyle.zIndex = 0;

                if(this.props.openChat) {
                    if(this.state.width < this.state.height) {
                        videoStyle.height = '70%';
                    } else {
                        videoStyle.width = '70%';
                    }
                }
            } else {
                videoStyle.width = width;
                videoStyle.height = height;
                videoStyle.zIndex = 1;
            }
        }

        return videoStyle;
    }

    makeStatusStyle() {
        var statusStyle = {color:'white', zIndex:2, position:'absolute', left:0, top:0, fontSize:40};
        
        if(this.props.displayType === this.props.displayTypeEnum.half) {
            if(this.props.remote) {
                if(this.state.width > this.state.height) {
                    statusStyle.left = this.state.width / 4 * 3 - 35;
                    if(this.props.openChat) {
                        statusStyle.left = this.state.width * 0.7 / 4 * 3 - 35;
                    }
                } else {
                    statusStyle.left = this.state.width / 2 - 35;
                    statusStyle.top = '50%';
                    if(this.props.openChat) {
                        statusStyle.top = '35%';
                    }
                }
            } else {
                statusStyle.left = this.state.width / 4 - 35;
            }
        } else if(this.props.displayType === this.props.displayTypeEnum.remoteInLocal) {
            if(!this.props.remote) {
                statusStyle.left = this.state.width / 2 - 35;
            }
        } else if(this.props.displayType === this.props.displayTypeEnum.localInRemote) {
            if(this.props.remote) {
                statusStyle.left = this.state.width / 2 - 35;
                if(this.props.openChat) {
                    statusStyle.left = this.state.width * 0.7 / 2 - 35;
                }
            }
        }

        return statusStyle;
    }

    render() {
        var videoStyle = this.makeVideoStyle();
        var muteSize = this.state.width / 16;
        
        return (
            <div>
                <video playsInline id="cam" preload="metadata" autoPlay ref={ ref => {
                    this.localVideo = ref;
                }} style={videoStyle}/>
                {this.state.status === 1 ? <label style={this.makeStatusStyle()}>Wait..</label> : <div></div>}
                {this.props.micMuteOppo ? <img src={IMG_MICMUTED} style={{width:muteSize, height:muteSize, top:this.state.height - muteSize, position:'absolute', zIndex:2}}/> : ''}
            </div>
        );
    }
}

const mapStateToProps = ({mainPage}) => ({
    displayType: mainPage.displayType,
    displayTypeEnum: mainPage.displayTypeEnum,
    speakerMuteOppo: mainPage.speakerMuteOppo,
    micMuteOppo: mainPage.micMuteOppo,
    openChat: mainPage.openChat,
});

export default connect(mapStateToProps, null, null, {forwardRef:true})(VideoPlayer);