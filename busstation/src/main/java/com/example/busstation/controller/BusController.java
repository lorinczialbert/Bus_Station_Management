package com.example.busstation.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller // damit Spring dies als Controller erkennt
public class BusController extends AbstractBaseController {

    // Ein neuer Test-Endpoint fur Busse
    @GetMapping("/bus/test")
    @ResponseBody
    public String testBus() {
        return "Bus Controller funktioniert!";
    }
    // aici vin altele

}