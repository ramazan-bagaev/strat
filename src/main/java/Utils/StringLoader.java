package Utils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class StringLoader {

    private FileReader fileReader;
    private BufferedReader bufferedReader;

    public StringLoader(){
    }

    public String get(String fileName){
        String result = "";
        try {
            this.fileReader = new FileReader(fileName);
            this.bufferedReader = new BufferedReader(fileReader);

            String currentLine;

            while((currentLine = bufferedReader.readLine()) != null){
                result+=currentLine + "\n";
            }
            return result;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {

                if (bufferedReader != null)
                    bufferedReader.close();

                if (fileReader != null)
                    fileReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }
}
