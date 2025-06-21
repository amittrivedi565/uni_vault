require('dotenv').config();
const express = require('express');
const app = express();
const path = require('path');
const express_layouts = require('express-ejs-layouts');
const {getInstitutes} = require('./apis/home');


const PORT = process.env.PORT || 4010;


app.set('view engine', 'ejs');
app.set('views', path.join(__dirname, 'views'));

app.use(express_layouts);

app.set('layout', 'layouts/main');

app.use(express.static(path.join(__dirname, 'public')));

app.get('/', async (req, res) => {
  try{
    const data = await getInstitutes();
    res.render('pages/index', { institutes: data, title: 'Home' });
  }catch(error){
    console.error('Error rendering home page:', error.message);
    res.status(500).render('pages/404', { title: 'Error', message: 'Failed to load home page.' });
  }
});


app.get('/:api/:uni/:course/:branch', async (req, res) => {
  try {
    const data = await getInstitutes();
    res.render('pages/courses', {
      title: 'NoteX',
      data,
      api: req.params.api,
      uni: req.params.uni,
      course: req.params.course,
      branch: req.params.branch
    });
  } catch (error) {
    console.error('Error fetching courses:', error.message);
    res.status(500).render('pages/404', {
      title: 'Error',
      message: 'Failed to load courses.'
    });
  }
});


app.get('*', (req, res) => {
  res.status(404).render('pages/404', { title: 'Page Not Found' });
});

app.listen(PORT, () => {
  console.log(`Server running at http://localhost:${PORT}`);
});
