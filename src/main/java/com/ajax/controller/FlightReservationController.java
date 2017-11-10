/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ajax.controller;

import com.ajax.model.Flight;
import com.ajax.service.FlightReservationService;
import java.util.ArrayList;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author majiasheng
 */
@Controller
@ControllerAdvice
public class FlightReservationController {

    @Autowired
    FlightReservationService flightReservationService;
    
    
    /**
     *
     * @param binder
     */
    @InitBinder
    public void InitBinder(WebDataBinder binder) {

        // can use binder.setDisallowedFields() to un-bind a property
        // SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-mm-dd");
        // use a customized date format for "dateAcquired" request param
        //binder.registerCustomEditor(Date.class, "dateAcquired" ,new CustomDateEditor(simpleDateFormat, false));
    }

    /**
     * 
     * @param requestParams
     * @return 
     */
    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public ModelAndView handleSearchFlight(@RequestParam Map<String, String> requestParams) {

        ModelAndView mv = new ModelAndView("result");

        // TODO: use request params to do query on db
        // FlightReservationService.searchFlight(String src, String dst, Date dep, Date ret)
        ArrayList<Flight> flights = (ArrayList<Flight>) flightReservationService.searchFlight(null, null, null, null);

        // add a list of flights as the search result for the view to render
        mv.addObject("result", flights);

        return mv;
    }
    
    @RequestMapping(value = "/bookflight", method = RequestMethod.GET)
    public ModelAndView handleBookFlight(@RequestParam Map<String, String> requestParams) {
        
        ModelAndView mv = new ModelAndView("");
        
        //TODO:
        
        
        return mv;
    }
    
}
