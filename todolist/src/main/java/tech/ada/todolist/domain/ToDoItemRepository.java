package tech.ada.todolist.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository //um esteriotipo, é opcional
public interface ToDoItemRepository extends JpaRepository<ToDoItem, Long> {
//<ToDoItem, Long> -> especifiquei a tabela (nome da classe) e o tipo da primary key (long)



}
