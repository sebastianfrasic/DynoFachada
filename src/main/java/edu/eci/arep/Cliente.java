package edu.eci.arep;


import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

public class Cliente {

    public static String get(String number, String operacion) throws UnirestException {

        HttpResponse<String> response = null;

        response = Unirest.get("https://dyno-calculator.herokuapp.com/results?number=" + number + "&operacion=" + operacion)
                .asString();

        return response.getBody();
    }

    public static void main(String[] args) throws UnirestException {
        HttpResponse<String> response = null;

        response = Unirest.get("https://dyno-calculator.herokuapp.com/results?number=90&operacion=tan")
                .asString();
        System.out.println(response.getBody());
    }


}