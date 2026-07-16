import React from 'react';

function CourseDetails(props) {
  const courses = [
    { id: 1, title: 'React Fundamentals', duration: '4 weeks' },
    { id: 2, title: 'Spring Boot Microservices', duration: '6 weeks' },
    { id: 3, title: 'JavaScript ES6 Essentials', duration: '3 weeks' }
  ];

  // Conditional Rendering Technique #3: Element variable
  let noticeMessage;
  if (courses.length === 0) {
    noticeMessage = <p>No courses available at the moment.</p>;
  } else {
    noticeMessage = <p>{courses.length} courses currently available.</p>;
  }

  return (
    <div>
      <h2>Course Details</h2>
      {noticeMessage}

      {/* Conditional Rendering Technique #4: Logical && operator */}
      {props.showCourses && (
        <ul>
          {courses.map((course) => (
            <li key={course.id}>
              {course.title} — Duration: {course.duration}
            </li>
          ))}
        </ul>
      )}
    </div>
  );
}

export default CourseDetails;