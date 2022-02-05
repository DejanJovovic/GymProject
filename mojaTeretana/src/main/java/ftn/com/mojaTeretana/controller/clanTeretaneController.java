package ftn.com.mojaTeretana.controller;

import ftn.com.mojaTeretana.model.Korisnik;
import ftn.com.mojaTeretana.model.Trening;
import ftn.com.mojaTeretana.service.korisnikService;
import ftn.com.mojaTeretana.service.treningService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/clanTeretane/Korisnicko_ime={korisnickoIme}")
public class clanTeretaneController implements ServletContextAware {

    @Autowired
    private ServletContext servletContext;
    private String bURL;

    @Autowired
    public treningService treningService;

    @Autowired
    private korisnikService korisnikService;

    @PostConstruct
    public void init() {
        bURL = servletContext.getContextPath()+"/";
    }

    @Override
    public void setServletContext(ServletContext servletContext) {
        this.servletContext = servletContext;
    }

    @RequestMapping("")
    public String treninzi() {
        return "treninzi";
    }

    @GetMapping
    @ResponseBody
    public ModelAndView getData(@PathVariable(required = true) String korisnickoIme){
        List<Trening> treninzi = treningService.findAll();
        Korisnik korisnik = korisnikService.findOneByUsername(korisnickoIme);
        ModelAndView rezultat = new ModelAndView("treninzi");
        rezultat.addObject("treninzi", treninzi);
        rezultat.addObject("korisnik", korisnik);

        return rezultat;
    }
    @GetMapping("/trening/{id}")
    public ModelAndView prikazTreninga(@PathVariable(required = true) Long id,
                                       @PathVariable(required = true) String korisnickoIme) {
        Trening trening = treningService.findOneById(id);
        Korisnik korisnik = korisnikService.findOneByUsername(korisnickoIme);

        ModelAndView rezultat = new ModelAndView("prikazTreninga");
        rezultat.addObject("trening", trening);
        rezultat.addObject("korisnik", korisnik);
        return rezultat;
    }
}
