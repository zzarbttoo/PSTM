import React, { Component } from 'react';
import {connect} from 'react-redux';

class ChatBox extends Component {
    textRef = 0;
    boxDivRef = null;

    updateScroll = false;

    state = {};

    componentDidMount() {
        this.setState({ width: window.innerWidth, height: window.innerHeight });
        this.updateWindowDimensions = this.updateWindowDimensions.bind(this);
        window.addEventListener('resize', this.updateWindowDimensions);
    }

    componentDidUpdate() {
        if(this.updateScroll) {
            this.boxDivRef.scrollTop = this.boxDivRef.scrollHeight - this.boxDivRef.clientHeight;
            this.updateScroll = false;
        }
    }

    componentWillUnmount() {
        window.removeEventListener('resize', this.updateWindowDimensions);
    }

    updateWindowDimensions() {
        this.setState({ width: window.innerWidth, height: window.innerHeight });
    }

    render() {

        let textHeight = this.state.height / 10;

        const mainStyle = {
            position:'absolute',
            left:this.state.width * 0.7,
        };

        const boxStyle = {
            overflow:'scroll',
            width:this.state.width * 0.3,
            height:this.state.height - this.props.footerHeight - textHeight,
            top:0,
            backgroundColor: '#333333',
            color:'white',
        };
        
        const textStyle = {
            height: textHeight,
            width: this.state.width * 0.3 / 4 * 3,
            paddingLeft:10,
            backgroundColor:'#222222',
            border:0,
            verticalAlign:'top',
            color:'white',
            fontSize:textHeight / 2.5,
        }
        const buttonStyle = {
            height: textHeight,
            width: (this.state.width * 0.3 / 4) - 12,
            border:0,
            backgroundColor:'#111111',
            verticalAlign:'top',
            color:'white',
            fontSize:textHeight / 3,
        }

        if(this.state.width < this.state.height) {
            boxStyle.width = this.state.width;
            boxStyle.height = this.state.height * 0.3 - this.props.footerHeight - 12;
            mainStyle.left = 0;
            mainStyle.top = this.state.height * 0.7;
            textStyle.width = this.state.width / 4 * 3;
            buttonStyle.width = (this.state.width / 4) - 12;
        }

        if(this.boxDivRef != null) {
            if(this.boxDivRef.scrollTop + this.boxDivRef.clientHeight >= this.boxDivRef.scrollHeight) {
                this.updateScroll = true;
            }
        }

        return (
            <div style={mainStyle}>
                <div style={boxStyle} ref={ ref => { this.boxDivRef = ref }} >
                    {this.props.chatList.map((chat) => (this.renderChat(chat)))}
                </div>
                <div>
                    <input type="text" style={textStyle} ref={ ref => {
                    this.textRef = ref}} onKeyDown={this.onKeyPress}/>
                    <input type="button" style={buttonStyle} onClick={this.sendMessage} value="보내기"/>
                </div>
            </div>
        )
    }

    renderChat(chat) {
        let margin = 20;
        return (<div style={{margin}}><b>{chat.name} </b><span style={{float:'right'}}>{chat.time}</span> <div style={{marginLeft:10}}>{chat.content}</div></div>);
    }

    onKeyPress = (e) => {
        if(e.key === 'Enter') {
            this.sendMessage();
        }
    }

    sendMessage = () => {
        if(this.textRef.value !== '') {
            this.props.onSendMessage(this.textRef.value);
            this.textRef.value = '';
        }
    }
}

const mapStateToProps = ({mainPage}) => ({
    chatList: mainPage.chatList,
});

export default connect(mapStateToProps, null, null, {forwardRef:true})(ChatBox);