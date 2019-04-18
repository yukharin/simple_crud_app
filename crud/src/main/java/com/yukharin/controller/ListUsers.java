package com.yukharin.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yukharin.dao.userdao.UserDao;
import com.yukharin.dao.utils.JdbcUtils;
import com.yukharin.model.User;

public class ListUsers extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final String MAIN_PAGE = "ListOfUsers.jsp";
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        UserDao dao = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            dao = new UserDao();
            List<User> users = dao.getUsersList();
            request.setAttribute("users", users);
            RequestDispatcher dispatcher = request.getRequestDispatcher(MAIN_PAGE);
            dispatcher.forward(request, response);
        } catch (ClassNotFoundException | IOException | ServletException | SQLException e) {
            e.printStackTrace();
        } finally {
            if (dao != null) {
                JdbcUtils.closeConnection(dao.getConnection());
            }
        }
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    {
        
    }
}
