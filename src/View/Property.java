package View;

public class Property {
    String Parametr;
    double Results;

    public Property(String parametr, double results) {
        this.Parametr = parametr;
        this.Results = results;
    }

    public String getParametr() {
        return Parametr;
    }

    public double getResults() {
        return Results;
    }
}
