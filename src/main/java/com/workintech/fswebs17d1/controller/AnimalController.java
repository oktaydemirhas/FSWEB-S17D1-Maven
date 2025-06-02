package com.workintech.fswebs17d1.controller;

import com.workintech.fswebs17d1.entity.Animal;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/workintech")
public class AnimalController {

    @Value("${course.name}")
    private String courseName;

    @Value("${project.developer.fullname}")
    private String developerFullName;

    private Map<Integer, Animal> animals = new HashMap<>();

    public AnimalController() {
        animals.put(1, new Animal(1, "Lion"));
        animals.put(2, new Animal(2, "Tiger"));
        animals.put(3, new Animal(3, "Elephant"));
    }

    // Properties değerlerini dönen endpoint
    @GetMapping("/info")
    public Map<String, String> getInfo() {
        Map<String, String> info = new HashMap<>();
        info.put("course", courseName);
        info.put("developer", developerFullName);
        return info;
    }

    // [GET] /workintech/animal => Tüm hayvanları listeler
    @GetMapping("/animal")
    public List<Animal> getAllAnimals() {
        return new ArrayList<>(animals.values());
    }

    // [GET] /workintech/animal/{id} => Belirli id'deki hayvanı getirir
    @GetMapping("/animal/{id}")
    public Animal getAnimalById(@PathVariable Integer id) {
        return animals.get(id);
    }

    // [POST] /workintech/animal => Yeni hayvan ekler
    @PostMapping("/animal")
    public Animal addAnimal(@RequestBody Animal animal) {
        animals.put(animal.getId(), animal);
        return animal;
    }

    // [PUT] /workintech/animal/{id} => Hayvanı günceller
    @PutMapping("/animal/{id}")
    public Animal updateAnimal(@PathVariable Integer id, @RequestBody Animal animal) {
        if (animals.containsKey(id)) {
            animal.setId(id); // URL'deki id ile güncelle
            animals.put(id, animal);
            return animal;
        }
        return null; // Hayvan bulunamadı
    }

    // [DELETE] /workintech/animal/{id} => Hayvanı siler
    @DeleteMapping("/animal/{id}")
    public Animal deleteAnimal(@PathVariable Integer id) {
        return animals.remove(id);
    }
} 