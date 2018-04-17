package ua.theater.db.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import ua.theater.db.models.*;
import ua.theater.db.services.interfaces.TheatersService;
import ua.theater.db.validators.ActorValidator;
import ua.theater.db.validators.PlayValidator;
import ua.theater.db.validators.TheaterValidator;

import java.util.List;


@Controller
public class HomeController {


    @Autowired
    public HomeController(TheatersService theatersService,
                          TheaterValidator theaterValidator,
                          ActorValidator actorValidator,
                          PlayValidator playValidator)
    {
        this.playValidator = playValidator;
        this.theatersService = theatersService;
        this.theaterValidator = theaterValidator;
        this.actorValidator = actorValidator;
    }

    private PlayValidator playValidator;
    private ActorValidator actorValidator;
    private TheaterValidator theaterValidator;
    private TheatersService theatersService;

    @RequestMapping(value = {"/home", "/"}, method = RequestMethod.GET)
    public ModelAndView getHome() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("home");
        List<Theater> theaters = theatersService.getTheaters();
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
    public ModelAndView addPlay(@ModelAttribute Play play, BindingResult bindingResult) {
        playValidator.validate(play, bindingResult);
        if (!bindingResult.hasErrors()) {
            if (play.getId() == 0) {
                theatersService.addPlay(play);
                return new ModelAndView("redirect:/get/" + play.getTheaterId());
            } else {
                theatersService.updatePlay(play);
                return new ModelAndView("redirect:/get-play/" + play.getId());
            }
        } else {
            ModelAndView modelAndView = new ModelAndView();
            List<Theater> theaters = theatersService.getTheaters();
            modelAndView.addObject("theaters", theaters);
            modelAndView.setViewName("newPlay");
            return modelAndView;
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
    public String insertNewTheater(@ModelAttribute Theater theater, BindingResult bindingResult) {
        theaterValidator.validate(theater, bindingResult);
       if (!bindingResult.hasErrors()) {
           if (theater.getId() == 0) {
               System.out.println("22222");
               theatersService.addTheater(theater);
           } else {
               System.out.println(theater.getTel());
               theatersService.updateTheater(theater);
           }
           return "redirect:home";
       } else {
           return "addTheater";
       }
    }

    @RequestMapping(value = "/add-actor", method = RequestMethod.GET)
    public ModelAndView getAddForm(@ModelAttribute ActorAndPlay actorAndPlay) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("actorAndPlay", actorAndPlay);
        modelAndView.setViewName("addActor");
        return modelAndView;
    }

    @RequestMapping(value = "/process-actor", method = RequestMethod.POST)
    public String addActor(@ModelAttribute ActorAndPlay actorAndPlay, BindingResult bindingResult) {
        actorValidator.validate(actorAndPlay, bindingResult);
        if (!bindingResult.hasErrors()) {
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
        } else {
            return "addActor";
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
