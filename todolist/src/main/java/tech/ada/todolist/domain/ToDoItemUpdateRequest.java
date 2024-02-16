package tech.ada.todolist.domain;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

public record ToDoItemUpdateRequest(String title,
                                    String description,
                                    Boolean isCompleted,
                                    //LocalDateTime updatedAt,
                                    LocalDate deadline
) {

    //checar se os atributos editados nao sao nulos
    public ToDoItemUpdateRequest(String title, String description, Boolean isCompleted, LocalDate deadline) {
        this.title = Objects.requireNonNull(title, "Title is required");
        this.description = Objects.requireNonNull(description, "Description is required");
        this.isCompleted = Objects.requireNonNull(isCompleted, "isCompleted is required");
        this.deadline = Objects.requireNonNull(deadline, "Deadline is required");
    }

 /*    public <T>T myNotNull(T objeto) {
         if(objeto == null)
             throw new NullPointerException();
         return objeto;
     }*/

}
