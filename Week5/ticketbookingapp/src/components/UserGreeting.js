import React, { Component } from 'react';

class UserGreeting extends Component {

  constructor(props) {
    super(props);
    this.state = {
      selectedFlight: '',
      bookingMessage: ''
    };

    this.handleChange = this.handleChange.bind(this);
    this.handleBooking = this.handleBooking.bind(this);
  }

  handleChange(event) {
    this.setState({ selectedFlight: event.target.value });
  }

  handleBooking(event) {
    event.preventDefault();
    this.setState({
      bookingMessage: `Ticket booked successfully for flight ${this.state.selectedFlight}!`
    });
  }

  render() {
    return (
      <div>
        <h2>Welcome back, User!</h2>
        <p>You are logged in. You can now book flight tickets.</p>

        <h3>Book a Flight</h3>
        <form onSubmit={this.handleBooking}>
          <label>
            Select Flight:{' '}
            <select value={this.state.selectedFlight} onChange={this.handleChange} required>
              <option value="">-- Choose a flight --</option>
              <option value="AI-202">AI-202 (Chennai → Delhi)</option>
              <option value="6E-345">6E-345 (Mumbai → Bangalore)</option>
              <option value="SG-118">SG-118 (Kolkata → Hyderabad)</option>
            </select>
          </label>
          <br /><br />
          <button type="submit">Book Ticket</button>
        </form>

        {this.state.bookingMessage && (
          <p style={{ color: 'green', fontWeight: 'bold' }}>
            {this.state.bookingMessage}
          </p>
        )}
      </div>
    );
  }
}

export default UserGreeting;