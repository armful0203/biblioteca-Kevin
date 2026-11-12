package pol.biblioteca.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pol.biblioteca.model.Libro;
import pol.biblioteca.repository.LibroRepository;
import pol.biblioteca.service.LibroService;

@RestController
@RequestMapping

public class LibroController {
    private LibroService libroService;
    public LibroController(LibroService libroService) {
        this.libroService = libroService;

    }

    @GetMapping("/{id}")
    public ResponseEntity<Libro> traerUno(@PathVariable int id) {
        return libroService.traerUno(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Libro> editar(@PathVariable int id, @RequestBody Libro libro) {
        return libroService.editar(id,libro).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Libro> eliminar(@PathVariable int id) {
        if(libroService.eliminar(id)) {
            return ResponseEntity.noContent().build();}
        else{
            return ResponseEntity.notFound().build();
        }
    }


    @GetMapping("/estado")
    public List<Libro>disponibles(){
        return libroService.estado();
    }

    @GetMapping("/antiguos")
    public List<Libro> antiguos(@RequestParam(defaultValue = "1950") int antes){
        return libroService.antesde(antes);

    }


    @PutMapping("/{id}/prestar")
    public ResponseEntity<Libro> prestar(@PathVariable int id) {
        if (libroService.traerUno(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return libroService.prestar(id).map(ResponseEntity::ok).orElse(ResponseEntity.status(HttpStatus.CONFLICT).build());
    }


    @PutMapping("/{id}/devolver")
    public ResponseEntity<Libro> devolver(@PathVariable int id) {
        if (libroService.traerUno(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return libroService.devolver(id)
                .map(ResponseEntity::ok)
                .orElse (ResponseEntity.status(HttpStatus.CONFLICT).build());
    }


}
