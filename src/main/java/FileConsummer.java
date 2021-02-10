import java.util.concurrent.BlockingQueue;

public class FileConsummer implements Runnable{

    private BlockingQueue<String> queue;
    private final String poisonPill;
    private IFileModifier fileModifier;

    public FileConsummer(BlockingQueue<String> queue, String poisonPill) {
        this.queue = queue;
        this.poisonPill = poisonPill;
    }

    public FileConsummer(IFileModifier fileModifier,
                         BlockingQueue<String> queue,
                         String poisonPill) {
        this.queue = queue;
        this.poisonPill = poisonPill;
        this.fileModifier = fileModifier;
    }

    @Override
    public void run() {
        try{
            while(true){
                String path = queue.take();
                if (path.equals(poisonPill)){
                    return;
                }
                fileModifier.modifyFile("A","XW", path);
            }
        }catch(InterruptedException e){
            Thread.currentThread().interrupt();
        }
    }
}
