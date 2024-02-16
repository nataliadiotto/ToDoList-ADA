package tech.ada.todolist.domain;

public record ChangeStatusRequest (Boolean status, String title, String description){
//definimos que podemos alterar apenas 1, pares desses ou os tres atributos
}
