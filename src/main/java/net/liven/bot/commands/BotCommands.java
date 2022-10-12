package net.liven.bot.commands;


import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.*;
import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.components.buttons.Button;
import net.liven.bot.Listeners.JedisListener;
import net.liven.bot.Managers.JedisManager;
import net.liven.bot.Tasks.Application_Tasks;
import net.liven.bot.Utils.DbFunctions;
import org.jetbrains.annotations.NotNull;
import redis.clients.jedis.Jedis;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import static net.liven.bot.DiscordBot.TableName;


public class BotCommands extends ListenerAdapter {

    DbFunctions db;
    Connection conn;
    String finalratt = " ";
    Jedis jedis;
    JedisListener listener;
    JedisManager jm;

    public BotCommands(DbFunctions db, Connection conn, Jedis jedis, JedisListener listener, JedisManager jm) {
        this.db = db;
        this.conn = conn;
        this.jedis = jedis;
        this.listener = listener;
        this.jm = jm;
    }


    @Override
    public void onButtonInteraction(@NotNull ButtonInteractionEvent event) {
        event.deferEdit().queue();

       if(event.getButton().getId().equals("Apply")) {
           Guild guild = event.getGuild();
           Category n = guild.getCategoryById("917017063866900491");
           guild.createTextChannel("application-" + event.getMember().getUser().getName(), n)
                   .addPermissionOverride(guild.getPublicRole(), 0, Permission.getRaw(Permission.VIEW_CHANNEL))
                   .addPermissionOverride(event.getMember(), Permission.getRaw(Permission.VIEW_CHANNEL, Permission.MESSAGE_SEND), Permission.getRaw(Permission.MANAGE_PERMISSIONS))
                   .setTopic(event.getUser().getName().replace(" ", "") + "[_]" + event.getUser().getId() + "[_]" + "")
                   .queue(channel -> new Application_Tasks(channel.getId(), event));
       }else if (event.getButton().getId().equals("Open ticket")) {
            Guild guild = event.getGuild();
            Category n = guild.getCategoryById("917019102281883668");
            guild.createTextChannel("ticket-" + event.getMember().getUser().getName(), n)
                    .addPermissionOverride(guild.getPublicRole(), 0, Permission.getRaw(Permission.VIEW_CHANNEL))
                    .addPermissionOverride(event.getMember(), Permission.getRaw(Permission.VIEW_CHANNEL, Permission.MESSAGE_SEND), Permission.getRaw(Permission.MANAGE_PERMISSIONS))
                    .queue();

        }else if (event.getButton().getId().equals("Support")) {
           Guild guild = event.getGuild();
           Category n = guild.getCategoryById("994551461507772557");
           guild.createTextChannel("support-" + event.getMember().getUser().getName(), n)
                   .addPermissionOverride(guild.getPublicRole(), 0, Permission.getRaw(Permission.VIEW_CHANNEL))
                   .addPermissionOverride(event.getMember(), Permission.getRaw(Permission.VIEW_CHANNEL, Permission.MESSAGE_SEND), Permission.getRaw(Permission.MANAGE_PERMISSIONS))
                   .queue();
       }
    }

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        String[] args = event.getMessage().getContentRaw().split(" ");
                event.getTextChannel().getTopic();

