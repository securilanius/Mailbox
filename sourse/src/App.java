import model.ConsoleParser;
import model.Cubicle;
import model.Paczkomat;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import static model.ConsoleParser.LINE_DELIMITER;

public class App {
    public static void main(String... args) {
        testableMethod(System.in, System.out);
    }

    protected static void testableMethod(InputStream inputStream, PrintStream outputStream) {
        Scanner sc = new Scanner(inputStream);
        Paczkomat paczkomat = new Paczkomat();
        while (sc.hasNext()) {
            String line = sc.nextLine();
            if (line.startsWith(LINE_DELIMITER)) {
                paczkomat.addRow(ConsoleParser.parseLine(line));
            } else if (line.startsWith("S")) {
                print(outputStream, paczkomat);
            } else if (line.startsWith("B")) {
                paczkomat.add(ConsoleParser.parsePackages(line));
                print(outputStream, paczkomat);
            }
        }
    }

    private static void print(PrintStream outputStream, Paczkomat paczkomat) {
        List<List<Cubicle>> result = paczkomat.get();
        result.forEach(r -> {
            String row = String.join(" ", r.stream().map(Cubicle::toString).collect(Collectors.toList()));
            outputStream.println("|" + row + "|");
        });
    }
}