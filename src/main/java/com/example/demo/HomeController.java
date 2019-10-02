package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class HomeController {
    @Autowired
    FlightsRepository flightsRepository;

    @RequestMapping("/")
    public String listFlights(Model model){
        model.addAttribute("flights", flightsRepository.findAll());
        return "list";
    }

    @GetMapping("/add")
    public String flightForm(Model model){
        model.addAttribute("flight", new Flight());
        return "flightform";
    }

    @PostMapping("/process")
    public String processForm(@ModelAttribute Flight flight, @RequestParam(name="date") String date){

        try {
            System.out.println(flight.getPrice());
            System.out.println(flight.getAirline());
            String pattern = "yyyy-MM-dd";
            System.out.println(date);
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
            String formattedDate = date.substring(1);
            System.out.println("formatted: " + formattedDate);
            Date realDate = simpleDateFormat.parse(formattedDate);
            flight.setDate(realDate);

            System.out.println(realDate.toString());
        }
        catch (java.text.ParseException e){
            e.printStackTrace();
        }
        flightsRepository.save(flight);
        return "redirect:/";
    }

    @PostMapping("/searchlist")
    public String searchResult(Model model, @RequestParam(name="search") String search) {

        model.addAttribute("flights", flightsRepository.findByAirlineIgnoreCase(search));
        return "searchlist";
    }
}
