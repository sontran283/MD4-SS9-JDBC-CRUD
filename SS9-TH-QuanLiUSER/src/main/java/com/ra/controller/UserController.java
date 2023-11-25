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
                    break;
                case "delete":
                    int idDelete = Integer.parseInt(request.getParameter("id"));
                    userService.delete(idDelete);
                    showListUser(request, response);
                    break;
                default:
                    showListUser(request, response);
                    break;
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    public void showListUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<User> listUser = userService.findAll();
        request.setAttribute("list_user", listUser);
        request.getRequestDispatcher("views/list_user.jsp").forward(request, response);
    }
}