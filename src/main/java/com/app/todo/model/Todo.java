package com.app.todo.model;

import java.time.LocalDate;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Builder
@Table(name = "Todo")
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class Todo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "serial")
    private int id;
    private String username;
    private String description;
    private LocalDate targetDate;
    private boolean done;

    public Todo(String username, String description, LocalDate targetDate, boolean done) {
    }


}