package lv.javaguru.java3.core.database.posts;

import lv.javaguru.java3.core.database.CRUDOperationDAOImpl;
import lv.javaguru.java3.core.domain.posts.Comment;
import org.springframework.stereotype.Component;

/**
 * Created by svetlana on 31/10/15.
 */
@Component
public class CommentDAOImpl extends CRUDOperationDAOImpl<Comment, Long> implements CommentDAO {


}
