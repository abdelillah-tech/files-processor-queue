import java.util.concurrent.BlockingQueue;

public class FileFeeder implements IFileFeeder{

    private BlockingQueue<String> fileQueue;

    public FileFeeder(BlockingQueue<String> fileQueue) {
        this.fileQueue = fileQueue;
    }

    @Override
    public void addFile(String filePath) throws InterruptedException {
        fileQueue.put(filePath);
    }
}
