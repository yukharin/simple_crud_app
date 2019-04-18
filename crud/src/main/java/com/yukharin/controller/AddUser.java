package com.yukharin.controller;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yukharin.dao.userdao.UserDao;
import com.yukharin.dao.utils.JdbcUtils;
import com.yukharin.model.User;

public class AddUser extends HttpServlet {

    private static final long serialVersionUID = 1L;


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.getWriter().write("Hello add");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        UserDao dao = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            dao = new UserDao();
            String name = request.getParameter("name");
            int age = Integer.valueOf(request.getParameter("age"));
            User user = new User(name, age);
            dao.addUser(user);
            response.sendRedirect(request.getContextPath());
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        } finally {
            if (dao != null) {
                JdbcUtils.closeConnection(dao.getConnection());
            }
        }
    }

}
