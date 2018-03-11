package com.example.helloworld;

import com.example.helloworld.SpeechSynthesizeUtils.SynthesizeListener;
import com.example.helloworld.TulingUtils.RequestListener;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import pl.droidsonroids.gif.GifImageView;

public class third extends Activity{


	private TextView mTvChart = null;
	private TextView mTvDis = null;
	private TextView mTvState = null;
	private GifImageView mGifImageView = null; 
	private OnlySpeak mOnlySpeak = null;
	private RequestListener mRequestListener = null;
	private SpeechSynthesizeUtils mSpeechSynthesizeUtils = null;
	private SpeechRecognizeUtils mSpeechRecognizeUtils = null;
	private SynthesizeListener mSynthesizeListener = null;
	private void refuseVoicePermissionsDialog()
	{
		//当拒绝了授权后，为提升用户体验，可以以弹窗的方式引导用户到设置中去进行设置  
			new AlertDialog.Builder(third.this)
						.setMessage("录音权限被禁止，需要开启权限才能使用此功能，请在授权管理或应用程序管理打开，否则无法正常使用")
						.setPositiveButton("设置", new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialog, int which) {
								//引导用户到设置中去进行设置
								Intent intent = new Intent();
								intent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
								intent.setData(Uri.fromParts("package", getPackageName(), null));
								startActivity(intent);
								
							}
							})
						.setNegativeButton("取消", null)
						.create()
						.show();
	}
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.third);
		
	}
//	@Override
//	protected void onCreate(Bundle savedInstanceState) {
//		super.onCreate(savedInstanceState);
//		NoHttp.initialize(this);
//		setContentView(R.layout.activity_main);
////		MediaRecorder mRecorders = new MediaRecorder();
////		mRecorders.setAudioSource(MediaRecorder.AudioSource.MIC);
//		//refuseVoicePermissionsDialog();
//		if(!CheckRecord.isHasPermission(this))
//		{
//			refuseVoicePermissionsDialog();
//		}
//		mTvChart = (TextView)findViewById(R.id.answer);
//		mTvDis = (TextView)findViewById(R.id.disListen);
//		mTvState = (TextView)findViewById(R.id.state);
//		mGifImageView = (GifImageView)findViewById(R.id.giv_robot);
//		mOnlySpeak = OnlySpeak.getInstance(this);
//		try {
//			mTvState.setText("小柚正向主人飞奔而来......");
////			mOnlySpeak.speek("小柚正向主人飞奔而来......");
//			GifDrawable  mGifDrawable = new GifDrawable(getResources(), R.drawable.loading);
//			mGifImageView.setImageDrawable(mGifDrawable);
//			mGifDrawable.setLoopCount(1);
//			mGifDrawable.addAnimationListener(new AnimationListener()
//			{
//				
//				@Override
//				public void onAnimationCompleted(int loopNumber) {
//					GifDrawable mGifDrawable;
//					try {
//						mTvState.setText("小柚正在静静聆听......");
////						mOnlySpeak.speek("小柚正在静静聆听......");
//						mGifDrawable = new GifDrawable(getResources(), R.drawable.ready);
//						mGifImageView.setImageDrawable(mGifDrawable);
//					} catch (NotFoundException e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					} catch (IOException e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					}
//					
//				}
//			});
//		} catch (NotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		//mOnlySpeak.release();
//		//mSpeechRecognizeUtils.start();
//		mSpeechRecognizeUtils = SpeechRecognizeUtils.getInstance(this, new RecogniseListener() {
//			
//			@Override
//			public void switched(int type) {
//				
//				
//			}
//			
//			@Override
//			public void succeed(String text) {
//				mTvDis.setText(text);
//				TulingUtils.requestChart(text, mRequestListener);
//			}
//			
//			@Override
//			public void recognised(String text) {
//				mTvDis.setText(text);
//			}
//			
//			@Override
//			public void ready() {
//				
//			}
//			
//			@Override
//			public void failed(int code) {
//				mSpeechRecognizeUtils.start();
//			}
//		});
//		mSynthesizeListener = new SynthesizeListener() {
//
//			@Override
//			public void succeed() {
//				runOnUiThread(new Runnable()
//				{
//					public void run()
//					{
//						mSpeechRecognizeUtils.start();
//					}
//				});
//			}
//
//			@Override
//			public void speech() {
//				// TODO Auto-generated method stub
//				//mSpeechRecognizeUtils.stop();
//			}
//
//			@Override
//			public void failed(int code) {
//				runOnUiThread(new Runnable()
//				{
//					public void run()
//					{
//						mSpeechRecognizeUtils.start();
//					}
//				});
//
//			}
//		};
//		mSpeechSynthesizeUtils = SpeechSynthesizeUtils.getInstance(this, mSynthesizeListener);
//		//mSpeechSynthesizeUtils.speek("你好");
//		mRequestListener = new RequestListener()
//		{
//			public void succeed(final String chart)
//			{
//				runOnUiThread(new Runnable()
//				{
//					public void run()
//					{
//						mTvChart.setText(chart);
//						mSpeechSynthesizeUtils.speek(chart);
//						//SpeechSynthesizeUtils.getInstance(this, recogniseListener);
//					}
//				});
//			}
//
//			@Override
//			public void failed(final int code) {
//				runOnUiThread(new Runnable()
//				{
//					public void run()
//					{
//						mSpeechRecognizeUtils.start();
//					}
//				}
//						);
//			}
//		};
//		mSpeechRecognizeUtils.start();
//		
//	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
}
