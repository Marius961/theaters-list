package ua.theater.db.dao.interfaces;

import ua.theater.db.models.Theater;

import java.util.List;

public interface TheaterDAO {

    List<Theater> getTheaters();

    Theater getTheaterById(int theaterId);

    List<Theater> searchTheatersByName(String name);

    Theater getTheater(Theater theater);

    int insertTheater(Theater theater);

    void deleteTheaterById(int theaterId);

    void updateTheater(Theater theater);


}
