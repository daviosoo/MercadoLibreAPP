package com.moviles.mercadolibreapp.Model;

import android.content.Intent;

public class CarPost {
    private int idProducto;
    private String nombreProducto;
    private int precio;
    private String urlProducto;
    private int cantidadProducto;
    private int identificacionUsuario;

    public CarPost(int id_producto, String nombre_producto, int precio_producto, String url_producto, int identificacion_usuario, int cantidad) {
        this.idProducto = id_producto;
        this.nombreProducto = nombre_producto;
        this.precio = precio_producto;
        this.urlProducto = url_producto;
        this.cantidadProducto = cantidad;
        this.identificacionUsuario = identificacion_usuario;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public String getUrlProducto() {
        return urlProducto;
    }

    public void setUrlProducto(String urlProducto) {
        this.urlProducto = urlProducto;
    }

    public int getCantidadProducto() {
        return cantidadProducto;
    }

    public void setCantidadProducto(int cantidadProducto) {
        this.cantidadProducto = cantidadProducto;
    }

    public int getIdentificacionUsuario() {
        return identificacionUsuario;
    }

    public void setIdentificacionUsuario(int identificacionUsuario) {
        this.identificacionUsuario = identificacionUsuario;
    }
}
