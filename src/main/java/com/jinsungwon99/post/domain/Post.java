package com.jinsungwon99.post.domain;

import com.jinsungwon99.common.domain.PositiveIntegerCounter;
import com.jinsungwon99.post.domain.common.DatetimeInfo;
import com.jinsungwon99.post.domain.content.PostContent;
import com.jinsungwon99.post.domain.content.PostPublicationState;
import com.jinsungwon99.user.domain.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class Post {

    private final Long id;
    private final User author;
    private final PostContent content;
    private String contentImageUrl;
    private final PositiveIntegerCounter likeCount;
    private PostPublicationState state;

    //정적 생성자 1
    public static Post createPost(Long id,User author, String content,String contentImageUrl, PostPublicationState state){
        return new Post(id,author,new PostContent(content),contentImageUrl,state);
    }

    //정적 생성자 2
    public static Post createDefaultPost(Long id,User author, String content,String contentImageUrl){
        return new Post(id,author,new PostContent(content),contentImageUrl ,PostPublicationState.PUBLIC);
    }

    public Post(Long id,User author, PostContent content,String contentImageUrl) {


        if(author == null){
            throw new IllegalArgumentException();
        }

        this.id = id;
        this.author = author;
        this.content = content;
        this.contentImageUrl = contentImageUrl;
        this.likeCount = new PositiveIntegerCounter();
        this.state = PostPublicationState.PUBLIC;

    }

    public Post(Long id,User author, PostContent content,String contentImageUrl, PostPublicationState state) {


        if(author == null){
            throw new IllegalArgumentException();
        }

        this.id = id;
        this.author = author;
        this.content = content;
        this.contentImageUrl = contentImageUrl;
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
    public void updatePost(User user, String updateContent, PostPublicationState state, String imageUrl) {
        if (!this.author.equals(user)) {
            throw new IllegalArgumentException("Only the author can update the post.");
        }
        this.state = state;
        this.content.updateContent(updateContent);

        // 이미지 URL이 변경된 경우, 이미지 URL을 업데이트
        if (imageUrl != null && !imageUrl.isEmpty()) {
            System.out.println("Updating image URL: " + imageUrl);
            this.contentImageUrl = imageUrl;
        }
    }

    public void deletePost(User user) {
        if (!this.author.equals(user)) {
            throw new IllegalArgumentException("Only the author can delete the post.");
        }
        // 삭제 작업 수행. 실제 데이터베이스 연동은 Service 또는 Repository에서 처리합니다.
        System.out.println("Post with ID: " + this.id + " has been deleted.");
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


    public Long getAuthorId() {
        return author.getId();
    }
    public PostContent getContentObject(){
        return content;
    }

    public boolean isPublic() {
        return PostPublicationState.PUBLIC.equals(this.state);
    }
}
