import React, { Component } from 'react';

class Home extends Component {
  constructor(props) {
    super(props);
    // constructor initializes the component
    // super(props) calls the parent Component class constructor
  }

  render() {
    return (
      <div>
        <h2>Welcome to the Home page of Student Management Portal</h2>
      </div>
    );
  }
}

export default Home;