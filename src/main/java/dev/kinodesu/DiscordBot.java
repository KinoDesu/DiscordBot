package dev.kinodesu;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.interactions.commands.build.Commands;

public class DiscordBot {
    private final JDA jda;
    public DiscordBot(){
        jda =DiscordBotEntity.getDiscordBot();
    }
    public static void main(String[] args) {

        DiscordBot bot = new DiscordBot();
        DiscordBotEntity.addCommand(Commands.slash("ola","Receba um olá amigável"));
    }
}