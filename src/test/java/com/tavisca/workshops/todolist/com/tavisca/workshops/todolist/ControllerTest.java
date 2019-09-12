package com.tavisca.workshops.todolist.com.tavisca.workshops.todolist;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.Test;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@WebMvcTest(value = TodoRestController.class)
public class ControllerTest {
	 @Autowired
	    private MockMvc mockMvc;

	    @MockBean
		private TodoDao todoDao;
	   

	    private List<TodoItem> mockTodoList;
	    
	    ObjectMapper mapper = new ObjectMapper();
	    
	    @Before
	    public void setUp(){
	        TodoItem todo1=new TodoItem();
	        todo1.setId(1);
	        todo1.setName("Mocking");
	        todo1.setDetails("Mockingdetails");

	        TodoItem todo2=new TodoItem();
	        todo2.setId(2);
	        todo2.setName("Test");
	        todo2.setDetails("Testingdetails");
	        mockTodoList = new ArrayList<TodoItem>();

	        mockTodoList.add(todo1);
	        mockTodoList.add(todo2);
	    }

	    @Test
	    public void getAllTodos() throws Exception {
	    
	    	
	        Mockito.when(todoDao.findAll())
	        .thenReturn(mockTodoList);
	        
	        String jsonResponse = mapper.writeValueAsString(mockTodoList);
	      
	
	         mockMvc.perform(get("/todo"))
	         .andExpect(status().isOk())
	         .andExpect(content().json(jsonResponse))
	        ;
	 }
	    @Test
	    public void testEmptyTodo() throws Exception {
	        List<TodoItem> todos=new ArrayList<>();
	        Mockito.when(todoDao.findAll()).thenReturn(todos);

	        
	        mockMvc.perform(get("/todo"))
	                .andExpect(status().isOk()).andExpect(content().json("[]"));
	    }
	    
	    @Test
	    public void postRequestCreatesAnItem() throws Exception {
	    	TodoItem todoList=new TodoItem();
	        todoList.setId(1);
	        todoList.setName("Mocking");
	        todoList.setDetails("Mockingdetails");
	        
	        List<TodoItem> list = new ArrayList<>();
	        list.add(todoList);
	        
//	        Mockito.when(todoDao.save(todoList)).thenReturn(todoList);
	        Mockito.when(todoDao.findAll()).thenReturn(list);
	        
	        String jsonRequest = mapper.writeValueAsString(todoList);
	        String jsonResponse = mapper.writeValueAsString(list);
	        
	        mockMvc.perform(
	        		post("/todo")
	        		.contentType(MediaType.APPLICATION_JSON)
	        		.content(jsonRequest))
	        		.andExpect(status().isCreated());
	        
	       mockMvc.perform(get("/todo"))
	       .andExpect(status().isOk())
	       .andExpect(content().json(jsonResponse));
	        
	    }
	    
	    @Test
	    public void deleteAnItem() throws Exception {
//	    	TodoItem todo=new TodoItem();
//	    	todo.setId(4);
//	        todo.setName("New Item");
//	        todo.setDetails("May get deleted");
//	        
//	        List<TodoItem> items=new ArrayList<TodoItem>();
	        
//	        items.add(todo);
//	    	
	    	//Mockito.when(todoDao.findAnItem(2)).thenReturn(todo);
	    	
	    	//Mockito.when(todoDao.findAnItem(4)).thenReturn().the‌​nReturn(null);

	        mockMvc.perform(delete("/todo/4"))
	        		.andExpect(status().isOk());
	        
	        mockMvc.perform(get("/todo"))
	        .andExpect(status().isOk())
	        .andExpect(content().json("[]"));
	    }
	    
	    @Test
	    public void updateTodoItem() throws Exception {
	    	
	        
	    	TodoItem todo=new TodoItem();
	    	todo.setId(1);
	        todo.setName("Updating Mocking");
	        todo.setDetails("Updating Mockingdetails");

	    	
	        Mockito.when(todoDao.save(todo)).thenReturn(todo);
	        
	        System.out.println(todo);
	        
	        mockMvc.perform(get("/todo")
	                .contentType(MediaType.APPLICATION_JSON)
	                .content(mapper.writeValueAsString(todo)))
	                .andExpect(status().isOk());
	    }
	    
	    
}
