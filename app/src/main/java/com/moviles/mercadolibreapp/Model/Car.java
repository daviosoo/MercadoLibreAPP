package com.moviles.mercadolibreapp.Model;

import android.content.Intent;

public class Car {
    private int id_producto;
    private String nombre_producto;
    private int precio_producto;
    private String url_producto;
    private int identification_usuario;
    private int cantidad_producto;

    public Car(int id_producto, String nombre_producto, int precio_producto, String url_producto, int identificacion_usuario, int cantidad) {
        this.id_producto = id_producto;
        this.nombre_producto = nombre_producto;
        this.precio_producto = precio_producto;
        this.url_producto = url_producto;
        this.identification_usuario = identificacion_usuario;
        this.cantidad_producto = cantidad;
    }

    public int getId_producto() {
        return id_producto;
    }

    public void setId_producto(int id_producto) {
        this.id_producto = id_producto;
    }

    public String getNombre_producto() {
        return nombre_producto;
    }

    public void setNombre_producto(String nombre_producto) {
        this.nombre_producto = nombre_producto;
    }

    public int getPrecio_producto() {
        return precio_producto;
    }

    public void setPrecio_producto(int precio_producto) {
        this.precio_producto = precio_producto;
    }

    public String getUrl_producto() {
        return url_producto;
    }

    public void setUrl_producto(String url_producto) {
        this.url_producto = url_producto;
    }

    public int getIdentificacion_usuario() {
        return identification_usuario;
    }

    public void setIdentificacion_usuario(int identificacion_usuario) {
        this.identification_usuario = identificacion_usuario;
    }

    public int getCantidad() {
        return cantidad_producto;
    }

    public void setCantidad(int cantidad) {
        this.cantidad_producto = cantidad;
    }
}
