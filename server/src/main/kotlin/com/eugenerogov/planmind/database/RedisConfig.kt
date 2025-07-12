package com.eugenerogov.planmind.database

import redis.clients.jedis.JedisPool

object RedisConfig {
    private lateinit var jedisPool: JedisPool

    fun init() {
        val host = System.getenv("REDIS_HOST") ?: "localhost"
        val port = System.getenv("REDIS_PORT")?.toInt() ?: 6379
        jedisPool = JedisPool(host, port)
    }

    fun getPool() = jedisPool

    fun close() {
        jedisPool.close()
    }
}