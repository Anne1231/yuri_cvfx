package core;

import javafx.scene.image.Image;
import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.imgcodecs.Imgcodecs;

import java.io.ByteArrayInputStream;

/**
 * Created by Anne on 2017/02/21.
 */
public class Convert {
    public static Image Mat2Image(Mat mat_img){
        /*
        * 一回ビットマップでメモリ上にマップする
        * そんでJavaFXのImageに変換して返す
         */
        MatOfByte byte_mat = new MatOfByte();
        Imgcodecs.imencode(".bmp", mat_img, byte_mat);
        return new Image(new ByteArrayInputStream(byte_mat.toArray()));
    }
}
