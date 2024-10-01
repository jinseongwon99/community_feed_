package com.jinsungwon99.post.domain;

import com.jinsungwon99.common.domain.PositiveIntegerCounter;
import com.jinsungwon99.post.domain.common.DatetimeInfo;
import com.jinsungwon99.post.domain.content.PostContent;
import com.jinsungwon99.post.domain.content.PostPublicatuionState;
import com.jinsungwon99.user.domain.User;

public class Post {

    private final Long id;
    private final User author;
    private final PostContent content;
    private final PositiveIntegerCounter likeCount;
    private PostPublicatuionState state;

    public Post(Long id,User author, PostContent content) {


        if(author == null){
            throw new IllegalArgumentException();
        }

        this.id = id;
        this.author = author;
        this.content = content;
        this.likeCount = new PositiveIntegerCounter();
        this.state = PostPublicatuionState.PUBLIC;

    }

    public void like(User user){
        if(this.author.equals(user)){
            throw new IllegalArgumentException();
        }
        likeCount.increase();
    }
    public void unlike(){
        likeCount.decrease();
    }
    public void updatePost(User user,String updateContent,PostPublicatuionState state){
        if(!this.author.equals(user)){
            throw new IllegalArgumentException();
        }
        this.state = state;
        this.content.updateContent(updateContent);
    }

    public int getLikeCount() {
        return likeCount.getCount();
    }

    public String getContent() {
        return content.getContentText();
    }

    public DatetimeInfo getDatetimeInfo() {
        return content.getDatetimeInfo();
    }


    public PostPublicatuionState getState() {
        return state;
    }
}
