package com.ebitik.todo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ebitik.todo.dao.EntryDao;
import com.ebitik.todo.domain.Entry;
import com.ebitik.todo.domain.User;

/**
 * 
 * Entry Service. bussiness logic for create, update, delete todo entries
 * 
 * @author erdal.bitik
 * */

@Service
public class EntryService {
	
	//private static Logger logger = Logger.getLogger(EntryService.class);
	
	@Autowired
	EntryDao dao;
		
	public Entry addEntry(User user, String text) {
		Entry entry = new Entry(text);
		entry.setUser(user);
		entry = dao.save(entry);
		return entry;
	}
	
	public void updateEntry(Long id, String text) {
		Entry entry = dao.findOne(id);
		entry.setText(text);
		dao.save(entry);
	}
	
	public Entry findEntry(Long id) {
		return dao.findOne(id);
	}
	
	public void deleteEntry(Long id) {
		dao.delete(id);
	}
	
	public List<Entry> findByUser(User user) {
		return dao.findByUser(user);
	}
	
	public Iterable<Entry> findAll() {
		return dao.findAll();
	}

}
