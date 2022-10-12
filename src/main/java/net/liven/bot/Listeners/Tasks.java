package net.liven.bot.Listeners;

import net.dv8tion.jda.api.entities.ChannelType;
import net.dv8tion.jda.api.entities.GuildChannel;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.channel.ChannelCreateEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import static net.liven.bot.DiscordBot.*;

public class Tasks extends ListenerAdapter {

    @Override
    public void onMessageReceived(@NotNull MessageReceivedEvent event) {

        if (event.getChannel().getType().equals(ChannelType.TEXT)){
           TextChannel c = (TextChannel) event.getChannel();
            if(event.getChannel().getId().equals(HM_Running_applications.get(event.getAuthor().getId()))){
                c.sendMessage("hello to application").queue();
            }
        }

        for (String users : LS_Running_applications ) {



        }
    }
}
