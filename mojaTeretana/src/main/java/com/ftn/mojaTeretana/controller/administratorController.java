package com.ftn.mojaTeretana.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/Korisnicko_ime={korisnickoIme}")
public class administratorController {

    @RequestMapping("")
    public String admin() {
        return "admin";
    }
}
