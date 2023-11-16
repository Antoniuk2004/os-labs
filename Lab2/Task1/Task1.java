import java.util.HashSet;
import java.util.Scanner;

public class Task1 {
    public static void main(String[] args) {
        boolean ignoreCase = true;

        if (args.length != 0) {
            if (args[0].equals("--ignore-case")) {
                ignoreCase = false;
            }
        }

        if (ignoreCase)
            outputDataWithExecelentValues();
        else
            outputDataWithDuplicateValues();
    }

    private static void outputDataWithExecelentValues() {
        HashSet<String> hashSetOfWords = new HashSet<String>();

        try (var scanner = new Scanner(System.in)) {
            while (scanner.hasNext()) {
                String line = scanner.next();
                
                String[] arrOfWords = line.toLowerCase().split("\\s+");

                for (String element : arrOfWords) {
                    if (hashSetOfWords.add(element)) {
                        System.out.println(element);
                    }
                }
            }
        }
    }

    private static void outputDataWithDuplicateValues() {
        HashSet<String> hashSetOfWords = new HashSet<String>();

        try (var scanner = new Scanner(System.in)) {
            while (scanner.hasNext()) {
                String line = scanner.next();

                String[] arrOfWords = line.split(" ");

                for (String element : arrOfWords) {
                    if (hashSetOfWords.add(element)) {
                        System.out.println(element);
                    }
                }
            }
        }
    }
}
