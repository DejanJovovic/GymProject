package ftn.com.mojaTeretana.controller;

import ftn.com.mojaTeretana.model.Korisnik;
import ftn.com.mojaTeretana.service.KorisnikService;
import ftn.com.mojaTeretana.service.TreningService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.ServletContextAware;
import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Controller
@RequestMapping("/")
public class mainController implements ServletContextAware {

    public static final String KORISNIK = "korisnik";

    @Autowired
    private ServletContext servletContext;
    private String bURL;

    @Autowired
    private KorisnikService korisnikService;

    @Autowired
    private TreningService treningService;

    @PostConstruct
    public void init() {
        bURL = servletContext.getContextPath()+"/";
    }

    @Override
    public void setServletContext(ServletContext servletContext) {
        this.servletContext = servletContext;
    }

    @RequestMapping("")
    public String home() {
        return "home";
    }

    @GetMapping("/login")
    public String getPrijava() {
        return "prijava";
    }

}
