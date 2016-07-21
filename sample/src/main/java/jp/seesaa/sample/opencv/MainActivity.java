package jp.seesaa.sample.opencv;

import android.databinding.DataBindingUtil;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import org.opencv.android.Utils;
import org.opencv.core.Mat;
import org.opencv.imgproc.Imgproc;

import jp.seesaa.sample.opencv.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        processOpenCV();
    }

    private void processOpenCV() {
        Bitmap lennaBmp = BitmapFactory.decodeResource(getResources(), R.drawable.lenna);

        Mat mat = new Mat();
        Utils.bitmapToMat(lennaBmp, mat);

        Imgproc.cvtColor(mat,mat, Imgproc.COLOR_RGB2GRAY);

        Bitmap outBmp = Bitmap.createBitmap(mat.cols(), mat.rows(), Bitmap.Config.ARGB_4444);
        Utils.matToBitmap(mat, outBmp);

        binding.processedImageView.setImageBitmap(outBmp);
    }

}
