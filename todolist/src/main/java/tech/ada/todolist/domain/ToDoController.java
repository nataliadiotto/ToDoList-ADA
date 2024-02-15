package tech.ada.todolist.domain;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

//@RequiredArgsConstructor //obrigatorio construtor com argumentos: cria um construtor obrigatorio com as dependências declaradas
//dependencia: um atributo que nao foi instanciadoe  dependemos de alguém instanciá-lo
@RestController("/todo")
public class ToDoController {

    //o controlador precisa ter um repositorio para exercer suas funcoes
    //@Autowired -> poderia usar aqui, mas por poder falhar, jogamnotionos antes do construtor:
    private final ToDoItemRepository toDoItemRepository; //dependeica do repositorio
    private final ModelMapper modelMapper; //nova dependencia modelmapper

    @Autowired //faz a injeção de dependencia, mas pode ter erro: é mais seguro usar a anotação + construtor recebendo o repository
    //Injetamos as dependencia vira construtor com padrao inversao de dependencia
    public ToDoController(ToDoItemRepository toDoItemRepository, ModelMapper modelMapper) {
        this.toDoItemRepository = toDoItemRepository;
        this.modelMapper = modelMapper;
    }


    @PostMapping("/todo-item")
    public ToDoItem registerItem(@RequestBody ToDoItemRequest toDoRequest){
        //converter um toDoItem para toDoItemRequest para que nossa entidade nao fique exposta
//        ToDoItem convertedToDoItem = new ToDoItem();
//        convertedToDoItem.setTitle(toDoRequest.title());
//        convertedToDoItem.setDescription(toDoRequest.description());
//        convertedToDoItem.setDeadline(toDoRequest.deadline());

        //Vamos converter a request "TodoItemRequest" que chegou no body para uma entidade "TodoItem" atraves do componente model mapper
        ToDoItem convertedToDoItem = modelMapper.map(toDoRequest, ToDoItem.class);

        //Vamos salvar a entidade criada no repositorio
        ToDoItem newToDoItem = toDoItemRepository.save(convertedToDoItem);

        //Retornamos o status 201 com o body "corpo da resposta" o novoTodoItem que foi criado pelo repositorio
        return ResponseEntity.status(HttpStatus.CREATED).body(newToDoItem).getBody();
    }

    @GetMapping("/todo-item")
    public List<ToDoItem> findAll() {
        List<ToDoItem> listAll = toDoItemRepository.findAll();
        return listAll;
    }

    @PatchMapping("/todo-item/{id}")
    public ResponseEntity<ToDoItem> changeStatus(
            @PathVariable Long id,
            @RequestBody ChangeStatusRequest request) throws Exception {
        // Buscamos pelo metodo findById que retorna um Optional<TodoItem> pois o mesmo pode nao existir no banco
        Optional<ToDoItem> optionalTodoItem = toDoItemRepository.findById(id);
        // Verificamos se existe valor dentro do Optional
        if(optionalTodoItem.isPresent()) {
            // Se existir vamos fazer o get() para tirar o valor de dentro do optional
            ToDoItem modifiedToDoItem = optionalTodoItem.get();
            // verificamos se um das tres variaveis que esperamos foi passada para ser atualizada
            if(request.status() != null) modifiedToDoItem.setIsCompleted(request.status());
            if(request.title() != null) modifiedToDoItem.setTitle(request.title());
            if(request.description() != null) modifiedToDoItem.setDescription(request.description());

            //Depois de atualizar o que precisamos, vamos salvar
            ToDoItem todoItemSalvo =  toDoItemRepository.save(modifiedToDoItem);
            return ResponseEntity.ok(todoItemSalvo);

        } else {
            // Caso nao encontramos na valor no Optional retornamos o codigo 404 - nao encontrado
            return ResponseEntity.notFound().build();
        }
    }


    //POST -> alterar alguma coisa ou passar a requisição por body
    //GET ->
    //PUT -> parecido com post, mas nem sempre vai alterar o servidor, altera todas as informações
    //PATCH -> altera informações especificas, nao cria nada novo, se nao achar o que estava procurando para att, ele da erro, e att somente campos especificos


}
