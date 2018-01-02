package com.x.av.act;

import android.content.Context;
import android.content.Intent;
import android.media.AudioFormat;
import android.media.AudioManager;
import android.media.AudioRecord;
import android.media.AudioTrack;
import android.media.MediaRecorder;
import android.os.Environment;
import android.view.View;

import com.x.av.R;
import com.x.core.base.BaseActivity;
import com.x.core.util.LogUtil;
import com.x.core.util.ToastUtil;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * AudioRecord和AudioTrack API完成音频采集和播放 PCM格式
 * Created by xudafeng on 2018/1/2.
 */

public class AudioRecordAndAudioTrackActivity extends BaseActivity {

    public static Intent createIntent(Context context) {
        return new Intent(context, AudioRecordAndAudioTrackActivity.class);
    }

    @Override
    protected int getContentView() {
        return R.layout.act_audio_record_play;
    }

    public void onRecord(View v) {
        ToastUtil.show(this,"onRecord");
        new Thread(new Runnable() {
            @Override
            public void run() {
                startRecord();
            }
        }).start();
    }

    public void onPause(View v) {
        isRecording = false;
        ToastUtil.show(this,"onPause");
    }

    public void onPlay(View v) {
        playRecord();
        ToastUtil.show(this,"onPlay");
    }

    private File saveFile;
    private boolean isRecording;
    public void startRecord() {
        //采样率
        int frequency = 16000;
        //格式
        int channelConfiguration = AudioFormat.CHANNEL_CONFIGURATION_MONO;
        //16Bit
        int audioEncoding = AudioFormat.ENCODING_PCM_16BIT;

        saveFile = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/x.pcm");

        if(saveFile.exists()) {
            saveFile.delete();
        }
        try {
            saveFile.createNewFile();
        }catch (Exception e) {
            e.printStackTrace();
        }

        try{
            OutputStream os = new FileOutputStream(saveFile);
            BufferedOutputStream bos = new BufferedOutputStream(os);
            DataOutputStream dos = new DataOutputStream(bos);

            int bufferSize = AudioRecord.getMinBufferSize(frequency, channelConfiguration, audioEncoding);
            AudioRecord audioRecord = new AudioRecord(MediaRecorder.AudioSource.MIC, frequency, channelConfiguration, audioEncoding, bufferSize);

            short[] buffer = new short[bufferSize];
            audioRecord.startRecording();
            isRecording = true;
            while (isRecording) {
                int bufferReadResult = audioRecord.read(buffer, 0, bufferSize);
                for (int i = 0; i < bufferReadResult; i++) {
                    dos.writeShort(buffer[i]);
                }
            }
            audioRecord.stop();
            dos.close();
        }catch (Exception e) {
            LogUtil.e(tag, "录音失败");
        }
    }

    private void playRecord() {
        if(saveFile == null)
            return;
        int musicLength = (int) (saveFile.length() / 2);
        short[] music = new short[musicLength];

        try {
            InputStream is = new FileInputStream(saveFile);
            BufferedInputStream bis = new BufferedInputStream(is);
            DataInputStream dis = new DataInputStream(bis);

            int i = 0;
            while (dis.available() > 0) {
                music[i] = dis.readShort();
                i++;
            }
            dis.close();

            AudioTrack audioTrack = new AudioTrack(AudioManager.STREAM_MUSIC, 16000, AudioFormat.CHANNEL_CONFIGURATION_MONO,
                    AudioFormat.ENCODING_PCM_16BIT, musicLength * 2, AudioTrack.MODE_STREAM);
            audioTrack.play();
            audioTrack.write(music, 0, musicLength);
            audioTrack.stop();
        }catch (Exception e) {
            LogUtil.e(tag, "播放失败");
        }
    }

}
