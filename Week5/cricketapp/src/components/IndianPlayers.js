import React from 'react';

function IndianPlayers() {

  // Full 11-player list, split conceptually into "Odd Team" and "Even Team"
  const allPlayers = [
    'Rohit Sharma', 'Virat Kohli', 'Shubman Gill', 'KL Rahul',
    'Suryakumar Yadav', 'Hardik Pandya', 'Ravindra Jadeja',
    'Jasprit Bumrah', 'Mohammed Shami', 'Yuzvendra Chahal', 'Ishan Kishan'
  ];

  // Destructuring: extracting specific positions as Odd/Even team representatives
  // Odd Team = players at index 0, 2, 4, 6, 8, 10 (odd positions when counted from 1)
  // Even Team = players at index 1, 3, 5, 7, 9 (even positions)
  const [
    oddPlayer1, evenPlayer1,
    oddPlayer2, evenPlayer2,
    oddPlayer3, evenPlayer3,
    oddPlayer4, evenPlayer4,
    oddPlayer5, evenPlayer5,
    oddPlayer6
  ] = allPlayers;

  // T20 and Ranji Trophy player arrays
  const T20players = ['Rohit Sharma', 'Virat Kohli', 'Suryakumar Yadav'];
  const RanjiTrophyplayers = ['Prithvi Shaw', 'Sarfaraz Khan', 'Shreyas Iyer'];

  // Merging arrays using the ES6 spread/merge feature
  const mergedPlayers = [...T20players, ...RanjiTrophyplayers];

  return (
    <div>
      <h2>Odd Team Players (Destructuring)</h2>
      <ul>
        <li>{oddPlayer1}</li>
        <li>{oddPlayer2}</li>
        <li>{oddPlayer3}</li>
        <li>{oddPlayer4}</li>
        <li>{oddPlayer5}</li>
        <li>{oddPlayer6}</li>
      </ul>

      <h2>Even Team Players (Destructuring)</h2>
      <ul>
        <li>{evenPlayer1}</li>
        <li>{evenPlayer2}</li>
        <li>{evenPlayer3}</li>
        <li>{evenPlayer4}</li>
        <li>{evenPlayer5}</li>
      </ul>

      <h2>T20 + Ranji Trophy Players (Merged using ES6 spread)</h2>
      <ul>
        {mergedPlayers.map((player, index) => (
          <li key={index}>{player}</li>
        ))}
      </ul>
    </div>
  );
}

export default IndianPlayers;