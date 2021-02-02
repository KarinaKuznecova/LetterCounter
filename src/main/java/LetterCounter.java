import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class LetterCounter {

    private Map<Character, Integer> letterMap = new HashMap<>();

    public static void main(String[] args) {
        LetterCounter letterCounter = new LetterCounter();
        File file = letterCounter.loadFile("src/main/resources/input.txt");
        letterCounter.readFile(file);
        letterCounter.printResults();
    }

    public File loadFile(String pathname) {
        return new File(pathname);
    }

    public void readFile(File file) {
        if (file != null) {
            try {
                BufferedReader reader = getReader(file);
                String line;
                while ((line = reader.readLine()) != null) {
                    countLettersInLine(line);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    BufferedReader getReader(File file) throws FileNotFoundException {
        return new BufferedReader(new FileReader(file));
    }

    public void countLettersInLine(String line) {
        for (Character letter : line.toCharArray()) {
            if (!Character.isLetter(letter)) {
                continue;
            }
            if (letterMap.containsKey(letter)) {
                Integer number = letterMap.get(letter);
                letterMap.replace(letter, ++number);
            } else {
                letterMap.put(letter, 1);
            }
        }
    }

    public void printResults() {
        for (char letter : letterMap.keySet()) {
            System.out.println(String.format("There is %s occurrences of letter: %s", letterMap.get(letter), letter));
        }
    }

    public Map<Character, Integer> getLetterMap() {
        return letterMap;
    }
}
