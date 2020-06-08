package ImageHoster.service;

import ImageHoster.model.Comment;
import ImageHoster.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//Added new Service to handle requests regarding comments of images
@Service
public class CommentService {
    @Autowired
    private CommentRepository commentRepository;

    //This method calls the Comment Repository to add the comment in the database.
    public Comment createComment(Comment comment) {
        return commentRepository.createComment(comment);
    }
}
