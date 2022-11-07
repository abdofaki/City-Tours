package com.techelevator.controller;

import com.techelevator.dao.UserDao;
import com.techelevator.model.Itinerary;
import com.techelevator.dao.ItineraryDao;

import com.techelevator.model.Landmark;
import com.techelevator.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;

import java.security.Principal;
import java.util.Date;
import javax.validation.Valid;

@RestController
@PreAuthorize("isAuthenticated()")
@RequestMapping(value = "/itinerary")
@CrossOrigin
public class ItineraryController {
    @Autowired
    ItineraryDao itineraryDao;

    @Autowired
    UserDao userDao;

    public ItineraryController(ItineraryDao itineraryDao, UserDao userDao) {
        this.itineraryDao = itineraryDao;
        this.userDao = userDao;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public void createItinerary(@Valid @RequestBody Itinerary itinerary, Principal principal) {
        User user = userDao.findByUsername(principal.getName());
        itinerary.setUserId(user.getId());
        itineraryDao.createItinerary(itinerary);
    }

    @RequestMapping(value = "/add-landmark", method = RequestMethod.POST)
    public void addLandmark(@RequestBody Landmark landmark, Itinerary itinerary) {
        itineraryDao.addLandmark(landmark, itinerary);
    }

//    @RequestMapping(value = "", method = RequestMethod.PUT)
//    public void editItinerary(@Valid @RequestBody Itinerary itinerary, Principal principal) {
//
//    }
}
