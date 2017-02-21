package main;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;

/**
 * Created by Anne on 2017/02/21.
 */
public class Main extends Application {

    public static void main(String argv[]){
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        launch(argv);
    }

    @Override
    public void start(Stage stage) throws Exception
    {
        VBox root = new VBox();

        Mat img = Imgcodecs.imread("tes.png");
        ImageView imgView = new ImageView(core.Convert.Mat2Image(img));
        root.getChildren().add(imgView);

        Mat img2 = core.Convert.Image2Mat(core.Convert.Mat2Image(img), -1);
        Imgcodecs.imwrite("test.png", img2);

        // シーンの作成
        Scene scene       = new Scene( root );

        // ウィンドウ表示
        stage.setScene( scene );
        stage.show();
    }
}
