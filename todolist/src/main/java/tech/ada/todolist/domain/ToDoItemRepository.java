package tech.ada.todolist.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository //um esteriotipo, é opcional
public interface ToDoItemRepository extends JpaRepository<ToDoItem, Long> {
//<ToDoItem, Long> -> especifiquei a tabela (nome da classe) e o tipo da primary key (long)

   //o propio JPA ja reconhece que existe o atributo titulo e cria a query
    public List<ToDoItem> findByTitle(String title);


}
