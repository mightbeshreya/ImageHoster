package ImageHoster.controller;

import ImageHoster.model.Comment;
import ImageHoster.model.Image;
import ImageHoster.model.User;
import ImageHoster.service.CommentService;
import ImageHoster.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.util.List;

@Controller
public class CommentController {

    @Autowired
    private ImageService imageService;

    @Autowired
    private CommentService commentService;

    /*
    This method is called when the POST request is of the type /image/{imageId}/{imageTitle}/comments.
    Added new feature to add comment to an image.
      This method creates the new Comment from the logged-in user, adds to the image and updates the image
      Lastly, redirects to /images/imageId/imageTitle
     */
    @RequestMapping(value = "/image/{imageId}/{imageTitle}/comments", method = RequestMethod.POST)
    public String createComment(@PathVariable("imageTitle") String imageTitle, @PathVariable("imageId") Integer imageId, @RequestParam(name="comment") String comment  , Model model, HttpSession session) {
        Image image = imageService.getImage(imageId);
        User user = (User)session.getAttribute("loggeduser");
        List<Comment> existingComments = image.getComments();
        Comment newComment = new Comment();
        //Setting all attributes for newComment
        newComment.setText(comment);
        newComment.setUser(user);
        LocalDate localDate = LocalDate.now();
        newComment.setCreatedDate(localDate);
        newComment.setImage(image);

        //Adding the new comment to the existing comments
        existingComments.add(newComment);

        image.setComments(existingComments);

        //Creating the new comment in database
        commentService.createComment(newComment);
        //Updating the image with new comment added to existing comments
        imageService.updateImage(image);

        model.addAttribute("image", image);
        model.addAttribute("tags", image.getTags());

        model.addAttribute("comments", image.getComments());
        return "redirect:/images/"+ image.getId()+"/"+image.getTitle();
    }

}
