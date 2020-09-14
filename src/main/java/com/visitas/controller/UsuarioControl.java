package com.visitas.controller;

import java.net.URI;

import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.visitas.dominio.Usuario;

public class UsuarioControl {

	private Usuario usuario;

	// ingresar al sistema
	public void login(String email, String password) {
		RestTemplate restTemplate = new RestTemplate();

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		JSONObject personJsonObject = new JSONObject();
		personJsonObject.put("email", "pqr.sistemas@gmail.com");
		personJsonObject.put("password", "123456");

		// personJsonObject.put("email", email);
		// personJsonObject.put("password",password);

		URI myUri = URI.create("https://pqr-api-rails.herokuapp.com/login");

		HttpEntity<String> request = new HttpEntity<String>(personJsonObject.toString(), headers);

		// ResponseEntity<String> responseEntityStr = restTemplate.postForEntity(myUri,
		// request, String.class);

		Usuario u = restTemplate.postForObject(myUri, request, Usuario.class);
		usuario = u;
		System.out.println(personJsonObject);

		System.out.println("\n token-----> " + u.getToken());

	}

	// crear usuario rol=usuario
	public void crearUsuario(String name, String password, String email) {

		JSONObject personJsonObject = new JSONObject();
		personJsonObject.put("email", email);
		personJsonObject.put("password", password);
		personJsonObject.put("name", name);

		RestTemplate restTemplate = new RestTemplate();

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		
		URI myUri = URI.create("https://pqr-api-rails.herokuapp.com/users");
		HttpEntity<String> request = new HttpEntity<String>(personJsonObject.toString(), headers);
		
		Usuario u = restTemplate.postForObject(myUri, request, Usuario.class);
		usuario = u;
	}

	// optener todas las pqr
	public void getAllPQR() {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.add("Authorization", usuario.getToken());

		URI myUri = URI.create("https://pqr-api-rails.herokuapp.com/requests/general_requests");
		RestTemplate restTemplate = new RestTemplate();
		HttpEntity<String> request = new HttpEntity<String>(null, headers);
		ResponseEntity<String> u = restTemplate.exchange(myUri, HttpMethod.GET, request, String.class);

		System.out.println("\n obtener cosas ---->" + u);
	}

}
