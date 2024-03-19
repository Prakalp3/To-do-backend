package com.app.todo.model;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TodoDto {
    private Integer id;
    private String username;
    private String description;
    private LocalDate targetDate;
    private boolean done;
}
