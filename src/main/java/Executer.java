import java.io.IOException;
import java.nio.file.*;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.regex.Pattern;

public class Executer {
    public Executer(){}
    public void execute() throws IOException, InterruptedException {
        String pathString = "C:\\Users\\aghomari\\IdeaProjects\\ALProject\\filesDepo\\";

        String newFileRegxPattern = "[f][i][l][e][0-9]*.[t][x][t]";

        WatchService watchService
                = FileSystems.getDefault().newWatchService();
        Path directoryPath = Paths.get(pathString);
        configurePath(directoryPath, watchService);

        WatchKey key;

        int BOUND = 10;
        int numberOfFileConsummer = Runtime.getRuntime().availableProcessors();
        String poisonPill = String.valueOf(Integer.MAX_VALUE);

        BlockingQueue<String> queue = new LinkedBlockingQueue<>(BOUND);

        IFileModifier fileModifier = new FileModifier();

        new Thread(new FileConsummer(fileModifier, queue, poisonPill)).start();

        IFileFeeder fileFeeder = new FileFeeder(queue);

        while ((key = watchService.take()) != null) {
            for (WatchEvent<?> event : key.pollEvents()) {
                if(Pattern.matches(newFileRegxPattern, event.context().toString()))
                    fileFeeder.addFile(pathString + event.context());
            }
            for (int j = 0; j < numberOfFileConsummer; j++) {
                new Thread(new FileConsummer(fileModifier, queue, poisonPill)).start();
            }
            key.reset();
        }
    }

    private void configurePath(Path directoryPath, WatchService watchService) throws IOException {

        directoryPath.register(
                watchService,
                StandardWatchEventKinds.ENTRY_CREATE,
                StandardWatchEventKinds.ENTRY_DELETE,
                StandardWatchEventKinds.ENTRY_MODIFY);
    }
}
