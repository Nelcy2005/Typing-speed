package util;

import java.util.Random;

public class StoryProvider {

    static String[] stories = {

        "The morning sun slowly painted the sky with soft orange colors. People began walking to work while birds sang from the trees. The peaceful start made the day feel hopeful.",

        "A quiet breeze moved through the park as children laughed near the playground. An old man sat on a bench reading his favorite book. The afternoon felt calm and warm.",

        "Rain tapped softly against the window while the street lights reflected on the wet road. Cars moved slowly through the silent city. It was the kind of night that made people want to stay inside.",

        "Early in the morning a small coffee shop opened its doors. The smell of fresh coffee filled the street. Travelers stopped for a warm drink before starting their busy day.",

        "A curious cat jumped onto the table and looked around proudly. The owner laughed and gently carried it back to the floor. The tiny moment filled the room with joy."

    };

    public static String getRandomStory() {

        Random r = new Random();

        return stories[r.nextInt(stories.length)];
    }
}