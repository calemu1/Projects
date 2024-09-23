public class Derangement {

   //This method will calculate the derangements using the fastDerange method
    public static double fastDerange(int n){ //@param
        if (n < 0) {
            throw new IllegalArgumentException("Input must be a non-negative integer.");
        } else if (n == 0) {
            return 1;
        }

        double term = -1.0;
        double sum = 0.0;

        for (int i = 2; i <= n; i++) {
            term = -term / i;
            sum += term;
        }

        return sum / Math.abs(term); //@return, returns the number of derangements inputted for the parameter int n
    }

    //This method will calculate the derangements using the naiveDerange method.
    public static double naiveDerange(int n) { //@param
        if (n < 0) {
            throw new IllegalArgumentException("Input must be a non-negative integer.");
        } else if (n == 0) {
            return 1;
        }

        double sum = 0;
        int sign = -1;

        double factRcp = 0;
        for (int i = 2; i <= n; i++) {
            sign = sign * -1;
            factRcp = 1;

            for (int k = 1; k <= i; k++) {
                factRcp = factRcp / k;
            }

            sum = sum + (sign * factRcp);
        }

        double delta = sum / Math.abs(factRcp);
        return delta; //@return , returns delta which is the number of derangements for the given parameter int n
    }
}