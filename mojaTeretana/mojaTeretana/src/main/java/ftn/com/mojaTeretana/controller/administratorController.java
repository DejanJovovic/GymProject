package ftn.com.mojaTeretana.controller;

import ftn.com.mojaTeretana.model.Komentar;
import ftn.com.mojaTeretana.model.Korisnik;
import ftn.com.mojaTeretana.model.Trening;
import ftn.com.mojaTeretana.service.korisnikService;
import ftn.com.mojaTeretana.service.treningService;
import ftn.com.mojaTeretana.service.komentarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.ServletContextAware;
import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import static ftn.com.mojaTeretana.controller.mainController.KORISNIK;

@Controller
@RequestMapping("/admin/Korisnicko_ime={korisnickoIme}")
public class administratorController implements ServletContextAware {

    @Autowired
    private ServletContext servletContext;
    private String bURL;

    @Autowired
    private treningService treningService;

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
    public String admin() {
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

    @GetMapping
    @RequestMapping("/dodavanjeTreninga")
    public String dodavanjeTreninga(@PathVariable("korisnickoIme") String korisnickoIme, Model model) {
        Korisnik korisnik = korisnikService.findOneByUsername(korisnickoIme);
        model.addAttribute("korisnik",korisnik);
        return "dodavanjeTreninga";
    }

    @GetMapping("/dodavanjeTreninga-novitrening")
    public String dodavanjeTreninga(@RequestParam(required = true) String naziv, @RequestParam(required = true) String trener,
                                  @RequestParam(required = true) String kratakOpis, @RequestParam(required = true) String slika,
                                  @RequestParam(required = true) int cena, @RequestParam(required = true) String tipTreninga,
                                  @RequestParam(required = true) String vrstaTreninga, @RequestParam(required = true) String nivoTreninga,
                                  @RequestParam(required = true) int trajanjeTreninga, HttpSession session,
                                  HttpServletResponse response,
                                  @PathVariable("korisnickoIme") String korisnickoIme) throws IOException {
        Korisnik korisnik = korisnikService.findOneByUsername(korisnickoIme);

        Trening trening = new Trening(naziv, kratakOpis, slika, tipTreninga, cena, vrstaTreninga,
                nivoTreninga, trajanjeTreninga, 0, trener);

        treningService.save(trening);
        return "redirect:/admin/Korisnicko_ime=" + korisnik.getKorisnickoIme();
    }

    @GetMapping("/trening/{id}")
    public String prikazTreninga(@PathVariable(required = true) Long id,
                                       @PathVariable(required = true) String korisnickoIme,
                                 Model model) {
        Trening trening = treningService.findOneById(id);
        Korisnik korisnik = korisnikService.findOneByUsername(korisnickoIme);

        model.addAttribute("trening", trening);
        model.addAttribute("korisnik", korisnik);
        return "prikazTreninga";
    }

    @GetMapping("/trening/{id}/izmenaTreninga")
    public String izmenaTreninga(@PathVariable("id") Long treningId, @PathVariable("korisnickoIme") String korisnickoIme,
                                 Model model) {
        model.addAttribute("trening", treningService.findOneById(treningId));
        model.addAttribute("korisnik", korisnikService.findOneByUsername(korisnickoIme));

        return "izmenaTreninga";
    }

    @GetMapping("/logout")
    public String Odjava(HttpServletRequest request) {

        request.getSession().removeAttribute(KORISNIK);
        request.getSession().invalidate();
        return "redirect:/login";
    }

    @GetMapping("/komentari")
    public String komentari(@PathVariable("korisnickoIme") String korisnickoIme, Model model) {
        List<Komentar> komentari = komentarService.FindAll();
        Korisnik korisnik = korisnikService.findOneByUsername(korisnickoIme);

        model.addAttribute("komentari", komentari);
        model.addAttribute("korisnik", korisnik);
        return "komentari";
    }

    @PostMapping("/komentari-odbi/{idKomentar}")
    public String odbiKomentar(@PathVariable("korisnickoIme") String korisnickoIme,
                               @PathVariable("idKomentar") Long idKomentar, @ModelAttribute Komentar komentar1) {
        Komentar komentar = komentarService.FindOneById(idKomentar);
        komentar.setId(komentar1.getId());
        komentar.setAnoniman(komentar1.isAnoniman());
        komentar.setTekstKomentara(komentar1.getTekstKomentara());
        komentar.setAutor(komentar1.getAutor());
        komentar.setTrening(komentar1.getTrening());
        komentar.setDatumPostavljanja(komentar1.getDatumPostavljanja());
        komentar.setStatus("Nije odobren");

        komentarService.update(komentar);

        return "redirect:/admin/Korisnicko_ime=" + korisnickoIme;
    }

    @GetMapping("/komentari-odobri/{idKomentar}")
    public String odobriKomentar1(@PathVariable("korisnickoIme") String korisnickoIme,
                               @PathVariable("idKomentar") Long idKomentar, @ModelAttribute Komentar komentar1, Model model) {
        Komentar komentar = komentarService.FindOneById(idKomentar);

        model.addAttribute("komentar", komentar);

        komentar.setId(komentar1.getId());
        komentar.setAnoniman(komentar1.isAnoniman());
        komentar.setTekstKomentara(komentar1.getTekstKomentara());
        komentar.setAutor(komentar1.getAutor());
        komentar.setTrening(komentar1.getTrening());
        komentar.setDatumPostavljanja(komentar1.getDatumPostavljanja());
        komentar.setStatus("Odobren");

        komentarService.update(komentar);

        return "redirect:/admin/Korisnicko_ime=" + korisnickoIme;
    }

    @GetMapping("/korisnici")
    public String korisnici(@PathVariable("korisnickoIme") String korisnickoIme,
                            Model model) {
        Korisnik korisnik = korisnikService.findOneByUsername(korisnickoIme);
        model.addAttribute("korisnik", korisnik);
        return "korisnici";
    }

    @PostMapping("trening/{id}/izmenaTreninga-izmena")
    public String IzmenaTreninga(@PathVariable("id") Long Treningid, @ModelAttribute Trening trening1,
                                 @PathVariable("korisnickoIme") String korisnickoIme) {
        Trening trening = treningService.findOneById(Treningid);

        if(trening != null) {
            trening.setId(trening1.getId());
            trening.setSlika(trening1.getSlika());
            trening.setNaziv(trening1.getNaziv());
            trening.setTrener(trening1.getTrener());
            trening.setKratakOpis(trening1.getKratakOpis());
            trening.setTipTreninga(trening1.getTipTreninga());
            trening.setCena(trening1.getCena());
            trening.setVrstaTreninga(trening1.getVrstaTreninga());
            trening.setNivoTreninga(trening1.getNivoTreninga());
            trening.setTrajanjeTreninga(trening1.getTrajanjeTreninga());
            trening.setProsecnaOcena(trening1.getProsecnaOcena());

            treningService.update(trening);
        }
        return "redirect:/admin/Korisnicko_ime=" + korisnickoIme + "/trening/" + Treningid;
    }
}
