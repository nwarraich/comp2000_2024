import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StageReader {
    public static Stage readStage(String path) throws IOException {
        Stage stage = new Stage();
        Properties props = new Properties();
        props.load(new FileInputStream(path));

        for (String key : props.stringPropertyNames()) {
            String value = props.getProperty(key);
            Pattern cellPattern = Pattern.compile("([A-Za-z])(\\d+)");

            Matcher cellMatcher = cellPattern.matcher(key);

            if (cellMatcher.matches()) {
                char col = cellMatcher.group(1).charAt(0);
                int row = Integer.parseInt(cellMatcher.group(2));

                // Calculate x and y position based on column and row
                int x = (col - 'A') * Cell.size; // Assuming columns start from 'A'
                int y = (row - 1) * Cell.size;   // Assuming rows are 1-indexed

                Cell cell = new Cell(col, row, x, y);
                if (value.equals("cat")) {
                    stage.actors.add(new Cat(cell));
                } else if (value.equals("dog")) {
                    stage.actors.add(new Dog(cell));
                } else if (value.equals("bird")) {
                    stage.actors.add(new Bird(cell));
                }
            } else {
                System.out.println("no match " + key);
            }
        }
        return stage;
    }
}
