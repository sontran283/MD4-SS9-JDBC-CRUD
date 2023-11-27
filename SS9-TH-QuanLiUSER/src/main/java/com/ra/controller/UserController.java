package com.ra.controller;

import com.ra.model.entity.User;
import com.ra.model.service.UserService;
import com.ra.model.service.UserServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "UserController", value = "/danh_sach")
public class UserController extends HttpServlet {
    private final UserService userService = new UserServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            showListUser(request, response);
        } else {
            switch (action) {
                case "add":
                    // chuyen den trang add
                    response.sendRedirect("views/add_user.jsp");
                    break;
                case "edit":
                    int idEdit = Integer.parseInt(request.getParameter("id"));
                    User user = userService.findById(idEdit);
                    request.setAttribute("user", user);
                    request.getRequestDispatcher("views/edit_user.jsp").forward(request, response);
                    break;
                case "delete":
                    int idDelete = Integer.parseInt(request.getParameter("id"));
                    userService.delete(idDelete);
                    showListUser(request, response);
                    break;
                case "search":
                    String name = request.getParameter("search");
                    List<User> listSearch = userService.finByName(name);
                    request.setAttribute("list_user", listSearch);
                    request.setAttribute("searchName", name);
                    request.getRequestDispatcher("views/list_user.jsp").forward(request, response);
                    break;
                case "sortByName":
                    List<User> sortList = userService.findAllSortedByName();
                    request.setAttribute("list_user", sortList);
                    request.getRequestDispatcher("views/list_user.jsp").forward(request, response);
                    break;
                default:
                    showListUser(request, response);
                    break;
            }
        }
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String action = request.getParameter("action");
        if (action == null) {
            User user = new User();
            user.setName(request.getParameter("name"));
            user.setEmail(request.getParameter("email"));
            user.setCountry(request.getParameter("country"));
            if (userService.saveOrUpdate(user, null)) {
                showListUser(request, response);
            } else {
                response.sendRedirect("views/add_user.jsp?err");
            }
        }
        if (action.equals("edit")) {
            editUserPost(request, response);
        }
    }

    private void editUserPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int idEdit = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String country = request.getParameter("country");
        User user = new User(idEdit, name, email, country);
        if (userService.saveOrUpdate(user, idEdit)) {
            showListUser(request, response);
        } else {
            response.sendRedirect("views/add_user.jsp");
        }
    }

    public void showListUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<User> listUser = userService.findAll();
        request.setAttribute("list_user", listUser);
        request.getRequestDispatcher("views/list_user.jsp").forward(request, response);
    }
}