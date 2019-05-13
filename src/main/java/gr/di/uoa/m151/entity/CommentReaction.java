package gr.di.uoa.m151.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("COMMENT")
public class CommentReaction extends UserPostReaction {

    public CommentReaction(){

    }

    public CommentReaction(Post post, AppUser appUser){
        super(post, appUser);
    }

    @Basic
    @Column(name = "comment_title", nullable = true, length = -1)
    private String commentTitle;

    @Basic
    @Column(name = "comment_text", nullable = true, length = -1)
    private String commentText;

    public String getCommentTitle() {
        return commentTitle;
    }

    public void setCommentTitle(String commentTitle) {
        this.commentTitle = commentTitle;
    }

    public String getCommentText() {
        return commentText;
    }

    public void setCommentText(String commentText) {
        this.commentText = commentText;
    }
}
