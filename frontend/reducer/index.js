import {combineReducers} from  'redux';
import {routerReducer} from 'react-router-redux';
import temperature from './temperature'

export default combineReducers({
    routing: routerReducer,
    temperature
})
