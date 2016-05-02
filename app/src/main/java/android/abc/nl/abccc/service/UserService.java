package android.abc.nl.abccc.service;

import android.abc.nl.abccc.config.MongoConfig;
import android.abc.nl.abccc.model.User;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class UserService extends android.app.Service {

    private ApplicationContext applicationContext;
    private MongoOperations mongoOperations;

    public UserService() {
        applicationContext = new AnnotationConfigApplicationContext(MongoConfig.class);
        mongoOperations = (MongoOperations) applicationContext.getBean("mongoTemplate");
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    /*
     * Save new user to the database.
     */
    public void save(User user) {
        mongoOperations.save(user);
    }

    /*
     * Get user by id.
     */
    public User findById(String id) {
        return mongoOperations.findOne(Query.query(Criteria.where("_id").is(id)), User.class);
    }

    /*
     * Get user by e-mail.
     */
    public User findByEmail(String email) {
        return mongoOperations.findOne(Query.query(Criteria.where("email").is(email)), User.class);
    }

    /*
     * Get users by firstname/lastname
     */
    public List<User> findByFirstnameLastname(String searchQuery, int skip, int limit) {
        if (!searchQuery.contains(" ")) {
            Criteria criteria = new Criteria();
            criteria.orOperator(Criteria.where("firstname").regex(searchQuery, "i"),
                    Criteria.where("lastname").regex(searchQuery, "i")).andOperator(Criteria.where("enabled").is(true));
            Query q = new Query().addCriteria(criteria).limit(limit).skip(skip);
            return mongoOperations.find(q, User.class);
        }

        String queryx[] = searchQuery.split(" ");
        ArrayList<Criteria> cr = new ArrayList<Criteria>();
        for (String q : queryx) {
            Criteria fCriteria = Criteria.where("firstname").regex(q, "i");
            cr.add(fCriteria);
            Criteria lCriteria = Criteria.where("lastname").regex(q, "i");
            cr.add(lCriteria);
        }

        Criteria c = cr.remove(0);
        Criteria[] orArray = cr.toArray(new Criteria[cr.size()]);
        c.orOperator(orArray).andOperator(Criteria.where("enabled").is(true));
        Query q = new Query().limit(limit).skip(skip);
        q.addCriteria(c);
        return mongoOperations.find(q, User.class);
    }

    /*
     * Get all users from the collection.
     */
    public List<User> getAll() {
        return mongoOperations.findAll(User.class);
    }

    public long getAmountOfUsers() {
        Query q = new Query();
        return mongoOperations.count(q, User.class);
    }

    /*
     * Delete user from the collection.
     */
    public void delete(User user) {
        mongoOperations.remove(user);
    }

    /*
     * Returns the logged in user (updated from database)
     */
    public User getLoggedInUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (!(auth instanceof AnonymousAuthenticationToken)) {
            User user = (User) SecurityContextHolder.getContext().getAuthentication()
                    .getPrincipal();
            return findById(user.getId());
        }
        return null;
    }

    /*
     * Retrieve all (accepted) contacts from user
     */
    public List<User> getAllContacts(User user) {
        List<User> contacts = new ArrayList<User>();
        for (String contactId : user.getContacts()) {
            Criteria criteria = new Criteria();
            criteria.orOperator(Criteria.where("_id").is(contactId)
                    .andOperator(Criteria.where("contacts").in(user.getId()))).andOperator(Criteria.where("enabled").is(true));
            Query query = new Query(criteria);
            User c = mongoOperations.findOne(query, User.class);
            if (c != null) {
                contacts.add(c);
            }
        }
        return contacts;
    }

    public int getAmountOfContacts(User user) {
        List<String> contacts = new ArrayList<String>();
        for (String contactId : user.getContacts()) {
            Criteria criteria = new Criteria();
            criteria.orOperator(Criteria.where("_id").is(contactId)
                    .andOperator(Criteria.where("contacts").in(user.getId()))).andOperator(Criteria.where("enabled").is(true));;
            Query query = new Query(criteria);
            User c = mongoOperations.findOne(query, User.class);
            if (c != null) {
                contacts.add(c.getId());
            }
        }
        return contacts.size();
    }

    /*
     * Retrieve all (accepted) contacts from user
     */
    public List<String> getIdsOfAllContacts(User user) {
        List<String> contacts = new ArrayList<String>();
        for (String contactId : user.getContacts()) {
            Criteria criteria = new Criteria();
            criteria.orOperator(Criteria.where("_id").is(contactId)
                    .andOperator(Criteria.where("contacts").in(user.getId()))).andOperator(Criteria.where("enabled").is(true));;
            Query query = new Query(criteria);
            User c = mongoOperations.findOne(query, User.class);
            if (c != null) {
                contacts.add(c.getId());
            }
        }
        return contacts;
    }

	/*
	 * Search contact by firstname/lastname
	 */

    public List<User> getContactsByFirstnameLastname(User user, String searchQuery) {
        List<User> contacts = new ArrayList<User>();
        for (String contactId : user.getContacts()) {
            Criteria criteria = new Criteria();
            criteria.orOperator(Criteria
                    .where("_id")
                    .is(contactId)
                    .andOperator(
                            Criteria.where("contacts")
                                    .in(user.getId())
                                    .orOperator(Criteria.where("firstname").regex(searchQuery, "i"),
                                            Criteria.where("lastname").regex(searchQuery, "i")))).andOperator(Criteria.where("enabled").is(true));;
            Query query = new Query(criteria);
            User c = mongoOperations.findOne(query, User.class);
            if (c != null) {
                contacts.add(c);
            }
        }
        return contacts;
    }

    public List<User> getUsersBySubdeptCountry(String subdeptId, String country) {
        Query query = new Query(Criteria.where("subdepartment").is(subdeptId)
                .and("addresses.country").is(country));
        return mongoOperations.find(query, User.class);
    }

    public List<User> getUsersInSubdepartement(String subdeptId) {
        Query query = new Query(Criteria.where("subdepartment").is(subdeptId));
        return mongoOperations.find(query, User.class);
    }
}

