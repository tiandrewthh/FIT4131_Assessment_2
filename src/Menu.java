public class Menu {
    public Menu() {

    }

    public String getStartMenu() {
        return ("""
                  _____                       _         _    _             _           \s
                 |  __ \\                     (_)       | |  | |           | |          \s
                 | |__) |__ _ __   __ _ _   _ _ _ __   | |__| |_   _ _ __ | |_ ___ _ __\s
                 |  ___/ _ \\ '_ \\ / _` | | | | | '_ \\  |  __  | | | | '_ \\| __/ _ \\ '__|
                 | |  |  __/ | | | (_| | |_| | | | | | | |  | | |_| | | | | ||  __/ |  \s
                 |_|   \\___|_| |_|\\__, |\\__,_|_|_| |_| |_|  |_|\\__,_|_| |_|\\__\\___|_|  \s
                                   __/ |                                               \s
                                  |___/                                                \s
                """);
    }
    public String getGameModeSelectionMenu() {
        return ("1. Arcade Mode\n" +
                "2. Story Mode");
    }

    public String getPlayerMenu() {
        return ("""
                1: catch fish
                2: borrow from loan shark
                3: Skip"""
                );
    }

//
//    public String getStoryModeMenu() {
//        String StoryModeMenu = "";
//        return StoryModeMenu;
//    }
}
