package Command;

import Util.CloseUtil;
import Util.TextUtil;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LineCommand implements Command {

    @Override
    public ArrayList<String> execute(String filePath) {
        int count = 0;
        try {
            FileReader fileReader = new FileReader(filePath);
            BufferedReader bf = new BufferedReader(fileReader);
            //-l E:\Eclipse\workspace\WordCount\src\test.txt

            while (bf.readLine()!=null) {
                count++;
            }
            CloseUtil.closeQuietly(bf, fileReader);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("行数 = [" + count + "]");
        return null;
    }
}




