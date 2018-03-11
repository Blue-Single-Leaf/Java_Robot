package com.example.helloworld;

import com.yanzhenjie.nohttp.NoHttp;
import com.yanzhenjie.nohttp.RequestMethod;
import com.yanzhenjie.nohttp.rest.Request;
import com.yanzhenjie.nohttp.rest.Response;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * 图灵对话工具类
 * 
 * By 于交龙 at 2017-05-21
 */

public class TulingUtils
{
    public interface RequestListener
    {
        public void succeed(String chart);
        public void failed(int code);
    }
    
    public class Error
    {
        public static final int CHART_FAILED = -1;
        public static final int JSON_PARSE_FAILED = -2;
        public static final int GET_CHART_DATA_FAILED = -3;
        public static final int CHART_REQUEST_FAILED = -4;
        public static final int JSON_EXCEPTION = -5;
        
    }
    
    private static final String API_KEY = "961da432ef7d46d2b4ab6bf5031d624c";
    private static final String CHART_REQ_URL = "http://www.tuling123.com/openapi/api";

    public static Response<JSONObject> requestChart(String chart)
    {
        Request<JSONObject> request = NoHttp.createJsonObjectRequest(CHART_REQ_URL, RequestMethod.POST);

        try
        {
            JSONObject jsonObj = new JSONObject();

            jsonObj.put("key", API_KEY);
            jsonObj.put("info", chart);
            jsonObj.put("userid", "123456");

            request.setDefineRequestBodyForJson(jsonObj);

            Response<JSONObject> response = NoHttp.startRequestSync(request);

            return response;
        }
        catch(JSONException e)
        {
            e.printStackTrace();
        }

        return null;
    }
    
    public static void requestChart(final String chart, final RequestListener listener)
    {
        new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                Request<JSONObject> request = NoHttp.createJsonObjectRequest(CHART_REQ_URL, RequestMethod.POST);

                try
                {
                    JSONObject jsonObj = new JSONObject();

                    jsonObj.put("key", API_KEY);
                    jsonObj.put("info", chart);
                    jsonObj.put("userid", "123456");

                    request.setDefineRequestBodyForJson(jsonObj);

                    Response<JSONObject> response = NoHttp.startRequestSync(request);
                    
                    // 图灵机器人对话请求成功
                    if(response != null)
                    {
                        JSONObject chartObj = response.get();

                        // 对话数据获取成功
                        if(chartObj != null)
                        {
                            try
                            {
                                int code = chartObj.getInt("code");
                                
                                // 图灵机器人对话成功
                                if(code == 100000)
                                {
                                    String textSyc = chartObj.getString("text");

                                    if(textSyc != null)
                                    {
                                        listener.succeed(textSyc);
                                    }
                                }
                                else
                                {
                                    listener.failed(Error.CHART_FAILED);
                                }
                            }
                            catch(JSONException e)
                            {
                                listener.failed(Error.JSON_PARSE_FAILED);
                            }
                        }
                        else
                        {
                            listener.failed(Error.GET_CHART_DATA_FAILED);
                        }
                    }
                    else
                    {
                        listener.failed(Error.CHART_REQUEST_FAILED);
                    }
                }
                catch(JSONException e)
                {
                    listener.failed(Error.JSON_EXCEPTION);
                }
            }
        }).start();
    }
}
