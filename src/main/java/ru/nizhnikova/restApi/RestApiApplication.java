package ru.nizhnikova.restApi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import ru.nizhnikova.restApi.model.User;
import ru.nizhnikova.restApi.service.UserService;
import ru.nizhnikova.restApi.service.UserServiceImp;

@SpringBootApplication
public class RestApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestApiApplication.class, args);

		UserService userService = new UserServiceImp();
		userService.getUsers();
		userService.createUser(new User(3L, "James", "Brown", (byte) 20));
		userService.updateUser(new User(3L, "Thomas", "Shelby", (byte) 20));
		userService.deleteUser(3L);

	}

}
