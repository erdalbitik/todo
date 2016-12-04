package com.ebitik.todo.web;

import java.net.URI;

import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import com.ebitik.todo.config.TodoAppConfig;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * WEB-MVC Test
 *
 * @author erdal.bitik
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {TodoAppConfig.class}, webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext
public class ControllerTest {

	@Autowired
	private TestRestTemplate restTemplate;

	@LocalServerPort
	private int port;

	@Test
	public void testHome() throws Exception {
		ResponseEntity<String> entity = this.restTemplate.getForEntity("/", String.class);
		assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(entity.getBody()).contains("<title>TODO");
		assertThat(entity.getBody()).doesNotContain("layout:fragment");
	}

	@Test
	public void testCreate() throws Exception {
		MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
		map.set("text", "FOO text");
		map.set("summary", "FOO");
		URI location = this.restTemplate.postForLocation("/", map);
		assertThat(location.toString()).contains("localhost:" + this.port);
	}

	@Test
	public void testCss() throws Exception {
		ResponseEntity<String> entity = this.restTemplate
				.getForEntity("/css/layout.css", String.class);
		assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(entity.getBody()).contains("body");
	}

}