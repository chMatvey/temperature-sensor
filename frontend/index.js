import React from 'react';
import ReactDOM from 'react-dom';
import {Provider} from 'react-redux';
import {Router, Route} from 'react-router';
import {Switch} from 'react-router-dom';
import store from './store';
import User from './component/user'
import Sensor from './component/sensor'
import history from './history';

ReactDOM.render(
    <Provider store={store}>
        <Router history={history}>
            <Switch>
                <Route exact path="/" component={User}/>
                <Route path="/sensor" component={Sensor}/>
            </Switch>
        </Router>
    </Provider>,
    document.getElementById('root')
);
