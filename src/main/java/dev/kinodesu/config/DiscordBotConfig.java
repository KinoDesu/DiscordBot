package dev.kinodesu.config;

import io.github.cdimascio.dotenv.Dotenv;

public class DiscordBotConfig {

    public DiscordBotConfig(){

    }

    public static Dotenv getEnv(){
        String profile = System.getProperty("profile");
        if(profile == null){
            profile="";
        }else {
            profile = "."+profile;
        }

        Dotenv config = Dotenv.configure()
                .directory("enviroments/")
                .filename(".env"+profile)
                .load();

        return config;
    }
}
