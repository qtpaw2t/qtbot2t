package de.ltmhs.qtbot2t;

import net.dv8tion.jda.api.JDABuilder;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        try {
            Path path = Paths.get("token.txt");
            final String token = Files.readString(path).trim();
            JDABuilder.createDefault(token)
                    .build();
        } catch (IOException e) {
            System.exit(1);
        }
    }
}