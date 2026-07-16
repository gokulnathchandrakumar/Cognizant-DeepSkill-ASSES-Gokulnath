import React from 'react';
import ListofPlayers from './components/ListofPlayers';
import Scorebelow70 from './components/Scorebelow70';
import ListofIndianPlayers from './components/ListofIndianPlayers';
import './App.css';

// Helper function: returns JSX list of players at odd positions (index 0, 2, 4...)
function OddPlayers(team) {
  const oddTeam = team.filter((player, index) => index % 2 === 0);
  return (
    <ul>
      {oddTeam.map((player, index) => (
        <li key={index}>{player}</li>
      ))}
    </ul>
  );
}

// Helper function: returns JSX list of players at even positions (index 1, 3, 5...)
function EvenPlayers(team) {
  const evenTeam = team.filter((player, index) => index % 2 !== 0);
  return (
    <ul>
      {evenTeam.map((player, index) => (
        <li key={index}>{player}</li>
      ))}
    </ul>
  );
}

function App() {
  var flag = false;

  // 11 players with name and score
  const players = [
    { name: 'Rohit Sharma', score: 85 },
    { name: 'Virat Kohli', score: 92 },
    { name: 'Shubman Gill', score: 45 },
    { name: 'KL Rahul', score: 60 },
    { name: 'Suryakumar Yadav', score: 78 },
    { name: 'Hardik Pandya', score: 55 },
    { name: 'Ravindra Jadeja', score: 40 },
    { name: 'Jasprit Bumrah', score: 15 },
    { name: 'Mohammed Shami', score: 20 },
    { name: 'Yuzvendra Chahal', score: 10 },
    { name: 'Ishan Kishan', score: 65 }
  ];

  // Indian team names for Odd/Even destructuring demo
  const IndianTeam = [
    'Rohit Sharma', 'Virat Kohli', 'Shubman Gill', 'KL Rahul',
    'Suryakumar Yadav', 'Hardik Pandya', 'Ravindra Jadeja',
    'Jasprit Bumrah', 'Mohammed Shami', 'Yuzvendra Chahal', 'Ishan Kishan'
  ];

  // T20 and Ranji Trophy players merged using spread operator
  const T20players = ['Rohit Sharma', 'Virat Kohli', 'Suryakumar Yadav'];
  const RanjiTrophyplayers = ['Prithvi Shaw', 'Sarfaraz Khan', 'Shreyas Iyer'];
  const IndianPlayers = [...T20players, ...RanjiTrophyplayers];

  if (flag === true) {
    return (
      <div>
        <h1> List of Players</h1>
        <ListofPlayers players={players} />
        <hr />
        <h1> List of Players having Scores Less than 70 </h1>
        <Scorebelow70 players={players} />
      </div>
    );
  }
  else {
    return (
      <div>
        <div>
          <h1> Indian Team </h1>
          <h1> Odd Players </h1>
          {OddPlayers(IndianTeam)}
          <hr />
          <h1> Even Players</h1>
          {EvenPlayers(IndianTeam)}
        </div>
        <hr />
        <div>
          <h1> List of Indian Players Merged:</h1>
          <ListofIndianPlayers IndianPlayers={IndianPlayers} />
        </div>
      </div>
    );
  }
}

export default App;