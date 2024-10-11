package com.jinsungwon99.post.repository.entity.comment;

import com.jinsungwon99.common.domain.PositiveIntegerCounter;
import com.jinsungwon99.common.repository.entity.TimeBaseEntity;
import com.jinsungwon99.post.domain.comment.Comment;
import com.jinsungwon99.post.domain.content.CommentContent;
import com.jinsungwon99.post.repository.entity.post.PostEntity;
import com.jinsungwon99.user.repository.entity.UserEntity;
import jakarta.persistence.ConstraintMode;
import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "community_comment")
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class CommentEntity extends TimeBaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "authorId",foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private UserEntity author;

    @ManyToOne
    @JoinColumn(name = "postId",foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private PostEntity post;

    private String content;
    private Integer likeCount;

    public CommentEntity(Comment comment) {
        this.post = new PostEntity(comment.getPost());
        this.id = comment.getId();
        this.author = new UserEntity(comment.getAuthor());
        this.content = comment.getContent();
        this.likeCount = comment.getLikeCount();
    }

    public Comment toComment(){
        return Comment.builder()
            .post(post.toPost())
            .id(id)
            .author(author.toUser())
            .content(new CommentContent(content))
            .likeCount(new PositiveIntegerCounter(likeCount))
            .build();
    }
}
