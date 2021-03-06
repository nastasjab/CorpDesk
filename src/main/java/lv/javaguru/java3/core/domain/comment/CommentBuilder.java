package lv.javaguru.java3.core.domain.comment;

import java.sql.Date;

/**
 * Created by svetlana on 31/10/15.
 */
public class CommentBuilder {

    private Long id;
    private Long postId;
    private Long userId;
    private String text;
    private Date postedDate;
    private Date modifiedDate;

    private CommentBuilder() {

    }

    public static CommentBuilder createComment() {
        return new CommentBuilder();
    }

    public Comment build() {
        Comment comment = new Comment();
        comment.setId(id);
        comment.setPostId(postId);
        comment.setUserId(userId);
        comment.setText(text);
        comment.setPostedDate(postedDate);
        comment.setModifiedDate(modifiedDate);
        return comment;
    }

    public CommentBuilder withId(Long id) {
        this.id = id;
        return this;
    }

    public CommentBuilder withPostId(Long postId) {
        this.postId = postId;
        return this;
    }

    public CommentBuilder withUserId(Long userId) {
        this.userId = userId;
        return this;
    }

    public CommentBuilder withText(String text) {
        this.text = text;
        return this;
    }

    public CommentBuilder withPostedDate(Date postedDate) {
        this.postedDate = postedDate;
        return this;
    }

    public CommentBuilder withModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
        return this;
    }
}
