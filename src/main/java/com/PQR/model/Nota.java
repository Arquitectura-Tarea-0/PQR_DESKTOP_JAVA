/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.PQR.model;

/**
 *
 * @author USUARIO
 */
public class Nota {
    
    private Long id;
    private Long userID;
    private Long solicitudID;
    private String descripcion;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserID() {
        return userID;
    }

    public void setUserID(Long userID) {
        this.userID = userID;
    }

    public Long getSolicitudID() {
        return solicitudID;
    }

    public void setSolicitudID(Long solicitudID) {
        this.solicitudID = solicitudID;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    
}
