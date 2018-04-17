package ua.theater.db.dao.interfaces;

import ua.theater.db.models.Actor;

import java.util.List;

public interface ActorDAO {

    Actor getActorById(int id);

    Actor getActorByName(String name);

    List<Actor> getActorsForPlay(int playId);

    List<Actor> getAllActors();

    int insertActor(String name);

    void insertActorToPlay(int actorId, int playId);

    void updateActor(Actor actor);

    void deleteActor(int actorId);

    void deleteActorFromPlay(int actorId, int playId);
}
