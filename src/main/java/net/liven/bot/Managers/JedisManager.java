package net.liven.bot.Managers;

import net.liven.bot.Listeners.JedisListener;
import redis.clients.jedis.Jedis;

public class JedisManager {
    JedisListener listener;
    Jedis jedis;
    String channel;

    public JedisManager(Jedis jedis, String channel){
        JedisListener listener = new JedisListener();
        this.listener = listener;
        this.jedis = jedis;
        this.channel = channel;
    }
//    public void broadcastMessage(String message){
//
//        jedis.publish(channel, message);
//
//    }

    public JedisListener getListener(){
        JedisListener listener = this.listener;
        return listener;
    }

    public void broadcastMessage(String message){

        jedis.publish("test", message);

    }
}
