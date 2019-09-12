package com.tavisca.workshops.todolist.com.tavisca.workshops.todolist;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.springframework.stereotype.Component;


@Component
public class TodoDao {
	
	
	TodoItem toDoList;
	private int todoCount=3;
	private static List<TodoItem> items=new ArrayList<>();
	
//	static {
//		items.add(new ToDoList(1,"call","call todo"));
////		items.add(new ToDoList(2,"eat", "eattodo"));
////		items.add(new ToDoList(3,"sleep","sleeptodo"));
//		}
//	
	public List<TodoItem> findAll() {
		return items; 
	}
	
	public TodoItem save(TodoItem item){
		 if(item.getId()==null)
		 {
			 item.setId(++todoCount);
			 
		}
		 items.add(item);
		 return item;
	}
	
	public TodoItem findAnItem(int itemId) {
		for (TodoItem item : items) {
			if(item.getId()==itemId)
				return item;
			
		}
		
		return null;
		
	}
	
	public TodoItem deleteById(int itemId) {
		Iterator<TodoItem> iterator=items.iterator();
		while(iterator.hasNext()) {
			TodoItem item=iterator.next();
			if(item.getId()==itemId) {
				iterator.remove();
				return item;
			}
		}
		return null;
	}
	
	public void updateItem(TodoItem itemToUpdate) {
		for (TodoItem item : items)
		{
			if(item.getId()==itemToUpdate.getId()) 
			{
				if(!itemToUpdate.getName().isEmpty())
					item.setName(itemToUpdate.getName());
				if(!itemToUpdate.getDetails().isEmpty())
					item.setDetails(itemToUpdate.getDetails());
			}
			
		}
	}

}
