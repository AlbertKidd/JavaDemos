package com.kidd.demos.json.sf;

import com.kidd.demos.beans.RegisterResult;
import net.sf.json.JSONObject;
import net.sf.json.util.JSONUtils;
import org.junit.Test;

/**
 * @author Kidd
 *         CreateTime 2017/8/14.
 */
public class SfJsonDemo {

    @Test
    public void test(){
        String s = "{\"success\":true,\"ucustomer\":{\"reg_machinename\":\"\",\"updateFlag\":\"Unchanged\",\"mobile\":\"\",\"ucustomerid\":\"00A\",\"memo\":\"\",\"credate\":\"1992-10-19 23:52:18\",\"linkaddress\":\"\",\"linkman\":\"\",\"pidcode\":\"\",\"machinecode\":\"123\",\"qqno\":\"\",\"midcode\":\"\",\"customername\":\"\",\"email\":\"xxxxx\",\"wechatno\":\"\"},\"errorMsg\":\"\"}";
        JSONObject jsonObject = JSONObject.fromObject(s);
        JSONUtils.getMorpherRegistry().registerMorpher(new TimestampMorpher());
        RegisterResult result = (RegisterResult)JSONObject.toBean(jsonObject, RegisterResult.class);
    }
}
