package com.example.demo.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="api/v1/faktura")
public class FVController {

    private final FVService FVService;

    @Autowired
    public FVController(FVService FVService) {
        this.FVService = FVService;
    }


    @GetMapping
    public List<Faktura> getFaktury()
    {
        return FVService.getFaktura();
    }

    @PostMapping
    public void registerNewFaktura(@RequestBody Faktura faktura){
        FVService.addNewFaktura(faktura);
    }
    @GetMapping("api/v1/faktura/{id}")
    public Faktura getSingleFaktura(@PathVariable long id){
        return FVService.getSingleFaktura(id);
    }


}
