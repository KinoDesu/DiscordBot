package dev.kinodesu.listener;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class DiscordSlashListener extends ListenerAdapter {
    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {
        String comando = event.getInteraction().getName();
        switch (comando) {
            case "ola":
                event.getChannel().sendMessage("olá, " + event.getUser().getName()).queue();
                break;
            case "teste":
                event.getChannel().sendMessage("teste").queue();
                break;
            default:
                event.getChannel().sendMessage("sla").queue();
        }
    }
}
