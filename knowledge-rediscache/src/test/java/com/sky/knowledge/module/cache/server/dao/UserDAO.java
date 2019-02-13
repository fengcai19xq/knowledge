package com.sky.knowledge.module.cache.server.dao;

import com.sky.knowledge.module.cache.shared.domain.User;


public interface UserDAO {

	public User getUser(final long id);
	public void saveUser(final User u);
}
