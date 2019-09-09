package com.tavisca.workshops.todolist.com.tavisca.workshops.todolist;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.tavisca.workshops.todolist.com.tavisca.workshops.todolist.exception.UserNotFoundException;
@CrossOrigin
@RestController
public class TodoRestController {
	
	@Autowired
	private TodoDao todoDao;
	
	@GetMapping(path="/todo")
	public List<ToDoList> retrieveListOfTodoItems(){
		return todoDao.ListOfItems();
	}
	
	@GetMapping(path="/todo/{id}")
	public ToDoList getItemById(@PathVariable int id){
		ToDoList item= todoDao.findAnItem(id);
		if(item==null)
			throw new UserNotFoundException("id-" +id);
		return item;
		
	}
	
	@PostMapping("/todo")
	public ResponseEntity<Object> createNewItem(@RequestBody ToDoList item) {
		ToDoList savedItem=todoDao.save(item);
		
		URI location= ServletUriComponentsBuilder.fromCurrentRequest().path("{/id}").
				buildAndExpand(savedItem.getId()).toUri();
		return ResponseEntity.created(location).build();
	}
	
	@DeleteMapping("/todo/{id}")
	public void deleteItem(@PathVariable int id){
		ToDoList item= todoDao.deleteById(id);
	}
		
}
