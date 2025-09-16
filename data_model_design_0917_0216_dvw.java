// 代码生成时间: 2025-09-17 02:16:29
package com.example.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

// 数据模型设计
// 使用实体类表示数据库中的表
@Entity
@Table(name = "user")
public class User implements Serializable {
    // 使用@Id注解标记为主键
    @Id
    @Column(name = "id")
    private Long id;

    // 使用@Column注解定义其他字段
    @Column(name = "username")
    private String username;

    @Column(name = "email")
    private String email;

    // 构造函数、getter和setter省略...
}

package com.example.mapper;

import com.example.model.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

// MyBatis Mapper接口
public interface UserMapper {
    // 查询用户信息
    @Select("SELECT * FROM user WHERE id = #{id}")
    User selectUserById(@Param("id") Long id);
    // 其他CRUD操作省略...
}

package com.example.service;

import com.example.model.User;
import com.example.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

// 服务层，处理业务逻辑
@Service
public class UserService {
    // 自动注入Mapper接口
    @Autowired
    private UserMapper userMapper;

    // 根据ID查询用户信息
    @Transactional(readOnly = true)
    public User getUserById(Long id) {
        try {
            return userMapper.selectUserById(id);
        } catch (Exception e) {
            // 错误处理
            e.printStackTrace();
            throw new RuntimeException("Error occurred while fetching user details.");
        }
    }
    // 其他业务逻辑方法省略...
}