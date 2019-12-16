package cn.edu.hcnu.anlzou.servlet;

import cn.edu.hcnu.anlzou.dao.Dao;
import java.io.IOException;
import java.sql.*;

public class LoginServlet extends javax.servlet.http.HttpServlet {
    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        select(request, response);
    }

    void select(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {

        Dao dao = new Dao();
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        Connection conn = null;
        ResultSet reset = null;
        PreparedStatement pstm = null;

        String sql_user_id = "SELECT * FROM t_users where user_id = ? and password = ?";

        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            conn = DriverManager.getConnection(dao.url, dao.username, dao.password);
            pstm = conn.prepareStatement(sql_user_id);
            pstm.setString(1, username);
            pstm.setString(2, password);
            reset = pstm.executeQuery();

            if (reset.next()) {
                response.sendRedirect("viewEmp");
            } else {
                response.sendRedirect("login.html");
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                reset.close();
                pstm.close();
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}