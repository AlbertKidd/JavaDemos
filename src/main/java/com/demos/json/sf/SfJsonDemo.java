package com.demos.json.sf;

import com.demos.beans.PubUcustomer;
import com.demos.beans.RegisterResult;
import net.sf.json.JSONObject;
import org.junit.Test;

import java.sql.Timestamp;

/**
 * @author Kidd
 *         CreateTime 2017/8/14.
 */
public class SfJsonDemo {

    @Test
    public void test(){
        RegisterResult registerResult = new RegisterResult();
        registerResult.setSuccess(true);
        registerResult.setErrorMsg("none");
        PubUcustomer pubUcustomer = new PubUcustomer();
        pubUcustomer.setEditdate(new Timestamp(System.currentTimeMillis()));
        String s = "{\"success\":true,\"ucustomer\":{\"foreignKeys\":[],\"reg_machinename\":\"\",\"updateFlag\":\"Unchanged\",\"mobile\":\"\",\"ucustomerid\":\"00A\",\"memo\":\"\",\"credate\":{\"date\":14,\"hours\":20,\"seconds\":9,\"month\":7,\"nanos\":780000000,\"timezoneOffset\":-480,\"year\":117,\"minutes\":50,\"time\":1502715009780,\"day\":1},\"linkaddress\":\"\",\"linkman\":\"\",\"pidcode\":\"\",\"machinecode\":\"123\",\"qqno\":\"\",\"midcode\":\"\",\"options\":{},\"details\":[],\"allProperty\":{},\"customername\":\"\",\"editdate\":{\"date\":14,\"hours\":20,\"seconds\":9,\"month\":7,\"nanos\":780000000,\"timezoneOffset\":-480,\"year\":117,\"minutes\":50,\"time\":1502715009780,\"day\":1},\"email\":\"xxxxx\",\"properties\":{},\"wechatno\":\"\"},\"errorMsg\":\"\"}";
        JSONObject jsonObject = JSONObject.fromObject(s);
    }
}
