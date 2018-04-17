package ua.theater.db.services.interfaces;

import ua.theater.db.models.*;

import java.util.List;
import java.util.Map;

public interface TheatersService {

    Theater getTheater(int theaterId);

    void addTheater(Theater theater);

//    boolean addPlay(Play play, List<Actor> actors, Theater theater);

    Play getPlay(int id);

    boolean addPlay(Play play);

    public List<Theater> getTheaters();

    List<Play> getPlays();

    List<Actor> getAllActors();

    void deletePlayById(int id);

    void addActorToPlay(ActorAndPlay actorAndPlay);

    void deleteActorFromPlay(DeletedActor deletedActor);

    void deleteTheater(int theaterId);

    List<Play> getPlaysForTheater(int theaterId);

    List<Actor> getActorsForPlay(int playId);

    void updateActor(Actor actor);
    void updateTheater(Theater theater);
    void updateaPlay(Play play);

}
