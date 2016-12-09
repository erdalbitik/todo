package com.ebitik.todo.service;

import java.util.List;
import java.util.Objects;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.ebitik.todo.config.TodoException;
import com.ebitik.todo.dao.UserDao;
import com.ebitik.todo.dao.UserRoleDao;
import com.ebitik.todo.domain.User;
import com.ebitik.todo.domain.UserRole;
import com.ebitik.todo.util.SecurityUtil;

/**
 * User Service
 * 
 * @author erdal.bitik
 * */

@Service
public class UserService {

	@Autowired
	UserDao dao;

	@Autowired
	UserRoleDao userRoleDao;

	public User findByEmail(String email) {
		if(StringUtils.isEmpty(email)) {
			return null;
		}
		return dao.findByEmail(email);
	}

	@Transactional
	public void addUser(User user) {
		String email = user.getEmail();

		if(StringUtils.isEmpty(email)) {
			throw new TodoException("message.register.emailRequired");
		}

		User orgUser = findByEmail(email);
		if(Objects.nonNull(orgUser)) {
			throw new TodoException("message.register.alreadyExist");
		}

		dao.save(user);

		//add a default user role 
		UserRole ur = new UserRole(SecurityUtil.DEFAULT_ROLE);
		ur.setUser(user);
		userRoleDao.save(ur);
	}
	
	public void deleteUser(User user) {
		//delete user roles
		List<UserRole> userRoles = findUserRoles(user);
		userRoleDao.delete(userRoles);
		//delete user
		dao.delete(user.getId());
	}

	public List<UserRole> findUserRoles(User user) {
		return userRoleDao.findByUser(user);
	}

	/**
	 * used for test purposes. 
	 * */
	@Secured("ROLE_USER")
	public String secureMethod() {
		return "Security";
	}

}
