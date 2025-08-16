package com.alura.literatura.service;

import com.alura.literatura.dto.DatosAPI;
import com.alura.literatura.dto.DatosAutor;
import com.alura.literatura.dto.DatosLibro;
import com.alura.literatura.model.Autor;
import com.alura.literatura.model.Libro;
import com.alura.literatura.repository.AutorRepository;
import com.alura.literatura.repository.LibroRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LiteraturaService {


    private final ConsumoAPI consumoAPI;
    private final ConvierteDatos conversor;
    private final AutorRepository autorRepository;
    private final LibroRepository libroRepository;

    public LiteraturaService(ConsumoAPI consumoAPI, ConvierteDatos conversor,
                             AutorRepository autorRepository, LibroRepository libroRepository){
        this.consumoAPI = consumoAPI;
        this.conversor = conversor;
        this.autorRepository = autorRepository;
        this.libroRepository = libroRepository;

    }


    public void buscarYGuardarLibros(String titulo) {
        String url = "https://gutendex.com/books/?search=" + titulo.replace(" ", "%20");
        String json = consumoAPI.obtenerDatos(url);
        DatosAPI datosAPI = conversor.convertir(json, DatosAPI.class);

        for (DatosLibro datosLibro : datosAPI.getResults()) {
            if (datosLibro.getAuthors().isEmpty()) continue;

            DatosAutor datosAutor = datosLibro.getAuthors().get(0);

            Autor autor = autorRepository.findByNombre(datosAutor.getName())
                    .orElseGet(() -> {
                        Autor nuevoAutor = new Autor();
                        nuevoAutor.setNombre(datosAutor.getName());
                        nuevoAutor.setAnioNacimiento(datosAutor.getBirth_year());
                        nuevoAutor.setAnioFallecimiento(datosAutor.getDeath_year());
                        return autorRepository.save(nuevoAutor);
                    });

            Optional<Libro> libroExistente = libroRepository.findByTitulo(datosLibro.getTitle());
            if (libroExistente.isPresent()) continue;

            Libro libro = new Libro();
            libro.setTitulo(datosLibro.getTitle());
            libro.setIdioma(datosLibro.getLanguages().isEmpty() ? "desconocido" : datosLibro.getLanguages().get(0));
            libro.setDescargas(datosLibro.getDownload_count());
            libro.setAutor(autor);

            libroRepository.save(libro);
            System.out.println("Libro Guardado: " + libro.getTitulo() + " - Autor: " + autor.getNombre());
        }
    }

    public List<Autor> listarAutoresVivosEnAnio(int anio) {
        return autorRepository.findByAnioNacimientoLessThanEqualAndAnioFallecimientoGreaterThanEqual(anio, anio);
    }

    public List<Libro> listarLibrosPorIdioma(String idioma) {
        return libroRepository.findByIdioma(idioma);
    }

    public List<Libro> listarLibrosRegistrados() {
        return libroRepository.findAll();
    }

    public List<Autor> listarAutoresRegistrados() {
        return autorRepository.findAll();
    }
}
