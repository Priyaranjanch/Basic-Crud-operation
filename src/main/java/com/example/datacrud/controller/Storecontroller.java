package com.example.datacrud.controller;

import com.example.datacrud.model.Lapstore;
import com.example.datacrud.repo.Storerepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class Storecontroller {
    @Autowired
    private Storerepo storerepo;

    @GetMapping("/getalllaps")
    public ResponseEntity<List<Lapstore>> getAllLaps(){
        try{
            List<Lapstore> laplist=new ArrayList<>();
            storerepo.findAll().forEach(laplist::add);
            if(laplist.isEmpty())
            {
                return new ResponseEntity<>(laplist, HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(laplist,HttpStatus.OK);

        } catch (Exception ex)
        {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/getlapbyprice/{price}")
    public ResponseEntity<Lapstore> getlapbyprice(@PathVariable("price") long price)
    {
        Optional<Lapstore> lapdata=storerepo.findById(price);

        if(lapdata.isPresent())
        {
            return new ResponseEntity<>(lapdata.get(),HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/addlap")
    public ResponseEntity<Lapstore> addlap(@RequestBody Lapstore lap)
    {

          Lapstore lapobj=storerepo.save(lap);
          return new ResponseEntity<>(lapobj,HttpStatus.OK);
    }

    @PutMapping ("/updatebyprice/{price}")
    public ResponseEntity<Lapstore> updateById(@PathVariable("price") long price,@RequestBody Lapstore newlapdata)
    {
        Optional<Lapstore> oldlapdata=storerepo.findById(price);

        if(oldlapdata.isPresent())
        {
            Lapstore updatedlapdata=oldlapdata.get();
            updatedlapdata.setLapname(newlapdata.getLapname());
            updatedlapdata.setQuantity(newlapdata.getQuantity());

            Lapstore lapobj=storerepo.save(updatedlapdata);

            return new ResponseEntity<>(lapobj,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    @DeleteMapping({"/remove/{price}"})
    public String deleteByPrice(@PathVariable("price") long price)
    {
        storerepo.deleteById(price);
        return "deleted";
    }
    @DeleteMapping({"/remove"})
    public String deleteall()
    {
        storerepo.deleteAll();
        return "deleted all";
    }



}
