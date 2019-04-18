package com.yukharin.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yukharin.dao.userdao.UserDao;
import com.yukharin.dao.utils.JdbcUtils;

public class DeleteUser extends HttpServlet {

  
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.getWriter().write("Hello delete");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserDao dao = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            dao = new UserDao();
            int id = Integer.valueOf(request.getParameter("id"));
            dao.deleteUser(id);
            response.sendRedirect(request.getContextPath()+"/");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (dao != null) {
                JdbcUtils.closeConnection(dao.getConnection());
            }
        }
    }

}
