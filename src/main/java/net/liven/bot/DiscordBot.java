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
//    static Jedis jedis;



    public static void main(String[] args) throws LoginException {

        DbFunctions db = new DbFunctions();
//        Connection conn = db.connect_to_db("jellydb","liven","liven12");

        String ip = "178.32.120.94";
        int port = 3306;
        String dbname = "test";
        String user = "mcgo";
        String pass = "dBzRWFq2VsJFf";

        String url = "jdbc:mysql://" + ip + ":" + port +"/" + dbname;

        System.out.println(url);

        Connection conn = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
//            String url = "jdbc:mysql://" + "178.32.120.94" + ":" + "3306" + "/" + dbname;


//            String url = "jdbc:mysql://" + ip + ":" + port + "/" + dbname;
            conn = DriverManager.getConnection(url, user, pass);
            if (conn != null) {
                System.out.println("Connection Established");

            } else {
                System.out.println("Connection Failed");
            }

        } catch (Exception e) {
            System.out.println(e);
        }

        System.out.println(db.getNameColor(conn, "TEST-oubsiuaiugfiagiyvsdiygawiu"));
//        jedis = new Jedis("localhost", 6379);
//        jedis.auth("liven@12");
//
//        jedis.publish("test", "message right");
//
//        JedisManager jm = new JedisManager(jedis, "test");
//
//        jm.broadcastMessage("testmessage2");

//        JDA jda = JDABuilder.createDefault("ODY0MzcwNDg2OTQ2MDM3ODEw.Gl4_k3.x_p-00U5P5vqrp5ZkaMFqQaWPujCfs0Tx3y8Bc", GatewayIntent.GUILD_MESSAGES, GatewayIntent.GUILD_MEMBERS)
//                .setActivity(Activity.playing("learning stuff"))
//                .addEventListeners(new BotCommands(db ,conn, jedis, jm.getListener(), jm))
//                .addEventListeners(new Tasks())
//                .build();

//        jedis.subscribe(jm.getListener(), "test");

    }



}
