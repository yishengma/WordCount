package Command;

import Util.CloseUtil;
import Util.TextUtil;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class LineCommand implements Command {

    @Override
    public void execute(String filePath) {
        int count = 0;
        try {
            FileReader fileReader = new FileReader(filePath);
            BufferedReader bf = new BufferedReader(fileReader);
            //-l E:\Eclipse\workspace\WordCount\src\test.txt
            String line ;
            while ((line=bf.readLine())!=null) {
                if (!TextUtil.isEmpty(line)){
                    count++;
                }
            }
            CloseUtil.closeQuietly(bf, fileReader);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("行数 = [" + count + "]");
    }
}




