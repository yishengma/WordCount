package Command;

import Util.CloseUtil;
import Util.TextUtil;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;



public class WordCommand implements Command {
    @Override
    public ArrayList<String> execute(String filePath) {
        int count = 0;
        try {
            FileReader fileReader = new FileReader(filePath);
            BufferedReader bf = new BufferedReader(fileReader);
            String line;//-w E:\Eclipse\workspace\WordCount\src\test.txt
            StringBuilder builder = new StringBuilder();
            while ((line = bf.readLine()) != null) {
                builder.append(line);
            }
            String[] result = builder.toString()
                    .replaceAll("[-+*/^().!@#$%&~`·：；;:\"',<>？?\\]\\[]", " ")//去除特殊字符
                    .replaceAll("[\u4e00-\u9fa5]", " ")//去除汉字
                    .replaceAll("\\d+", " ")//去除数字
                    .split("\\s+");
            for (String s : result) {
                if (!TextUtil.isEmpty(s)) {
                    count++;
                }
            }
            CloseUtil.closeQuietly(bf, fileReader);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("单词数 = [" + count + "]");
        return null;
    }
}
