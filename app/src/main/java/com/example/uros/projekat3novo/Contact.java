package com.example.uros.projekat3novo;

public class Contact {

    String nameContact, numberContact;
    long id;
    int photo;
    int fav;

    public Contact(String nameContact, String numberContact){
        this.nameContact = nameContact;
        this.numberContact = numberContact;
    }

    public Contact(){

    }

    public void setId(long id){
        this.id = id;
    }


    public  long getId(){
        return this.id;
    }


    public void setNameContact(String nameContact){
        this.nameContact = nameContact;
    }


    public  String getNameContact(){
        return this.nameContact;
    }

    public void setNumberContact(String numberContact){
        this.numberContact = numberContact;
    }


    public  String getNumberContact(){
        return this.numberContact;
    }

    public void setPhoto(int photo){
        this.photo = photo;
    }


    public  int getPhoto(){
        return this.photo;
    }

    public void setFav(int fav) {
        this.fav = fav;
    }

    public int getFav() {
        return fav;
    }
}
