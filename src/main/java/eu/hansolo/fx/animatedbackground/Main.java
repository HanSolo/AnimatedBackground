package eu.hansolo.fx.animatedbackground;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.effect.Light;
import javafx.scene.effect.Lighting;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.SVGPath;
import javafx.stage.Stage;


public class Main extends Application {
    private SVGPath        logo;
    private StackPane      pane;
    private Light.Distant  distantLight;
    private double         angle;
    private long           lastTimerCall;
    private AnimationTimer timer;


    @Override public void init() {
        distantLight = new Light.Distant();
        distantLight.setAzimuth(100); // -135
        distantLight.setElevation(50); // 50
        distantLight.setColor(Color.color(1.0, 1.0, 1.0, 1.0));

        final Lighting lighting = new Lighting();
        lighting.setLight(distantLight);
        lighting.setDiffuseConstant(1.0);   // 1.0    0...2
        lighting.setSpecularConstant(0.3);  // 0.3    0...2
        lighting.setSpecularExponent(40.0); // 20     0...40
        lighting.setSurfaceScale(10);        // 1.5    0...10

        final String path = Helper.getPathFromSvgFile("azul.svg");
        logo = new SVGPath();
        logo.setContent(path);
        logo.setFill(Constants.AZUL_BLUE);
        logo.setStroke(Color.TRANSPARENT);
        logo.setScaleX(3.5);
        logo.setScaleY(3.5);
        logo.setEffect(lighting);

        pane = new StackPane(logo);
        pane.setBackground(new Background(new BackgroundFill(Constants.AZUL_BLUE, CornerRadii.EMPTY, Insets.EMPTY)));
        pane.setPrefSize(1920, 1080);

        angle         = 0;
        lastTimerCall = System.nanoTime();
        timer         = new AnimationTimer() {
            @Override public void handle(long now) {
                if (now > lastTimerCall + 50_000_000l) {
                    distantLight.setAzimuth(angle);
                    angle++;
                    if (angle > 359) { angle = 0; }
                    lastTimerCall = System.nanoTime();
                }
            }
        };

        registerListeners();
    }

    private void registerListeners() {

    }

    @Override public void start(final Stage stage) throws Exception {
        Scene scene = new Scene(pane);

        stage.setScene(scene);
        stage.setTitle("");
        stage.setResizable(false);
        //stage.initStyle(StageStyle.TRANSPARENT);
        stage.show();
        stage.centerOnScreen();

        timer.start();
    }

    @Override public void stop() {
        Platform.exit();
        System.exit(0);
    }


    public static void main(final String[] args) {
        launch(args);
    }
}