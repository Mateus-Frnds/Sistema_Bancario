package logs;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<String> logFiles = Arrays.asList("log1.txt", "log2.txt", "log3.txt");
        String wordToCount = "error";

        ParallelLogProcessor processor = new ParallelLogProcessor(4);
        int totalOccurrences = processor.processLogs(logFiles, wordToCount);
        processor.shutdown();

        System.out.println("Total occurrences of word '" + wordToCount + "': " + totalOccurrences);
    }
}
