package com.example.REST314.dao;

import com.example.REST314.model.User;
import org.springframework.stereotype.Repository;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

    @PersistenceContext
    private EntityManager em;


    @Override
    public List<User> getAllUsers() {
        return em.createQuery("select u from User u", User.class).getResultList();
    }

    @Override
    public User getUser(int id) {
        return em.find(User.class, id);
    }

    @Override
    public void addUser(User user) {
        em.persist(user);
    }

    @Override
    public User findByUsername(String email) {
        TypedQuery<User> q = (em.createQuery("select u from User u join fetch u.roleSet where u.email =:email"
                , User.class));
        q.setParameter("email", email);
        return q.getResultList().stream().findFirst().orElse(null);

    }

    @Override
    public void updateUser(int id, User updatedUser) {
        em.merge(updatedUser);
    }

    @Override
    public void deleteUser(int id) {
        User user = em.find(User.class, id);
        em.remove(user);
    }
}
