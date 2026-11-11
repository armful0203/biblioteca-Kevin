package pol.biblioteca.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pol.biblioteca.model.Libro;

import java.util.List;

public interface LibroRepository extends JpaRepository<Libro, Integer> {
    List<Libro> findByAutorIgnoreCase(String autor);

}
