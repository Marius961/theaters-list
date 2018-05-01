package ua.theater.db.services.impls;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.theater.db.dao.interfaces.ActorDAO;
import ua.theater.db.dao.interfaces.PlayDAO;
import ua.theater.db.dao.interfaces.TheaterDAO;
import ua.theater.db.models.*;
import ua.theater.db.services.interfaces.TheatersService;

import java.util.List;
import java.util.Map;

@Service
public class TheaterServiceImpl implements TheatersService {

    private TheaterDAO theaterDAO;
    private ActorDAO actorDAO;
    private PlayDAO playDAO;

    @Autowired
    public void setDAO(TheaterDAO theaterDAO, ActorDAO actorDAO, PlayDAO playDAO) {
        this.theaterDAO = theaterDAO;
        this.actorDAO = actorDAO;
        this.playDAO = playDAO;
    }

    @Override
    public Theater getTheater(int theaterId) {
        return theaterDAO.getTheaterById(theaterId);
    }

    @Override
    public void addTheater(Theater theater) {
        theaterDAO.insertTheater(theater);
    }

    @Override
    public Play getPlay(int id) {
        return playDAO.getPlayById(id);
    }


    @Override
    public boolean addPlay(Play play) {
        Play tempPlay = playDAO.getPlay(play);
        if (tempPlay != null) {
            return false;
        }
        playDAO.insertPlay(play);
        return true;
    }

    @Override
    public List<Theater> searchTheater(String name) {
        List<Theater> theaters = theaterDAO.searchTheatersByName(name);
        for (Theater theater : theaters) {
            theater.setPlaysCount(fillPlaysCount(theater.getId()));
        }
        return theaters;
    }

    @Override
    public List<Play> searchPlay(String name) {
        return playDAO.searchPlaysByName(name);
    }

    @Override
    public List<Theater> getTheaters() {
        List<Theater> theaters = theaterDAO.getTheaters();
        for (Theater theater : theaters) {
            theater.setPlaysCount(fillPlaysCount(theater.getId()));
        }
        return theaters;
    }

    private int fillPlaysCount(int theaterId) {
        return playDAO.getPlaysCount(theaterId);
    }

    @Override
    public List<Play> getPlays() {
        List<Play> plays = playDAO.getAllPlays();
        for (Play play : plays) {
            play.setActors(actorDAO.getActorsForPlay(play.getId()));
        }
        return plays;
    }

    @Override
    public List<Actor> getAllActors() {
        return actorDAO.getAllActors();
    }

    @Override
    public void deletePlayById(int id) {
        playDAO.deleteAllActorsForPlay(id);
        playDAO.deletePlayById(id);
    }

    @Override
    public void addActorToPlay(ActorAndPlay actorAndPlay) {
        Actor tempActor = actorDAO.getActorByName(actorAndPlay.getActorName());
        if (tempActor != null) {
            actorDAO.insertActorToPlay(tempActor.getId(), actorAndPlay.getPlayId());
        } else {
            int id = actorDAO.insertActor(actorAndPlay.getActorName());
            actorDAO.insertActorToPlay(id, actorAndPlay.getPlayId());
        }
    }

    @Override
    public void deleteActorFromPlay(DeletedActor deletedActor) {
        actorDAO.deleteActorFromPlay(deletedActor.getdActorId(), deletedActor.getdPlayId());
        actorDAO.deleteActor(deletedActor.getdActorId());
    }

    @Override
    public void deleteTheater(int theaterId) {
        List<Play> plays = getPlaysForTheater(theaterId);
        for (Play play : plays) {
            deletePlayById(play.getId());
        }
        theaterDAO.deleteTheaterById(theaterId);
    }

    @Override
    public List<Play> getPlaysForTheater(int theaterId) {
        return playDAO.getAllPlaysByTheaterID(theaterId);
    }

    @Override
    public List<Actor> getActorsForPlay(int playId) {
        return actorDAO.getActorsForPlay(playId);
    }

    @Override
    public void updateActor(Actor actor) {
        actorDAO.updateActor(actor);
    }

    @Override
    public void updateTheater(Theater theater) {
        theaterDAO.updateTheater(theater);
    }

    @Override
    public void updatePlay(Play play) {
        playDAO.updatePlay(play);
    }


    //    @Override
//    public boolean addPlay(Play play, List<Actor> actors, Theater theater) {
//        Play tempPlay = playDAO.getPlay(play);
//
//        if (tempPlay != null) {
//            return false;
//        }
//        int theaterId;
//        Theater tempTheater = theaterDAO.getTheater(theater);
//        if (tempTheater == null) {
//            theaterId = theaterDAO.insertTheater(theater);
//        } else {
//            theaterId = tempTheater.getId();
//        }
//        play.setTheaterId(theaterId);
//        int playId = playDAO.insertPlay(play);
//
//        int tempActorId;
//        for(Actor actor : actors) {
//            Actor tempActor = actorDAO.getActor(actor);
//            if (tempActor == null) {
//                tempActorId = actorDAO.insertActor(actor);
//                actorDAO.insertActorToPlay(tempActorId, playId);
//            } else {
//                actorDAO.insertActorToPlay(tempActor.getId(), playId);
//            }
//        }
//        return true;
//    }

}
