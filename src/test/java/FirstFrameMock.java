import go.game.frames.FirstFrame;

class FirstFrameMock extends FirstFrame {
    private boolean newGame = false;
    private boolean loadGame = false;

    @Override
    public boolean getNewGame() {
        return newGame;
    }

    @Override
    public boolean getLoadGame() {
        return loadGame;
    }

    public void setNewGame(boolean newGame) {
        this.newGame = newGame;
    }

    public void setLoadGame(boolean loadGame) {
        this.loadGame = loadGame;
    }
}

