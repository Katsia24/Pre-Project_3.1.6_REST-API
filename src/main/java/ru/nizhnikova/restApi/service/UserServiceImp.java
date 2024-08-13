package ru.nizhnikova.restApi.service;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import ru.nizhnikova.restApi.model.User;

import java.util.List;

@Component
public class UserServiceImp implements UserService{
    private final String url = "http://94.198.50.185:7081/api/users";

    private final RestTemplate restTemplate = new RestTemplate();
    private final HttpHeaders httpHeaders = new HttpHeaders();
    private String cookie;
    private final StringBuilder result = new StringBuilder();

    @Override
    public void getUsers() { // …/api/users ( GET )
        ResponseEntity<List<User>> responseEntity =
                restTemplate.exchange(url,
                        HttpMethod.GET,
                        null,
                        new ParameterizedTypeReference<List<User>>() {
                        });
        cookie = responseEntity.getHeaders().get("Set-Cookie").get(0);
        System.out.println("cookie for getUsers = " + cookie);
        System.out.println("responseEntity.getBody(); for getUsers = " + responseEntity.getBody());
    }

    @Override
    public void createUser(User user) { // …/api/users ( POST )
        httpHeaders.set("Cookie", cookie);
        HttpEntity<User> request = new HttpEntity<>(user, httpHeaders);
        ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.POST, request, String.class);
        result.append(responseEntity.getBody());
        System.out.println("result.append(responseEntity.getBody()); for createUser = " + result);
    }

    @Override
    public void updateUser(User user) { // …/api/users ( PUT )
        httpHeaders.set("Cookie", cookie);
        HttpEntity<User> request = new HttpEntity<>(user, httpHeaders);
        ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.PUT, request, String.class);
        result.append(responseEntity.getBody());
        System.out.println("result.append(responseEntity.getBody()); for createUser = " + result);
    }

    @Override
    public void deleteUser(Long id) { // …/api/users/{id} ( DELETE )
        httpHeaders.set("Cookie", cookie);
        HttpEntity<User> request = new HttpEntity<>(null, httpHeaders);
        String urlForDelete = url + "/" + id;
        ResponseEntity<String> responseEntity = restTemplate.exchange(urlForDelete, HttpMethod.DELETE, request, String.class);
        result.append(responseEntity.getBody());
        System.out.println("result.append(responseEntity.getBody()); for createUser = " + result);
    }
}
