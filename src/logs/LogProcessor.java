package logs;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.Callable;

public class LogProcessor implements Callable<Integer> {
    private String filePath;
    private String wordToCount;

    public LogProcessor(String filePath, String wordToCount) {
        this.filePath = filePath;
        this.wordToCount = wordToCount;
    }

    @Override
    public Integer call() {
        int count = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                count += countOccurrences(line, wordToCount);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return count;
    }

    private int countOccurrences(String line, String word) {
        int count = 0;
        int index = 0;
        while ((index = line.indexOf(word, index)) != -1) {
            count++;
            index += word.length();
        }
        return count;
    }
}
