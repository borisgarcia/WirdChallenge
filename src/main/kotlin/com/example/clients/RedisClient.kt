package com.example.clients
import com.example.config.Config
import redis.clients.jedis.Jedis

class RedisClient {
    private val jedis: Jedis = Jedis(Config.instance.redisHost, Config.instance.redisPort)

    fun connect() {
        jedis.connect()
    }

    fun set(key: String, value: String){
        jedis.set(key, value);
    }

    fun get(key: String): String{
        return jedis.get(key)
    }

    fun exists(key: String): Boolean{
        return jedis.exists(key)
    }
}