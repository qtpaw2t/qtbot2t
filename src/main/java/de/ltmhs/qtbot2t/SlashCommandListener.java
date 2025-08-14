package de.ltmhs.qtbot2t;

import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.util.ArrayList;
import java.util.List;

public class SlashCommandListener extends ListenerAdapter {
    @Override
    public void onSlashCommandInteraction (SlashCommandInteractionEvent event) {
        switch (event.getName()) {
            case "update":
                ArrayList<CustomUser> updateList = new ArrayList<>();
                Guild guild = event.getGuild();
                guild.loadMembers()
                        .onSuccess((List<Member> members) -> {
                            List<User> users = members.stream()
                                    .map(Member::getUser).
                                    toList();
                            System.out.println(users);
                            users.forEach(user -> {
                                CustomUser customUser = new CustomUser();
                                customUser.setId(user.getId());
                                customUser.setName(user.getName());
                                customUser.setGuildId(event.getGuild().getId());
                                updateList.add(customUser);
                            });
                        });
                event.reply("User des Servers wurden aktualisiert.")
                        .setEphemeral(true)
                        .queue();
                break;
            default:
                event.reply("Unknown command: " + event.getName()).setEphemeral(true).queue();
                break;
        }
    }
}
