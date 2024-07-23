package logs;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ParallelLogProcessor {
    private ExecutorService executorService;

    public ParallelLogProcessor(int threadCount) {
        this.executorService = Executors.newFixedThreadPool(threadCount);
    }

    public int processLogs(List<String> filePaths, String wordToCount) {
        int totalOccurrences = 0;
        try {
            List<Future<Integer>> futures = executorService.invokeAll(createTasks(filePaths, wordToCount));
            for (Future<Integer> future : futures) {
                totalOccurrences += future.get();
            }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return totalOccurrences;
    }

    private List<LogProcessor> createTasks(List<String> filePaths, String wordToCount) {
        return filePaths.stream()
                .map(filePath -> new LogProcessor(filePath, wordToCount))
                .toList();
    }

    public void shutdown() {
        executorService.shutdown();
    }
}
