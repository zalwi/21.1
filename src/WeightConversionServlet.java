import java.io.IOException;
import java.io.PrintWriter;

@javax.servlet.annotation.WebServlet("/calculateWeight")
public class WeightConversionServlet extends javax.servlet.http.HttpServlet {
    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        //Params
        request.setCharacterEncoding("UTF-8");
        String kilograms =  request.getParameter("kilograms");
        String grams =      request.getParameter("grams");
        String miligrams =  request.getParameter("miligrams");

        //Log to console inputParams
        System.out.println("Web service request: "  + kilograms + ";"
                                                    + grams + ";"
                                                    + miligrams);
        //Check invalid input
        int incorrectInputFlag      = 0;
        if(!kilograms.isEmpty())    incorrectInputFlag+=1;
        if(!grams.isEmpty())        incorrectInputFlag+=2;
        if(!miligrams.isEmpty())    incorrectInputFlag+=4;

        //Set result encoding
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html");
        PrintWriter writer = response.getWriter();

        /*Error codes
        * 3 - filled kilograms and grams
        * 5 - filled kilograms and miligrams
        * 6 - filled grams and miligrams
        * 7 - filled all inputBoxes
        * 0 - all fields are blank
        * */

        switch (incorrectInputFlag){
            //Invalid input
            case 3  -> writer.println("Wypełnij tylko jedno pole, usuń kilogramy lub gramy");
            case 5  -> writer.println("Wypełnij tylko jedno pole, usuń kilogramy lub miligramy");
            case 6  -> writer.println("Wypełnij tylko jedno pole, usuń gramy lub miligramy");
            case 7  -> writer.println("Wypełnij tylko jedno pole, wyczyść dwa wybrane pola (wypełniono wszystkie trzy)");
            case 0  -> writer.println("Wszystkie pola są puste, wypełnij tylko jedno pole");
            //Valid input
            case 1  -> printToHtml(writer, MeasureCalculator.calculateWeight(kilograms,  MeasureCalculator.KILOGRAMS));
            case 2  -> printToHtml(writer, MeasureCalculator.calculateWeight(grams,      MeasureCalculator.GRAMS));
            case 4  -> printToHtml(writer, MeasureCalculator.calculateWeight(miligrams,  MeasureCalculator.MILIGRAMS));
        }
    }

    private void printToHtml(PrintWriter writer, String[] values){
        String meters       = values[0];
        String centimeters  = values[1];
        String milimeters   = values[2];

        writer.println("<h1>Podana wartość w przeliczeniu na:</h1>");
        writer.println("kilogramy: " + meters);
        writer.println("<br>gramy: " + centimeters);
        writer.println("<br>miligramy: "+ milimeters);
    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        System.out.println("Wywołanie doGet nie jest obsługiwane");
    }
}
