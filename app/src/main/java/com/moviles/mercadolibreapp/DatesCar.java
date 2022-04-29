package com.moviles.mercadolibreapp;


public class DatesCar {
    String txt_description, txt_value, txt_Quantity,img_car;

    public DatesCar(String txt_description, String txt_value, String txt_Quantity, String img_car) {
        this.txt_description = txt_description;
        this.txt_value = txt_value;
        this.txt_Quantity = txt_Quantity;
        this.img_car = img_car;
    }

    public String getTxt_description() {
        return txt_description;
    }

    public void setTxt_description(String txt_description) {
        this.txt_description = txt_description;
    }

    public String getTxt_value() {
        return txt_value;
    }

    public void setTxt_value(String txt_value) {
        this.txt_value = txt_value;
    }

    public String getTxt_Quantity() {
        return txt_Quantity;
    }

    public void setTxt_Quantity(String txt_Quantity) {
        this.txt_Quantity = txt_Quantity;
    }

    public String getImg_car() {
        return img_car;
    }

    public void setImg_car(String img_car) {
        this.img_car = img_car;
    }
}
