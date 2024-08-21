package services;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.user.usermanagementapphibernate.model.User;

public interface UserService {
	void listAllUsers(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException;
	void listFilteredUsers(String keyword, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
	void editUsers(HttpServletRequest request, HttpServletResponse response, String editId) throws ServletException, IOException;
	String generateUserTable(List<User> users);
}
