export default function section(state = [], action){
    switch(action.type){
        case 'GET_LAST':{
            state = action.payload;
        }
        default: break
    }
    return state
}
