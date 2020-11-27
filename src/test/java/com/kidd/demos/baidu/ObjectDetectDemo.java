package com.kidd.demos.baidu;

import com.baidu.aip.imageclassify.AipImageClassify;
import org.json.JSONObject;

import java.util.HashMap;

/**
 * 识别图像主体，如车辆、菜品等，并非识别图像的文字
 * @author Kidd
 * CreateTime 2017/12/21.
 */
public class ObjectDetectDemo {

    //设置APPID/AK/SK
    public static final String APP_ID = "10565834";
    public static final String API_KEY = "BcXbrKsK9azzqbPMf0hmkxqA";
    public static final String SECRET_KEY = "e4ddS1GwhG5xuyz2HEAwhIHuRGmwaGM1";

    public static void main(String[] args) {
        // 初始化一个AipImageClassifyClient
        AipImageClassify client = new AipImageClassify(APP_ID, API_KEY, SECRET_KEY);

        // 可选：设置网络连接参数
        client.setConnectionTimeoutInMillis(2000);
        client.setSocketTimeoutInMillis(60000);

        // 调用接口
        String path = "src/main/resources/ocr/1.jpg";
        JSONObject res = client.objectDetect(path, new HashMap<String, String>());
        System.out.println(res.toString(2));
    }
}
