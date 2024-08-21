package com.user.usermanagementapphibernate.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.user.usermanagementapphibernate.model.User;
import com.user.usermanagementapphibernate.util.HibernateUtil;

public class UserDao {

	


	public void insertUser(User user) {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			Transaction transaction = session.beginTransaction();
			session.save(user);
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void updateUser(User user, String editId) {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			Transaction transaction = session.beginTransaction();
			user.setId(Integer.parseInt(editId));
			session.update(user);
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List<User> selectAllUsers() {
		List<User> users = new ArrayList<>();
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			users = session.createQuery("FROM User", User.class).list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return users;
	}

	public User editUser(String userId) {
		User user = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			user = session.get(User.class, Integer.parseInt(userId));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return user;
	}

	public List<User> searchUsers(String keyword) {
		List<User> users = new ArrayList<>();
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			String searchKeyword = "%" + keyword + "%";
			users = session.createQuery(
					"FROM User WHERE username LIKE :keyword OR firstName LIKE :keyword OR lastName LIKE :keyword",
					User.class).setParameter("keyword", searchKeyword).list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return users;
	}

	public void deleteUser(String userId) {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			Transaction transaction = session.beginTransaction();
			User user = session.get(User.class, Integer.parseInt(userId));
			if (user != null) {
				session.delete(user);
			}
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
