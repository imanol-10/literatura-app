package com.alura.literatura;

import com.alura.literatura.service.LiteraturaService;
import com.alura.literatura.model.Autor;
import com.alura.literatura.model.Libro;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;
import java.util.Scanner;

@SpringBootApplication
public class LiteraturaApplication implements CommandLineRunner {

	private final LiteraturaService literaturaService;
	private final Scanner scanner = new Scanner(System.in);

	public LiteraturaApplication(LiteraturaService literaturaService) {
		this.literaturaService = literaturaService;
	}

	public static void main(String[] args) {
		SpringApplication.run(LiteraturaApplication.class, args);
	}

	@Override
	public void run(String... args) {
		int opcion = -1;

		while (opcion != 0) {
			mostrarMenu();
			opcion = leerEntero("Elige una opción: ");

			switch (opcion) {
				case 1 -> buscarLibroPorTitulo();
				case 2 -> {
					List<Libro> libros = literaturaService.listarLibrosRegistrados();
					if (libros.isEmpty()){
						System.out.println("No hay libros registrados.");
					}else {
						for (Libro libro : libros) {
							System.out.println("****** LIBRO ******");
							System.out.println("TITULO: " + libro.getTitulo());
							System.out.println("AUTOR: " + (libro.getAutor() != null ? libro.getAutor().getNombre() : "desconocido"));
							System.out.println("IDIOMA: " + libro.getIdioma());
							System.out.println("NUMERO DE DESCARGA: " + libro.getDescargas());
							System.out.println();
						}
					}
				}
				case 3 -> {
					List<Autor> autores = literaturaService.listarAutoresRegistrados();
					if (autores.isEmpty()) {
						System.out.println("No hay autores registrados");
					}else {
						for (Autor autor : autores){
							System.out.println("****** AUTOR ******");
							System.out.println("NOMBRE: " + autor.getNombre());
							System.out.println("AÑO DE NACIMIENTO: " + autor.getAnioNacimiento());
							System.out.println("AÑO FALLECIMIENTO: " + autor.getAnioFallecimiento());
							System.out.println();
						}
					}
				}
				case 4 -> {
					int anio = leerEntero("Ingrese el año: ");
					List<Autor> autores = literaturaService.listarAutoresVivosEnAnio(anio);
					if (autores.isEmpty()){
						System.out.println("No hay autores vivos en el año: " + anio);
					}else {
						for (Autor autor : autores){
							System.out.println("****** AUTOR VIVO ******");
							System.out.println("NOMBRE: " + autor.getNombre());
							System.out.println("AÑO NACIMIENTO: " + autor.getAnioNacimiento());
							System.out.println("AÑO FALLECIMIENTO: " + autor.getAnioFallecimiento());
							System.out.println();
						}
					}
				}
				case 5 -> {
					String idioma = leerTexto("Ingrese el codigo del idioma (ej: en, es, fr): ");
					List<Libro> libros = literaturaService.listarLibrosPorIdioma(idioma);
					if (libros.isEmpty()){
						System.out.println("No hay libros registrados en el idioma " + idioma);
					}else {
						for (Libro libro : libros){
							System.out.println("****** LIBRO ******");
							System.out.println("TITULO: " + libro.getTitulo());
							System.out.println("AUTOR: " + (libro.getAutor() != null ? libro.getAutor().getNombre() : "desconocido"));
							System.out.println("IDIOMA: " + libro.getIdioma());
							System.out.println("NUMERO DE DESCARGA: " + libro.getDescargas());
							System.out.println();
						}
					}
				}
				case 0 -> System.out.println("Saliendo... ¡Hasta luego!");
				default -> System.out.println("Opción inválida.");
			}
		}
	}

	private void mostrarMenu() {
		System.out.println("\n=== Challenge Literatura ===");
		System.out.println("1 - Buscar libro por título");
		System.out.println("2 - Listar libros registrados");
		System.out.println("3 - Listar autores registrados");
		System.out.println("4 - Listar autores vivos en un año");
		System.out.println("5 - Listar libros por idioma");
		System.out.println("0 - Salir");
	}

	private int leerEntero(String mensaje) {
		System.out.print(mensaje);
		while (!scanner.hasNextInt()) {
			System.out.println("Por favor, ingrese un número válido.");
			scanner.next();
		}
		int valor = scanner.nextInt();
		scanner.nextLine(); // limpiar buffer
		return valor;
	}

	private String leerTexto(String mensaje) {
		System.out.print(mensaje);
		return scanner.nextLine();
	}

	private void buscarLibroPorTitulo() {
		String titulo = leerTexto("Ingrese el título a buscar: ");
		literaturaService.buscarYGuardarLibros(titulo);
	}

	private void listarAutoresVivosPorAnio() {
		int anio = leerEntero("Ingrese el año: ");
		literaturaService.listarAutoresVivosEnAnio(anio)
				.forEach(a -> System.out.println(a.getNombre() + " (" + a.getAnioNacimiento() + " - " + a.getAnioFallecimiento() + ")"));
	}

	private void listarLibrosPorIdioma() {
		String idioma = leerTexto("Ingrese el código del idioma (ej: en, es, fr): ");
		literaturaService.listarLibrosPorIdioma(idioma)
				.forEach(l -> System.out.println(l.getTitulo() + " - " + l.getAutor().getNombre() + " (" + l.getIdioma() + ")"));
	}

	private void listarLibrosRegistrados() {
		literaturaService.listarLibrosRegistrados()
				.forEach(l -> System.out.println(l.getTitulo() + " - " + l.getAutor().getNombre() + " (" + l.getIdioma() + ")"));
	}

	private void listarAutoresRegistrados() {
		literaturaService.listarAutoresRegistrados()
				.forEach(a -> System.out.println(a.getNombre() + " (" + a.getAnioNacimiento() + " - " + a.getAnioFallecimiento() + ")"));
	}
}

