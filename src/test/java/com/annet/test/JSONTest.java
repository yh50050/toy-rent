package com.annet.test;

import org.json.JSONException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.toy.ToyApplication;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ToyApplication.class)
public class JSONTest {
	
	@Autowired
	ObjectMapper ma;
	
	@Test
	public void main1() throws JSONException {
	}
	
}
