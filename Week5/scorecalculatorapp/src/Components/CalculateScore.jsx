// CalculateScore.jsx
// ================================================================
// Import React — required for JSX
// Import the CSS stylesheet for styling
// ================================================================
import React from 'react'
import '../Stylesheets/mystyle.css'

// ================================================================
// CalculateScore — Function Component
//
// Props (properties) explained:
// Props are inputs passed FROM parent (App.jsx) TO this component
// Like function arguments — parent sends data, child receives it
//
// Props received:
// - name   : Student's name
// - school : School name
// - total  : Total marks obtained (sum of all subjects)
// - goal   : Target/goal score percentage
//
// Props are READ-ONLY — component cannot modify them
// ================================================================
function CalculateScore(props) {

    // ============================================================
    // Calculate average score
    // Assuming total is sum of 5 subjects
    // average = total / 5
    // ============================================================
    const numberOfSubjects = 5
    const average = props.total / numberOfSubjects

    // ============================================================
    // Calculate percentage achieved
    // ============================================================
    const percentage = ((props.total / 500) * 100).toFixed(2)

    // ============================================================
    // Check if goal is achieved
    // Compare average with goal
    // ============================================================
    const goalAchieved = average >= props.goal

    // ============================================================
    // Return JSX — the UI this component renders
    // JSX Rules:
    // - className instead of class (class is reserved in JS)
    // - All tags must be closed (<br /> not <br>)
    // - Only ONE parent element
    // - JavaScript expressions inside { } curly braces
    // ============================================================
    return (
        <div className="score-card">

            {/* Header Section */}
            <div className="header">
                <h1 className="portal-title">
                    Student Management Portal
                </h1>
                <h2 className="subtitle">Score Calculator</h2>
            </div>

            {/* Student Details Section */}
            <div className="student-info">
                <h3 className="section-title">Student Details</h3>
                <table className="info-table">
                    <tbody>
                        <tr>
                            <td className="label">Student Name</td>
                            <td className="value">{props.name}</td>
                        </tr>
                        <tr>
                            <td className="label">School</td>
                            <td className="value">{props.school}</td>
                        </tr>
                        <tr>
                            <td className="label">Total Marks</td>
                            <td className="value">{props.total} / 500</td>
                        </tr>
                        <tr>
                            <td className="label">Goal Score</td>
                            <td className="value">{props.goal}</td>
                        </tr>
                    </tbody>
                </table>
            </div>

            {/* Score Results Section */}
            <div className="results">
                <h3 className="section-title">Score Results</h3>
                <div className="result-box">
                    <p className="result-item">
                        <span className="result-label">
                            Average Score:
                        </span>
                        <span className="result-value">
                            {average}
                        </span>
                    </p>
                    <p className="result-item">
                        <span className="result-label">
                            Percentage:
                        </span>
                        <span className="result-value">
                            {percentage}%
                        </span>
                    </p>
                    <p className="result-item">
                        <span className="result-label">
                            Goal Status:
                        </span>
                        <span className={goalAchieved
                            ? "status-pass"
                            : "status-fail"}>
                            {goalAchieved
                                ? "✅ Goal Achieved!"
                                : "❌ Goal Not Achieved"}
                        </span>
                    </p>
                </div>
            </div>

        </div>
    )
}

// ================================================================
// Export the component so App.jsx can import and use it
// Without this, App.jsx cannot find CalculateScore
// ================================================================
export default CalculateScore