package com.cloudwise.smartagent.communicate;

import javax.ws.rs.core.MediaType;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class HttpResource {
	private String baseUrl;

	public HttpResource(String baseUrl) {
		this.baseUrl = baseUrl;
	}

	public String getBaseUrl() {
		return baseUrl;
	}

	public ClientResponse post(String path, Object object, MediaType mediaType) {
		Client client = Client.create();
		WebResource webResource = client.resource(baseUrl + path);
		return webResource.type(mediaType).post(ClientResponse.class, object);
	}

	public ClientResponse put(String path, Object object, MediaType mediaType) {
		Client client = Client.create();
		WebResource webResource = client.resource(baseUrl + path);
		return webResource.type(mediaType).put(ClientResponse.class, object);
	}

	public String get(String path, String id, MediaType mediaType) {
		Client client = Client.create();
		WebResource webResource = client.resource(baseUrl + path);
		return webResource.path(id).accept(mediaType).get(String.class);
	}

	public <T> T get(String path, String id, MediaType mediaType, Class<T> clazz) {
		Client client = Client.create();
		WebResource webResource = client.resource(baseUrl + path);
		return webResource.path(id).accept(mediaType).get(clazz);
	}
}
