package edu.eci.arep;


import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

public class Cliente {

    public static String get(String operation, String number) throws UnirestException {

        HttpResponse<String> response = null;
        HttpResponse<String> response2 = null;

        response = Unirest.get("https://dyno-calculator.herokuapp.com/results?number=" + number + "&operacion=" + operation)
                .asString();
        response2 = Unirest.get("https://dyno-calculator.herokuapp.com/results?number=0.785398&operacion=cos").asString();
        System.out.println(response.getBody());

        return response2.getBody();
    }

    public static void main(String[] args) throws UnirestException {
        HttpResponse<String> response = null;

        response = Unirest.get("https://dyno-calculator.herokuapp.com/results?number=90&operacion=tan")
                .asString();
        System.out.println(response.getBody());
    }


}
