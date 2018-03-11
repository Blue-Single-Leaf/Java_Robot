/*
 * @��Ŀ����: robot_walle
 * @�ļ�����: SpeechRecognizeUtils.java
 * 
 * @������Ա: �ڽ���
 * @��������: 2017��8��31��
 */
package com.example.helloworld;

import android.content.Context;

import com.baidu.tts.client.SpeechError;
import com.baidu.tts.client.SpeechSynthesizer;
import com.baidu.tts.client.SpeechSynthesizerListener;
import com.baidu.tts.client.TtsMode;

/**
 * �����ϳɹ�����
 * 
 * By �ڽ��� at 2017-08-31
 */
public class OnlySpeak
{
    private static OnlySpeak mSpeechSynthesizeUtils = null;
    
    private SpeechSynthesizer mSpeechSynthesizer = null;
    
    private Context mContext = null;
    
    /**
     * �����ϳ���
     */
    public static class Synthesize
    {
        /**
         * �����ϳ�������
         */
        static class Config
        {
            /** ������ */
            public static final String SPEAKER = "4";
            
            /** ������ģʽ�ĺϳɲ��� */
            public static final String MIX_MODE = SpeechSynthesizer.MIX_MODE_HIGH_SPEED_NETWORK;
            
            /** �ϳ�����ģʽ */
            public static final TtsMode ENGINE_MODE = TtsMode.ONLINE;
        }
        
        /**
         * �����ϳ���֤��
         */
        static class Auth
        {
            public static final String APP_ID = "9670938";
            public static final String API_KEY = "bL6Vm7scxhng05DR6o4sbHkC";
            public static final String SECRET_KEY = "7bcecb441cc97fb6cd100d252fd2d483";
        }
    }
    
    /**
     * �����ϳɽ�������ӿ�
     */
   
    
    /**
     * ˽�й��캯��
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
        
        // ���ðٶ������ϳ���Ȩ��֤��Ϣ
        mSpeechSynthesizer.setAppId(Synthesize.Auth.APP_ID);
        mSpeechSynthesizer.setApiKey(Synthesize.Auth.API_KEY, Synthesize.Auth.SECRET_KEY);
        
        // ���÷�����
        mSpeechSynthesizer.setParam(SpeechSynthesizer.PARAM_SPEAKER, Synthesize.Config.SPEAKER);
        
        // ����������ģʽ�ĺϳɲ���
        mSpeechSynthesizer.setParam(SpeechSynthesizer.PARAM_MIX_MODE, Synthesize.Config.MIX_MODE);

        // ��ʼ�������ϳ�����
        mSpeechSynthesizer.initTts(Synthesize.Config.ENGINE_MODE);
    }
    
    /**
     * ��ȡ��ǰ��Ψһʵ��
     * 
     * @param context
     * 
     * @return ��ǰ��Ψһʵ��
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
