package cn.northpark.test;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * @author bruce
 * @date 2023年07月04日 17:39:49
 */
public class MusicShuffler {
    public static void shuffleMusicFiles(String directoryPath) {
        File directory = new File(directoryPath);
        if (!directory.isDirectory()) {
            System.out.println("The specified path is not a directory.");
            return;
        }

        File[] files = directory.listFiles();
        if (files == null || files.length == 0) {
            System.out.println("The specified directory is empty.");
            return;
        }

        // Filter out non-music files
        List<File> musicFiles = new ArrayList<>();
        for (File file : files) {
            if (isMusicFile(file)) {
                musicFiles.add(file);
            }
        }

        if (musicFiles.isEmpty()) {
            System.out.println("The specified directory does not contain any music files.");
            return;
        }

        // Shuffle the music files
        Collections.shuffle(musicFiles, new Random());

        // Rename the music files with random numbers as prefixes
        int counter = 486;
        for (File file : musicFiles) {
            String newName = String.format("%03d_", counter) + file.getName();
            File newFile = new File(directoryPath, newName);
            file.renameTo(newFile);
            counter++;
        }

        System.out.println("Music files shuffled and renamed successfully.");
    }

    private static boolean isMusicFile(File file) {
        String name = file.getName().toLowerCase();
        return name.endsWith(".mp3") || name.endsWith(".wav") || name.endsWith(".flac")||  name.endsWith(".wma")|| name.endsWith(".m4a");
    }

    public static void main(String[] args) {
        shuffleMusicFiles("C:\\Users\\Bruce\\Music\\英语榜单");
    }
}
