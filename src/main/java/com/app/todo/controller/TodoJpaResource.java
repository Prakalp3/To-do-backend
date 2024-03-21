package com.app.todo.controller;

import java.util.List;

import com.app.todo.model.Todo;
import com.app.todo.repository.TodoRepository;
import com.app.todo.service.TodoService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/users")
public class TodoJpaResource {
	private TodoService todoService;

	public TodoJpaResource(TodoService todoService) {
		this.todoService = todoService;
	}
	
	@GetMapping("/{username}/todos")
	@PreAuthorize("hasAuthority('ROLE_USER')")
	public List<Todo> retrieveTodos(@PathVariable String username) {
		return todoService.findByUsername(username);
	}


	@GetMapping("/{username}/todos/{id}")
	@PreAuthorize("hasAuthority('ROLE_USER')")
	public Todo retrieveTodo(@PathVariable String username,
			@PathVariable int id) {
		return todoService.findById(id);
	}

	@DeleteMapping("/{username}/todos/{id}")
	@PreAuthorize("hasAuthority('ROLE_USER')")
	public ResponseEntity<Void> deleteTodo(@PathVariable String username,
			@PathVariable int id) {
		todoService.deleteById(id);
		return ResponseEntity.noContent().build();
	}


	@PutMapping("/{username}/todos/{id}")
	@PreAuthorize("hasAuthority('ROLE_USER')")
	public Todo updateTodo(@PathVariable String username,
			@PathVariable int id, @RequestBody Todo todo) {
		todoService.updateTodo(todo, id);
		return todo;
	}

	@PostMapping("/{username}/createTodos")
	@PreAuthorize("hasAuthority('ROLE_USER')")
	public Todo createTodo(@PathVariable String username,
			 @RequestBody Todo todo) {
		Todo createdTodo = todoService.addTodo(username, todo.getDescription(),
				todo.getTargetDate(),todo.isDone());
		return createdTodo;
	}

}
