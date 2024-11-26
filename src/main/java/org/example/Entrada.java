package org.example;

import dao.AlumnoDAO;
import dao.ProfesorDAO;
import model.Alumno;
import model.Profesor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Entrada {
    public static void main(String[] args) {

        AlumnoDAO alumnoDAO = new AlumnoDAO();
        ProfesorDAO profesorDAO = new ProfesorDAO();





        Scanner scanner = new Scanner(System.in);
        int opcion;

        do {
            // Mostrar el menú
            System.out.println("Menú de Opciones:");
            System.out.println("1. Insertar un nuevo profesor");
            System.out.println("2. Insertar un nuevo alumno");
            System.out.println("3. Mostrar todos los datos de alumnos y profesores");
            System.out.println("4. Mostrar todos los profesores");
            System.out.println("5. Mostrar todos los alumnos");
            System.out.println("6. Buscar alumno por email");
            System.out.println("7. Buscar profesores por rango de edad");
            System.out.println("8. Actualizar calificación del profesor por email");
            System.out.println("9. Dar de baja a los alumnos con menos de un 5. Al sleccionar esta opcion se borraran automaticamente.");
            System.out.println("10. Salir");

            // Pedir la opción al usuario
            System.out.print("Selecciona una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine();  // Limpiar el buffer de entrada después de leer un int

            switch (opcion) {
                case 1:
                    // Insertar nuevo profesor
                    String nameProf;
                    do {
                        System.out.println("Por favor introduce el nombre del profesor: ");
                        nameProf = scanner.nextLine();
                    } while (nameProf.isEmpty());  // Si el nombre está vacío, se repite la solicitud

                    // Pedir edad
                    int ageProf = -1;
                    while (ageProf < 0) {
                        System.out.println("Por favor introduce la edad del profesor: ");
                        if (scanner.hasNextInt()) {
                            ageProf = scanner.nextInt();
                            scanner.nextLine(); // Limpiar el salto de línea
                            if (ageProf < 0) {
                                System.out.println("La edad no puede ser negativa. Por favor ingresa una edad válida.");
                            }
                        } else {
                            System.out.println("Por favor ingresa un número válido para la edad.");
                            scanner.nextLine(); // Limpiar el buffer de entrada
                        }
                    }

                    // Pedir calificación
                    double ratingProf = -1;
                    while (ratingProf < 0 || ratingProf > 10) {
                        System.out.println("Por favor introduce la calificación del profesor (0 a 10): ");
                        if (scanner.hasNextDouble()) {
                            ratingProf = scanner.nextDouble();
                            scanner.nextLine(); // Limpiar el salto de línea
                            if (ratingProf < 0 || ratingProf > 10) {
                                System.out.println("La calificación debe estar entre 0 y 10.");
                            }
                        } else {
                            System.out.println("Por favor ingresa una calificación válida (número entre 0 y 10).");
                            scanner.nextLine(); // Limpiar el buffer de entrada
                        }
                    }

                    // Pedir género
                    String genderProf;
                    do {
                        System.out.println("Por favor introduce el género del profesor: ");
                        genderProf = scanner.nextLine();
                    } while (genderProf.isEmpty());

                    // Pedir email
                    String emailProf;
                    do {
                        System.out.println("Por favor introduce el email del profesor: ");
                        emailProf = scanner.nextLine();
                    } while (emailProf.isEmpty());

                    // Pedir teléfono
                    String phoneProf;
                    do {
                        System.out.println("Por favor introduce el teléfono del profesor: ");
                        phoneProf = scanner.nextLine();
                    } while (phoneProf.isEmpty());

                    // Pedir materias

                    List<String> subjectsList;
                    do {
                        System.out.println("Por favor introduce las asignaturas que imparte el profesor, separadas por espacios: ");
                        String input = scanner.nextLine();  // Lee la línea completa

// Convierte la entrada en una lista de asignaturas
                        subjectsList = Arrays.asList(input.split("\\s+"));
                    } while (subjectsList.isEmpty());

                    // Pedir título
                    String titleProf;
                    do {
                        System.out.println("Por favor introduce el título del profesor: ");
                        titleProf = scanner.nextLine();
                    } while (titleProf.isEmpty());

                    // Ahora se hace la inserción en la base de datos
                    profesorDAO.insertarProfesor(new Profesor(ratingProf, ageProf, nameProf, genderProf, emailProf, phoneProf, subjectsList, titleProf));
                    // Asegúrate de implementar tu DAO o la inserción en la base de datos
                    System.out.println("Profesor insertado: " + nameProf + ", Edad: " + ageProf + ", Calificación: " + ratingProf + ", Género: " + genderProf + ", Email: " + emailProf + ", Teléfono: " + phoneProf + ", Materias: " + subjectsList + ", Título: " + titleProf);

                    break;

                case 2:
                    // Insertar un nuevo alumno
                    String name;
                    do {
                        System.out.println("Por favor introduce el nombre del alumno: ");
                        name = scanner.nextLine();
                    } while (name.isEmpty());  // Si el nombre está vacío, se repite la solicitud

                    // Pedir edad
                    int age = -1;
                    while (age < 0) {
                        System.out.println("Por favor introduce la edad: ");
                        if (scanner.hasNextInt()) {
                            age = scanner.nextInt();
                            scanner.nextLine(); // Limpiar el salto de línea
                            if (age < 0) {
                                System.out.println("La edad no puede ser negativa. Por favor ingresa una edad válida.");
                            }
                        } else {
                            System.out.println("Por favor ingresa un número válido para la edad.");
                            scanner.nextLine(); // Limpiar el buffer de entrada
                        }
                    }

                    // Pedir calificación
                    double rating = -1;
                    while (rating < 0 || rating > 10) {
                        System.out.println("Por favor introduce la calificación (0 a 10): ");
                        if (scanner.hasNextDouble()) {
                            rating = scanner.nextDouble();
                            scanner.nextLine(); // Limpiar el salto de línea
                            if (rating < 0 || rating > 10) {
                                System.out.println("La calificación debe estar entre 0 y 10.");
                            }
                        } else {
                            System.out.println("Por favor ingresa una calificación válida (número entre 0 y 10).");
                            scanner.nextLine(); // Limpiar el buffer de entrada
                        }
                    }

                    // Pedir grado de estudio
                    String higher_grade;
                    do {
                        System.out.println("Por favor introduce el grado de estudio del alumno: ");
                        higher_grade = scanner.nextLine();
                    } while (higher_grade.isEmpty());

                    // Pedir email
                    String email;
                    do {
                        System.out.println("Por favor introduce el email del alumno: ");
                        email = scanner.nextLine();
                    } while (email.isEmpty());

                    // Pedir teléfono
                    String phone;
                    do {
                        System.out.println("Por favor introduce el teléfono del alumno: ");
                        phone = scanner.nextLine();
                    } while (phone.isEmpty());

                    // Ahora se hace la inserción en la base de datos (simulada aquí)
                    alumnoDAO.insertAlumno(new Alumno(rating, age, name, email, phone, higher_grade));
                    // Asegúrate de implementar tu DAO o la inserción en la base de datos
                    System.out.println("Alumno insertado: " + name + ", Edad: " + age + ", Calificación: " + rating + ", Email: " + email + ", Teléfono: " + phone + ", Grado: " + higher_grade);

                    break;

                case 3:
                    // Mostrar todos los datos de alumnos y profesores
                    alumnoDAO.findAllAlumnos();
                    profesorDAO.findAllProfesores();
                    break;

                case 4:
                    // Mostrar todos los profesores
                    System.out.println("Mostrando todos los profesores...");
                    profesorDAO.findAllProfesores();
                    break;

                case 5:
                    // Mostrar todos los alumnos
                    System.out.println("Mostrando todos los alumnos...");
                    alumnoDAO.findAllAlumnos();
                    break;

                case 6:
                    // Buscar alumno por email
                    System.out.println("Introduce un correo para bsucar el alumno: ");
                    String correo = scanner.nextLine();
                    alumnoDAO.findAlumnoByEmail(correo);
                    break;

                case 7:
                    // Buscar profesores por rango de edad
                    int edadMin;
                    int edadMmax;
                    System.out.println("Introduce una edad mininma:");
                    edadMin = scanner.nextInt();
                    System.out.println("Introduce una edad maxima:");
                    edadMmax = scanner.nextInt();
                    profesorDAO.findProfesoresByAge(edadMin, edadMmax);
                    break;

                case 8:
                    // Actualizar calificación del profesor por email
                    String emailProfesor;
                    Double newRating;
                    System.out.println("Introduce el email del profesor que quieres actualizar.");
                    emailProfesor = scanner.nextLine();
                    System.out.println("Introduce la nueva calificacion del profesor: ");
                    newRating = scanner.nextDouble();
                    profesorDAO.updateRatingByEmail(emailProfesor, newRating);
                    break;

                case 9:
                    // Dar de baja a los alumnos con menos de un 5
                    alumnoDAO.deleteAlumnLt5();
                    break;

                case 10:
                    // Salir
                    System.out.println("¡Hasta luego!");
                    break;

                default:
                    System.out.println("Opción no válida, por favor selecciona una opción válida.");
                    break;
            }

        } while (opcion != 10); // El ciclo termina cuando la opción es 10 (salir)

        scanner.close();  // Cerrar el scanner al final








    }
}