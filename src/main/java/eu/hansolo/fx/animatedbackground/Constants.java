package eu.hansolo.fx.animatedbackground;

import javafx.scene.paint.Color;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public final class Constants {
    public static final Color AZUL_BLUE          = Color.web("#142241"); // rgb(20, 34, 65)

    public static final Pattern SVG_PATH_PATTERN = Pattern.compile("<path\\s+d=\"(.*)\"\\s");
    public static final Matcher SVG_PATH_MATCHER = SVG_PATH_PATTERN.matcher("");
}
