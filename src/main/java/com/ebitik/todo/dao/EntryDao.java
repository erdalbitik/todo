package com.ebitik.todo.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ebitik.todo.domain.Entry;
import com.ebitik.todo.domain.User;

/**
 * DAO for Entry
 * 
 * @author erdal.bitik
 * */

@Transactional
@Repository
public interface EntryDao extends CrudRepository<Entry, Long> {

  /**
   * Return entries created by given user
   * 
   * @param user who is created entries
   */
  public List<Entry> findByUser(User user);

}