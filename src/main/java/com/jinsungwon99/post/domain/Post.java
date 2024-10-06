package com.jinsungwon99.post.domain;

import com.jinsungwon99.common.domain.PositiveIntegerCounter;
import com.jinsungwon99.post.domain.common.DatetimeInfo;
import com.jinsungwon99.post.domain.content.Content;
import com.jinsungwon99.post.domain.content.PostContent;
import com.jinsungwon99.post.domain.content.PostPublicationState;
import com.jinsungwon99.user.domain.User;

public class Post {

    private final Long id;
    private final User author;
    private final PostContent content;
    private final PositiveIntegerCounter likeCount;
    private PostPublicationState state;

    //정적 생성자 1
    public static Post createPost(Long id,User author, String content,PostPublicationState state){
        return new Post(id,author,new PostContent(content),state);
    }

    //정적 생성자 2
    public static Post createDefaultPost(Long id,User author, String content){
        return new Post(id,author,new PostContent(content),PostPublicationState.PUBLIC);
    }

    public Post(Long id,User author, PostContent content) {


        if(author == null){
            throw new IllegalArgumentException();
        }

        this.id = id;
        this.author = author;
        this.content = content;
        this.likeCount = new PositiveIntegerCounter();
        this.state = PostPublicationState.PUBLIC;

    }

    public Post(Long id,User author, PostContent content, PostPublicationState state) {


        if(author == null){
            throw new IllegalArgumentException();
        }

        this.id = id;
        this.author = author;
        this.content = content;
        this.likeCount = new PositiveIntegerCounter();
        this.state = state;

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
    public void updatePost(User user,String updateContent, PostPublicationState state){
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


    public PostPublicationState getState() {
        return state;
    }

    public Long getId() {
        return id;
    }

    public User getAuthor() {
        return author;
    }

    public Long getAuthorId() {
        return author.getId();
    }
    public PostContent getContentObject(){
        return content;
    }

}
