const mongoose = require('mongoose');
const Course = require('./models/course.js');

mongoose.connect('mongodb://127.0.0.1:27017/elearning', {
  useNewUrlParser: true,
  useUnifiedTopology: true,
})
  .then(() => {
    console.log('Connected to MongoDB');

    // Dodawanie przykładowych kursów
    const courses = [
      { title: 'JavaScript Fundamentals', description: 'Learn the basics of JavaScript programming.',price: 10,duration:"2 godziny",preview:"https://www.youtube.com/watch?v=-WRv1ssXTXA"},
      { title: 'Node.js for Beginners', description: 'Get started with server-side JavaScript using Node.js.',price: 10,duration:"2 godziny",preview:"https://www.youtube.com/watch?v=-WRv1ssXTXA"},
      { title: 'MongoDB Essentials', description: 'Learn how to work with MongoDB, a NoSQL database.',price: 10,duration:"2 godziny",preview:"https://www.youtube.com/watch?v=-WRv1ssXTXA"},
    ];

    Course.insertMany(courses)
      .then(() => {
        console.log('Courses added to the database');
      })
      .catch((error) => {
        console.error('Error adding courses to the database:', error);
      })
      .finally(() => {
        // Zamknięcie połączenia z bazą danych
        mongoose.disconnect();
      });
  })
  .catch((error) => {
    console.error('Error connecting to MongoDB:', error);
  });
