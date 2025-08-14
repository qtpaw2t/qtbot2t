package de.ltmhs.qtbot2t;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.InteractionContextType;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.requests.restaction.CommandListUpdateAction;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.IOException;
import java.util.EnumSet;

public class Main extends ListenerAdapter {

    public static void main(String[] args) {
        try {
            EnumSet<GatewayIntent> intents = EnumSet.of(
                    GatewayIntent.GUILD_MEMBERS,
                    GatewayIntent.GUILD_PRESENCES,
                    GatewayIntent.GUILD_MESSAGES,
                    GatewayIntent.MESSAGE_CONTENT
            );
            Path path = Paths.get("token.txt");
            final String token = Files.readString(path).trim();

            JDA jda = JDABuilder.createDefault(token, intents)
                    .addEventListeners(new SlashCommandListener())
                    .build();
            CommandListUpdateAction commands = jda.updateCommands();
            commands.addCommands(
                    Commands.slash("update", "Updates the backend Database")
                            .setContexts(InteractionContextType.GUILD)
            );
            commands.queue();
        } catch (IOException e) {
            System.exit(1);
        }
    }

}
