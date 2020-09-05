package edu.eci.arep;

import org.json.JSONObject;
import spark.Request;
import spark.Response;
import edu.eci.arep.Main;
import static spark.Spark.*;
public class Main {
    public static void main(String[] args) {
        port(getPort());
        get("/",(req,res)->inputDataPage(req,res));
        get("/API/calculator",(req,res)->{
            String operation = req.queryParams("operacion");
            String number = req.queryParams("number");
            JSONObject jsonObject = new JSONObject(Cliente.get(operation,number));
            return jsonObject;
        });

        get("/results",(req,res)->{
            String operation = req.queryParams("operacion");
            String number = req.queryParams("number");
            JSONObject jsonObject = new JSONObject(Cliente.get(operation,number));
            return outputDataPage(jsonObject);

        });

    }

    /**
     * This function returns the HTML structure to present the output data
     * @param json A JSON with the structure operation and number
     * @return A String that have a template of a HTML
     */
    private static String outputDataPage(JSONObject json){
        String pageContent
                = "<!DOCTYPE html>"
                + "<html>"
                + "<body>"
                + "<h2>Trigonometry Calculator</h2>"
                + "<h4>Operation</h4>"
                + json.get("operacion")
                + "<h4>Value</h4>"
                + json.get("number")
                + "<br/>"
                + "<button onclick=\"window.location.href='/'\">New Calculation</button>"
                + "</body>"
                + "</html>";
        return pageContent;
    }

    /**
     * This function returns the HTML structure to present the input data
     * @param req The Spark HTTP Request
     * @param res The Spark HTTP Response
     * @return A String that have a template of a HTML
     */
    private static String inputDataPage(Request req, Response res) {
        String pageContent
                = "<!DOCTYPE html>"
                + "<html>"
                + "<head>"
                + "<title>AREP Parcial</title>"
                + " <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">"
                + "<link rel=\"stylesheet\" href=\"https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css\" integrity=\"sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk\" crossorigin=\"anonymous\">"
                + "<link rel=\"stylesheet\" href=\"https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css\" integrity=\"sha384-wvfXpqpZZVQGK6TAh5PVlGOfQNHSoD2xbE+QkPxCAFlNEevoEH3Sl0sibVcOQVnN\" crossorigin=\"anonymous\">"
                + "</head>"
                + "<body>"
                + "<h2>Calculadora</h2>"
                + "<form action=\"/results\">"
                + "  Numero:<br>"
                + "  <input type=\"text\" name=\"number\" value=\"\">"
                + "  <br><br>"
                + "<label for=\"method\">Choose a Operation:</label>"
                + "<select name=\"operation\" id=\"operation\">"
                + "<option value=\"sin\">Sin</option>"
                + "<option value=\"cos\">Cos</option>"
                + "<option value=\"tan\">Tan</option>"
                + "</select>"
                + "<br/>"
                + "<input type=\"submit\" value=\"Submit\">"
                + "</form>"
                + "</body>"
                + "</html>";
        return pageContent;
    }


    static int getPort() {
        if (System.getenv("PORT") != null) {
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 4567;
    }
}
