require('dotenv').config();
const express = require('express');
const app = express();
const path = require('path');
const express_layouts = require('express-ejs-layouts');
const { getInstitutes, getSemestersByBranch } = require('./apis/microservices_endpoints');
const {log_error} = require("./log_errors");


const PORT = process.env.PORT || 4010;


app.set('view engine', 'ejs');
app.set('views', path.join(__dirname, 'views'));

app.use(express_layouts);

app.set('layout', 'layouts/main');

app.use(express.static(path.join(__dirname, 'public')));

app.get('/', async (req, res) => {
  try {
    const data = await getInstitutes();
    res.render('pages/index', { institutes: data, title: 'Home' });
  } catch (error) {
    log_error(error);
    res.status(500).render('pages/404', { title: 'Error', message: 'Failed to load home page.' });
  }
});


app.get('/resources/:branchId', async (req, res) => {
  try {
    const branchId = req.params.branchId;

    const data = await getSemestersByBranch(branchId);

    res.render('pages/courses', {
      title: 'NoteX',
      data,
      branchId
    });
  } catch (error) {
    log_error(error);
    res.status(500).render('pages/404', {
      title: 'Error',
      message: 'Failed to load courses.'
    });
  }
});

app.get('/cdn/:resourceId', async (req, res) => {
  try {
    const data = await downloadResource(req.params.resourceId);
    res.render('pages/courses', { institutes: data, title: 'Home' });
  } catch (error) {
    log_error(error);
    res.status(500).render('pages/404', { title: 'Error', message: 'Failed to load home page.' });
  }
});


app.get('*', (req, res) => {
  res.status(404).render('pages/404', { title: 'Page Not Found' });
});

app.listen(PORT, () => {
  console.log(`Server running at http://localhost:${PORT}`);
});
