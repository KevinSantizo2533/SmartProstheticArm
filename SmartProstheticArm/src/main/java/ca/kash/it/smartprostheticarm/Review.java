package ca.kash.it.smartprostheticarm;

public class Review {

String name;
String email;
String comment;
int phone;
String rating;

    public Review(String name,String email,int phone, String comment, String rating){

        this.name = name;
        this.email=email;
        this.phone=phone;
        this.comment=comment;
        this.rating=rating;
    }

    public String getName(){
        return name;

    }
    public String getRating(){
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
