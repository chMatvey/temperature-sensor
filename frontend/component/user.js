import React, {Component} from 'react';
import {connect} from 'react-redux'
import axios from 'axios';
import {Link} from 'react-router-dom';

class User extends Component {
    constructor(props){
        super(props);
        this.onBtnClick = this.onBtnClick.bind(this);
        this.returnCity = this.returnCity.bind(this);
    }

    onBtnClick() {
        axios.get('/list')
            .then(res => {
                this.props.onGetLast(res.data);
            })
    }

    returnCity(city){
        if(city === null)
            return "";
        else
            return city.name;
    }

    render() {
        return (
            <div>
                <h2>Last inputs</h2><br/>
                <button onClick={this.onBtnClick}>Get last inputs</button>
                <table>
                    <thead>
                    <tr>
                        <th>Degree °C</th>
                        <th>Latitude</th>
                        <th>Longitude</th>
                        <th>City</th>
                    </tr>
                    </thead>
                    <tbody>
                    {this.props.data.map((temperature) =>
                        <tr key={temperature.id}>
                            <th>{temperature.degrees}</th>
                            <th>{temperature.coordinate.latitude}</th>
                            <th>{temperature.coordinate.longitude}</th>
                            <th>{this.returnCity(temperature.coordinate.city)}</th>
                        </tr>)}
                    </tbody>
                </table><br/>
                <Link to="/sensor">Sensor page</Link><br/><br/>
                <a href="/logout">Logout</a>
            </div>
        )
    }
}

export default connect(
    state => ({
        data: state.temperature,
    }),
    dispatch => ({
        onGetLast: (temperature) => {
            dispatch({type: 'GET_LAST', payload: temperature})
        },
    })
)(User);
