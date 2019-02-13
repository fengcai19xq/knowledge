package com.sky.knowledge.module.cache.server.dao.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import com.sky.knowledge.module.cache.server.dao.UserDAO;
import com.sky.knowledge.module.cache.shared.domain.User;
public class UserDAOImpl implements UserDAO {

	    @Autowired
	    protected RedisTemplate redisTemplate;

	    public void saveUser(final User user) {
	        redisTemplate.execute(new RedisCallback<Object>() {

	            public Object doInRedis(RedisConnection connection) throws DataAccessException {
	                connection.set(redisTemplate.getStringSerializer().serialize("user.uid." + user.getId()),
	                               redisTemplate.getStringSerializer().serialize(user.getName()));
	                
	                return null;
	            }
	        });
	    }
	    
		public User getUser(final long id) {
	        return (User) redisTemplate.execute(new RedisCallback<User>() {
	            
	            public User doInRedis(RedisConnection connection) throws DataAccessException {
	                byte[] key = redisTemplate.getStringSerializer().serialize("user.uid." + id);
	                if (connection.exists(key)) {
	                    byte[] value = connection.get(key);
	                    String name = (String) redisTemplate.getStringSerializer().deserialize(value);
	                    User user = new User();
	                    user.setName(name);
	                    user.setId(id);
	                    return user;
	                }
	                return null;
	            }
	        });
	    }
}
