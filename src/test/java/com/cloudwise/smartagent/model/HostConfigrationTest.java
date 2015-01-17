package com.cloudwise.smartagent.model;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.io.Charsets;
import org.junit.Test;

import com.cloudwise.smartagent.utils.StringUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.io.Files;

public class HostConfigrationTest {

	@Test
	public void test() {
		String hostKey = StringUtil.MD5("123");
		String targetId = String.valueOf(UUID.randomUUID());
		
		try {
			Map<String,ServiceItem> itemList = Maps.newConcurrentMap();
			
			Map extConfig = Maps.newHashMap();
			extConfig.put("name", "zl");
			extConfig.put("pwd", "123");
			itemList.put("100",new ServiceItem("100", "100", 60, 1, extConfig));
			
			extConfig = Maps.newHashMap();
			extConfig.put("port", "8080");
			extConfig.put("service_name", "memcache-/usr/bin/memcached");
			itemList.put("101",new ServiceItem("101", "101", 60, 2, extConfig));
			
			HostConfigration config = new HostConfigration(hostKey, targetId, "1", itemList);
			String writeValueAsString = new ObjectMapper().writeValueAsString(config);
			System.out.print(writeValueAsString);
			
			System.out.print("\n--------------------------------------------------------------------------\n");
			try {
				File file = new File(getClass().getResource("/test.json").getFile());
				List<String> strList = Files.readLines(file, Charsets.UTF_8);
				StringBuffer buffer = new StringBuffer();
				for(String tmp:strList){
					buffer.append(tmp);
				}
				config = new ObjectMapper().readValue(buffer.toString(), HostConfigration.class);
				System.out.print(new ObjectMapper().writeValueAsString(config));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
