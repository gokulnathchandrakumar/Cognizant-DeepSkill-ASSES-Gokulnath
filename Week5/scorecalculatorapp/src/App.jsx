// App.jsx
// ================================================================
// App is the ROOT component — parent of all other components
// It imports and uses the CalculateScore component
// Passes data to CalculateScore via props
// ================================================================
import React from 'react'

// Import CalculateScore component from Components folder
// ./ means current directory (src/)
// Components/CalculateScore = path to the file
import CalculateScore from './Components/CalculateScore'

function App() {
    return (
        <div>
            {/*
                Using CalculateScore component like a custom HTML tag
                Passing props (data) to the component:
                - name="Gokulnath"     → props.name
                - school="ABC School"  → props.school
                - total={450}          → props.total (number, use {})
                - goal={80}            → props.goal  (number, use {})

                String values use "quotes"
                Number values use {curly braces}
            */}
            <CalculateScore
                name="Gokulnath"
                school="ABC Higher Secondary School"
                total={450}
                goal={80}
            />
        </div>
    )
}

export default App