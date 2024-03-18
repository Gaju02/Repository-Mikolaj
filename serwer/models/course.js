const mongoose = require('mongoose');

const CourseSchema = new mongoose.Schema({
  title: String,
  description: String,
  price: Number,
  duration: String,
  preview: String
});

const Course = mongoose.model('Course', CourseSchema);

module.exports = Course;


