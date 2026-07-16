import React from 'react';
import Counter from './components/Counter';
import Welcome from './components/Welcome';
import ClickEvent from './components/ClickEvent';
import CurrencyConvertor from './components/CurrencyConvertor';
import './App.css';

function App() {
  return (
    <div className="App">
      <h1>React Event Handling Examples</h1>

      <h2>1. Counter with Increment/Decrement</h2>
      <Counter />

      <hr />

      <h2>2. Say Welcome Button (with argument)</h2>
      <Welcome />

      <hr />

      <h2>3. Synthetic Event Example</h2>
      <ClickEvent />

      <hr />

      <CurrencyConvertor />
    </div>
  );
}

export default App;