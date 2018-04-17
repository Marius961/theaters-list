package ua.theater.db.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import ua.theater.db.dao.impls.TheaterDAOImpl;
import ua.theater.db.dao.interfaces.TheaterDAO;
import ua.theater.db.models.*;
import ua.theater.db.services.interfaces.TheatersService;

import javax.servlet.http.HttpServletRequest;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;


@Controller
public class HomeController {


    @Autowired
    public HomeController(TheatersService theatersService) {
        this.theatersService = theatersService;
    }

    private TheatersService theatersService;

    @RequestMapping(value = {"/home", "/"}, method = RequestMethod.GET)
    public ModelAndView getHome() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("home");
        List<Theater> theaters = theatersService.getTheaters();
        System.out.println(theaters.get(0).getName());
        modelAndView.addObject("theaters", theaters);
        return modelAndView;
    }

    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
    public ModelAndView getTheaterPage(@PathVariable int id) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("plays");
        Theater theater = theatersService.getTheater(id);
        if (theater != null) {
            modelAndView.addObject("deletedTheater", new DeletedTheater());
            modelAndView.addObject("theater", theater);
            List<Play> plays = theatersService.getPlaysForTheater(id);
            modelAndView.addObject("plays", plays);
            return modelAndView;
        } else {
            modelAndView.setViewName("redirect:home");
            return modelAndView;
        }

    }

    @RequestMapping(value = "/get-play/{id}", method = RequestMethod.GET)
    public ModelAndView getPlayPage(@PathVariable int id) {
        ModelAndView modelAndView = new ModelAndView();
        Play play = theatersService.getPlay(id);
        if (play != null) {
            modelAndView.addObject("play", play);
            modelAndView.addObject("actors", theatersService.getActorsForPlay(id));
            modelAndView.addObject("actorAndPlay", new ActorAndPlay());
            modelAndView.addObject("deletedPlay", new DeletedPlay());
            modelAndView.addObject("deletedActor", new DeletedActor());
            modelAndView.setViewName("play-info");
            return modelAndView;
        } else {
            modelAndView.setViewName("redirect:home");
            return modelAndView;
        }
    }



    @RequestMapping(value = "/delete-play", method = RequestMethod.POST)
    public String deletePlay(@ModelAttribute DeletedPlay play) {
        if (play == null) {
            return "redirect:home";
        }
        theatersService.deletePlayById(play.getDeletedPlayId());
        return "redirect:home";
    }
//
    @RequestMapping(value = "/add-play", method = RequestMethod.GET)
    public ModelAndView getAddForm(@ModelAttribute Play play) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("newPlay");
        List<Theater> theaters = theatersService.getTheaters();
        modelAndView.addObject("theaters", theaters);
        modelAndView.addObject("play", play);
        return modelAndView;
    }
//
    @RequestMapping(value = "/process-play", method = RequestMethod.POST)
    public String addPlay(@ModelAttribute Play play) {
        if (play.getId() == 0) {
            theatersService.addPlay(play);
            return "redirect:/get/" + play.getTheaterId();
        } else {
            theatersService.updateaPlay(play);
            return "redirect:/get-play/" + play.getId();
        }
    }

    @RequestMapping(value = "/add-theater", method = RequestMethod.GET)
    public ModelAndView addTheater(@ModelAttribute Theater theater) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("theater", theater);
        modelAndView.setViewName("addTheater");
        return modelAndView;
    }

    @RequestMapping(value = "/process-theater", method = RequestMethod.POST)
    public String insertNewTheater(@ModelAttribute Theater theater) {
        if (theater.getId() == 0) {
            System.out.println("22222");
            theatersService.addTheater(theater);
        } else {
            System.out.println(theater.getTel());
            theatersService.updateTheater(theater);
        }
        return "redirect:home";
    }

    @RequestMapping(value = "/add-actor", method = RequestMethod.GET)
    public ModelAndView getAddForm(@ModelAttribute ActorAndPlay actorAndPlay) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("actorAndPlay", actorAndPlay);
        modelAndView.setViewName("addActor");
        return modelAndView;
    }

    @RequestMapping(value = "/process-actor", method = RequestMethod.POST)
    public String addActor(@ModelAttribute ActorAndPlay actorAndPlay) {
        if (actorAndPlay.getRedorectPlayId() == 0) {
            theatersService.addActorToPlay(actorAndPlay);
            return "redirect:/get-play/"+ actorAndPlay.getPlayId();
        } else {
            Actor actor = new Actor();
            actor.setId(actorAndPlay.getActorId());
            actor.setName(actorAndPlay.getActorName());
            theatersService.updateActor(actor);

            return "redirect:/get-play/" + actorAndPlay.getRedorectPlayId();
        }
    }

    @RequestMapping(value = "/delete-actor", method = RequestMethod.POST)
    public String deleteActor(@ModelAttribute DeletedActor deletedActor) {
        theatersService.deleteActorFromPlay(deletedActor);
        return "redirect:/get-play/" + deletedActor.getdPlayId();
    }

    @RequestMapping(value = "/delete-theater", method = RequestMethod.POST)
    public String deleteTheater(@ModelAttribute DeletedTheater deletedTheater) {
        theatersService.deleteTheater(deletedTheater.getDeletedTheaterId());
        return "redirect:home";
    }




}
