package net.liven.bot.Listeners;

import redis.clients.jedis.JedisPubSub;

public class JedisListener extends JedisPubSub {

    @Override
    public void onMessage(String channel, String message) {
        System.out.println(message);
    }

    public void onSubscribe(String channel, int subscribedChannels) {
    }

    public void onUnsubscribe(String channel, int subscribedChannels) {
    }

    public void onPSubscribe(String pattern, int subscribedChannels) {
    }

    public void onPUnsubscribe(String pattern, int subscribedChannels) {
    }

    public void onPMessage(String pattern, String channel, String message) {
    }
}
