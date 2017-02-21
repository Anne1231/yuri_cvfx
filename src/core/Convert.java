package core;

import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.WritablePixelFormat;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.imgcodecs.Imgcodecs;
import java.io.ByteArrayInputStream;
import java.nio.ByteBuffer;

/**
 * Created by Anne on 2017/02/21.
 */
public class Convert {
    /*
    * OpenCV:MatからJavaFX(Image)に変換する
     */
    public static Image Mat2Image(Mat mat_img){
        /*
        * 一回ビットマップでメモリ上にマップする
        * そんでJavaFXのImageに変換して返す
         */
        MatOfByte byte_mat = new MatOfByte();
        Imgcodecs.imencode(".bmp", mat_img, byte_mat);
        return new Image(new ByteArrayInputStream(byte_mat.toArray()));
    }

    /*
    * JavaFX(Image)からOpenCV:Matに変換する
     */
    public static Mat Image2Mat(Image fx_image, int mat_type){
        /*
        * 各種値を取りだす
         */
        int width = (int)fx_image.getWidth();
        int height = (int)fx_image.getHeight();

        /*
        * 画像のバッファを新しく作る
         */
        byte[] buffer = new byte[width * height * 4];

        /*
        * 変換する
         */
        PixelReader reader = fx_image.getPixelReader();
        WritablePixelFormat<ByteBuffer> format = WritablePixelFormat.getByteBgraInstance();
        reader.getPixels(0, 0, width, height, format, buffer, 0, width * 4);

        /*
        *返す
         */
        Mat mat;
        if(mat_type == -1) {    //-1が渡されたらデフォルト
            mat = new Mat(height, width, CvType.CV_8UC4);
        }else{                  //指定さられたタイプ
            mat = new Mat(height, width, mat_type);
        }

        mat.put(0, 0, buffer);
        return mat;
    }
}
