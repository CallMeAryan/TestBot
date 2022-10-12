package net.liven.bot.Managers;

import net.dv8tion.jda.api.entities.GuildChannel;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;

import java.util.List;

public class Application_Manager {

    private User user;
    private TextChannel textChannel;
    private ButtonInteractionEvent e;


    public Application_Manager(ButtonInteractionEvent e, TextChannel textChannel) {

        this.e = e;
        this.textChannel = textChannel;

    }

    public void startApplication(){

        List<GuildChannel> c = e.getGuild().getChannels();

    }

}
