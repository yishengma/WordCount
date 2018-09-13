package Command;

import Util.CloseUtil;
import Util.TextUtil;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;


public class AllCommand implements Command {

    @Override
    public ArrayList<String> execute(String filePath) {
        int codeLineCount = 0;
        int blankLineCount = 0;
        int annotationLineCount = 0;
        try {
            FileReader fileReader = new FileReader(filePath);
            BufferedReader bf = new BufferedReader(fileReader);
            String line;//-a E:\Eclipse\workspace\WordCount\src\test.txt

            while ((line = bf.readLine()) != null) {
                if (TextUtil.isEmpty(line)){
                    blankLineCount++;
                    continue;
                }
                if (line.contains("//")){
                    annotationLineCount++;
                    continue;
                }
                codeLineCount++;

            }
            CloseUtil.closeQuietly(bf, fileReader);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("代码行数 = [" + codeLineCount + "]");
        System.out.println("空白行数 = [" + blankLineCount + "]");
        System.out.println("注释行数 = [" + annotationLineCount + "]");
        return null;
    }
}
