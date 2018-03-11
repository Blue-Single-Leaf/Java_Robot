/*
 * @项目名称: robot_walle
 * @文件名称: SpeechRecognizeUtils.java
 * 
 * @开发人员: 于交龙
 * @创建日期: 2017年8月31日
 */
package com.example.helloworld;

import java.util.ArrayList;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognitionListener;
import android.speech.SpeechRecognizer;

import com.baidu.speech.VoiceRecognitionService;

/**
 * 语音识别工具类
 * 
 * By 于交龙 at 2017-08-31
 */
public class SpeechRecognizeUtils
{
    private static SpeechRecognizeUtils mSpeechRecognizeUtils = null;
    
    private SpeechRecognizer mSpeechRecognizer = null;
    
    private Context mContext = null;
    
    public class Recoginse
    {
        /**
         * 语音识别验证类
         */
        class Auth
        {
            public static final int APP_ID = 9670938;
            public static final String API_KEY = "bL6Vm7scxhng05DR6o4sbHkC";
            public static final String SECRET_KEY = "7bcecb441cc97fb6cd100d252fd2d483";
        }
        
        /**
         * 语音识别配置类
         */
        class Config
        {
            /** 授权配置 -开始 */
            public static final String KEY_APP_ID = "appid";
            public static final int VALUE_APP_ID = Auth.APP_ID;
            
            public static final String KEY_API_KEY = "key";
            public static final String VALUE_API_KEY = Auth.API_KEY;
            
            public static final String KEY_SECRET_KEY = "secret";
            public static final String VALUE_SECRET_KEY = Auth.SECRET_KEY;
            /** 授权配置 -结束 */
            
            /** 语音识别配置 -开始 */
            public static final String KEY_LANGUAGE = "language";
            public static final String VALUE_LANGUAGE = "cmn-Hans-CN";
            
            public static final String KEY_SAMPLE = "sample";
            public static final int VALUE_SAMPLE = 16000;
            /** 语音识别配置 -结束 */
        }
        
        /**
         * 语音识别错误类
         */
        class Error
        {
            public final static int NO_RESULT = 0;
        }
        
        /**
         * 语音识别引擎类型类
         */
        class EngineType
        {
            public final static int ONLINE = 0;
            public final static int OFFLINE = 1;
        }
    }
    
    /**
     * 识别结果监听接口
     */
    public interface RecogniseListener
    {
        public void failed(int code);
        public void succeed(String text);
        public void recognised(String text);
        public void switched(int type);
        public void ready();
    }
    
    /**
     * 私有构造函数
     * 
     * @param context
     */
    private SpeechRecognizeUtils(Context context, final RecogniseListener recogniseListener)
    {
        mContext = context;
        
        // 初始化百度语音识别组件
        ComponentName componentName = new ComponentName(mContext, VoiceRecognitionService.class);
        
        // 初始化语音识别对象(使用百度语音识别组件)
        mSpeechRecognizer = SpeechRecognizer.createSpeechRecognizer(mContext, componentName);
        
        mSpeechRecognizer.setRecognitionListener(new RecognitionListener()
        {
            @Override
            public void onRmsChanged(float rmsdB)
            {
                
            }
            
            @Override
            public void onResults(Bundle results)
            {
                ArrayList<String> resultList = results.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);
                
                if(resultList.size() > 0)
                {
                    String finalResult = resultList.get(0);
                    recogniseListener.succeed(finalResult);
                }
                else
                {
                    recogniseListener.failed(Recoginse.Error.NO_RESULT);
                }
            }
            
            @Override
            public void onReadyForSpeech(Bundle params)
            {
                recogniseListener.ready();
            }
            
            @Override
            public void onPartialResults(Bundle partialResults)
            {
                ArrayList<String> tempResultList = partialResults.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);
                
                if(tempResultList.size() > 0)
                {
                    recogniseListener.recognised(tempResultList.get(0));
                }
            }
            
            @Override
            public void onEvent(int eventType, Bundle params)
            {
                if(VoiceRecognitionService.EVENT_ENGINE_SWITCH == eventType)
                {
                    int type = params.getInt("engine_type");
                    recogniseListener.switched(type);
                }
                else
                {
                    recogniseListener.failed(eventType);
                }
            }
            
            @Override
            public void onError(int error)
            {
                recogniseListener.failed(error);
            }
            
            @Override
            public void onEndOfSpeech()
            {
                
            }
            
            @Override
            public void onBufferReceived(byte[] buffer)
            {
                
            }
            
            @Override
            public void onBeginningOfSpeech()
            {
                
            }
        });
    }
    
    /**
     * 获取当前类唯一实例
     * 
     * @param context
     * 
     * @return 当前类唯一实例
     */
    public static SpeechRecognizeUtils getInstance(Context context, RecogniseListener recogniseListener)
    {
        if(mSpeechRecognizeUtils == null)
        {
            synchronized(SpeechRecognizeUtils.class)
            {
                if(mSpeechRecognizeUtils == null)
                {
                    mSpeechRecognizeUtils = new SpeechRecognizeUtils(context, recogniseListener);
                }
            }
        }
        
        return mSpeechRecognizeUtils;
    }
    
    public void start()
    {
        Intent defaultConfig = getDefaultConfig();
        start(defaultConfig);
    }
    
    public void start(Intent intent)
    {
        mSpeechRecognizer.startListening(intent);
    }
    
    public void stop()
    {
        mSpeechRecognizer.stopListening();
    }
    
    public void cancle()
    {
        mSpeechRecognizer.cancel();
    }
    
    public void destroy()
    {
        mSpeechRecognizer.destroy();
        
        mSpeechRecognizer = null;
        mSpeechRecognizeUtils = null;
    }
    
    public Intent getDefaultConfig()
    {
        Intent intent = new Intent();
        intent.putExtra(Recoginse.Config.KEY_APP_ID, Recoginse.Config.VALUE_APP_ID);
        intent.putExtra(Recoginse.Config.KEY_API_KEY, Recoginse.Config.VALUE_API_KEY);
        intent.putExtra(Recoginse.Config.KEY_SECRET_KEY, Recoginse.Config.VALUE_SECRET_KEY);
        intent.putExtra(Recoginse.Config.KEY_LANGUAGE, Recoginse.Config.VALUE_LANGUAGE);
        intent.putExtra(Recoginse.Config.KEY_SAMPLE, Recoginse.Config.VALUE_SAMPLE);
        
        return intent;
    }
}
