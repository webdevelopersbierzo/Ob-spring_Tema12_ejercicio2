package com.example.Ejercicio2Sesion101112.controller;

import com.example.Ejercicio2Sesion101112.entity.Laptop;
import com.example.Ejercicio2Sesion101112.repository.LaptopRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@RestController
public class LaptopController {

    // atributoes
    private LaptopRepository laptopRepository;
    private final Logger log = LoggerFactory.getLogger(LaptopController.class);
    // constructores
    public LaptopController(LaptopRepository laptopRepository) {
        this.laptopRepository = laptopRepository;
    }
    //CRUD

    /**
     * Bucar todos los laptop
     * http://localhost:8080/laptops
     * @return List<Laptop>
     */
    @GetMapping("/laptops")
     public List<Laptop> findAll(){

         return laptopRepository.findAll();

     }
    /**
     * Guardar un libro
     * http://localhost:8080/laptops
     * Metodo post
     * @return Laptop entity
     *
     */
    @PostMapping("/laptops")
    public ResponseEntity<Laptop> create(@RequestBody Laptop laptop){
        if(laptop.getId() != null){
            log.warn("trying to create un laptop existent");
            return ResponseEntity.badRequest().build();
        }
        Laptop result = laptopRepository.save(laptop);

        return ResponseEntity.ok(result);
    }

    /**
     * Buscar un laptop por id
     * @param id
     * @return ResponseEntity
     */
    @GetMapping("/laptops/{id}")

    public ResponseEntity<Laptop> findOnebyId(@PathVariable Long id){
        Optional<Laptop> laptopOpt = laptopRepository.findById(id);
        if(laptopOpt.isPresent()){
            return ResponseEntity.ok(laptopOpt.get());
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Actualizar un laptop existente en base de datos
     * @param
     * @return
     */
    @PutMapping("/laptops")
    public ResponseEntity<Laptop> update(@RequestBody Laptop laptop){
       if(laptop.getId() == null){
           log.warn("trying to update a non existent laptop");
           return ResponseEntity.badRequest().build();
       }
       if(!laptopRepository.existsById(laptop.getId())){
           log.warn("trying to update a non existente book");
           return ResponseEntity.notFound().build();
        }
       Laptop result = laptopRepository.save(laptop);
       return ResponseEntity.ok(result);
    }

    /**
     * Borrar un laptop por id
     * @param id
     * @return ResponseEntity
     */
    @DeleteMapping("/laptops/{id}")
    public ResponseEntity<Laptop> deleteById(@PathVariable Long id ){
        if(!laptopRepository.existsById(id)){
            log.warn("Trying delete a non existent laptop");
            return ResponseEntity.notFound().build();
        }
        laptopRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * Borrar todos los laptos de la base de datos
     *
     * @return ResponseEntity
     */
    @DeleteMapping("/laptops")
    public ResponseEntity<Laptop> deleteAll(){
        log.info("Warning REST request for delete laptop");
        laptopRepository.deleteAll();
        return ResponseEntity.noContent().build();
    }


}
