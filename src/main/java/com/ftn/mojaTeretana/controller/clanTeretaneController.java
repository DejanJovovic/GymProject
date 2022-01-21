package com.ftn.mojaTeretana.controller;

import com.ftn.mojaTeretana.model.Korisnik;
import com.ftn.mojaTeretana.model.Trening;
import com.ftn.mojaTeretana.service.treningService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/clanTeretane/{id}")
public class clanTeretaneController {

    @Autowired
    public treningService treningService;

    @Autowired
    private ServletContext servletContext;
    private String bURL;

    @PostConstruct
    public void init() {
        bURL = servletContext.getContextPath()+"/";
    }

    @GetMapping
    @ResponseBody
    public ModelAndView getTreninzi(){
        List<Trening> treninzi = treningService.findAll();
        ModelAndView rezultat = new ModelAndView("clanTeretane");
        rezultat.addObject("treninzi", treninzi);

        return rezultat;
    }
}
