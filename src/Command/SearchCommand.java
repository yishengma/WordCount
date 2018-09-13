package Command;

import java.io.File;
import java.util.ArrayList;


public class SearchCommand implements Command {
    private ArrayList<String> mFileList = new ArrayList<>();

    @Override
    public ArrayList<String> execute(String filePath) {
        searchFile(filePath);

        return mFileList;
    }

    /**
     * 查找目录下符合条件的文件
     *
     * @param filePath 文件夹路径
     */
    private void searchFile(String filePath) {
        boolean special = true;
        String fileDir = filePath.substring(0, filePath.indexOf("*") - 1); // 文件所在文件夹
        String fileType = filePath.substring(filePath.indexOf("*") + 1, filePath.length()); //文件类型

        //-s E:\Eclipse\workspace\WordCount\src\*.java
        if (fileType.equals(".?")) {
            special = false;
        }
        File dir = new File(fileDir);

        if (dir.isDirectory()) {
            File files[] = dir.listFiles(); //找到文件夹所有的文件
            if (files == null) {
                return;
            }
            //对文件循环处理
            for (File file : files) {
                if (file.isDirectory()) {
                    //递归文件夹
                    searchFile(file.getPath() + "\\*" + fileType);
                    continue;
                }
                //没有指定文件类型
                if (!special) {
                    mFileList.add(fileDir + "\\" + file.getName());
                } else if (file.getName().substring(file.getName().indexOf("."),file.getName().length()).equals(fileType)){
                    mFileList.add(fileDir + "\\" + file.getName());
                }



            }
        } else {
            System.out.println("找不到指定文件夹！");
        }

    }

}
