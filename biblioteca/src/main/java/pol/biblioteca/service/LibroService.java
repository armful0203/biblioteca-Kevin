package pol.biblioteca.service;


import org.springframework.stereotype.Service;
import pol.biblioteca.model.Libro;
import pol.biblioteca.repository.LibroRepository;

@
@Service
public class LibroService {
    private LibroRepository libroRepository;

    public LibroService(LibroRepository libroRepository) {
        this.libroRepository = libroRepository;

    }

    public List<Libro> traerLibros() {
        return libroRepository.findAll()
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

    public List<Libro> disponibles() {
        return libroRepository.findAll().stream().filter(libro -> libro.isEstado())
    }



}
