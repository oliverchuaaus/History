package com.tougher.prattle.rest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.tougher.prattle.domain.Prattler;

public class RestTest {

	@Test
	public void testGetPrattler() {
		Map<String, String> urlVariables = new HashMap<String, String>();
		urlVariables.put("username", "username");
		ResponseEntity<Prattler> prattler = new RestTemplate().getForEntity(
				"http://localhost:8080/prattle/prattlers/rest/{username}",
				Prattler.class, urlVariables);
		assertEquals("username", prattler.getBody().getUsername());
	}

	@Test
	public void testGetPrattlers() {
		Map<String, String> urlVariables = new HashMap<String, String>();
		urlVariables.put("username", "username");
		@SuppressWarnings("unchecked")
		List<Prattler> prattler = new RestTemplate().getForObject(
				"http://localhost:8080/prattle/prattlers/rest/prattlers",
				List.class, urlVariables);
		assertEquals(3, prattler.size());
	}

	@Test
	public void testPutPrattler() {
		Map<String, String> urlVariables = new HashMap<String, String>();
		urlVariables.put("username", "username");
		RestTemplate rt = new RestTemplate();
		Prattler prattler = rt.getForObject(
				"http://localhost:8080/prattle/prattlers/rest/{username}",
				Prattler.class, urlVariables);
		assertEquals("username", prattler.getUsername());
		prattler.setFullName("Boy Praning");
		rt.put("http://localhost:8080/prattle/prattlers/rest/{username}",
				prattler, urlVariables);
		prattler = rt.getForObject(
				"http://localhost:8080/prattle/prattlers/rest/{username}",
				Prattler.class, urlVariables);
		assertEquals("Boy Praning", prattler.getFullName());
	}

	@Test
	public void testPostPrattler() {
		Map<String, String> urlVariables = new HashMap<String, String>();
		urlVariables.put("username", "username");
		RestTemplate rt = new RestTemplate();
		Prattler prattler = new Prattler();
		prattler.setFullName("Boy Praning");
		prattler.setUsername("boypraning");
		prattler.setPassword("boypraning");
		prattler.setEmail("boypraning@mail.com");

		rt.postForObject(
				"http://localhost:8080/prattle/prattlers/rest/{username}",
				prattler, Prattler.class, urlVariables);
		prattler = rt.getForObject(
				"http://localhost:8080/prattle/prattlers/rest/{username}",
				Prattler.class, urlVariables);
		assertEquals("Boy Praning", prattler.getFullName());
	}
}
