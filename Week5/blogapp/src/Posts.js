import React, { Component } from 'react';

class Posts extends Component {

  // STEP 5: Initialize state with an empty list of posts
  constructor(props) {
    super(props);
    this.state = {
      posts: [],
      hasError: false
    };
  }

  // STEP 6: Fetch posts from the API and update state
  loadPosts() {
    fetch('https://jsonplaceholder.typicode.com/posts')
      .then(response => response.json())
      .then(data => {
        this.setState({ posts: data });
      })
      .catch(error => {
        console.error('Error fetching posts:', error);
      });
  }

  // STEP 7: componentDidMount() — called once, right after component mounts
  componentDidMount() {
    this.loadPosts();
  }

  // STEP 9: componentDidCatch() — catches errors in this component's tree
  componentDidCatch(error, info) {
    this.setState({ hasError: true });
    alert('An error occurred while rendering posts: ' + error.toString());
    console.error('Error caught in componentDidCatch:', error, info);
  }

  // STEP 8: render() — displays title and body of each post
  render() {
    if (this.state.hasError) {
      return <h3>Something went wrong while loading posts.</h3>;
    }

    return (
      <div>
        <h1>Blog Posts</h1>
        {this.state.posts.map(post => (
          <div key={post.id} style={{ marginBottom: '20px' }}>
            <h2>{post.title}</h2>
            <p>{post.body}</p>
          </div>
        ))}
      </div>
    );
  }
}

export default Posts;