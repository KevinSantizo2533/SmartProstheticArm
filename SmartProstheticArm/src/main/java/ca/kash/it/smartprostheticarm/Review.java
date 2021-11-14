package ca.kash.it.smartprostheticarm;

public class Review {

String name;
String email;
String comment;
int phone;
float rating;
String model;

    public Review(String name,String email,int phone, String comment,float rating, String model){

        this.name = name;
        this.email=email;
        this.phone=phone;
        this.comment=comment;
        this.rating=rating;
        this.model=model;
    }

    public String getName(){
        return name;

    }
    public String getModel(){
        return model;

    }
    public float getRating(){
        return rating;

    }

    public String getComment(){
        return comment;

    }
    public int getPhone(){
        return phone;

    }
    public String getEmail(){
        return email;

    }

}
