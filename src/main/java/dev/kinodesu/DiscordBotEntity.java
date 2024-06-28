package dev.kinodesu;

import dev.kinodesu.config.DiscordBotConfig;
import dev.kinodesu.listener.DiscordSlashListener;
import io.github.cdimascio.dotenv.Dotenv;
import io.github.cdimascio.dotenv.internal.DotenvReader;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.exceptions.InvalidTokenException;
import net.dv8tion.jda.api.interactions.commands.build.SlashCommandData;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.requests.restaction.CommandListUpdateAction;

import java.util.ArrayList;
import java.util.List;

public class DiscordBotEntity {
    private static JDA jda;
    private static CommandListUpdateAction commands;
    private final Dotenv config;

    private DiscordBotEntity() throws InvalidTokenException {

        config = DiscordBotConfig.getEnv();

        JDABuilder builder = JDABuilder.createDefault(config.get("TOKEN"));
        List<GatewayIntent> intents = new ArrayList<>();
        intents.add(GatewayIntent.MESSAGE_CONTENT);
        intents.add(GatewayIntent.DIRECT_MESSAGE_TYPING);
        intents.add(GatewayIntent.GUILD_MEMBERS);
        builder.enableIntents(intents);
        builder.setStatus(OnlineStatus.ONLINE);
        builder.setActivity(Activity.watching("Sei lá man..."));
        builder.addEventListeners(new DiscordSlashListener());
        try {
            jda = builder.build().awaitReady();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        commands = jda.updateCommands();
    }


    public static JDA getDiscordBot() {
        if (jda == null) {
            DiscordBotEntity discordBotEntity = new DiscordBotEntity();
        }
        return jda;
    }

    public static void addCommand(SlashCommandData slashCommandData) {
        if (jda != null) {
            commands.addCommands(slashCommandData);
            commands.queue();
        } else {
            throw new RuntimeException("bot not initialized");
        }
    }

    public static void addCommand(List<SlashCommandData> slashCommandDataList) {
        if (jda != null) {
            for (SlashCommandData slashCommandData : slashCommandDataList) {
                commands.addCommands(slashCommandData);
            }
            commands.queue();
        } else {
            throw new RuntimeException("bot not initialized");
        }
    }
}
