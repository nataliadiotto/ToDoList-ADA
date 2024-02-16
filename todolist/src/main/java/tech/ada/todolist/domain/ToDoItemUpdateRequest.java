package tech.ada.todolist.domain;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

public record ToDoItemUpdateRequest(String title,
                                    String description,
                                    Boolean isCompleted,
                                    LocalDateTime createdAt,
                                    //LocalDateTime updatedAt,
                                    LocalDate deadline
) {

    //checar se os atributos editados nao sao nulos
    public ToDoItemUpdateRequest(String title, String description, Boolean isCompleted, LocalDateTime createdAt, LocalDate deadline) {
        this.title = Objects.requireNonNull(title, "Titulo é obrigatorio");
        this.description = Objects.requireNonNull(description);
        this.isCompleted = Objects.requireNonNull(isCompleted);
        this.createdAt = Objects.requireNonNull(createdAt);
        this.deadline = Objects.requireNonNull(deadline);
    }

 /*    public <T>T myNotNull(T objeto) {
         if(objeto == null)
             throw new NullPointerException();
         return objeto;
     }*/

}
