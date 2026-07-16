import React from 'react';

function BookDetails(props) {
  const books = [
    { id: 1, title: 'Atomic Habits', author: 'James Clear' },
    { id: 2, title: 'The Alchemist', author: 'Paulo Coelho' },
    { id: 3, title: 'Deep Work', author: 'Cal Newport' }
  ];

  // Conditional Rendering Technique #1: if / else
  if (!props.showBooks) {
    return null; // Prevents this component from rendering anything
  }

  return (
    <div>
      <h2>Book Details</h2>
      <ul>
        {books.map((book) => (
          <li key={book.id}>
            {book.title} — by {book.author}
          </li>
        ))}
      </ul>
    </div>
  );
}

export default BookDetails;