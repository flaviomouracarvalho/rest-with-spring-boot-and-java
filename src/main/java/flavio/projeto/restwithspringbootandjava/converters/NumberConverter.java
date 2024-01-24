package flavio.projeto.restwithspringbootandjava.converters;

public class NumberConverter {
    public static Double convertToDouble(String strNumber) {
        if (strNumber == null){
            return 1D;
        }
        String number  =strNumber.replace(",", ".");
        if (isNumeric(number)){
            return Double.parseDouble(strNumber);
        }
        return 0D;
    }

    public static boolean isNumeric(String strNumber ) {
        if (strNumber == null){
            return false;
        }
        String number  =strNumber.replace(",", ".");
        return number.matches("[-+]?[0-9]*\\.?[0-9]+");

    }
}
