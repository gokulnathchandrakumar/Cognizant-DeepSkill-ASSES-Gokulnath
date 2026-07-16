import React, { Component } from 'react';

class Counter extends Component {

  constructor(props) {
    super(props);
    this.state = {
      count: 0
    };

    // Binding 'this' so it refers to the component inside the handler
    this.increment = this.increment.bind(this);
    this.decrement = this.decrement.bind(this);
    this.sayHello = this.sayHello.bind(this);
  }

  // Increments the counter value
  increment() {
    this.setState({ count: this.state.count + 1 });
  }

  // Decrements the counter value
  decrement() {
    this.setState({ count: this.state.count - 1 });
  }

  // Displays a static hello message
  sayHello() {
    alert('Hello! This is a static message.');
  }

  // Called by the Increment button - invokes TWO methods
  handleIncrementClick() {
    this.increment();
    this.sayHello();
  }

  render() {
    return (
      <div>
        <h2>Counter: {this.state.count}</h2>
        <button onClick={() => this.handleIncrementClick()}>Increment</button>
        <button onClick={this.decrement}>Decrement</button>
      </div>
    );
  }
}

export default Counter;