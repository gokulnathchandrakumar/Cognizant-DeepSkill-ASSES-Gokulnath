import React from 'react';

function BlogDetails(props) {
  const blogs = [
    { id: 1, title: 'React Hooks Explained', author: 'Priya Sharma' },
    { id: 2, title: 'Understanding JSX', author: 'Arun Kumar' },
    { id: 3, title: 'State vs Props', author: 'Divya Menon' }
  ];

  return (
    <div>
      <h2>Blog Details</h2>
      {/* Conditional Rendering Technique #2: Ternary operator */}
      {props.showBlogs ? (
        <ul>
          {blogs.map((blog) => (
            <li key={blog.id}>
              {blog.title} — by {blog.author}
            </li>
          ))}
        </ul>
      ) : (
        <p>Blog details are currently hidden.</p>
      )}
    </div>
  );
}

export default BlogDetails;