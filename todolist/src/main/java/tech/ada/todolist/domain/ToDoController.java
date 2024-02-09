package tech.ada.todolist.domain;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
//@RequiredArgsConstructor //obrigatorio construtor com argumentos: cria um construtor obrigatorio com as dependências declaradas
//dependencia: um atributo que nao foi instanciadoe  dependemos de alguém instanciá-lo
@RestController("/todo")
public class ToDoController {

    //o controlador precisa ter um repositorio para exercer suas funcoes
    //@Autowired -> poderia usar aqui, mas por poder falhar, jogamos antes do construtor:
    private final ToDoItemRepository toDoItemRepository;

    @Autowired //faz a injeção de dependencia, mas pode ter erro: é mais seguro usar a anotação + construtor recebendo o repository
    public ToDoController(ToDoItemRepository toDoItemRepository) {
        this.toDoItemRepository = toDoItemRepository;
    }


    @PostMapping("/todo-item")
    public ToDoItem registerItem(@RequestBody ToDoItemRequest toDoRequest){
        //converter um toDoItem para toDoItemRequest para que nossa entidade nao fique exposta
        ToDoItem convertedToDoItem = new ToDoItem();
        convertedToDoItem.setTitle(toDoRequest.title());
        convertedToDoItem.setDescription(toDoRequest.description());
        convertedToDoItem.setDeadline(toDoRequest.deadline());

        ToDoItem newToDoItem = toDoItemRepository.save(convertedToDoItem);
       return newToDoItem;
    }

    @GetMapping("/todo-item")
    public List<ToDoItem> findAll() {
        return toDoItemRepository.findAll();
    }

    //POST -> alterar alguma coisa ou passar a requisição por body
    //GET ->
    //PUT -> parecido com post, mas nem sempre vai alterar o servidor, altera todas as informações
    //PATCH -> altera informações especificas, nao cria nada novo, se nao achar o que estava procurando para att, ele da erro, e att somente campos especificos


}
