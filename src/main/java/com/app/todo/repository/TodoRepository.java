package com.app.todo.repository;

import java.util.List;
import java.util.Optional;

import com.app.todo.model.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface TodoRepository extends CrudRepository<Todo, Integer> {

	Optional<Todo> findByIdAndDescription(Integer id, String desc);
	List<Todo> findByUsername(String username);

}
