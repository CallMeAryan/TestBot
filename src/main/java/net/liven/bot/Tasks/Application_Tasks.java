package net.liven.bot.Tasks;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;

import static net.liven.bot.DiscordBot.HM_Running_applications;

public class Application_Tasks {

    TextChannel channel;

    public Application_Tasks(String id, ButtonInteractionEvent e){
        this.channel = e.getGuild().getTextChannelById(id);
        HM_Running_applications.put(e.getUser().getId(), id);
        channel.sendMessage("<@" + e.getUser().getId() + ">").queue();
        channel.sendMessage("<@" + e.getUser().getId() + ">").queue();
    }


    public EmbedBuilder infoEmbed(){

        EmbedBuilder emb = new EmbedBuilder();
        emb.setTitle("Application");

        return null;
    }

}
