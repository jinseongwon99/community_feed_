package com.jinsungwon99.post.domain.content;

public class PostContent extends Content {

    private static final int Min_Post_Length = 5;
    private static final int Max_Post_Length = 500;

    public PostContent(String content) {

        super(content);
    }

    @Override
    protected void checkText(String contentText) {
        if(contentText == null || contentText.isEmpty()){
            throw new IllegalArgumentException();
        }
        if(contentText.length() < Min_Post_Length){
            throw new IllegalArgumentException();
        }
        if (contentText.length() > Max_Post_Length){
            throw new IllegalArgumentException();
        }
    }

}