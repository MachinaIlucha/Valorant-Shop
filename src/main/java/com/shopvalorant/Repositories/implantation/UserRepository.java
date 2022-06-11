package com.shopvalorant.Repositories.implantation;

import com.shopvalorant.Exception.InternalServerError;
import com.shopvalorant.Exception.UserNotFoundException;
import com.shopvalorant.Model.User;
import com.shopvalorant.Repositories.GenericRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.Date;

@Repository
@Transactional
public class UserRepository implements GenericRepository<User> {

    private final String SQL_findByEmailAndPassword = "From User user where user.email = :email and user.password = :password";

    @PersistenceContext
    private EntityManager entityManager;

    public User findByEmailAndPassword(String email, String password){
        Query query = entityManager.createQuery(SQL_findByEmailAndPassword);
        query.setParameter("email", email);
        query.setParameter("password", password);

        User user;

        try {
            user = (User) query.getSingleResult();
        } catch (NoResultException noResultException) {
            throw new UserNotFoundException("User not found");
        } catch (Exception e){
            e.printStackTrace();
            throw new InternalServerError("Can't register user, 'couse something went wrong in db");
        }

        return user;
    }


    @Override
    public User save(User user){
        user.setDateRegistered(new Date());
        user.setDateLastActive(new Date());

        try {
            entityManager.persist(user);
        } catch (Exception e){
            e.printStackTrace();
            throw new InternalServerError("Can't register user, 'couse something went wrong in db");
        }

        return user;
    }

    @Override
    public User read(Long id){
        User user;

        try {
            user = entityManager.find(User.class, id);
        } catch (NoResultException noResultException) {
            throw new UserNotFoundException("User not found");
        } catch (Exception e){
            e.printStackTrace();
            throw new InternalServerError("Can't read user, 'couse something went wrong in db");
        }

        return user;
    }

    @Override
    public User update(User t) {
        return null;
    }

    @Override
    public void delete(User t) {

    }
}
