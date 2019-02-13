package com.sky.knowledge.module.cache.server.services;

import redis.clients.jedis.ShardedJedis;

public interface IRedisDataSource {

	public abstract ShardedJedis getRedisClient();
    public void returnResource(ShardedJedis shardedJedis);
    public void returnResource(ShardedJedis shardedJedis,boolean broken);
}
