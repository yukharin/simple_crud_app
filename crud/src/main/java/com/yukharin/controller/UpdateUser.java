package com.yukharin.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yukharin.dao.userdao.UserDao;
import com.yukharin.dao.utils.JdbcUtils;
import com.yukharin.model.User;

public class UpdateUser extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private static final String EDIT_USER = "EditUser.jsp";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.getWriter().write("Hello update");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        UserDao dao = null;

        String id = request.getParameter("id");
        String name = request.getParameter("name");
        String age = request.getParameter("age");

        if (id == null || name == null || age == null || id.isEmpty() || name.isEmpty() || age.isEmpty()) {
            request.getSession().setAttribute("id", id);
            response.sendRedirect(EDIT_USER);
        } else {
            try {
                Class.forName("com.mysql.jdbc.Driver");
                dao = new UserDao();
                dao.updateUser(Integer.parseInt(id), new User(name, Integer.parseInt(age)));
                response.sendRedirect(request.getContextPath());
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (dao != null) {
                    JdbcUtils.closeConnection(dao.getConnection());
                }
            }
        }
    }
}
