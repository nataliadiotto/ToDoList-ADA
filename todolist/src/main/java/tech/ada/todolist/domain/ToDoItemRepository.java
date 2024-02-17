package tech.ada.todolist.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository //um esteriotipo, é opcional
public interface ToDoItemRepository extends JpaRepository<ToDoItem, Long> {
//<ToDoItem, Long> -> especifiquei a tabela (nome da classe) e o tipo da primary key (long)

   //o propio JPA ja reconhece que existe o atributo titulo e cria a query
    List<ToDoItem> findByTitle(String title);


    //criando uma query manual
    @Query("SELECT t FROM ToDoItem t WHERE t.titulo = ?1")
    List<ToDoItem> findByTitleQuery (String title);

    //criando uma query nativa
    @Query(value = "SELECT * FROM ToDoItem WHERE title = ?1", nativeQuery = true)
    List<ToDoItem> findByTitleNativeQuery (String title);




}
