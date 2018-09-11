package Command;

import Util.CloseUtil;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class CharCommand implements Command.Command {

    @Override
    public void execute(String filePath) {
        int count = 0;
        try {
            FileReader fileReader = new FileReader(filePath);
            BufferedReader bf = new BufferedReader(fileReader);
            String line;//E:\Eclipse\workspace\WordCount\src\WordCount.java

            while ((line = bf.readLine()) != null) {
                count += line.length();
            }
            CloseUtil.closeQuietly(bf, fileReader);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("字符数 = [" + count + "]");
    }
}
