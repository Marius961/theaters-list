package ua.theater.db.dao.interfaces;

import ua.theater.db.models.Play;
import ua.theater.db.models.Theater;

import java.util.List;
import java.util.Map;

public interface PlayDAO {

    Play getPlayById(int playId);

    List<Play> getAllPlaysByTheaterID(int theaterId);

    List<Play> getAllPlays();

    Play getPlay(Play play);

    List<Play> searchPlaysByName(String name);

    int insertPlay(Play play);

    void deletePlayById(int playId);

    void updatePlay(Play play);

    void deleteAllActorsForPlay(int playId);

    void deleteAllPlayForTheater(int id);

    int getPlaysCount(int theaterId);
}
