import go.game.frames.SecondFrame;

class SecondFrameMock extends SecondFrame {
    private static boolean startGame = false;
    private static int gameMode = -1;

    public static void setStartGame(boolean value) {
        startGame = value;
    }

    public static boolean getStartGame() {
        return startGame;
    }

    public static void setGameMode(int value) {
        gameMode = value;
    }

    public static int getGameMode() {
        return gameMode;
    }

}