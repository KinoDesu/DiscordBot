package dev.kinodesu;

import net.dv8tion.jda.api.JDA;

public class DiscordBot {
    private final JDA jda;
    public DiscordBot(){
        jda =DiscordBotEntity.getDiscordBot();
    }
    public static void main(String[] args) {

        DiscordBot bot = new DiscordBot();
    }
}