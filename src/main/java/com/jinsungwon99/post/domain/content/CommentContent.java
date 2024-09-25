package com.jinsungwon99.post.domain.content;

public class CommentContent extends Content {

    private static final int Max_Post_Length = 100;

    public CommentContent(String content) {

        super(content);
    }

    @Override
    protected void checkText(String contentText) {
        if(contentText == null || contentText.isEmpty()){
            throw new IllegalArgumentException();
        }
        if (contentText.length() > Max_Post_Length){
            throw new IllegalArgumentException();
        }
    }

}