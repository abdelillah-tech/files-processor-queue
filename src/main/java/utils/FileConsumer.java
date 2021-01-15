package utils;

import java.io.*;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadLocalRandom;

public class FileConsumer implements Runnable{

    private BlockingQueue<String> queue;
    private final String poisonPill;

    public FileConsumer(BlockingQueue<String> queue, String poisonPill) {
        this.queue = queue;
        this.poisonPill = poisonPill;

    }

    @Override
    public void run() {
        try{
            while(true){
                String path = queue.take();
                if (path.equals(poisonPill)){
                    return;
                }
                modifyFile("A","XW",path);
            }
        }catch(InterruptedException e){
            Thread.currentThread().interrupt();
        }
    }

    static void modifyFile(String oldString, String newString, String fileToProcess) {
        String oldContent = "";
        BufferedReader reader = null;
        FileWriter writer = null;

        try {
            reader = new BufferedReader(new FileReader(fileToProcess));
            String line = reader.readLine();

            while (line != null) {
                oldContent = oldContent + line + System.lineSeparator();
                line = reader.readLine();
            }
            String newContent = oldContent.replaceAll(oldString, newString);
            File newFile = new File(fileToProcess.substring(0, fileToProcess.lastIndexOf(".txt")) + "Processed" + ".txt");
            writer = new FileWriter(newFile);
            writer.write(newContent);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                reader.close();
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}