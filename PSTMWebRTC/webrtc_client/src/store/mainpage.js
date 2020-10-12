const SET_DISPLAYTYPE = "main/SET_DISPLAYTYPE";
const SET_SPEAKERMUTE = "main/SET_SPEAKERMUTE";
const SET_MICMUTE = 'main/SET_MICMUTE';
const SET_SPEAKERMUTE_OPPO = "main/SET_SPEAKERMUTE_OPPO";
const SET_MICMUTE_OPPO = 'main/SET_MICMUTE_OPPO';
const SET_OPEN_CHAT = 'main/SET_OPEN_CHAT';
const SET_CHAT_ALERT = 'main/SET_CHAT_ALERT';
const ADD_CHAT = 'main/ADD_CHAT';

export const setDisplayType = (displayType) => ({ type: SET_DISPLAYTYPE, displayType });
export const setSpeakerMute = (mute) => ({ type: SET_SPEAKERMUTE, mute });
export const setMicMute = (mute) => ({ type:SET_MICMUTE, mute });
export const setOpponectSpeakerMute = (mute) => ({ type:SET_SPEAKERMUTE_OPPO, mute });
export const setOpponectMicMute = (mute) => ({ type:SET_MICMUTE_OPPO, mute });
export const setOpenChat = (open) => ({ type:SET_OPEN_CHAT, open });
export const setChatAlert = (alert) => ({ type:SET_CHAT_ALERT, alert });
export const addChat = (chat) => ({ type:ADD_CHAT, chat});

const initialState = {
    displayType: 1,
    speakerMute:false,
    micMute:false,
    speakerMuteOppo:false,
    micMuteOppo:false,
    openChat:false,
    chatAlert:false,
    chatList:[],
    displayTypeEnum: { half:1, remoteInLocal:2, localInRemote:3 }    
};

export default function MainPage(state = initialState, action) {
    switch(action.type) {
        case SET_DISPLAYTYPE:
            return {
                ...state,
                displayType:action.displayType,
            }
        case SET_SPEAKERMUTE:
            return {
                ...state,
                speakerMute:action.mute,
            }
        case SET_MICMUTE:
            return {
                ...state,
                micMute:action.mute,
            }
        case SET_SPEAKERMUTE_OPPO:
            return {
                ...state,
                speakerMuteOppo: action.mute,
            }
        case SET_MICMUTE_OPPO:
            return {
                ...state,
                micMuteOppo: action.mute,
            }
        case SET_OPEN_CHAT:
            return {
                ...state,
                openChat: action.open,
            }
        case SET_CHAT_ALERT:
            return {
                ...state, 
                chatAlert: action.alert,
            }
        case ADD_CHAT:
            return {
                ...state,
                chatList:state.chatList.concat(action.chat),
            }
        default:
            return state;
    }
}