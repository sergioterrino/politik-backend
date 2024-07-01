package com.stb.politik.dto;

public class PostDTO {
    private String text;
    private String imagePath;
    private String videoPath;

    
    public String getText() {
        return text;
    }
    public void setText(String text) {
        this.text = text;
    }
    public String getImagePath() {
        return imagePath;
    }
    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }
    public String getVideoPath() {
        return videoPath;
    }
    public void setVideoPath(String videoPath) {
        this.videoPath = videoPath;
    }

    
}
