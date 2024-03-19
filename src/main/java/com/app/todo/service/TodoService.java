package com.app.todo.service;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import com.app.todo.model.Todo;
import com.app.todo.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TodoService {
	
	private static List<Todo> todos = new ArrayList<>();

	private TodoRepository todoRepository;

	public TodoService(TodoRepository todoRepository){
		this.todoRepository = todoRepository;
	}
	
	public List<Todo> findByUsername(String username){
		List<Todo> todoList= todoRepository.findByUsername(username);
		return todoList;
	}
	
	public Todo addTodo(String username, String description, LocalDate targetDate, boolean done) {
		Todo todo = new Todo(username,description,targetDate,done);
		todoRepository.save(todo);
		return todo;
	}
	
	public void deleteById(int id) {
		todoRepository.deleteById(id);
	}

	public Todo findById(int id) {
		Todo todo = todoRepository.findById(id).get();
		return todo;
	}

	public void updateTodo(Todo todo) {
		Todo todoData = todoRepository.findById(todo.getId()).get();
		todoData.setDescription(todo.getDescription());
		todoData.setDone(todo.isDone());
		todoData.setTargetDate(todo.getTargetDate());
		todoRepository.save(todo);
	}
}