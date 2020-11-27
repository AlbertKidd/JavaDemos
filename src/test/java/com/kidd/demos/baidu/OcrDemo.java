package com.kidd.demos.baidu;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.time.StopWatch;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.annotations.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author Kidd
 * CreateTime 2017/12/21.
 */
public class OcrDemo {

    //设置APPID/AK/SK
    public static final String APP_ID = "10565834";
    public static final String API_KEY = "BcXbrKsK9azzqbPMf0hmkxqA";
    public static final String SECRET_KEY = "e4ddS1GwhG5xuyz2HEAwhIHuRGmwaGM1";

    //高精度文字识别
    public static final String HIGH_PRECISION = "https://aip.baidubce.com/rest/2.0/ocr/v1/accurate_basic?access_token=";
    //高精度文字识别含位置
    public static final String HIGH_PRECISION_LOCATION = "https://aip.baidubce.com/rest/2.0/ocr/v1/accurate?access_token=";

    private static final String IMG_1 = "src/test/resources/ocr/1.jpg";
    private static final String IMG_2 = "src/test/resources/ocr/2.jpg";
    private static final String IMG_3 = "src/test/resources/ocr/3.jpg";
    private static final String IMG_a = "src/test/resources/ocr/a.jpg";
    private static final String IMG_b = "src/test/resources/ocr/b.jpg";
    private static final String IMG_c = "src/test/resources/ocr/c.jpg";

    private StopWatch stopWatch;

    /**
     * 获取权限token
     *
     * @return 返回示例：
     * {
     * "access_token": "24.460da4889caad24cccdb1fea17221975.2592000.1491995545.282335-1234567",
     * "expires_in": 2592000
     * }
     */
    private String getAuth() {
        return getAuth(API_KEY, SECRET_KEY);
    }

    /**
     * 获取API访问token
     * 该token有一定的有效期，需要自行管理，当失效时需重新获取.
     *
     * @param ak - 百度云官网获取的 API Key
     * @param sk - 百度云官网获取的 Securet Key
     * @return assess_token 示例：
     * "24.460da4889caad24cccdb1fea17221975.2592000.1491995545.282335-1234567"
     */
    private String getAuth(String ak, String sk) {
        // 获取token地址
        String authHost = "https://aip.baidubce.com/oauth/2.0/token?";
        String getAccessTokenUrl = authHost
                // 1. grant_type为固定参数
                + "grant_type=client_credentials"
                // 2. 官网获取的 API Key
                + "&client_id=" + ak
                // 3. 官网获取的 Secret Key
                + "&client_secret=" + sk;
        try {
            URL realUrl = new URL(getAccessTokenUrl);
            // 打开和URL之间的连接
            HttpURLConnection connection = (HttpURLConnection) realUrl.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();
            // 获取所有响应头字段
            Map<String, List<String>> map = connection.getHeaderFields();
            // 遍历所有的响应头字段
            for (String key : map.keySet()) {
                System.err.println(key + "--->" + map.get(key));
            }
            // 定义 BufferedReader输入流来读取URL的响应
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String result = "";
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
            /**
             * 返回结果示例
             */
            System.err.println("result:" + result);
            JSONObject jsonObject = new JSONObject(result);
            String access_token = jsonObject.getString("access_token");
            return access_token;
        } catch (Exception e) {
            System.err.printf("获取token失败！");
            e.printStackTrace(System.err);
        }
        return null;
    }

    public JsonArray getWordsResult(String recType, String imgPath) throws Exception{
        stopWatch = new StopWatch();
        stopWatch.start();

        HttpPost httpPost = new HttpPost(recType + getAuth());
        httpPost.setHeader("Content-type", "application/x-www-form-urlencoded");
        List<NameValuePair> list = new ArrayList<>();

        File file = new File(imgPath);
        byte[] bytes = FileUtils.readFileToByteArray(file);
        String imgBase64 =  Base64.encodeBase64String(bytes);

        switch (recType){
            case HIGH_PRECISION:
                break;
            case HIGH_PRECISION_LOCATION:
                //是否定位单字符位置，big：不定位单字符位置，默认值；small：定位单字符位置
                list.add(new BasicNameValuePair("recognize_granularity", "big"));
                //是否返回文字外接多边形顶点位置，不支持单字位置。默认为false
                list.add(new BasicNameValuePair("vertexes_location", "true"));
                break;
            default:
                break;
        }
        //图像数据，base64编码后进行urlencode
        list.add(new BasicNameValuePair("image", imgBase64));
        //是否检测图像朝向，默认不检测
        list.add(new BasicNameValuePair("detect_direction", "true"));
        //是否返回识别结果中每一行的置信度
        list.add(new BasicNameValuePair("probability", "false"));

        UrlEncodedFormEntity urlEncodedFormEntity = new UrlEncodedFormEntity(list, "utf-8");
        httpPost.setEntity(urlEncodedFormEntity);

        CloseableHttpClient aDefault = HttpClients.createDefault();
        long start = System.currentTimeMillis();
        CloseableHttpResponse response = aDefault.execute(httpPost);
        HttpEntity entity = response.getEntity();
        String s = EntityUtils.toString(entity);
        long end = System.currentTimeMillis();
        System.out.println("请求耗时：" + (end - start)/1000.0);
        JsonObject jsonObject = new JsonParser().parse(s).getAsJsonObject();
        String logId = jsonObject.get("log_id").getAsString();
        System.out.println("log_id: " + logId);
        String resultNum = jsonObject.get("words_result_num").getAsString();
        System.out.println("words_result_num: " + resultNum);
        JsonArray words = jsonObject.get("words_result").getAsJsonArray();
        return words;

    }

    @Test
    public void testHighPrecisionLocation() throws Exception{
        JsonArray wordsResult = getWordsResult(HIGH_PRECISION_LOCATION, IMG_1);
        for (int i = 0; i < wordsResult.size(); i++){
            JsonObject result = wordsResult.get(i).getAsJsonObject();
            String  location = result.get("location").toString();
            System.out.println("location: " + location);
            String verTexesLocation = result.get("vertexes_location").toString();
            System.out.println("vertexes_location: " + verTexesLocation);
            String wordStr = result.get("words").getAsString();
            System.out.println("word: " + wordStr);
        }
        System.out.println("spend time: " + (double)stopWatch.getTime()/1000 + "s");
        stopWatch.stop();
    }

    @Test
    public void testHighPrecision() throws Exception{
        JsonArray wordsResult = getWordsResult(HIGH_PRECISION_LOCATION, IMG_c);
        for (int i = 0; i < wordsResult.size(); i++){
            JsonObject word = wordsResult.get(i).getAsJsonObject();
            String wordStr = word.get("words").getAsString();
            System.out.println("word: " + wordStr);
        }
        System.out.println("spend time: " + (double)stopWatch.getTime()/1000 + "s");
        stopWatch.stop();
    }

}
