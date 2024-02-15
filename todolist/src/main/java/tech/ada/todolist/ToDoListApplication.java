package tech.ada.todolist;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ToDoListApplication {

	public static void main(String[] args) {
		SpringApplication.run(ToDoListApplication.class, args);
	}

	@Bean
	// Criamos o Bean de configuracao do modelMapper, isso significa que ensinamos o spring como criar o ModelMapper
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

}
