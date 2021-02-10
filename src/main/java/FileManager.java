import utils.FileConsummerModifier;

import java.io.IOException;
import java.nio.file.*;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.regex.Pattern;

public class FileManager {
    public static void main(String[] args) throws IOException, InterruptedException {
        Executer executer = new Executer();
        executer.execute();
    }
}
/***
 * String pathString = "C:\\Users\\aghomari\\IdeaProjects\\ALProject\\generatedFiles\\";
 *
 *         WatchService watchService
 *                 = FileSystems.getDefault().newWatchService();
 *
 *         Path path = Paths.get(pathString);
 *
 *         path.register(
 *                 watchService,
 *                 StandardWatchEventKinds.ENTRY_CREATE,
 *                 StandardWatchEventKinds.ENTRY_DELETE,
 *                 StandardWatchEventKinds.ENTRY_MODIFY);
 *
 *         WatchKey key;
 *
 *         int BOUND = 10;
 *         int N_CONSUMERS = Runtime.getRuntime().availableProcessors();
 *         String poisonPill = String.valueOf(Integer.MAX_VALUE);
 *
 *         BlockingQueue<String> queue = new LinkedBlockingQueue<>(BOUND);
 *
 *         new Thread(new FileConsummerModifier(queue, poisonPill)).start();
 *
 *         while ((key = watchService.take()) != null) {
 *             for (WatchEvent<?> event : key.pollEvents()) {
 *                 if(Pattern.matches("[f][i][l][e][0-9]*.[t][x][t]", event.context().toString()))
 *                     queue.put(pathString + event.context());
 *             }
 *             for (int j = 0; j < N_CONSUMERS; j++) {
 *                 new Thread(new FileConsummerModifier(queue, poisonPill)).start();
 *             }
 *             key.reset();
 *         }
 **/