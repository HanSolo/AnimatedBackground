package eu.hansolo.fx.animatedbackground;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Collectors;


public final class Helper {

    public static String getPathFromSvgFile(final String filename) {
        try {
            String svgText = readTextFile(filename, Charset.forName("UTF-8"));
            Constants.SVG_PATH_MATCHER.reset(svgText);
            if (Constants.SVG_PATH_MATCHER.find()) {
                return Constants.SVG_PATH_MATCHER.group(1);

            } else {
                return "";
            }
        } catch (IOException e) {
            return "";
        }
    }

    public static String readTextFile(final String filename, final Charset charset) throws IOException {
        if (new File(filename).exists()) {
            return Files.readString(Paths.get(filename), null == charset ? Charset.forName("UTF-8") : charset);
        } else {
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(Helper.class.getResourceAsStream(filename), charset))) {
                return reader.lines().collect(Collectors.joining("\n"));
            } catch (IOException e) {
                return "";
            }
        }
    }
}
