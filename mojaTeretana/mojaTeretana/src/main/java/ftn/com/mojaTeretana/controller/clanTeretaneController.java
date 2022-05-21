package ftn.com.mojaTeretana.controller;

import ftn.com.mojaTeretana.model.Komentar;
import ftn.com.mojaTeretana.model.Korisnik;
import ftn.com.mojaTeretana.model.Trening;
import ftn.com.mojaTeretana.service.komentarService;
import ftn.com.mojaTeretana.service.korisnikService;
import ftn.com.mojaTeretana.service.TreningService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.ServletContextAware;
import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import static ftn.com.mojaTeretana.controller.mainController.KORISNIK;

@Controller
@RequestMapping("/clanTeretane/Korisnicko_ime={korisnickoIme}")
public class clanTeretaneController implements ServletContextAware {

    @Autowired
    private ServletContext servletContext;
    private String bURL;

    @Autowired
    public TreningService treningService;

    @Autowired
    private korisnikService korisnikService;

    @Autowired
    private komentarService komentarService;

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
    public String getData(@PathVariable(required = true) String korisnickoIme,
                          Model model){
        List<Trening> treninzi = treningService.findAll();
        Korisnik korisnik = korisnikService.findOneByUsername(korisnickoIme);
        model.addAttribute("treninzi", treninzi);
        model.addAttribute("korisnik", korisnik);

        return "treninzi";
    }

    @GetMapping("/logout")
    public String Odjava(HttpServletRequest request){

        request.getSession().removeAttribute(KORISNIK);
        request.getSession().invalidate();
        return "redirect:/login";
    }

    @GetMapping("/trening/{id}")
    public String prikazTreninga(@PathVariable(required = true) Long id,
                                       @PathVariable(required = true) String korisnickoIme,
                                 Model model) {
        Trening trening = treningService.findOneById(id);
        Korisnik korisnik = korisnikService.findOneByUsername(korisnickoIme);
        List<Komentar> komentari = komentarService.FindAll();
        /*
        for (int i = 0; i < komentari.size(); i++) {
            if(komentari.get(i).getStatus().equals("Nije odobren")){
                komentari.remove(i);
            }
        }
        for (int i = 0; i < komentari.size(); i++) {
            if(komentari.get(i).getStatus().equals("Na cekanju")){
                komentari.remove(i);
            }
        }
        for(int i = 0; i < komentari.size(); i++) {
            if(!trening.getNaziv().equals(komentari.get(i).getTrening())){
                komentari.remove(i);
            }
        }
        */

        model.addAttribute("trening", trening);
        model.addAttribute("komentari", komentari);
        model.addAttribute("korisnik", korisnik);
        return "prikazTreninga";
    }
}
