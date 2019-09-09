package com.tavisca.workshops.todolist.com.tavisca.workshops.todolist;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.springframework.stereotype.Component;


@Component
public class TodoDao {
	
	
	ToDoList toDoList;
	private int todoCount=3;
	private static List<ToDoList> items=new ArrayList<>();
	
	static {
		items.add(new ToDoList(1,"call","call todo"));
		items.add(new ToDoList(2,"eat", "eattodo"));
		items.add(new ToDoList(3,"sleep","sleeptodo"));
		}
	
	public List<ToDoList> ListOfItems() {
		return items; 
	}
	
	public ToDoList save(ToDoList item){
		 if(item.getId()==null)
		 {
			 item.setId(++todoCount);
			 
		}
		 items.add(item);
		 return item;
	}
	
	public ToDoList findAnItem(int itemId) {
		for (ToDoList item : items) {
			if(item.getId()==itemId)
				return item;
			
		}
		
		return null;
		
	}
	
	public ToDoList deleteById(int itemId) {
		Iterator<ToDoList> iterator=items.iterator();
		while(iterator.hasNext()) {
			ToDoList item=iterator.next();
			if(item.getId()==itemId) {
				iterator.remove();
				return item;
			}
		}
		return null;
	}

}
