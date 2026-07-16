import React, { Component } from 'react';
import styles from './CohortDetails.module.css';

class CohortDetails extends Component {

  constructor(props) {
    super(props);
    this.state = {
      cohorts: [
        {
          id: 1,
          name: 'Java Full Stack - Batch 12',
          status: 'ongoing',
          startDate: '01-Jun-2026',
          endDate: '30-Sep-2026',
          trainer: 'Arun Kumar'
        },
        {
          id: 2,
          name: 'React Frontend - Batch 8',
          status: 'completed',
          startDate: '01-Jan-2026',
          endDate: '31-Mar-2026',
          trainer: 'Priya Sharma'
        },
        {
          id: 3,
          name: 'Spring Boot Microservices - Batch 5',
          status: 'ongoing',
          startDate: '15-May-2026',
          endDate: '15-Sep-2026',
          trainer: 'Suresh Nair'
        },
        {
          id: 4,
          name: 'DevOps Essentials - Batch 3',
          status: 'completed',
          startDate: '01-Feb-2026',
          endDate: '30-Apr-2026',
          trainer: 'Divya Menon'
        }
      ]
    };
  }

  render() {
    return (
      <div>
        <h1>Cognizant Academy - Cohort Dashboard</h1>
        {this.state.cohorts.map(cohort => (
          <div key={cohort.id} className={styles.box}>
            <h3 style={{ color: cohort.status === 'ongoing' ? 'green' : 'blue' }}>
              {cohort.name}
            </h3>
            <dl>
              <dt>Status</dt>
              <dd>{cohort.status}</dd>
              <dt>Start Date</dt>
              <dd>{cohort.startDate}</dd>
              <dt>End Date</dt>
              <dd>{cohort.endDate}</dd>
              <dt>Trainer</dt>
              <dd>{cohort.trainer}</dd>
            </dl>
          </div>
        ))}
      </div>
    );
  }
}

export default CohortDetails;