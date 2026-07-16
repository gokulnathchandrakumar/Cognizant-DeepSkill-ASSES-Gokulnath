import React from 'react';

function GuestGreeting() {
  return (
    <div>
      <h2>Welcome, Guest!</h2>
      <p>Please log in to book tickets.</p>

      <h3>Available Flights</h3>
      <table border="1" cellPadding="10" style={{ borderCollapse: 'collapse' }}>
        <thead>
          <tr>
            <th>Flight No</th>
            <th>From</th>
            <th>To</th>
            <th>Departure</th>
            <th>Price</th>
          </tr>
        </thead>
        <tbody>
          <tr>
            <td>AI-202</td>
            <td>Chennai</td>
            <td>Delhi</td>
            <td>06:30 AM</td>
            <td>₹5,500</td>
          </tr>
          <tr>
            <td>6E-345</td>
            <td>Mumbai</td>
            <td>Bangalore</td>
            <td>09:15 AM</td>
            <td>₹4,200</td>
          </tr>
          <tr>
            <td>SG-118</td>
            <td>Kolkata</td>
            <td>Hyderabad</td>
            <td>01:45 PM</td>
            <td>₹3,800</td>
          </tr>
        </tbody>
      </table>
    </div>
  );
}

export default GuestGreeting;