        if (args[0].equals("-info")) {

            if (args.length == 1 && event.getMember().getRoles().contains(event.getGuild().getRoleById("916802016880234496"))){
                EmbedBuilder eB = new EmbedBuilder();
                eB.setTitle(event.getAuthor().getName() + "'s Info", event.getAuthor().getAvatarUrl());
                eB.setThumbnail(event.getAuthor().getAvatarUrl());
                eB.setDescription(" ");
                eB.addField("Total completed ", "0", true);
                eB.addField("TimeZone", "N/A", true);
                eB.setColor(0x1884C6);
                eB.addField("Portfolio", "none set", false);
                eB.addField("Mc-Market", "N/A", false);
                eB.addField("Ratings", calculateRating(event), false);
                eB.setFooter("Ran by " + event.getAuthor().getName());
                event.getChannel().sendMessageEmbeds(eB.build()).queue();

                Guild guild = event.getGuild();
                Category n = guild.getCategoryById("917019102281883668");
                guild.createTextChannel(event.getAuthor().getName() + "-ticket", n)
                        .addPermissionOverride(guild.getPublicRole(), 0, Permission.getRaw(Permission.VIEW_CHANNEL))
                        .addPermissionOverride(event.getMember(), Permission.getRaw(Permission.VIEW_CHANNEL, Permission.MESSAGE_SEND), Permission.getRaw(Permission.MANAGE_PERMISSIONS))
                        .queue();
            }
            if (args.length == 2){

                String s = args[1].replace("<", "").replace(">", "").replace("@", "");
                event.getChannel().sendMessage(s).queue();
                Member m = event.getGuild().getMemberById(s);
                event.getChannel().sendMessage(m.getAvatarUrl()).queue();
            }


//        } else if (args[0].equals("-register")) {
//            db.addUser(conn, TableName, event.getAuthor().getName(), "0", 5, event.getAuthor().getIdLong());
            event.getMessage().reply("successfully registered").queue();


        } else if (args.length == 2 && args[0].equals("-update-rating")) {
            db.update_rating(conn, event.getAuthor().getIdLong(), Integer.parseInt(args[1]));
            event.getChannel().sendMessage("rating updated").queue();

        } else if (args[0].equals("-create") && event.getMember().getRoles().contains(event.getGuild().getRoleById("916648594352766978"))) {
            db.createTable(conn, TableName);
            event.getChannel().sendMessage("datatable created").queue();

        } else if (args.length == 2 && args[0].equals("-setportfolio")) {

            db.update_rating(conn, event.getAuthor().getIdLong(), Integer.parseInt(args[1]));
            event.getChannel().sendMessage("rating updated").queue();

        }else if (args.length == 2 && args[0].equalsIgnoreCase("-broadcast")){
            jm.getListener().unsubscribe("broadcast");
            jm.broadcastMessage(args[1]);
            jm.getListener().subscribe("broadcast");

        }else if (args[0].equalsIgnoreCase("-addticket") && event.getMember().getRoles().contains(event.getGuild().getRoleById("916648594352766978"))) {
            EmbedBuilder eB = new EmbedBuilder();
            eB.setTitle("Open a ticket!");
            eB.setDescription(" ");
            eB.addField("Commission Ticket", "*Interact with :envelope_with_arrow: to open a Commission ticket*", false);
            eB.addField("Application Ticket", "*Interact with :clipboard: to open a Application ticket*", false);
            eB.addField("", "**•** *Pinging staff about your application will result in your application being immediately denied*", false);
            eB.addField("", "**•** *Applications can take up to 24-48 hours to be reviewed, we might ask to have a live call to discuss answers on your application*", false);
            eB.addField("Support Ticket", "*Interact with :question: to open a Support ticket*", false);
            eB.setColor(0x1884C6);
            eB.setFooter("</Jelly Services>");

            event.getChannel().sendMessageEmbeds(eB.build()).setActionRow(addButttons()).queue();


        } else {
            event.getMessage().reply("you dont have sufficient perms");
        }




    }

    public List<Button> addButttons(){
        List<Button> buttons = new ArrayList<>();
        buttons.add(Button.secondary("Open ticket", "open a ticket").withEmoji(Emoji.fromUnicode("\uD83D\uDCE9")));
        buttons.add(Button.secondary("Support", "open a support ticket").withEmoji(Emoji.fromMarkdown("❓")));
        buttons.add(Button.secondary("Apply", "open a application ticket").withEmoji(Emoji.fromMarkdown("📋")));


        return buttons;
    }

    public String calculateRating(MessageReceivedEvent e) {

//        long rating = db.getRating(conn, e.getAuthor().getIdLong());
//        String finalrat = ":star:";
//        for (int i = 0; i < rating; i++) {
//
//            String rat = this.finalratt + ":star:";
//            this.finalratt = rat;
//
//        }
//        return this.finalratt;
        return null;
    }





}

