package com.example.demo.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDate;
import java.util.List;

@Service
public class FVService {

    private final FVRepository fvRepository;

    @Autowired
    public FVService(FVRepository fvRepository) {
        this.fvRepository = fvRepository;
    }


    @GetMapping
    public List<Faktura> getFaktura()
    {
        return fvRepository.findAll();
    }

    public void addNewFaktura(Faktura faktura) {
        System.out.println(faktura);
    }


    public Faktura getSingleFaktura(long id) {
        return fvRepository.findById(id).orElseThrow();
    }
}
