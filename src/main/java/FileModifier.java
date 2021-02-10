import java.io.*;

public class FileModifier implements IFileModifier{

    public FileModifier() {
    }

    @Override
    public void modifyFile(String stringToModify, String stringModified, String filePath) {
        String oldContent = "";
        BufferedReader reader = null;
        FileWriter writer = null;
        try {
            reader = new BufferedReader(new FileReader(filePath));
            String line = reader.readLine();

            while (line != null) {
                oldContent = oldContent + line + System.lineSeparator();
                line = reader.readLine();
            }
            String newContent = oldContent.replaceAll(stringToModify, stringModified);
            File newFile = new File(filePath.substring(0, filePath.lastIndexOf(".txt")) + "Processed" + ".txt");
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
