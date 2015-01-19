package com.cloudwise.smartagent.communicate;

import static org.junit.Assert.*;

import java.util.List;

import javax.annotation.Resource;
import javax.ws.rs.core.MediaType;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Lists;
import com.sun.jersey.api.client.ClientResponse;

public class HttpResourceTest {
	private HttpResource httpResource;

	@Before
	public void setUp() throws Exception {
		httpResource = new HttpResource("http://localhost/rest/proxy_discover");
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testHttpPost() {
		List list = Lists.newArrayList("1", "2", "3", "4");

		ClientResponse result = null;
		try {
			result = httpResource.post("/postServices/12345",
					new ObjectMapper().writeValueAsString(list),
					MediaType.APPLICATION_JSON_TYPE);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		assertEquals(200, result.getStatus());

	}

	@Test
	public void testHttpPut() {

	}

	@Test
	public void testHttpGet() {
		String result = httpResource.get("/download", "123",
				MediaType.TEXT_PLAIN_TYPE);
		assertEquals("agent-plugin-os_1.0.0.1.jar", result);
	}

}
