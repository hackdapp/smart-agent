package com.cloudwise.smartagent.communicate;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

/**
 * @author nolan
 *
 */
public abstract class AbstractCommunication {
	
	private String baseUrl;

	public AbstractCommunication(String baseUrl) {
		this.baseUrl = baseUrl;
	}

	public ClientResponse httpPost(String path, Entity entity) {
		 Client client = Client.create();
		WebResource webResource = client.resource(baseUrl + path);
		return webResource.type(entity.getMediaType()).post(
				ClientResponse.class, entity);
	}

	public ClientResponse httpPut(String path, Entity entity) {
		 Client client = Client.create();
		WebResource webResource = client.resource(baseUrl + path);
		return webResource.type(entity.getMediaType()).put(
				ClientResponse.class, entity);
	}

	public String httpGet(String path, String id, MediaType mediaType) {
		 Client client = Client.create();
		WebResource webResource = client.resource(baseUrl + path);
		return webResource.path(id).accept(mediaType).get(String.class);
	}
}
