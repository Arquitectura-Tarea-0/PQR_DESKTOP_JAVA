package com.PQR.controller;

import java.net.URI;

import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.PQR.model.Usuario;

public class UsuarioControl {

	private Usuario usuario;

        public UsuarioControl(){
            
        }
        
	// ingresar al sistema
	public boolean login(String email, String password) {
		RestTemplate restTemplate = new RestTemplate();

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		JSONObject personJsonObject = new JSONObject();
		personJsonObject.put("email", email);
		personJsonObject.put("password", password);

		// personJsonObject.put("email", email);
		// personJsonObject.put("password",password);

		URI myUri = URI.create("https://pqr-api-rails.herokuapp.com/login");

		HttpEntity<String> request = new HttpEntity<String>(personJsonObject.toString(), headers);

		// ResponseEntity<String> responseEntityStr = restTemplate.postForEntity(myUri,
		// request, String.class);

		Usuario u = restTemplate.postForObject(myUri, request, Usuario.class);
		usuario = u;
		if(u!=null && u.getToken()!=null){
                    return true;
                }return false;

	}

	// crear usuario rol=usuario
	public boolean crearUsuario(String name, String password, String email) {

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
              
		if(u!=null && u.getToken()!=null){
                    return true;
                }return false;
                
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

	public void crearPQR(String subject, String description) {
		
		//cuerpo Json
		JSONObject personJsonObject = new JSONObject();
		personJsonObject.put("subject", subject);		
		personJsonObject.put("description", description);		

		//cabecera
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.add("Authorization", usuario.getToken());
		
		RestTemplate restTemplate = new RestTemplate();
		
		URI myUri = URI.create("https://pqr-api-rails.herokuapp.com/users");
		HttpEntity<String> request = new HttpEntity<String>(personJsonObject.toString(), headers);
		
		String u = restTemplate.postForObject(myUri, request, String.class);
	}
}
