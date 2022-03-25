package com.moviles.mercadolibreapp.Model;

import android.text.Editable;

public class Register {
    private String identificacion;
    private String email;
    private String celular;
    private String contra;

    public Register(String email, String contra) {
        this.email = email;
        this.contra = contra;
    }
    public Register(){

    }

    public String getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getContra() {
        return contra;
    }

    public void setContra(String contra) {
        this.contra = contra;
    }
}
