package com.app.todo.repository;

import java.util.List;

import com.app.todo.Todo;
import org.springframework.data.jpa.repository.JpaRepository;



public interface TodoRepository extends JpaRepository<Todo, Integer>{
	
	List<Todo> findByUsername(String username);

}
