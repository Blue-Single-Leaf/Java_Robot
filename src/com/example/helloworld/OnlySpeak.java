/*
 * @项目名称: robot_walle
 * @文件名称: SpeechRecognizeUtils.java
 * 
 * @开发人员: 于交龙
 * @创建日期: 2017年8月31日
 */
package com.example.helloworld;

import android.content.Context;

import com.baidu.tts.client.SpeechError;
import com.baidu.tts.client.SpeechSynthesizer;
import com.baidu.tts.client.SpeechSynthesizerListener;
import com.baidu.tts.client.TtsMode;

/**
 * 语音合成工具类
 * 
 * By 于交龙 at 2017-08-31
 */
public class OnlySpeak
{
    private static OnlySpeak mSpeechSynthesizeUtils = null;
    
    private SpeechSynthesizer mSpeechSynthesizer = null;
    
    private Context mContext = null;
    
    /**
     * 语音合成类
     */
    public static class Synthesize
    {
        /**
         * 语音合成配置类
         */
        static class Config
        {
            /** 发声人 */
            public static final String SPEAKER = "4";
            
            /** 离在线模式的合成策略 */
            public static final String MIX_MODE = SpeechSynthesizer.MIX_MODE_HIGH_SPEED_NETWORK;
            
            /** 合成引擎模式 */
            public static final TtsMode ENGINE_MODE = TtsMode.ONLINE;
        }
        
        /**
         * 语音合成验证类
         */
        static class Auth
        {
            public static final String APP_ID = "9670938";
            public static final String API_KEY = "bL6Vm7scxhng05DR6o4sbHkC";
            public static final String SECRET_KEY = "7bcecb441cc97fb6cd100d252fd2d483";
        }
    }
    
    /**
     * 语音合成结果监听接口
     */
   
    
    /**
     * 私有构造函数
     * 
     * @param context
     */
    private OnlySpeak(Context context)
    {
        mContext = context;
        
        mSpeechSynthesizer = SpeechSynthesizer.getInstance();
        mSpeechSynthesizer.setContext(mContext);
//        mSpeechSynthesizer.setSpeechSynthesizerListener(new SpeechSynthesizerListener()
//        {
//            @Override
//            public void onSynthesizeStart(String utteranceId)
//            {
//                
//            }
//            
//            @Override
//            public void onSynthesizeFinish(String utteranceId)
//            {
//                
//            }
//            
//            @Override
//            public void onSynthesizeDataArrived(String utteranceId, byte[] audioData, int progress)
//            {
//                
//            }
//            
//            @Override
//            public void onSpeechStart(String utteranceId)
//            {
//                synthesizeListener.speech();
//            }
//            
//            @Override
//            public void onSpeechProgressChanged(String utteranceId, int progress)
//            {
//                
//            }
//            
//            @Override
//            public void onSpeechFinish(String utteranceId)
//            {
//                synthesizeListener.succeed();
//            }
//            
//            @Override
//            public void onError(String utteranceId, SpeechError error)
//            {
//                synthesizeListener.failed(error.code);
//            }
//        });
        
        // 设置百度语音合成授权验证信息
        mSpeechSynthesizer.setAppId(Synthesize.Auth.APP_ID);
        mSpeechSynthesizer.setApiKey(Synthesize.Auth.API_KEY, Synthesize.Auth.SECRET_KEY);
        
        // 设置发声人
        mSpeechSynthesizer.setParam(SpeechSynthesizer.PARAM_SPEAKER, Synthesize.Config.SPEAKER);
        
        // 设置离在线模式的合成策略
        mSpeechSynthesizer.setParam(SpeechSynthesizer.PARAM_MIX_MODE, Synthesize.Config.MIX_MODE);

        // 初始化语音合成引擎
        mSpeechSynthesizer.initTts(Synthesize.Config.ENGINE_MODE);
    }
    
    /**
     * 获取当前类唯一实例
     * 
     * @param context
     * 
     * @return 当前类唯一实例
     */
    public static OnlySpeak getInstance(Context context)
    {
        if(mSpeechSynthesizeUtils == null)
        {
            synchronized(OnlySpeak.class)
            {
                if(mSpeechSynthesizeUtils == null)
                {
                    mSpeechSynthesizeUtils = new OnlySpeak(context);
                }
            }
        }
        
        return mSpeechSynthesizeUtils;
    }
    
    public int speek(String text)
    {
        int result = mSpeechSynthesizer.speak(text);
        
        return result;
    }
    
    public void pause()
    {
        mSpeechSynthesizer.pause();
    }
    
    public void resume()
    {
        mSpeechSynthesizer.resume();
    }
    
    public void stop()
    {
        mSpeechSynthesizer.stop();
    }
    
    public void release()
    {
        mSpeechSynthesizer.release();
        
        mSpeechSynthesizer = null;
        mSpeechSynthesizeUtils = null;
    }
}
