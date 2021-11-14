package ca.kash.it.smartprostheticarm;

public class Review {

String name;
String email;

    public Review(String name,String email){

        this.name = name;
        this.email=email;
    }
    public String getName(){
        return name;

    }


    public String getEmail(){
        return email;

    }

}
