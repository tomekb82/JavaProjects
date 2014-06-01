package com.spring.hibernate;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Arrays;

import javax.annotation.Resource;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration({ "file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"})
public class TodoControllerTest {
	
	@Autowired
	private WebApplicationContext webApplicationContext;
	
	private MockMvc mockMvc;
	
	@Before
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

	@Test
	public void test() throws Exception{
		
		//mockMvc.perform(get("/todo"))
        //.andExpect(status().isOk());
	};
	/* 

	@Test
	public void test() throws Exception{
		 mockMvc.perform(get("/todo"))
         .andExpect(status().isOk());
         //.andExpect(view().name("todo/list"))
         //.andExpect(forwardedUrl("/WEB-INF/jsp/todo/list.jsp"))
         //.andExpect(model().attribute("todos", hasSize(2)));
		//fail("Not yet implemented");
	}
*/
}
