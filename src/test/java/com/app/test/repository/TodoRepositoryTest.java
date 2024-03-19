package com.app.test.repository;

import com.app.todo.model.Todo;
import com.app.todo.repository.TodoRepository;
import org.assertj.core.api.Assertions;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class TodoRepositoryTest {
    @Autowired
    private TodoRepository todoRepository;


//    @Autowired
//    public TodoRepositoryTest(TodoRepository todoRepository) {
//        this.todoRepository = todoRepository;
//    }

    @Test
    public void todoRepositorySaveAll() {
        Todo todo = Todo.builder().username("testUser").description("user for testing").targetDate(LocalDate.now()).done(false).build();

        Todo savedTodo = todoRepository.save(todo);

        Assertions.assertThat(savedTodo).isNotNull();
        Assertions.assertThat(savedTodo.getId()).isGreaterThan(0);

    }

    @Test
    public void todoRepositoryGetAll(){
        Todo todo1 = Todo.builder().username("testUser1").description("user for testing").
                targetDate(LocalDate.now()).done(false).build();
        Todo todo2 = Todo.builder().username("testUser1").description("user for testing").
                targetDate(LocalDate.now()).done(false).build();

        Todo savedTodo1 = todoRepository.save(todo1);
        Todo savedTodo2 = todoRepository.save(todo2);

        List<Todo> todoList = (List<Todo>) todoRepository.findAll();
        Assertions.assertThat(todoList).isNotNull();
        Assertions.assertThat(savedTodo1).isEqualTo(savedTodo2);
    }

    @Test
    public void todoRepositoryFindById(){
        Todo todo1 = Todo.builder().username("testUser1").description("user for testing").
                targetDate(LocalDate.now()).done(false).build();


        Todo savedTodo1 = todoRepository.save(todo1);

        Todo todoList = todoRepository.findById(savedTodo1.getId()).get();
        Assertions.assertThat(todoList).isNotNull();
    }

    @Test
    public void todoRepositoryFindByIdAndDescription(){
        Todo todo1 = Todo.builder().username("testUser1").description("user for testing").
                targetDate(LocalDate.now()).done(false).build();


        Todo savedTodo1 = todoRepository.save(todo1);

        Todo todoData = todoRepository.findByIdAndDescription(savedTodo1.getId(), "user for testing").get();
        Assertions.assertThat(todoData).isNotNull();
    }

    @Test
    public void todoRepositoryFindByUserName(){
        Todo todo1 = Todo.builder().username("testUser1").description("user for testing").
                targetDate(LocalDate.now()).done(false).build();


        Todo savedTodo1 = todoRepository.save(todo1);

        List<Todo> todoList = todoRepository.findByUsername(savedTodo1.getUsername());
        Assertions.assertThat(todoList).isNotNull();
    }

    @Test
    public void todoRepositoryUpdateDescription(){
        Todo todo1 = Todo.builder().username("testUser1").description("user for testing").
                targetDate(LocalDate.now()).done(false).build();
        Todo savedTodo1 = todoRepository.save(todo1);

        Todo todoData = todoRepository.findById(savedTodo1.getId()).get();
        todoData.setUsername("Abhishek");
        todoData.setDescription("local user");
        Todo updatedTodoData = todoRepository.save(todoData);

        Assertions.assertThat(todoData).isNotNull();
        Assertions.assertThat(updatedTodoData).isNotNull();
    }

    @Test
    public void todoRepositoryDeleteById(){
        Todo todo1 = Todo.builder().username("testUser1").description("user for testing").
                targetDate(LocalDate.now()).done(false).build();
        Todo savedTodo1 = todoRepository.save(todo1);

        todoRepository.deleteById(savedTodo1.getId());
        Optional<Todo> todoData = todoRepository.findById(savedTodo1.getId());

        Assertions.assertThat(todoData).isEmpty();
    }

}
