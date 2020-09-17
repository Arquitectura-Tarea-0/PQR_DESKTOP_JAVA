package com.PQR.controller;

import com.PQR.model.Request;
import com.PQR.model.User;
import java.net.URI;

import org.json.*;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class UsuarioControl {

    private User usuario;
    private RestTemplate restTemplate;

    public UsuarioControl() {
        usuario = new User();
        restTemplate = new RestTemplate();      
            }

    // ingresar al sistema
    public boolean login(String email, String password) {

        JSONObject personJsonObject = new JSONObject();
        personJsonObject.put("email", email);
        personJsonObject.put("password", password);

        String respuestaApi = this.post(null, personJsonObject, "https://pqr-api-rails.herokuapp.com/login");

        if (respuestaApi.length() != 37) {
            JSONObject Juser = new JSONObject(respuestaApi);
            usuario.userFromJson(new JSONObject(Juser.get("user").toString()), Juser.get("token").toString());
            
            getAllPQR();
            return true;
        }
        return false;

    }

    // crear usuario rol=usuario
    public boolean crearUsuario(String name, String password, String email) {
        JSONObject personJsonObject = new JSONObject();
        personJsonObject.put("email", email);
        personJsonObject.put("password", password);
        personJsonObject.put("name", name);

        String respuestaApi = this.post(null, personJsonObject, "https://pqr-api-rails.herokuapp.com/users");

        if (respuestaApi.length() != 37) {
            JSONObject Juser = new JSONObject(respuestaApi);
            usuario.userFromJson(new JSONObject(Juser.get("user").toString()), Juser.get("token").toString());
            return true;
        }
        return false;
    }

    // optener todas las pqr
    public void getAllPQR() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("Authorization", usuario.getToken());

        URI myUri = URI.create("https://pqr-api-rails.herokuapp.com/requests/user_requests");

        HttpEntity<String> request = new HttpEntity<String>(headers);
        ResponseEntity<String> u = restTemplate.exchange(myUri, HttpMethod.GET, request, String.class);
        
        JSONObject uno = new JSONObject(u);
        System.out.println(u);
        
    }

    //crear una pqr
    public boolean crearPQR(String subject, String description, String tipo) {

        //cuerpo Json
        JSONObject personJsonObject = new JSONObject();
        personJsonObject.put("subject", subject);
        personJsonObject.put("description", description);
        personJsonObject.put("request_type", tipo);

        this.post(usuario.getToken(), personJsonObject, "https://pqr-api-rails.herokuapp.com/requests/create");

        return true;

    }    
    

    private String post(String token, JSONObject json, String url) {
        String cad = "";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        if (token != null) {
            headers.add("Authorization", usuario.getToken());
        }

        URI myUri = URI.create(url);
        HttpEntity<String> request = new HttpEntity<String>(json.toString(), headers);

        cad = restTemplate.postForObject(myUri, request, String.class);

        return cad;
    }
}
