package com.alura.literatura;

import com.alura.literatura.service.LiteraturaService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication
public class LiteraturaApplication implements CommandLineRunner {

	private final LiteraturaService literaturaService;
	Scanner scanner = new Scanner(System.in);

	public LiteraturaApplication(LiteraturaService literaturaService){
		this.literaturaService = literaturaService;
	}

	public static void main(String[] args) {

		SpringApplication.run(LiteraturaApplication.class, args);
	}

	@Override
	public void run(String... args){
		int opcion = -1;

		while (opcion != 0){
			mostrarMenu();
			opcion = leerEntero("Elige una opcion: ");

			switch (opcion){
				case 1 -> buscarLibroPorTitulo();
				case 2 -> literaturaService.listarLibrosRegistrados();
				case 3 -> literaturaService.listarAutoresRegistrados();
				case 4 -> listarAutoresVivosPorAnio();
				case 5 -> listarLibrosPorIdioma();
				case 0 -> System.out.println("Saliendo... ¡Hasta luego!");
				default -> System.out.println("Opcion Invalida.");
			}
		}
	}

	private void mostrarMenu(){
		System.out.println("\n=== Challenge Literatura ===");
		System.out.println("1 - Buscar libro por titulo");
		System.out.println("2 - Listar libros registrados");
		System.out.println("3 - Listar autores registrados");
		System.out.println("4 - Listar autores vivos en un año");
		System.out.println("5 - Listar libros por idioma");
		System.out.println("0 - Salir");
	}

	private int leerEntero(String mensaje){
		System.out.print(mensaje);
		while (!scanner.hasNextInt()){
			System.out.println("Por favor, ingrese un numero valido.");
			scanner.next();
		}
		return scanner.nextInt();
	}

	private String leerTexto(String mensaje){
		System.out.print(mensaje);
		scanner.nextLine();
		return scanner.nextLine();
	}

	private void buscarLibroPorTitulo() {
		String titulo = leerTexto("Ingrese el título a buscar: ");
		literaturaService.buscarYGuardarLibros(titulo);
	}

	private void listarAutoresVivosPorAnio() {
		int anio = leerEntero("Ingrese el año: ");
		literaturaService.listarAutoresVivosEnAnio(anio);
	}

	private void listarLibrosPorIdioma() {
		String idioma = leerTexto("Ingrese el código del idioma (ej: en, es, fr): ");
		literaturaService.listarLibrosPorIdioma(idioma);
	}

}
