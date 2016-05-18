package android.abc.nl.abccc.service;

import android.abc.nl.abccc.config.MongoConfig;
import android.abc.nl.abccc.model.Notification;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

public class NotificationService  extends android.app.Service {
    private ApplicationContext applicationContext;
    private MongoOperations mongoOperations;

    public NotificationService() {
        applicationContext = new AnnotationConfigApplicationContext(MongoConfig.class);
        mongoOperations = (MongoOperations) applicationContext.getBean("mongoTemplate");
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    /*
     * Get all notifications
     */
    public List<Notification> getAll() {
        return mongoOperations.findAll(Notification.class);
    }

    /*
     * Save new user to the database.
     */
    public void save(Notification notification) {
        mongoOperations.save(notification);
    }

    /*
     * Get user by id.
     */
    public Notification findById(String id) {
        return mongoOperations.findOne(Query.query(Criteria.where("_id").is(id)), Notification.class);
    }

    public List<Notification> findUnseenContactNotifications(String receiverId) {
        Criteria criteria = new Criteria();
        criteria.andOperator(Criteria.where("receiverUserId").is(receiverId), Criteria.where("seen").is(false));
        Query query = new Query(criteria);
        return mongoOperations.find(query, Notification.class);
    }

    public List<Notification> findAllNotifications(String receiverId) {
        Criteria criteria = new Criteria();
        criteria.andOperator(Criteria.where("receiverUserId").is(receiverId));
        Query query = new Query(criteria);
        return mongoOperations.find(query, Notification.class);
    }
}