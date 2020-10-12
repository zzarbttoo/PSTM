import React, {Component} from 'react';
import { connect } from 'react-redux';

class Temp extends Component {
    render() {
        return (<div></div>);
    }
}

const mapStateToProps = ({mainPage}) => ({
    displayType: mainPage.displayType,
    mute: mainPage.mute,
    mic: mainPage.mic,
    displayTypeEnum: mainPage.displayTypeEnum,
});

export default connect(mapStateToProps)(Temp);