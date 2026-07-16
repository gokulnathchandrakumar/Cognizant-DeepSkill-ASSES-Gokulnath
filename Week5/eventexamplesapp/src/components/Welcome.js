import React, { Component } from 'react';

class Welcome extends Component {

  // Takes an argument and displays a welcome message
  sayWelcome(message) {
    alert('Say ' + message);
  }

  render() {
    return (
      <div>
        <button onClick={() => this.sayWelcome('welcome')}>
          Say Welcome
        </button>
      </div>
    );
  }
}

export default Welcome;