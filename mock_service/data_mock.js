const express = require("express");
const { v4: uuidv4 } = require("uuid");
const app = express();
const port = 3000;

app.use(express.json());

const db = {
  institutes: [],
  courses: [],
  branches: [],
  semesters: [],
  subjects: [],
  units: []
};

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
