import Command.*;
import Constant.CommandType;
import Util.ListUtil;
import Util.TextUtil;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class WordCount {
    private static List<Command> sCommandList = new ArrayList<>();
    private static List<String> sArgs = new ArrayList<>();

    public static void main(String[] args) {

        Scanner s = new Scanner(System.in);
        String m;
        while (!TextUtil.isEmpty(m = s.nextLine())) {
            String arr[] = m.split("\\s+");
            parseCommand(arr);

        }


    }

    /**
     * 解析并执行输入的命令
     *
     * @param str 输入
     */
    private static void parseCommand(String str[]) {
        String lastInput = str[str.length - 1];
        if (!checkInput(str)) {
            ListUtil.clear(sCommandList,sArgs);
            return;
        }

        if (sArgs.contains(CommandType.SEARCH_COUNT)) {
            executeWithDir(lastInput);
        } else {
            executeWithFile(lastInput);
        }
        ListUtil.clear(sCommandList,sArgs);

    }

    /**
     * 检查输入是否为正确的输入
     *
     * @param str 输入
     * @return 返回结果
     */
    private static boolean checkInput(String[] str) {
        String args;
        boolean isCorrect = true;
        for (int i = 0; i < str.length; i++) {
            args = str[i];
            if (args.equals(CommandType.CHAR_COUNT) && !sArgs.contains(args)) {
                sCommandList.add(new CharCommand());
                sArgs.add(args);
            } else if (args.equals(CommandType.WORD_COUNT) && !sArgs.contains(args)) {
                sCommandList.add(new WordCommand());
                sArgs.add(args);
            } else if (args.equals(CommandType.LINE_COUNT) && !sArgs.contains(args)) {
                sCommandList.add(new LineCommand());
                sArgs.add(args);
            } else if (args.equals(CommandType.ALL_COUNT) && !sArgs.contains(args)) {
                sCommandList.add(new AllCommand());
                sArgs.add(args);
            } else if (args.equals(CommandType.SEARCH_COUNT) && !sArgs.contains(args)) {
                sArgs.add(args);
            } else if (i == str.length - 1) {
                isCorrect = checkFile(args);
            } else {
                showErrorMsg(args);
                isCorrect = false;
            }

        }


        return isCorrect;
    }

    /**
     * 检查文件输入是否正确
     *
     * @param args 输入
     * @return 返回结果
     */
    private static boolean checkFile(String args) {

        if (sArgs.contains(CommandType.SEARCH_COUNT)) {
            return checkCommandDir(args);
        } else {
            return checkCommandFile(args);
        }

    }

    /**
     * 显示提示
     */
    private static void showErrorMsg(String s) {
        System.out.println(s + " 不是内部命令！");
        System.out.println("用法：");
        System.out.println("-c <文件名> ，统计文件字符数。");
        System.out.println("-w <文件名> ，统计文件单词数。");
        System.out.println("-l <文件名> ，统计文件行数数。");
        System.out.println("-s <文件路径+类型（?任意）> ，递归符合条件的文件。");
        System.out.println("-a <文件名> ，统计文件代码行，空白行，注释行。");

    }


    /**
     * 检查文件输入是否正确
     *
     * @param filePath 输入文件名
     * @return 返回结果
     */
    private static boolean checkCommandFile(String filePath) {
        File file = new File(filePath);
        if (!file.isFile()) {
            System.out.println(filePath + " 不是一个完整或者正确的文件名！");
            return false;
        } else if (!file.exists()) {
            System.out.println(filePath + " 文件不存在！");
            return false;
        }
        return true;
    }

    /**
     * 检查文件夹输入是否正确
     *
     * @param dirPath 输入路径和文件通配符
     * @return 返回结果
     */
    private static boolean checkCommandDir(String dirPath) {
        if (!dirPath.contains("*")) {
            System.out.println(dirPath+"不是一个合法输入！");
            System.out.println("-s <文件路径+类型（?任意）> ，递归符合条件的文件。");
            return false;
        }
        String fileDir = dirPath.substring(0, dirPath.indexOf("*") - 1); // 文件所在文件夹
        File dir = new File(fileDir);
        if (!dir.isDirectory()) {
            System.out.println(fileDir + " 不是一个完整或者正确的文件夹名！");
            return false;
        } else if (!dir.exists()) {
            System.out.println(fileDir + " 文件夹不存在！");
            return false;
        }
        return true;
    }

    /**
     * 处理文件夹
     *
     * @param input 输入
     */
    private static void executeWithDir(String input) {
        ArrayList<String> list = new SearchCommand().execute(input);
        for (String s : list) {
            System.out.println(s);
            for (Command c : sCommandList) {
                c.execute(s);
            }

        }
    }

    /**
     * 处理文件
     *
     * @param input 输入
     */
    private static void executeWithFile(String input) {
        for (Command c : sCommandList) {
            c.execute(input);
        }
    }



}



