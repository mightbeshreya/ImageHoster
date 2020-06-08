package ImageHoster.repository;

import ImageHoster.model.Comment;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceUnit;

//This repository interacts with comments table
@Repository
public class CommentRepository {
    @PersistenceUnit(unitName = "imageHoster")
    private EntityManagerFactory emf;
    //This method adds the information of new comment in the database.
    // If the transaction is successful, the transaction is committed , else, rolledback
    public Comment createComment(Comment newcomment) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();

        try {
            transaction.begin();
            em.persist(newcomment);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
        }
        return newcomment;
    }
}
