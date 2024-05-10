package com.instrument.music;


import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class SpeechRecognition {
    
    public static void main(String[] args) throws Exception {
        
    }
    
    // 读取音频文件
    public static AudioInputStream getAudioInputStream(String filename) throws IOException, UnsupportedAudioFileException {
        File file = new File(filename);
        InputStream inputStream = new FileInputStream(file);
        return AudioSystem.getAudioInputStream(inputStream);
    }
    
    // 保存音频文件
    public static void saveAudioFile(AudioInputStream audioInputStream, String filename, AudioFileFormat.Type fileType) throws IOException {
        File file = new File(filename);
        AudioSystem.write(audioInputStream, fileType, file);
    }
}