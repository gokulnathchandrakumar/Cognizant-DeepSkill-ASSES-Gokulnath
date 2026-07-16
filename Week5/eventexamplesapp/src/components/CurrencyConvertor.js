import React, { Component } from 'react';

class CurrencyConvertor extends Component {

  constructor(props) {
    super(props);
    this.state = {
      rupees: '',
      euros: 0
    };

    this.handleChange = this.handleChange.bind(this);
    this.handleSubmit = this.handleSubmit.bind(this);
  }

  // Updates state as the user types the rupee value
  handleChange(event) {
    this.setState({ rupees: event.target.value });
  }

  // Handles form submission - converts Rupees to Euro
  handleSubmit(event) {
    event.preventDefault(); // prevents default form page-reload behavior

    const conversionRate = 0.011; // approx 1 INR = 0.011 EUR
    const euroValue = (this.state.rupees * conversionRate).toFixed(2);

    this.setState({ euros: euroValue });
  }

  render() {
    return (
      <div>
        <h2>Currency Convertor (INR to EUR)</h2>
        <form onSubmit={this.handleSubmit}>
          <label>
            Enter Amount in Rupees:{' '}
            <input
              type="number"
              value={this.state.rupees}
              onChange={this.handleChange}
            />
          </label>
          <br /><br />
          <button type="submit">Convert</button>
        </form>
        <h3>Converted Amount: € {this.state.euros}</h3>
      </div>
    );
  }
}

export default CurrencyConvertor;