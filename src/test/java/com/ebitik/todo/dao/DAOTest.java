package com.ebitik.todo.dao;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.ebitik.todo.config.TodoAppConfig;
import com.ebitik.todo.domain.Entry;
import com.ebitik.todo.domain.User;

/**
 * Tests for userdao and Entry Dao
 * 
 * @author erdal.bitik
 * */

@RunWith(SpringRunner.class)
@SpringBootTest(classes={TodoAppConfig.class})
public class DAOTest {

	@Autowired
	UserDao userRepository;
	
	@Autowired
	EntryDao entryRepository;

	@Test
	public void addUserTest() {
		User user = addNewUser("erdalnew@gmail.com");
		//check if created
		assertThat(user.getId()).isNotNull();
	}
	
	@Test
	public void updateUserTest() {
		User user = addNewUser("erdalupdate@gmail.com");
		assertThat(user.getId()).isNotNull();

		//get same user
		User findByEmail = userRepository.findByEmail(user.getEmail());
		//change name
		findByEmail.setName("TestName");
		findByEmail = userRepository.save(findByEmail);
		
		assertThat(findByEmail.getName()).isEqualTo("TestName");
	}
	
	@Test
	public void deleteUserTest() {
		User user = addNewUser("erdaldelete@gmail.com");
		assertThat(user.getId()).isNotNull();

		//delete user
		userRepository.delete(user);
		
		//check if exist
		User findByEmail = userRepository.findByEmail(user.getEmail());
		
		assertThat(findByEmail).isNull();
	}
	
	private User addNewUser(String email) {
		User user = new User(email, "Erdal Bitik");
		user.setPassword("test");
		user.setPasswordHash("test_hash");
		return userRepository.save(user);
	}
	
	@Test
	public void addEntryTest() {
		//firstly add a user
		User user = addNewUser("erdalnewentry@gmail.com");
		
		Entry entry = new Entry("Just an entry");
		entry.setUser(user);
		entryRepository.save(entry);
		
		assertThat(entry.getId()).isNotNull();
	}
	
	@Test
	public void updateEntryTest() {
		User user = addNewUser("erdalupdateentry@gmail.com");
		assertThat(user.getId()).isNotNull();

		//add an entry
		Entry entry = new Entry("Just an entry");
		entry.setUser(user);
		entryRepository.save(entry);
		
		//get entry from db
		entry = entryRepository.findOne(entry.getId());
		assertThat(entry).isNotNull();
		
		//change text
		entry.setText("newtext");
		entry = entryRepository.save(entry);
		
		//get entry again
		entry = entryRepository.findOne(entry.getId());
		assertThat(entry.getText()).isEqualTo("newtext");
	}
	
	@Test
	public void deleteEntryTest() {
		//add user
		User user = addNewUser("erdaldeleteentry@gmail.com");
		assertThat(user.getId()).isNotNull();

		//add an entry
		Entry entry = new Entry("Just an entry");
		entry.setUser(user);
		entryRepository.save(entry);
		
		//delete it
		entryRepository.delete(entry);
		
		//check if exist
		entry = entryRepository.findOne(entry.getId());
		
		assertThat(entry).isNull();
	}
	

}
