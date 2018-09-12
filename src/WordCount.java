import Command.CharCommand;
import Command.Command;
import Command.LineCommand;
import Command.WordCommand;
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

    /**
     * 解析并执行输入的命令
     * @param str
     */
    private static void parseCommand(String str[]) {
        if (checkInput(str) && checkCommandHead(str[0]) && checkCommandFile(str[1])) {
            mCommand.execute(str[1]);
        }


    }

    /**
     * 检查输入是否为正确的输入
     * @param str
     * @return
     */
    private static boolean checkInput(String[] str) {
        if (str.length != 2) {
            System.out.println("用法：");
            System.out.println("-c <文件名> ，统计文件字符数。");
            System.out.println("-w <文件名> ，统计文件单词数。");
            System.out.println("-l <文件名> ，统计文件行数数。");
            return false;

        }
        return true;
    }

    /**
     * 检查输入的命令的类型
     * @param head
     * @return
     */
    private static boolean checkCommandHead(String head) {
        boolean correct = true;
        switch (head) {
            case CommandHead.CHAR_COUNT:
                mCommand = new CharCommand();
                break;
            case CommandHead.WORD_COUNT:
                mCommand = new WordCommand();
                break;
            case CommandHead.LINE_COUNT:
                mCommand = new LineCommand();
                break;
            default:
                correct = false;
                System.out.println(head + " 不是内部命令！");

        }
        return correct;
    }

    /**
     * 检查文件输入是否正确
     * @param filePath
     * @return
     */
    private static boolean checkCommandFile(String filePath) {
        File file = new File(filePath);
        if (!file.isFile()) {
            System.out.println(filePath + " 不是正确的或者完整的文件名！");
            return false;
        } else if (!file.exists()) {
            System.out.println("找不到指定文件！");
            return false;
        }
        return true;
    }

}



