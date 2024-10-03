//Candor Alemu
//10/1/24
//CSC 3102
//Project #1
//Dr. William Duncan

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;
import java.io.FileReader;
import java.io.IOException;

public class HuffmanCodeGenerator {
    public static ArrayList<SymData> genTable(ArrayList<SymData> codeList) throws PQueueException {
        PQueue<SymData> pq = new PQueue<>(Comparator.comparing(SymData::getSymb));

        for (SymData code : codeList) {
            pq.insert(code);
        }

        ArrayList<SymData> sortedList = new ArrayList<>();
        while (!pq.isEmpty()) {
            sortedList.add(pq.remove());
        }

        return sortedList;
    }
    public static ArrayList<SymData> genHuffCodes(SymData root) throws PQueueException {
        ArrayList<SymData> huffCodes = new ArrayList<>();
        ArrayList<SymData> queue = new ArrayList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            SymData node = queue.remove(0);

            if (node.getLeft() != null) {
                node.getLeft().setCode(node.getCode() + "0");
                queue.add(node.getLeft());
            }

            if (node.getRight() != null) {
                node.getRight().setCode(node.getCode() + "1");
                queue.add(node.getRight());
            }

            if (node.getLeft() == null && node.getRight() == null) {
                huffCodes.add(node);
            }
        }

        return huffCodes;
    }

    public static SymData genHuffTree(PQueue<SymData> huffForest) throws PQueueException {
        while (huffForest.size() > 1) {
            SymData left = huffForest.remove();
            SymData right = huffForest.remove();

            SymData newNode = new SymData('\0', left.getFreq() + right.getFreq(), "", left, right);
            huffForest.insert(newNode);
        }

        return huffForest.remove();
    }

    public static void main(String[] args) throws IOException {
        try {
            String usage = "HuffmanCodeGenerator <data-file-name>\n";
            usage += "The data file name is entered as a command line argument.\n";
            usage += "The data file contains two columns per row.\n";
            usage += "Each row has a symbol (char) and frequency pair.";

            // Check if the file name is provided as a command line argument
            if (args.length != 1) {
                System.out.println("Invalid number of command line arguments");
                System.out.println(usage);
                System.exit(1);
            }


            String filename = args[0];
            Scanner sc = new Scanner(new FileReader(filename));

            PQueue<SymData> huffForest = new PQueue<>(Comparator.comparing(SymData::getFreq).thenComparing(SymData::getSymb));

            while (sc.hasNext()) {
                char symbol = sc.next().charAt(0);
                int frequency = sc.nextInt();
                huffForest.insert(new SymData(symbol, frequency, "", null, null));
            }
            sc.close();

            SymData root = genHuffTree(huffForest);

            //Will generate the Huffman codes
            ArrayList<SymData> codeList = genHuffCodes(root);

            // Sort in lexicographical order by symbol
            ArrayList<SymData> sortedList = genTable(codeList);

            //How the table will display
            System.out.printf("%-7s %-9s %-8s\n", "Symbol", "Frequency", "Codeword");
            for (SymData sd : sortedList) {
                System.out.printf("%-7s %-9d %-8s\n", sd.getSymb(), sd.getFreq(), sd.getCode());
            }
        } catch (PQueueException e) {
            System.out.println(e);//Will print the Exception
        }
    }
}