/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.PQR.model;

import java.util.Date;

/**
 *
 * @author USUARIO
 */
public class Solicitud {
    
    private Long id;
    private Long userID;
    private String tipo;
    private String descripcion;
    private Date create_at;
    private Date responsed_at;

    public Long getId() {
        return id;
    }

  
    public Long getUserID() {
        return userID;
    }

    public void setUserID(Long userID) {
        this.userID = userID;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getCreate_at() {
        return create_at;
    }

    public void setCreate_at(Date create_at) {
        this.create_at = create_at;
    }

    public Date getResponsed_at() {
        return responsed_at;
    }

    public void setResponsed_at(Date responsed_at) {
        this.responsed_at = responsed_at;
    }
    
    
    
    
    
}
