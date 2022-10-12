package net.liven.bot;

import javax.security.auth.login.LoginException;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.liven.bot.Listeners.Tasks;
import net.liven.bot.Managers.JedisManager;
import net.liven.bot.Utils.DbFunctions;
import net.liven.bot.commands.BotCommands;
import redis.clients.jedis.Jedis;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DiscordBot {

    public static String TableName = "userdata";
    public static String Support_ChannelID = "994551461507772557";
    public static String Inactive_Commission_Channel_ID = "917019102281883668";
    public static String Active_Commission_Channel_ID = "917019131843317771";
    public static String Completed_Commission_Channel_ID = "917019102281883668";
    public static String Application_Channel_ID = "917017063866900491";
    public static String Denied_Application_Channel_ID = "917017063866900491";
    public static String Accepted_Application_Channel_ID = "917017063866900491";
    public static HashMap<String, String> HM_Running_applications = new HashMap<>();
    public static List<String> LS_Running_applications = new ArrayList<>();


    public static void main(String[] args) throws LoginException {

        DbFunctions db = new DbFunctions();
        JDA jda = JDABuilder.createDefault("****************************", GatewayIntent.GUILD_MESSAGES, GatewayIntent.GUILD_MEMBERS)
                .setActivity(Activity.playing("learning stuff"))
                .addEventListeners(new BotCommands(db ,conn, jedis, jm.getListener(), jm))
                .addEventListeners(new Tasks())
                .build();

    }



}
