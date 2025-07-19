export const total_branches_count = (courses = []) => {
  return courses.reduce((sum, course) => {
    return sum + (Array.isArray(course.branches) ? course.branches.length : 0);
  }, 0);
};

export const total_semesters_count = (courses = []) => {
  return courses.reduce((sum, course) => {
    if (!Array.isArray(course.branches)) return sum;
    return sum + course.branches.reduce((branchSum, branch) => {
      return branchSum + (Array.isArray(branch.semesters) ? branch.semesters.length : 0);
    }, 0);
  }, 0);
};

export const total_subjects_count = (courses = []) => {
  return courses.reduce((courseSum, course) => {
    if (!Array.isArray(course.branches)) return courseSum;
    return courseSum + course.branches.reduce((branchSum, branch) => {
      if (!Array.isArray(branch.semesters)) return branchSum;
      return branchSum + branch.semesters.reduce((semSum, semester) => {
        return semSum + (Array.isArray(semester.subjects) ? semester.subjects.length : 0);
      }, 0);
    }, 0);
  }, 0);
};

export const total_units_count = (courses = []) => {
  return courses.reduce((courseSum, course) => {
    if (!Array.isArray(course.branches)) return courseSum;

    return courseSum + course.branches.reduce((branchSum, branch) => {
      if (!Array.isArray(branch.semesters)) return branchSum;

      return branchSum + branch.semesters.reduce((semSum, semester) => {
        if (!Array.isArray(semester.subjects)) return semSum;

        return semSum + semester.subjects.reduce((subjectSum, subject) => {
          return subjectSum + (Array.isArray(subject.units) ? subject.units.length : 0);
        }, 0);

      }, 0);

    }, 0);
  }, 0);
};