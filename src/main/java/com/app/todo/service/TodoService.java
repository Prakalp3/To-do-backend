package com.app.todo.service;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
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

	public void updateTodo(Todo todo, int id) {

		Optional<Todo> todoData = todoRepository.findById(todo.getId());
		if(todoData.isPresent()){
			Todo updatedTodo =todoData.get();
			updatedTodo.setDescription(todo.getDescription());
			updatedTodo.setDone(todo.isDone());
			updatedTodo.setTargetDate(todo.getTargetDate());
			todoRepository.save(updatedTodo);
			return;
		}
		Todo newTodo = new Todo();
		newTodo.setId(2);
		newTodo.setUsername(todo.getUsername());
		newTodo.setDescription(todo.getDescription());
		newTodo.setTargetDate(todo.getTargetDate());
		newTodo.setDone(todo.isDone());
		todoRepository.save(newTodo);
	}
}