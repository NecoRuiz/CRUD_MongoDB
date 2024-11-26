package dao;

import DataBase.MongoDBconnection;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import com.mongodb.client.result.UpdateResult;
import model.Profesor;
import org.bson.conversions.Bson;

import java.util.Scanner;

public class ProfesorDAO {

    MongoCollection collectionProfesores;

    public ProfesorDAO(){
        //ACCESO A LA COLECCION PROFESORES
        collectionProfesores = new MongoDBconnection().getProfesoresCollection();
    }

    //INSERT PROFESOR
    public void insertarProfesor(Profesor profesor){
        MongoCollection collection = new MongoDBconnection().getProfesoresCollection();
        collection.insertOne(profesor);
    }

    //FIND TODOS LOS PROFESORES
    public void findAllProfesores() {
        MongoCollection<Profesor> collection = new MongoDBconnection().getProfesoresCollection();
        FindIterable<Profesor> iterable = collection.find();

        try (MongoCursor<Profesor> cursor = iterable.iterator()) {  // Cambié a MongoCursor<Profesor>
            while (cursor.hasNext()) {
                Profesor profesor = cursor.next();
                System.out.println("Nombre: " + profesor.getName());
                System.out.println("Edad: " + profesor.getAge());
                System.out.println("Genero: " + profesor.getGender());
                System.out.println("Email: " + profesor.getEmail());
                System.out.println("Telefono: " + profesor.getPhone());
                System.out.println("Titulo: " + profesor.getTitle());
                System.out.println("Calificacion: " + profesor.getRating());
                System.out.println("Asignaturas: " + profesor.getSubjects());
            }
        }
    }

    //FIND PROFESORES POR EDAD
    public void findProfesoresByAge(int edadMin, int edadMax){
        //documento filtrado
        Bson filtrado = Filters.and(
               Filters.gte("age",edadMin),
                Filters.lte("age",edadMax)
        );

        MongoCollection<Profesor> collection = new MongoDBconnection().getProfesoresCollection();

        FindIterable<Profesor> iterable = collection.find(filtrado);

        MongoCursor cursor = iterable.cursor();

        System.out.println("Los profesores encontrados que tienen entre " +edadMin+ " y " +edadMax+ " son:");
        while (cursor.hasNext()){
            Profesor profesor = (Profesor) cursor.next();
            System.out.println("Nombre: " +profesor.getName());
            System.out.println("Edad: " +profesor.getAge());
            System.out.println("Email: " +profesor.getEmail());
        }
        cursor.close();

    }

    //UPDATE CALIFICACION DEL PROFESOR POR EMAIL
    public void updateRatingByEmail(String emailProfesor, Double newRating){
        //documento buscado(filtro)
        Bson filtrado = Filters.eq("email",emailProfesor);
        //documento actualizado
        Bson actualizado = Updates.set("rating",newRating);
        //accedemos a la coleccion...
        MongoCollection<Profesor> collection = new MongoDBconnection().getProfesoresCollection();
        //el metodo UpdateOne lo almaceno en un UpdateResult
        UpdateResult resultado = collection.updateOne(filtrado, actualizado);

        if (resultado.getModifiedCount() > 0){
            System.out.println("El profesor se ha actualizado correctamente.");
        }else{
            System.out.println("El proceso de actualizacion no se pudo completar.");
        }
    }




}
