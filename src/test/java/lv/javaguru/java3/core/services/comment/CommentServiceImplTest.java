package lv.javaguru.java3.core.services.comment;

import lv.javaguru.java3.core.database.post.CommentDAO;
import lv.javaguru.java3.core.domain.post.Comment;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.sql.Date;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

/**
 * Created by svetlana on 10/12/15.
 */
@RunWith(MockitoJUnitRunner.class)
public class CommentServiceImplTest {

    @Mock
    private CommentValidator commentValidator;

    @Mock
    private CommentDAO commentDAO;

    @InjectMocks
    private CommentService commentService = new CommentServiceImpl();

    private static final Long COMMENTID = 1L;
    private static final Long POSTID = 1L;
    private static final Long USERID = 1L;
    private static final String TEXT = "Text";
    private static final Date POSTEDDATE = new Date(System.currentTimeMillis());
    private static final Long NEWPOSTID = 2L;
    private static final Long NEWUSERID = 2L;
    private static final String NEWTEXT = "NewText";
    private static final Date NEWPOSTEDDATE = new Date(System.currentTimeMillis() + 2);

    @Test
    public void getShouldReturnComment() {
        when(commentDAO.getRequired(COMMENTID)).thenReturn(createCommentForTest());
        Comment comment = commentService.get(COMMENTID);

        assertThat(comment.getPostId(), is(POSTID));
        assertThat(comment.getUserId(), is(USERID));
        assertThat(comment.getText(), is(TEXT));
        assertThat(comment.getPostedDate(), is(POSTEDDATE));
    }

    @Test
    public void deleteShouldInvokeGetRequired() {
        when(commentDAO.getRequired(COMMENTID)).thenReturn(createCommentForTest());
        commentService.delete(COMMENTID);
        verify(commentDAO).getRequired(COMMENTID);
    }

    @Test
    public void deleteShouldInvokeDeleteFromDAO() {
        Comment comment = createCommentForTest();
        when(commentDAO.getRequired(COMMENTID)).thenReturn(comment);
        commentService.delete(COMMENTID);
        verify(commentDAO).delete(comment);
    }

    @Test
    public void updateShouldInvokeValidator() {
        when(commentDAO.getRequired(COMMENTID)).thenReturn(createCommentForTest());
        commentService.update(COMMENTID, NEWPOSTID, NEWUSERID, NEWTEXT, NEWPOSTEDDATE);
        verify(commentValidator).validate(NEWPOSTID, NEWUSERID, NEWTEXT, NEWPOSTEDDATE);
    }

    @Test (expected = IllegalArgumentException.class)
    public void updateShouldFailIfValidateFail() {
        when(commentDAO.getRequired(POSTID)).thenReturn(createCommentForTest());
        doThrow(new IllegalArgumentException())
                .when(commentValidator).validate(NEWPOSTID, NEWUSERID, NEWTEXT, NEWPOSTEDDATE);
        commentService.update(COMMENTID, NEWPOSTID, NEWUSERID, NEWTEXT, NEWPOSTEDDATE);
    }

    @Test
    public void updateShouldPersistUpdatedCommentAfterValidation() {
        Comment comment = createCommentForTest();
        when(commentDAO.getRequired(COMMENTID)).thenReturn(comment);
        commentService.update(COMMENTID, NEWPOSTID, NEWPOSTID, NEWTEXT, NEWPOSTEDDATE);

        InOrder inOrder = inOrder(commentValidator, commentDAO);
        inOrder.verify(commentValidator).validate(NEWPOSTID, NEWUSERID, NEWTEXT, NEWPOSTEDDATE);
        inOrder.verify(commentDAO).getRequired(COMMENTID);
    }

    @Test
    public void updateShouldReturnUpdatedComment() {
        when(commentDAO.getRequired(COMMENTID)).thenReturn(createCommentForTest());
        Comment comment = commentService.update(COMMENTID, NEWPOSTID, NEWUSERID, NEWTEXT, NEWPOSTEDDATE);

        assertThat(comment.getPostId(), is(NEWPOSTID));
        assertThat(comment.getUserId(), is(NEWUSERID));
        assertThat(comment.getText(), is(NEWTEXT));
        assertThat(comment.getPostedDate(), is(NEWPOSTEDDATE));
    }

    private Comment createCommentForTest() {
        Comment comment = new Comment();
        comment.setPostId(POSTID);
        comment.setUserId(USERID);
        comment.setText(TEXT);
        comment.setPostedDate(POSTEDDATE);
        return comment;
    }

}