package by.sidina.it_team.entity;

public enum Level {
    JUNIOR(1), MIDDLE(2), SENIOR(3), MANAGER(4);

    private final int levelID;

    Level(int id) {
        this.levelID = id;
    }

    public int getLevelID() {
        return levelID;
    }
}
