import React, {Component} from 'react';
import axios from 'axios';
import {Link} from 'react-router-dom';

class Sensor extends Component{
    constructor(props){
        super(props);
        this.onBtnClick = this.onBtnClick.bind(this);
    }

    onBtnClick(){
        if (this.degrees.value === "" || this.latitude.value === "" || this.longitude.value === ""){
            alert("Please, fill in the fields");
            return;
        }

        const url = "/send";
        const temperature = {};
        temperature.degrees = this.degrees.value;
        temperature.coordinate = {};
        temperature.coordinate.latitude = this.latitude.value;
        temperature.coordinate.longitude = this.longitude.value;

        axios.post(url, temperature)
            .then(res => {
                if (typeof res.data.error === "undefined"){
                    alert("Data was successfully loaded");
                } else {
                    alert(res.data.error + " Repeat please")
                }
            })
            .catch(error => {
                if (error.status = 403){
                    alert("Insufficient permissions, please log in as sensor");
                } else {
                    alert(error);
                }
            })

    }

    render(){
        return(
            <div>
                <h2>Input data</h2><br/>
                <label>Degree °C</label><br/>
                <input placeholder="degree" ref={(input) => {this.degrees = input}}/><br/>
                <label>Latitude</label><br/>
                <input placeholder="latitude" ref={(input) => {this.latitude = input}}/><br/>
                <label>Longitude</label><br/>
                <input placeholder="longitude" ref={(input) => {this.longitude = input}}/><br/>
                <button onClick={this.onBtnClick}>submit</button><br/><br/>
                <Link to="/">User page</Link><br/><br/>
                <a href="/logout">Logout</a>
            </div>
        )
    }
}

export default Sensor;
