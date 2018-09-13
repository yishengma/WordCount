package Command;

import java.util.ArrayList;
import java.util.List;

public interface Command {

    ArrayList<String> execute(String filePath);
}
