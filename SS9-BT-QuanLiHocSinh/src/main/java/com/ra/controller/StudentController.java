package com.ra.controller;

import com.ra.model.entity.Student;
import com.ra.model.service.StudentService;
import com.ra.model.service.StudentServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.Date;
import java.util.List;

@WebServlet(name = "UserController", value = "/student")
public class StudentController extends HttpServlet {
    private final StudentService studentService = new StudentServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            showStudent(request, response);
        } else {
            switch (action) {
                case "add":
                    response.sendRedirect("views/add_student.jsp");
                    break;
                case "edit":
                    int idEdit = Integer.parseInt(request.getParameter("id"));
                    Student student = studentService.findById(idEdit);
                    request.setAttribute("student", student);
                    request.getRequestDispatcher("views/edit_student.jsp").forward(request, response);
                    break;
                case "delete":
                    int idDelete = Integer.parseInt(request.getParameter("id"));
                    studentService.delete(idDelete);
                    showStudent(request, response);
                    break;
                case "search":
                    String name = request.getParameter("search");
                    List<Student> listSearch = studentService.findByName(name);
                    request.setAttribute("list_student", listSearch);
                    request.setAttribute("searchName", name);
                    request.getRequestDispatcher("views/student_list.jsp").forward(request, response);
                    break;
                case "sortByName":
                    List<Student> sortList = studentService.findAllSortedByName();
                    request.setAttribute("list_student", sortList);
                    request.getRequestDispatcher("views/student_list.jsp").forward(request, response);
                    break;
                default:
                    showStudent(request, response);
                    break;
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String action = request.getParameter("action");
        if (action == null) {
            Student student = new Student();
            student.setName(request.getParameter("name"));
            student.setEmail(request.getParameter("email"));
            student.setBirthday(Date.valueOf(request.getParameter("birthday")));
            student.setAddress(request.getParameter("address"));
            if (studentService.saveOrUpdate(student, null)) {
                showStudent(request, response);
            } else {
                response.sendRedirect("views/add_student.jsp");
            }
        }
        if ("edit".equals(action)) {
            editStudentPost(request, response);
        }
    }

    private void editStudentPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int idEdit = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        Date birthday = Date.valueOf(request.getParameter("birthday"));
        String address = request.getParameter("address");
        Student student = new Student(idEdit, name, email, birthday, address);
        if (studentService.saveOrUpdate(student, idEdit)) {
            showStudent(request, response);
        } else {
            response.sendRedirect("views/edit_student.jsp");
        }
    }

    public void showStudent(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Student> list = studentService.findAll();
        request.setAttribute("list_student", list);
        request.getRequestDispatcher("views/student_list.jsp").forward(request, response);
    }
}