package tech.ada.todolist.domain;

import java.time.LocalDate;

public record ToDoItemRequest (String title,
                               String description,
                               LocalDate deadline){}


