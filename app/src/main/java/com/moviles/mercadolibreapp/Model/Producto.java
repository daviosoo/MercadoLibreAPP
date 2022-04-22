package com.moviles.mercadolibreapp.Model;

public class Producto {

    private int id, precio, cantidad, identification_usuario;
    private String url, nombre;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getIdentification_usuario() {
        return identification_usuario;
    }

    public void setIdentification_usuario(int identification_usuario) {
        this.identification_usuario = identification_usuario;
    }
}
