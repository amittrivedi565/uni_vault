const express = require("express");
const { v4: uuidv4 } = require("uuid");
const app = express();
const port = 3000;
const cors = require("cors");


app.use(express.json());
app.use(cors()); // Allows CORS from all origins (equivalent to Access-Control-Allow-Origin: *)



const db = {
  institutes: [],
  courses: [],
  branches: [],
  semesters: [],
  subjects: [],
  units: []
};

// Sample mock data on startup
const sampleInstituteId = uuidv4();
const sampleCourseId = uuidv4();
const sampleBranchId = uuidv4();
const sampleSemesterId = uuidv4();
const sampleSubjectId = uuidv4();

db.institutes.push({
  id: sampleInstituteId,
  name: "ABC Institute",
  shortname: "ABC",
  code: "ABC001",
  description: "Top tech institute"
});

db.courses.push({
  id: sampleCourseId,
  institute_id: sampleInstituteId,
  name: "Computer Science",
  shortname: "CS",
  code: "CS01",
  description: "CS Engineering"
});

db.branches.push({
  id: sampleBranchId,
  course_id: sampleCourseId,
  name: "Software Engineering",
  shortname: "SE",
  code: "SE01",
  description: "SE Specialization"
});

db.semesters.push({
  id: sampleSemesterId,
  branch_id: sampleBranchId,
  name: "Semester 1",
  shortname: "S1",
  code: "SEM1",
  description: "Intro semester"
});

db.subjects.push({
  id: sampleSubjectId,
  semester_id: sampleSemesterId,
  name: "Data Structures",
  shortname: "DS",
  code: "DS101",
  description: "Fundamentals of DS"
});

db.units.push({
  id: uuidv4(),
  subject_id: sampleSubjectId,
  name: "Arrays and Lists",
  shortname: "A&L",
  code: "UNIT1",
  description: "Intro to arrays and linked lists"
});


// Generic CRUD handler
function createRoutes(entity, foreignKey = null, parentEntity = null) {
  app.get(`/${entity}`, (req, res) => {
    if (foreignKey && req.query[foreignKey]) {
      return res.json(db[entity].filter(e => e[foreignKey] === req.query[foreignKey]));
    }
    res.json(db[entity]);
  });

  app.post(`/${entity}`, (req, res) => {
    const { name, shortname, code, description } = req.body;
    const entry = {
      id: uuidv4(),
      name,
      shortname,
      code,
      description
    };

    if (foreignKey) {
      const foreignId = req.body[foreignKey];
      if (!foreignId) {
        return res.status(400).json({ error: `${foreignKey} is required` });
      }
      entry[foreignKey] = foreignId;
    }

    db[entity].push(entry);
    res.status(201).json(entry);
  });

  app.put(`/${entity}/:id`, (req, res) => {
    const index = db[entity].findIndex(e => e.id === req.params.id);
    if (index === -1) return res.status(404).json({ error: `${entity.slice(0, -1)} not found` });

    db[entity][index] = { ...db[entity][index], ...req.body };
    res.json(db[entity][index]);
  });

  app.delete(`/${entity}/:id`, (req, res) => {
    const index = db[entity].findIndex(e => e.id === req.params.id);
    if (index === -1) return res.status(404).json({ error: `${entity.slice(0, -1)} not found` });

    db[entity].splice(index, 1);
    res.status(204).send();
  });
}

// Register routes for all entities
createRoutes("institutes"); // no foreign key
createRoutes("courses", "instituteId");
createRoutes("branches", "courseId");
createRoutes("semesters", "branchId");
createRoutes("subjects", "semesterId");
createRoutes("units", "subjectId");

app.listen(port, () => {
  console.log(`🚀 University Content Service running at http://localhost:${port}`);
});
