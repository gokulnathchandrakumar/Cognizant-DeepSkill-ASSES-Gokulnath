import React, { Component } from 'react';

class ClickEvent extends Component {

  // 'event' here is a React SyntheticEvent, automatically passed by React
  handleClick(event) {
    console.log('Synthetic Event Type:', event.type);
    alert('I was clicked');
  }

  render() {
    return (
      <div>
        <button onClick={(event) => this.handleClick(event)}>
          Click Me
        </button>
      </div>
    );
  }
}

export default ClickEvent;