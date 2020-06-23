import java.io.IOException;
import java.io.PrintWriter;

@javax.servlet.annotation.WebServlet("/calculateMeters")
public class MetricConversionServlet extends javax.servlet.http.HttpServlet {
    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        //Params
        request.setCharacterEncoding("UTF-8");
        String meters =         request.getParameter("meters");
        String centimeters =    request.getParameter("centimeters");
        String millimeters =    request.getParameter("milimeters");

        //Log to console inputParams
        System.out.println("Web service request: "  + meters + ";"
                                                    + centimeters + ";"
                                                    + millimeters);
        //Check invalid input
        int incorrectInputFlag      = 0;
        if(!meters.isEmpty())       incorrectInputFlag+=1;
        if(!centimeters.isEmpty())  incorrectInputFlag+=2;
        if(!millimeters.isEmpty())  incorrectInputFlag+=4;

        //Set result encoding
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html");
        PrintWriter writer = response.getWriter();

        /*Error codes
        * 3 - filled meters and centimeters
        * 5 - filled meters and milimeters
        * 6 - filled centimeters and milimeters
        * 7 - filled all inputBoxes
        * 0 - all fields are blank
        * */

        switch (incorrectInputFlag){
            //Invalid input
            case 3  -> writer.println("Wypełnij tylko jedno pole, usuń metry lub centymetry");
            case 5  -> writer.println("Wypełnij tylko jedno pole, usuń metry lub milimetry");
            case 6  -> writer.println("Wypełnij tylko jedno pole, usuń centrymetry lub milimetry");
            case 7  -> writer.println("Wypełnij tylko jedno pole, wyczyść dwa wybrane pola (wypełniono wszystkie trzy)");
            case 0  -> writer.println("Wszystkie pola są puste, wypełnij tylko jedno pole");
            //Valid input
            case 1  -> printToHtml(writer, MeasureCalculator.calculateMetric(meters,       MeasureCalculator.METERS));
            case 2  -> printToHtml(writer, MeasureCalculator.calculateMetric(centimeters,  MeasureCalculator.CENTIMETERS));
            case 4  -> printToHtml(writer, MeasureCalculator.calculateMetric(millimeters,  MeasureCalculator.MILIMETERS));
        }
    }

    private void printToHtml(PrintWriter writer, String[] values){
        String meters       = values[0];
        String centimeters  = values[1];
        String milimeters   = values[2];

        writer.println("<h1>Podana wartość w przeliczeniu na:</h1>");
        writer.println("metry: " + meters);
        writer.println("<br>centrymetry: " + centimeters);
        writer.println("<br>milimetry: "+ milimeters);
    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        System.out.println("Wywołanie doGet nie jest obsługiwane");
    }
}
