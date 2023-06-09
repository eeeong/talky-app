package com.example.talky_project;

//글 쓰기 정보 저장
public class WriteInfo {
    private String title; //제목
    private String contents; //내용
    private String publisher; //작성자
    private String photoUrl; //이미지

    public WriteInfo(String title, String contents, String publisher){
        this.title = title;
        this.contents = contents;
        this.publisher = publisher;
    }

    //이미지 추가
    public WriteInfo(String title, String contents, String publisher, String photoUrl){
        this.title = title;
        this.contents = contents;
        this.publisher = publisher;
        this.photoUrl = photoUrl;
    }

    public String getTitle(){
        return this.title;
    }
    public void setTitle(String title){
        this.title = title;
    }
    public String getContents(){
        return this.contents;
    }
    public void setContents(String contents){
        this.contents = contents;
    }
    public String getPublisher(){
        return this.publisher;
    }
    public void setPublisher(String publisher){
        this.publisher = publisher;
    }
    
    //이미지 추가
    public String getPhotoUrl() { return this.photoUrl; }
    public void setPhotoUrl(String photoUrl) { this.photoUrl = photoUrl; }
}