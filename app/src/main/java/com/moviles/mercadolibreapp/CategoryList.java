package com.moviles.mercadolibreapp;

public class CategoryList {

    Integer imgCategory;
    String txtCategory;

    public CategoryList(Integer imgCategory, String txtCategory) {
        this.imgCategory = imgCategory;
        this.txtCategory = txtCategory;
    }


    public Integer getImgCategory() {
        return imgCategory;
    }

    public void setImgCategory(Integer imgCategory) {
        this.imgCategory = imgCategory;
    }

    public String getTxtCategory() {
        return txtCategory;
    }

    public void setTxtCategory(String txtCategory) {
        this.txtCategory = txtCategory;
    }
}