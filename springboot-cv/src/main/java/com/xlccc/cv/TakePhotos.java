package com.xlccc.cv;

import javax.swing.JFrame;

import org.bytedeco.javacpp.opencv_core;
import org.bytedeco.javacpp.opencv_imgcodecs;
import org.bytedeco.javacv.CanvasFrame;
import org.bytedeco.javacv.OpenCVFrameConverter;
import org.bytedeco.javacv.FrameGrabber.Exception;
import org.bytedeco.javacv.OpenCVFrameGrabber;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Linker
 * @date 4/17/2023 10:57 PM
 * @description：每10s拍摄照片
 */
public class TakePhotos {

    public static void main(String[] args) throws Exception, InterruptedException {
        OpenCVFrameGrabber grabber = new OpenCVFrameGrabber(0);//新建opencv抓取器，一般的电脑和移动端设备中摄像头默认序号是0，不排除其他情况
        grabber.start();//开始获取摄像头数据

        OpenCVFrameConverter.ToIplImage converter = new OpenCVFrameConverter.ToIplImage();

        CanvasFrame canvas = new CanvasFrame("摄像头预览");//新建一个预览窗口
        canvas.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //窗口是否关闭
        while (canvas.isDisplayable()) {
            /*获取摄像头图像并在窗口中显示,这里Frame frame=grabber.grab()得到是解码后的视频图像*/
            canvas.showImage(grabber.grab());
            opencv_core.Mat mat = converter.convertToMat(grabber.grabFrame());
            canvas.showImage(converter.convert(mat));
            //保存第一帧到本地
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
            Date d = new Date();
            String date = sdf.format(d);
            String substring = date.substring(date.length() - 2);
            if (Integer.valueOf(substring) % 10 == 0) {
                opencv_imgcodecs.imwrite("E:\\picture-save\\" + date + ".jpg", mat);
            }
        }
        grabber.close();//停止抓取
    }
}
