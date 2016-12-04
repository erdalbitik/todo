package com.ebitik.todo.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ebitik.todo.domain.User;
import com.ebitik.todo.domain.UserRole;

/**
 * @author erdal.bitik
 * */

@Transactional
@Repository
public interface UserRoleDao extends CrudRepository<UserRole, Long> {
	
	public List<UserRole> findByUser(User user);

}