import React, {Component} from 'react';
import axios from 'axios';
import {Link} from 'react-router-dom';

class Sensor extends Component{
    constructor(props){
        super(props);
        this.onBtnClick = this.onBtnClick.bind(this);
        this.setTemperature = this.setTemperature.bind(this);
        this.state = {
            degrees: 0,
            latitude: 55.7522200,
            longitude: 37.6155600,
        }
    }

    setTemperature(city){
        if(city === "Moscow"){
            this.setState({latitude: 55.7522200, longitude: 37.6155600});
        } else if (city === "Saint Petersburg") {
            this.setState({latitude: 59.9386300, longitude: 30.3141300});
        } else if (city === "Novosibirsk"){
            this.setState({latitude: 55.0415000, longitude: 82.9346000});
        }
    }

    onBtnClick(){
        const temperature = {};
        temperature.degrees = this.state.degrees;
        temperature.coordinate = {};
        temperature.coordinate.latitude = this.state.latitude;
        temperature.coordinate.longitude = this.state.longitude;
        const url = "/send";

        axios.post(url, temperature)
            .then(res => {
                if (typeof res.data.error === "undefined"){
                    alert("Data was successfully loaded");
                } else {
                    alert(res.data.error + " Repeat please")
                }
            })
            .catch(error => {
                alert(error);
            })
    }

    render(){
        return(
            <div>
                <h2>Input data</h2><br/>
                <label>Degree °C</label><br/>
                <input value={this.state.degrees}
                       onChange={e => this.setState({degrees: e.target.value})}
                       placeholder="degree"/><br/>
                <label>Latitude</label><br/>
                <input value={this.state.latitude}
                       onChange={e => this.setState({latitude: e.target.value})}
                       placeholder="latitude"/><br/>
                <label>Longitude</label><br/>
                <input value={this.state.longitude}
                       onChange={e => this.setState({longitude: e.target.value})}
                       placeholder="longitude"/><br/><br/>
                <select onChange={(e) => this.setTemperature(e.target.value)}>
                    <option value="Moscow">Moscow</option>
                    <option value="Saint Petersburg">Saint Petersburg</option>
                    <option value="Novosibirsk">Novosibirsk</option>
                </select><br/><br/>
                <button onClick={this.onBtnClick}>submit</button><br/><br/>
                <Link to="/">User page</Link><br/><br/>
                <a href="/logout">Logout</a>
            </div>
        )
    }
}

export default Sensor;
