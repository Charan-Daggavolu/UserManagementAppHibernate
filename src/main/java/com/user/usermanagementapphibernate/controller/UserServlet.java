package com.user.usermanagementapphibernate.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.user.usermanagementapphibernate.dao.UserDao;
import com.user.usermanagementapphibernate.model.User;

import services.UserService;
import services.impl.UserServiceImpl;

public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserDao userDao;
	private transient UserService userService;

	public void init() {
		userDao = new UserDao();
		userService = new UserServiceImpl();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String keyword = request.getParameter("keyword");
		String deleteId = request.getParameter("deleteId");
		String editId = request.getParameter("editId");
		if (keyword != null && !keyword.isEmpty()) {
			userService.listFilteredUsers(keyword, request, response);
		} else if (deleteId != null && !deleteId.isEmpty()) {
			String userId = deleteId;
			userDao.deleteUser(userId);
		} else if (editId != null && !editId.isEmpty()) {
			userService.editUsers(request, response, editId);
		} else {
			userService.listAllUsers(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String updateId = request.getParameter("updateId");
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String username = request.getParameter("username");
		String address = request.getParameter("address");
		String contact = request.getParameter("contact");
		String email = request.getParameter("email");
		String userDataJson = request.getParameter("userData");
		String countryCode = request.getParameter("countryCode");

		User user = new User(firstName, lastName, username, email, address, contact, countryCode);
		if (updateId == null) {
			userDao.insertUser(user);
		} else if (updateId != null && !updateId.isEmpty() && userDataJson != null && !userDataJson.isEmpty()) {
			Gson gson = new Gson();
			User updateUser = gson.fromJson(userDataJson, User.class);
			userDao.updateUser(updateUser, updateId);
		}

		userService.listAllUsers(request, response);
		response.sendRedirect("index.jsp");
	}
}
