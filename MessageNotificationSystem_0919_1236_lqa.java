// 代码生成时间: 2025-09-19 12:36:01
package com.example.notification;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.session.TransactionIsolationLevel;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.transaction.TransactionFactory;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;
import org.apache.ibatis.datasource.pooled.PooledDataSource;
import org.apache.ibatis.mapping.Mapper;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.List;

public class MessageNotificationSystem {
    
    /**
     * Method to send message notifications to users.
     * 
     * @param message The message to be sent.
     * @param userIds The list of user IDs to whom the message will be sent.
     * @return The result of the operation.
     */
    public boolean sendMessage(String message, List<Integer> userIds) {
        boolean result = false;
        try {
            // Configure MyBatis
            String resource = "mybatis-config.xml";
            InputStream inputStream = MessageNotificationSystem.class.getClassLoader().getResourceAsStream(resource);
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
            
            try (SqlSession session = sqlSessionFactory.openSession(ExecutorType.BATCH, TransactionIsolationLevel.READ_COMMITTED)) {
                // Retrieve the Mapper
                NotificationMapper mapper = session.getMapper(NotificationMapper.class);
                
                // Send message to each user
                for (Integer userId : userIds) {
                    mapper.sendMessage(userId, message);
                }
                
                // Commit the transaction
                session.commit();
                result = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}

/**
 * NotificationMapper.java
 * 
 * MyBatis Mapper interface for notification operations.
 */
package com.example.notification;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface NotificationMapper {
    
    /**
     * Method to insert a new notification into the database.
     * 
     * @param userId The ID of the user to whom the notification will be sent.
     * @param message The message to be sent.
     */
    @Insert("INSERT INTO notifications (user_id, message) VALUES (#{userId}, #{message})")
    void sendMessage(@Param("userId") int userId, @Param("message") String message);
}