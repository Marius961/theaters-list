package ua.theater.db.models;

public class ActorAndPlay {

    private int playId;
    private String playName;
    private int actorId;
    private String actorName;
    private int redorectPlayId;

    public int getRedorectPlayId() {
        return redorectPlayId;
    }

    public void setRedorectPlayId(int redorectPlayId) {
        this.redorectPlayId = redorectPlayId;
    }

    public int getPlayId() {
        return playId;
    }

    public void setPlayId(int playId) {
        this.playId = playId;
    }

    public String getActorName() {
        return actorName;
    }

    public void setActorName(String actorName) {
        this.actorName = actorName;
    }

    public String getPlayName() {
        return playName;
    }

    public void setPlayName(String playName) {
        this.playName = playName;
    }

    public int getActorId() {
        return actorId;
    }

    public void setActorId(int actorId) {
        this.actorId = actorId;
    }
}
