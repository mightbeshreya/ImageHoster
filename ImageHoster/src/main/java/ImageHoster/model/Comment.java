package ImageHoster.model;

import javax.persistence.*;
import java.time.LocalDate;

//Creating a JPA Entity with table name as 'comments'
@Entity
@Table(name = "comments")
public class Comment {
    //id is the Primary key and automatically generated. So, @Id and @GeneratedValue(strategy = GenerationType.AUTO) is added
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    //Column 'id' will be created in comments table
    @Column(name = "id")
    private Integer id;

    //Column 'text' will be created in comments table
    @Column(name = "text")
    private String text;

    //Column 'created_date' will be created in comments table
    @Column(name = "created_date")
    private LocalDate createdDate;

    //ManyToOne relationship for the column user_id. Means that one user can have many comments but one comment can be assigned to only one user
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    //ManyToOne relationship for the column image_id. Means that one image can have many comments but one comment can be assigned to only one image
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "image_id")
    private Image image;

    //Getters and Setter Methods
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }
}
