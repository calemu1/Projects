//Candor Alemu
//9/9/24
//CSC 3102
//Project #0
//Dr. William Duncan


import java.util.Scanner;

public class DerangeProfiler {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);//allows the use of a Scanner so inputs are allowed.


        System.out.print("Enter two non-negative integers: ");
        int n1 = scanner.nextInt();//first user input
        int n2 = scanner.nextInt();//second user input


        double fastResult1 = Derangement.fastDerange(n1);//calls the fastDerange method in the Derangement class for first user input
        double naiveResult1 = Derangement.naiveDerange(n1);//calls the fastDerange method in the Derangement class for first user input

        double fastResult2 = Derangement.fastDerange(n2);
        double naiveResult2 = Derangement.naiveDerange(n2);

        System.out.printf("Using Fast Derange\n %d =  %.0f%n %d = %.0f%n", n1, fastResult1,n2,fastResult2);//formats the output from the Derangement class along with printing that output.
        System.out.printf("Using Naive Derange\n %d = %.0f%n %d = %.0f%n", n1, naiveResult1, n2, naiveResult2);

        System.out.printf("\n%-10s%-20s%-20s%-20s%-20s%n", "n", "!n (naive)", "Time (ns)", "!n (fast)", "Time (ns)");//creates a format for the following for loop

        for (int n = 20; n <= 160; n += 20) {//for loop which will exceutes so long as n <= 160
            long startNaive = System.nanoTime();//Using a long variable to calculate the time which it took to calculate the derangements.
            double naiveResult = Derangement.naiveDerange(n);
            long endNaive = System.nanoTime();
            long naiveTime = endNaive - startNaive;

            long startFast = System.nanoTime();
            double fastResult = Derangement.fastDerange(n);
            long endFast = System.nanoTime();
            long fastTime = endFast - startFast;

            System.out.printf("%-10d%-20e%-20d%-20e%-20d%n", n, naiveResult,naiveTime ,fastResult, fastTime);//print out the values while also formatting them in the order shown.
        }


    }
}
