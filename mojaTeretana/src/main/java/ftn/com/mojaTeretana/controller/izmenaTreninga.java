package ftn.com.mojaTeretana.controller;

import ftn.com.mojaTeretana.model.Trening;
import ftn.com.mojaTeretana.service.treningService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@RequestMapping("admin/Korisnicko_ime={korisnickoIme}/trening/{id}/izmenaTreninga")
public class izmenaTreninga {

    @Autowired
    private treningService treningService;

    @GetMapping
    @ResponseBody
    public ModelAndView getData2(@PathVariable(required = true) Long id){
        Trening trening = treningService.findOneById(id);
        ModelAndView rezultat = new ModelAndView("izmenaTreninga");
        rezultat.addObject("trening", trening);

        return rezultat;
    }
}
