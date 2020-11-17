package cn.electricdog.noveltools.pojo;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

/**
 * @Author: Sruifeng
 * @Email: suruifeng@wistronits.com
 * @Date: 2020/11/18 2:21
 */
public class Result {
    Integer code = 0;
    String msg = "";
    Object data;

    public Result(Integer code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this, SerializerFeature.WriteMapNullValue);
    }
}
