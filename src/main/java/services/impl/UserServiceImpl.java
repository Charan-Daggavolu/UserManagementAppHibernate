package services.impl;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.user.usermanagementapphibernate.dao.UserDao;
import com.user.usermanagementapphibernate.model.User;

import services.UserService;

public class UserServiceImpl implements UserService {
    private UserDao userDao;

    public UserServiceImpl() {
        userDao = new UserDao();
    }

    public void listAllUsers(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<User> listUser = userDao.selectAllUsers();
        String html = generateUserTable(listUser);
        response.setContentType("text/html");
        response.getWriter().write(html);
    }

    public void listFilteredUsers(String keyword, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<User> filteredUsers = userDao.searchUsers(keyword);
        String html = generateUserTable(filteredUsers);
        response.setContentType("text/html");
        response.getWriter().write(html);
    }

    public void editUsers(HttpServletRequest request, HttpServletResponse response, String editId) throws ServletException, IOException {
        User user = userDao.editUser(editId);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        String userJson = "{"
                + "\"id\":\"" + editId + "\","
                + "\"firstName\":\"" + user.getFirstName() + "\","
                + "\"lastName\":\"" + user.getLastName() + "\","
                + "\"username\":\"" + user.getUsername() + "\","
                + "\"email\":\"" + user.getEmail() + "\","
                + "\"address\":\"" + user.getAddress() + "\","
                + "\"contact\":\"" + user.getContact() + "\","
                + "\"countryCode\":\"" + user.getCountryCode() + "\""
                + "}";
        response.getWriter().write(userJson);
    }

    public String generateUserTable(List<User> users) {
        StringBuilder html = new StringBuilder();
        html.append("<table><thead><tr><th>First Name</th><th>Last Name</th><th>Username</th><th>Email</th><th>Address</th><th>Contact</th><th>Actions</th></tr></thead><tbody>");
        for (User user : users) {
            html.append("<tr><td>")
                    .append(user.getFirstName()).append("</td><td>")
                    .append(user.getLastName()).append("</td><td>")
                    .append(user.getUsername()).append("</td><td>")
                    .append(user.getEmail()).append("</td><td>")
                    .append(user.getAddress()).append("</td><td>")
                    .append(user.getContact()).append("</td><td>")
                    .append("<img src='icons/edit.png' style='cursor:pointer;width: 25px;margin-bottom: 5px;' class='edit' id='").append(user.getId()).append("' style='cursor:pointer;' alt='Edit'/>")
                    .append("&nbsp;&nbsp;&nbsp;&nbsp;")
                    .append("<img src='icons/delete.png' style='cursor:pointer;width: 25px;margin-bottom: 5px;' class='delete' id='").append(user.getId()).append("' style='cursor:pointer;' alt='Delete'/>")
                    .append("</td></tr>");
        }
        html.append("</tbody></table>");
        return html.toString();
    }
}
