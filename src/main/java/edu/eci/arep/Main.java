package edu.eci.arep;

import org.json.JSONObject;
import spark.Request;
import spark.Response;

import static spark.Spark.*;


public class Main {
    public static void main(String[] args) {
        port(getPort());
        get("/", (req, res) -> inputDataPage(req, res));
        get("/calculator", (req, res) -> {
            String operacion = req.queryParams("operacion");
            String number = req.queryParams("number");
            JSONObject jsonObject = new JSONObject(Cliente.get(number, operacion));
            return jsonObject;
        });

        get("/resultadps", (req, res) -> {
            String operacion = req.queryParams("operacion");
            String number = req.queryParams("number");
            JSONObject jsonObject = new JSONObject(Cliente.get(number, operacion));
            System.out.println(jsonObject);
            return outputDataPage(jsonObject);

        });

    }

    /**
     * This function returns the HTML structure to present the output data
     *
     * @param json A JSON with the structure operation and number
     * @return A String that have a template of a HTML
     */
    private static String outputDataPage(JSONObject json) {
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
                + "<h2>Resultados:</h2>"
                + "<h4>Operacion</h4>"
                + json.get("operacion")
                + "<h4>Resultado</h4>"
                + json.get("resultado")
                + "</body>"
                + "</html>";

        return pageContent;
    }

    /**
     * This function returns the HTML structure to present the input data
     *
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
                + "<label for=\"method\">Escoga una operación:</label>"
                + "<select name=\"operacion\" id=\"operacion\">"
                + "<option value=\"sin\">Seno</option>"
                + "<option value=\"cos\">Coseno</option>"
                + "<option value=\"tan\">Tangente</option>"
                + "</select>"
                + "<br/>"
                + "<input type=\"submit\" value=\"Enviar\">"
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
