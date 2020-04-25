import React, {
  Component
} from 'react';
import axios from 'axios';
import CanvasJSReact from '../assets/canvasjs.react';
var CanvasJSChart = CanvasJSReact.CanvasJSChart;

class MultiseriesChart extends Component {

constructor(props) {
    super(props);
    this.state = {value: '',
    plaka: '',
    cities: [],
    infectedData:[],
    };

    this.handleChange = this.handleChange.bind(this);
       this.handleSubmit = this.handleSubmit.bind(this);
       this.add = this.add.bind(this);
       this.handleChangeCombo = this.handleChangeCombo.bind(this);
}
componentDidMount() {
    this.updateInfectedCity();
    this.updateInfecedDataOfTurkey();

}
updateInfectedCity(){
  fetch("http://localhost:8080/cor/get-infected-cities")
    .then(res => res.json())
    .then(
      (result) => {
        this.setState({cities: result.data});
      },
      (error) => {
        console.info(error);
      }
    )
}
updateInfecedDataOfTurkey(){
    fetch("http://localhost:8080/cor/get-case/0")
      .then(res => res.json())
      .then(
        (result) => {
          this.setState({infectedData: result.data});
      },

      (error) => {
        console.info(error);

      }
    )
  }
  add(data){
      const headers = {
        'Content-Type': 'application/json',
      }
      axios.post("http://localhost:8080/cor/add", {"entry":data},{headers: headers })
        .then((res) => {
          this.updateInfectedCity();
        })
        .catch((error) => {
          console.info(error);
        })
  }
  handleSubmit(event) {
      this.add(this.state.value);
  }
  handleChange(event) {
      this.setState({value: event.target.value});
  }
  handleChangeCombo(event) {
     this.setState({plaka: event.target.value});
     fetch("http://localhost:8080/cor/get-case/"+event.target.value)
       .then(res => res.json())
       .then(
         (result) => {
           console.info(result);
           this.setState({infectedData: result.data});
         },
         (error) => {
           console.info(error);
         }
       )

 }
  render() {

    const { cities } = this.state;
    const { infectedData } = this.state;

    const options = {
      animationEnabled: true,
      title: {
        text: "YENI HASTA SAYISI"
      },
      axisY: {
        title: "HASTA SAYISI",
        includeZero: false
      },
      toolTip: {
        shared: true
      },
      data: infectedData
    }


    const divStyle = {
      resize: 'both',
      width: '888px',
    };
    const formStyle = {
      'margin-left': '80px',
    };
    const chartStyle = {
      'padding': '5% 10% 10% 10%',

    };

    return ( <div style={chartStyle}>
      <section class="content">
        <form style={formStyle} onSubmit={this.handleSubmit}>
          <div class="ui segment">
            <div class="row">
              < h1 > COVID-19 VAKALARI < /h1>
            </div>
            <div class="row">
              <label>Bülten: </label>
            </div>
            <div class="row">
              <textarea type="text" style={divStyle} value={this.state.value} onChange={this.handleChange} ></textarea>
            </div>
            <div class="row">
              <input type="submit" value="KAYDET" />
              <select value={this.state.plaka} onChange={this.handleChangeCombo}>
                {cities.map(function(city, index){
                            return <option value={ city.plaka } >{city.name}</option>;
                          })}
              </select>
            </div>
          </div>

        </form>

        <CanvasJSChart  options = { options} /> {}
     </section>

      </div>
  );
}
}

export default MultiseriesChart;
