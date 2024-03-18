const express = require('express');
const mongoose = require('mongoose');
const session = require('express-session');
const multer = require('multer');
const fs = require('fs');
const bcrypt = require('bcrypt');
const path = require('path');
const app = express();
const port = 3001;

// Połączenie z bazą danych MongoDB
mongoose.connect('mongodb://127.0.0.1/elearning', {
  useNewUrlParser: true,
  useUnifiedTopology: true,
});

// Obsługa statycznych plików
app.use(express.static(__dirname + '/public'));

// Schemat i model dla użytkownika
const userSchema = new mongoose.Schema({
  username: {
    type: String,
    required: true,
    unique: true,
  },
  password: {
    type: String,
    required: true,
  },
});

const User = mongoose.model('User', userSchema);

// Schemat i model dla kursu
const courseSchema = new mongoose.Schema({
  title: {
    type: String,
    required: true,
  },
  description: {
    type: String,
    required: true,
  },
  price: {
    type: Number,
    required: true,
  },
  duration: {
    type: String,
    required: true,
  },
  preview: {
    type: String,
    required: true,
  },
});

const Course = mongoose.model('Course', courseSchema);

// Inicjalizacja sesji
app.use(
  session({
    secret: 'my-secret-key',
    resave: false,
    saveUninitialized: false,
  })
);

// Middleware dla logowania
app.use(express.urlencoded({ extended: true }));

app.use((req, res, next) => {
  res.locals.user = req.session.user; // przekazanie informacji o użytkowniku do lokalnych zmiennych
  next();
});

// Ustawienie widoków
app.set('view engine', 'ejs');


// Strona główna - wyświetlanie dostępnych kursów
app.get('/', async (req, res) => {
  const courses = await Course.find();
  const userLoggedIn = req.session.user ? true : false;
  res.render('index', { courses, userLoggedIn });
});

// Rejestracja użytkownika
app.get('/register', (req, res) => {
  res.render('register');
});

app.post('/register', async (req, res) => {
  const { username, password } = req.body;
  const hashedPassword = await bcrypt.hash(password, 10);
  const newUser = new User({
    username: username,
    password: hashedPassword,
  });
  await newUser.save();
  res.redirect('/login');
});

// Logowanie użytkownika
app.get('/login', (req, res) => {
  res.render('login');
  req.session.user = { id: userId, username: username }; // Przykładowe informacje o użytkowniku
  res.redirect('/');
});

app.post('/login', async (req, res) => {
  const { username, password } = req.body;
  const user = await User.findOne({ username: username });
  if (user && (await bcrypt.compare(password, user.password))) {
    req.session.user = user;
    res.redirect('/');
  } else {
    res.redirect('/login');
  }
});

// Wylogowywanie użytkownika
app.get('/logout', (req, res) => {
  req.session.destroy();
  res.redirect('/');
});

// Inicjalizacja bazy danych
app.get('/seed', async (req, res) => {
  const courses = [
    {
      title: 'Kurs 1',
      description: 'Opis kursu 1',
      price: 19.99,
      duration: '2 godziny',
      preview: 'https://www.youtube.com/watch?v=abcdefg',
    },
    {
      title: 'Kurs 2',
      description: 'Opis kursu 2',
      price: 29.99,
      duration: '3 godziny',
      preview: 'https://www.youtube.com/watch?v=hijklmn',
    },
    // Dodaj więcej kursów według potrzeb
  ];

  await Course.deleteMany();
  await Course.insertMany(courses);
  res.send('Courses added to the database');
});

app.get('/course/:id', async (req, res) => {
  try {
    const courseId = req.params.id;
    const course = await Course.findById(courseId);
    if (!course) {
      return res.render('error', { message: 'Course not found.' });
    }
    res.render('course', { course });
  } catch (error) {
    console.error(error);
    res.render('error', { message: 'Failed to load course details.' });
  }
});

app.post('/course/:id/purchase', async (req, res) => {
  try {
    const courseId = req.params.id;
    const course = await Course.findById(courseId);
    if (!course) {
      return res.render('error', { message: 'Course not found.' });
    }
    res.render('purchase', { courseId, course });
  } catch (error) {
    console.error(error);
    res.render('error', { message: 'Failed to load purchase page.' });
  }
});

app.post('/course/:id/purchase/payments', async (req, res) => {
  try {
    const courseId = req.params.id;
    const course = await Course.findById(courseId);

    if (!course) {
      return res.render('error', { message: 'Course not found.' });
    }

    // Przetwarzanie zakupu

    res.render('payments');
  } catch (error) {
    console.error(error);
    res.render('error', { message: 'Failed to process purchase.' });
  }
});

app.post('/course/:id/purchase/cancel', async (req, res) => {
  try {
    const courseId = req.params.id;

    // Przekierowanie na stronę główną
    res.redirect('/');
  } catch (error) {
    console.error(error);
    res.render('error', { message: 'Failed to cancel purchase.' });
  }
});
app.get('/add', (req, res) => {
  res.render('addCourse');
});

app.post('/add', async (req, res) => {
  const { title, description, price, duration,preview } = req.body;

  // Tworzenie nowej instancji kursu na podstawie danych z formularza
  const newCourse = new Course({
    title: title,
    description: description,
    price: price,
    duration: duration,
    preview:preview
  });
  console.log(newCourse.title)
  console.log(newCourse.description)
  console.log(newCourse.price)
  console.log(duration)
  console.log(preview)

  // Zapisanie nowego kursu w bazie danych
  await newCourse.save().then(savedCourse => {
      console.log('Dodano nowy kurs:', savedCourse);
      res.redirect('/'); // Przekierowanie użytkownika na stronę główną
    })
    .catch(error => {
      console.log('Błąd podczas dodawania kursu:', error);
      res.redirect('/add'); // Przekierowanie użytkownika z powrotem do formularza w przypadku błędu
    });
});
app.get('/logout', (req, res) => {
  req.session.destroy();
});

// Uruchomienie serwera
app.listen(port, () => {
  console.log(`Server is running on http://localhost:${port}`);
});