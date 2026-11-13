package pol.biblioteca.service;


import org.springframework.stereotype.Service;
import pol.biblioteca.model.Libro;
import pol.biblioteca.repository.LibroRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class LibroService {
    private LibroRepository libroRepository;

    public LibroService(LibroRepository libroRepository) {
        this.libroRepository = libroRepository;

    }

    public List<Libro> traerLibros() {
        return libroRepository.findAll();
    }

    public Optional<Libro> traerUno(int id) {
        return libroRepository.findById(id);
    }

    public Libro guardar(Libro libro) {
        return libroRepository.save(libro);
    }

    //editar
    public Optional<Libro> editar(int id, Libro libroActualizado) {
        return libroRepository.findById(id)
                .map(libro -> {
                    libro.setTitulo(libroActualizado.getTitulo());
                    libro.setAutor(libroActualizado.getAutor());
                    libro.setAnio(libroActualizado.getAnio());
                    libro.setEstado(libroActualizado.isEstado());
                    return libroRepository.save(libro);
                });

    }

    //ELIMINAR

    public boolean eliminar(int id) {
        if(libroRepository.existsById(id)) {
            libroRepository.deleteById(id);
            return true;
        }
        else {
            return false;

        }
    }

    public List<Libro> estado() {
        return libroRepository.findAll().stream().filter(libro -> libro.isEstado()).collect(collectors.tolist());

    }

    public List<Libro> antesde(int antes) {
        return libroRepository.findAll().stream().filter(libro -> libro.getAnio() < antes).collect(Collectors.toList());
    }

    public Optional<Libro> prestar(int id) {
        return libroRepository.findById(id).filter(libro -> libro.isEstado()).map(libro -> libro.setEstado(false));return libroRepository.save(libro);});

    }
    public Optional<Libro> devolver(int id) {
        return libroRepository.findById(id)
            .filter(Libro -> !libro.isDisponible())
            .map(libro -> {
                libro.setDisponible(true);
                return libroRepository.save(libro);
            });

    }




