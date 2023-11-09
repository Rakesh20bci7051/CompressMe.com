/*package Rakesh;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

@WebServlet("/SignupAndLoginServlet")
public class SignupAndLoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Resource(name = "jdbc/project")
    private DataSource datasource;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        String action = request.getParameter("action");
        if (action.equals("signup")) {
            // Handle user signup
            String username = request.getParameter("username");
            String password = request.getParameter("password");

            try {
                connection = datasource.getConnection();
                String insertQuery = "INSERT INTO user (username, password) VALUES (?, ?)";
                preparedStatement = connection.prepareStatement(insertQuery);
                preparedStatement.setString(1, username);
                preparedStatement.setString(2, password);
                int rowsAffected = preparedStatement.executeUpdate();

                if (rowsAffected > 0) {
                    out.println("Signup successful!");
                } else {
                    out.println("Signup failed.");
                }
            } catch (SQLException e) {
                e.printStackTrace();
                out.println("Signup failed due to a database error.");
            }
        } else if (action.equals("login")) {
            // Handle user login
            String username = request.getParameter("username");
            String password = request.getParameter("password");

            try {
                connection = datasource.getConnection();
                String selectQuery = "SELECT * FROM user WHERE username = ? AND password = ?";
                preparedStatement = connection.prepareStatement(selectQuery);
                preparedStatement.setString(1, username);
                preparedStatement.setString(2, password);
                resultSet = preparedStatement.executeQuery();

                if (resultSet.next()) {
                    out.println("Login successful!");
                } else {
                    out.println("Login failed. Invalid credentials.");
                }
            } catch (SQLException e) {
                e.printStackTrace();
                out.println("Login failed due to a database error.");
            }
        }
    }
}
*/
package Rakesh;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

@WebServlet("/SignupAndLoginServlet")
public class SignupAndLoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Resource(name = "jdbc/project")
    private DataSource datasource;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        String action = request.getParameter("action");
        if (action.equals("signup")) {
            // Handle user signup
            String username = request.getParameter("username");
            String password = request.getParameter("password");

            try {
                connection = datasource.getConnection();
                String insertQuery = "INSERT INTO user (username, password) VALUES (?, ?)";
                preparedStatement = connection.prepareStatement(insertQuery);
                preparedStatement.setString(1, username);
                preparedStatement.setString(2, password);
                int rowsAffected = preparedStatement.executeUpdate();

                if (rowsAffected > 0) {
                    out.println("Successfully signed in!");
                    // Redirect to index.html after displaying the message
                    response.setHeader("Refresh", "2;URL=index.html");
                } else {
                    out.println("Signup failed.");
                }
            } catch (SQLException e) {
                e.printStackTrace();
                out.println("Signup failed due to a database error.");
            }
        } else if (action.equals("login")) {
            // Handle user login
            String username = request.getParameter("username");
            String password = request.getParameter("password");

            try {
                connection = datasource.getConnection();
                String selectQuery = "SELECT * FROM user WHERE username = ? AND password = ?";
                preparedStatement = connection.prepareStatement(selectQuery);
                preparedStatement.setString(1, username);
                preparedStatement.setString(2, password);
                resultSet = preparedStatement.executeQuery();

                if (resultSet.next()) {
                    out.println("Successfully signed in!");
                    // Redirect to index.html after displaying the message
                    response.setHeader("Refresh", "2;URL=index.html");
                } else {
                    out.println("Login failed. Invalid credentials.");
                }
            } catch (SQLException e) {
                e.printStackTrace();
                out.println("Login failed due to a database error.");
            }
        }
    }
}
