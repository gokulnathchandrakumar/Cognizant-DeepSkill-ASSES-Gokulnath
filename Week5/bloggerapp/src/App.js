import React, { Component } from 'react';
import { books, blogs, courses } from './data';
import './App.css';

class App extends Component {

  constructor(props) {
    super(props);
    this.state = {
      showBooks: true,
      showBlogs: true,
      showCourses: true
    };
  }

  render() {

    // ============================================================
    // Element variable #1: bookdet — matches your hint exactly
    // Conditional rendering technique: if/else (returns null if hidden)
    // ============================================================
    let bookdet = null;
    if (this.state.showBooks) {
      bookdet = (
        <ul>
          {books.map((book) => (
            <div key={book.id}>
              <h3> {book.bname}</h3>
              <h4>{book.price}</h4>
            </div>
          ))}
        </ul>
      );
    }

    // ============================================================
    // Element variable #2: content (for Blog Details)
    // Conditional rendering technique: ternary operator
    // ============================================================
    const content = this.state.showBlogs ? (
      <ul>
        {blogs.map((blog) => (
          <div key={blog.id}>
            <h3>{blog.btitle}</h3>
            <h4>by {blog.author}</h4>
          </div>
        ))}
      </ul>
    ) : (
      <p>Blog details are hidden.</p>
    );

    // ============================================================
    // Element variable #3: coursedet (for Course Details)
    // Conditional rendering technique: logical && operator
    // ============================================================
    const coursedet = this.state.showCourses && (
      <ul>
        {courses.map((course) => (
          <div key={course.id}>
            <h3>{course.ctitle}</h3>
            <h4>Duration: {course.duration}</h4>
          </div>
        ))}
      </ul>
    );

    return (
      <div>
        <h1>Blogger App</h1>

        <button onClick={() => this.setState({ showBooks: !this.state.showBooks })}>
          Toggle Books
        </button>{' '}
        <button onClick={() => this.setState({ showBlogs: !this.state.showBlogs })}>
          Toggle Blogs
        </button>{' '}
        <button onClick={() => this.setState({ showCourses: !this.state.showCourses })}>
          Toggle Courses
        </button>

        <hr />

        <div>
          <div className="st2">
            <h1> Book Details</h1>
            {bookdet}
          </div>
          <div className="v1">
            <h1> Blog Details</h1>
            {content}
          </div>
          <div className="mystyle1">
            <h1> Course Details</h1>
            {coursedet}
          </div>
        </div>
      </div>
    );
  }
}

export default App;