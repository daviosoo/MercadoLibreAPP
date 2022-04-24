package com.moviles.mercadolibreapp.Model;

public class Producto {

    private int id, id_producto, precio_producto, cantidad_producto, identification_usuario;
    private String url_producto, nombre_producto;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_producto() {
        return id_producto;
    }

    public void setId_producto(int id_producto) {
        this.id_producto = id_producto;
    }

    public int getPrecio_producto() {
        return precio_producto;
    }

    public void setPrecio_producto(int precio_producto) {
        this.precio_producto = precio_producto;
    }

    public int getCantidad_producto() {
        return cantidad_producto;
    }

    public void setCantidad_producto(int cantidad_producto) {
        this.cantidad_producto = cantidad_producto;
    }

    public int getIdentification_usuario() {
        return identification_usuario;
    }

    public void setIdentification_usuario(int identification_usuario) {
        this.identification_usuario = identification_usuario;
    }

    public String getUrl_producto() {
        return url_producto;
    }

    public void setUrl_producto(String url_producto) {
        this.url_producto = url_producto;
    }

    public String getNombre_producto() {
        return nombre_producto;
    }

    public void setNombre_producto(String nombre_producto) {
        this.nombre_producto = nombre_producto;
    }
}
