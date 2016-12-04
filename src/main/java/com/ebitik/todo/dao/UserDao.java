package com.ebitik.todo.dao;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ebitik.todo.domain.User;

/**
 * DAO for User
 * 
 * @author erdal.bitik
 * */

@Transactional
@Repository
public interface UserDao extends CrudRepository<User, Long> {

  /**
   * Return a user by given email or null if no user is found.
   * 
   * @param email the user email.
   */
  public User findByEmail(String email);

}