package cn.northpark.test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @author bruce
 * @date 2023年07月04日 17:45:56
 */
public class LyricsRenamer {
    public static void renameLyrics(String directoryPath) {
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

        // Find music files and store their names in a list
        List<String> musicFileNames = new ArrayList<>();
        for (File file : files) {
            if (isMusicFile(file)) {
                musicFileNames.add(file.getName());
            }
        }

        if (musicFileNames.isEmpty()) {
            System.out.println("The specified directory does not contain any music files.");
            return;
        }

        // Find lyrics files and rename them to match the corresponding music file name
        for (File file : files) {
            if (isLyricsFile(file)) {
                String lyricsFileName = file.getName();
                String newLyricsFileName = findMatchingMusicFileName(lyricsFileName, musicFileNames);
                if (newLyricsFileName != null) {
                    File newLyricsFile = new File(directoryPath, newLyricsFileName);
                    if (file.renameTo(newLyricsFile)) {
                        System.out.println("Renamed " + lyricsFileName + " to " + newLyricsFileName);
                    } else {
                        System.out.println("Failed to rename " + lyricsFileName);
                    }
                }
            }
        }

        System.out.println("Lyrics files renamed successfully.");
    }

    private static boolean isMusicFile(File file) {
        String name = file.getName().toLowerCase();
        return name.endsWith(".mp3") || name.endsWith(".wav") || name.endsWith(".flac")||  name.endsWith(".wma");
    }

    private static boolean isLyricsFile(File file) {
        String name = file.getName().toLowerCase();
        return name.endsWith(".lrc") || name.endsWith(".txt");
    }

    private static String findMatchingMusicFileName(String lyricsFileName, List<String> musicFileNames) {
//        String lyricsPrefix = lyricsFileName.substring(0, 3);
        String lyricsName = lyricsFileName.substring(6,lyricsFileName.length()-4);
        for (String musicFileName : musicFileNames) {
            String musicPrefix = musicFileName.substring(0, 4);
            String musicName = musicFileName.substring(5,musicFileName.length()-4);
            if (musicName.contains(lyricsName)) {
                // Extract the original number from the music file name
                int musicNumber = Integer.parseInt(musicPrefix);
                // Use the original number to construct the new lyrics file name
                String newLyricsFileName = String.format("%04d_", musicNumber) + musicName+".lrc";
                return newLyricsFileName;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        renameLyrics("C:\\Users\\Bruce\\Music\\1000");
    }
}
