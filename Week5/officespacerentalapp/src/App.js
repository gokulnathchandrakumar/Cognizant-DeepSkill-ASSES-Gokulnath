import React from 'react';
import './App.css';

// Uncomment this line if you added a local image file named office.jpg in src/
// import sr from './office.jpg';

function App() {

  // Using a placeholder image URL (remove this line if using local import above)
  const sr = 'https://images.unsplash.com/photo-1497366216548-37526070297c?w=400&h=250&fit=crop';

  // Element created using JSX
  const element = "Office Space";

  // JSX attribute example - image element with width, height, alt attributes
  const jsxatt = <img src={sr} width="25%" height="25%" alt="Office Space" />;

  // Object holding office details
  const ItemName = { Name: "DBS", Rent: 50000, Address: 'Chennai' };

  // Determine color based on Rent value (JS expression logic)
  let colors = [];
  if (ItemName.Rent <= 60000) {
    colors.push('textRed');
  } else {
    colors.push('textGreen');
  }

  // List of office objects to loop through
  const officeList = [
    { Name: "DBS", Rent: 50000, Address: 'Chennai' },
    { Name: "TCS", Rent: 75000, Address: 'Bangalore' },
    { Name: "Infosys", Rent: 45000, Address: 'Hyderabad' },
    { Name: "Wipro", Rent: 68000, Address: 'Pune' }
  ];

  return (
    <div className="App">
      <h1>{element} , at Affordable Range </h1>
      {jsxatt}
      <h1>Name: {ItemName.Name}</h1>
      <h3 style={{ color: colors[0] === 'textRed' ? 'red' : 'green' }}>
        Rent: Rs. {ItemName.Rent}
      </h3>
      <h3>Address: {ItemName.Address}</h3>

      <hr />

      <h2>List of Office Spaces</h2>
      {officeList.map((office, index) => (
        <div key={index} style={{ marginBottom: '15px' }}>
          <h3>Name: {office.Name}</h3>
          <h3 style={{ color: office.Rent <= 60000 ? 'red' : 'green' }}>
            Rent: Rs. {office.Rent}
          </h3>
          <h3>Address: {office.Address}</h3>
        </div>
      ))}
    </div>
  );
}

export default App;