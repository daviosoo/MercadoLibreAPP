package com.moviles.mercadolibreapp;

public class ListProducts {

    String image,name;
    int value;

    public ListProducts (String image, int value, String name) {
        this.image = image;
        this.value = value;
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
