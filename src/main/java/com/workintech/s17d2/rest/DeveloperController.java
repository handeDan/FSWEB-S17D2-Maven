package com.workintech.s17d2.rest;

import com.workintech.s17d2.tax.Taxable;
import jakarta.annotation.PostConstruct;
import com.workintech.s17d2.model.Developer;
import com.workintech.s17d2.model.JuniorDeveloper;
import com.workintech.s17d2.model.MidDeveloper;
import com.workintech.s17d2.model.SeniorDeveloper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/developers")
public class DeveloperController {
    private final Taxable taxable;
    public Map<Integer, Developer> developers;

    @PostConstruct
    public void init() {
        developers = new HashMap<>();
        developers.put(1, new JuniorDeveloper(1, "Nil", 50000.0));
        developers.put(2, new MidDeveloper(2, "Hande", 75000.0));
        developers.put(3, new SeniorDeveloper(3, "Hakan", 90000.0));
    }

    @Autowired
    public DeveloperController(Taxable taxable) {
        this.taxable = taxable;
        this.developers = new HashMap<>();
    }

    //GET:
    @GetMapping
    public List<Developer> getAllDevelopers() {
        return developers.values().stream().toList();
    }

    //GET:
    @GetMapping("/{id}")
    public Developer getDeveloperById(@PathVariable int id) {
        return developers.get(id);
    }

    //POST:
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED) // 201 Created döndürmesi için eklendi
    public Developer addDeveloper(@RequestBody Developer developer) {
        return developers.put(developer.getId(), developer);
    }

    //PUT:
    @PutMapping("/{id}")
    public Developer updateDeveloper(@PathVariable int id,@RequestBody Developer developer) {
        if(!developers.containsKey(id)) {
            throw new IllegalArgumentException("Developer with id " + id + " does not exist.");
        }
        developers.put(id, developer);
        return developer;
    }

    //DELETE:
    @DeleteMapping("/{id}")
    public String deleteDeveloper(@PathVariable int id) {
        if(developers.remove(id) != null) {
            return "Developer with id " + id + " deleted.";
        }else {
            return "Developer with id " + id + " does not exist.";
        }
    }
}
