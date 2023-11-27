package com.ra.model.service;

import com.ra.model.dao.StudentDAO;
import com.ra.model.dao.StudentDAOImpl;
import com.ra.model.entity.Student;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class StudentServiceImpl implements StudentService {
    private final StudentDAO studentDAO = new StudentDAOImpl();

    @Override
    public List<Student> findAll() {
        return studentDAO.findAll();
    }

    @Override
    public List<Student> findByName(String name) {
        return studentDAO.findByName(name);
    }

    @Override
    public Student findById(Integer integer) {
        return studentDAO.findById(integer);
    }

    @Override
    public boolean saveOrUpdate(Student student, Integer integer) {
        return studentDAO.saveOrUpdate(student, integer);
    }

    @Override
    public void delete(Integer integer) {
        studentDAO.delete(integer);
    }

    @Override
    public List<Student> findAllSortedByName() {
        List<Student> studentList = studentDAO.findAll();
        Collections.sort(studentList, Comparator.comparing(Student::getName));
        return studentList;
    }
}
