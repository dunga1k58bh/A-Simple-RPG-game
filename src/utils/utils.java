package utils;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;

public class utils {
    public static StackPane createButton (String s, String text, int x, int y) {
        javafx.scene.control.Button a = new Button("", new ImageView(new Image(s)));
        a.setStyle("    -fx-border-color: transparent;\n" +
                "    -fx-border-width: 0;\n" +
                "    -fx-background-radius: 0;\n" +
                "    -fx-background-color: transparent;\n");
        StackPane stack = new StackPane();
        Text zzz = new Text(text);
        stack.getChildren().addAll(a,zzz);
        stack.setLayoutX(x);
        stack.setLayoutY(y);
        return stack;
    }
}
