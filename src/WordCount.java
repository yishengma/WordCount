import Command.CharCommand;
import Command.Command;
import Util.CloseUtil;

import java.io.*;
import java.util.Scanner;

public class WordCount {
    private static Command mCommand;
    public static void main(String[] args) {
        System.out.print("请输入命令：");
        Scanner s = new Scanner(System.in);
        String m = s.nextLine();
        String arr[] = m.split("\\s+");
        parseCommand(arr);


    }

    private static void parseCommand(String str[]) {
        if (str.length != 2) {
            System.out.println("用法：");
            System.out.println("-c <文件名> ，统计文件字符数。");
            System.out.println("-w <文件名> ，统计文件单词数。");
            System.out.println("-l <文件名> ，统计文件行数数。");

        }
        checkCommandHead(str[0]);
        checkCommandFile(str[1]);
    }


    private static void checkCommandHead(String head) {
        switch (head) {
            case CommandHead.CHAR_COUNT:
                mCommand = new CharCommand();
                break;
            case CommandHead.WORD_COUNT:
                break;
            case CommandHead.LINE_COUNT:
                break;
            default:
                System.out.println(head + " 不是内部命令！");

        }
    }

    private static void checkCommandFile(String filePath) {
        File file = new File(filePath);
        if (!file.isFile()) {
            System.out.println(filePath + " 不是正确的或者完整的文件名！");
        } else if (!file.exists()) {
            System.out.println("找不到指定文件！");
        }
    }

}
