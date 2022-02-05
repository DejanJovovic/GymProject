package ftn.com.mojaTeretana.controller;

import ftn.com.mojaTeretana.model.Trening;
import ftn.com.mojaTeretana.service.treningService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("admin/Korisnicko_ime={korisnickoIme}/trening/{id}/izmenaTreninga")
public class izmenaTreninga {

    @Autowired
    private ftn.com.mojaTeretana.service.treningService treningService;

    @GetMapping
    @ResponseBody
    public ModelAndView getData2(@PathVariable(required = true) Long id){
        Trening trening = treningService.findOneById(id);
        ModelAndView rezultat = new ModelAndView("izmenaTreninga");
        rezultat.addObject("trening", trening);

        return rezultat;
    }
}
