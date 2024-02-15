package tech.ada.todolist.domain;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class ToDoItemRequest {
    private String title;
    private String description;
    private LocalDate deadline;

    //Transformar uma request em entidade
    public ToDoItem toEntity() {
        return new ToDoItem(title, description, deadline);
    }
}



