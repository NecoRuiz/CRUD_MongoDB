package dao;

import DataBase.MongoDBconnection;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.model.Filters;
import model.Alumno;
import org.bson.conversions.Bson;

import java.security.PublicKey;

public class AlumnoDAO {
    MongoCollection coleccionAlumnos;

    public AlumnoDAO(){
        //ACCESO A LA COLECCION ALUMNOS
        coleccionAlumnos = new MongoDBconnection().getAlumnosCollecion();
    }


    //INSERT ALUMNO
    public void insertAlumno(Alumno alumno){
        MongoCollection collection = new MongoDBconnection().getAlumnosCollecion();
        collection.insertOne(alumno);
    }

    //FIND TODOS LOS ALUMNOS
    public void findAllAlumnos(){
        //accedo a la collection mediante la conexion y la guardo en una MongoCollection de tipo ALUMNO
        MongoCollection<Alumno> collection = new MongoDBconnection().getAlumnosCollecion();
        //el metodo find() devulve un FindIterable--->lo creo y en el almaceno los datos que haya encontrado en la coleccion (DATOS ENCONTRADOS)
        FindIterable<Alumno> iterable = collection.find();
        //el metodo cursor del iterable, devuelve un MongoCursor--->lo creo y en el almaceno los datos ya recogidos (DATOS SELECCIONADOS)
        MongoCursor cursor = iterable.cursor();
        //utilizo un while para recorrer e imprimir por pantalla
        try {
            //hay dato para recoger??
            while (cursor.hasNext()){
                //recogelo y construye un alumno para despues ser leido
                Alumno alumno = (Alumno) cursor.next();
                System.out.println("Nombre: " + alumno.getName());
                System.out.println("Edad: " + alumno.getAge());
                System.out.println("Calificacion: " + alumno.getRating());
                System.out.println("Grado: " + alumno.getHigher_grade());
                System.out.println("Email: " + alumno.getEmail());
                System.out.println("Telefono: " + alumno.getPhone());


            }
        }finally {
            cursor.close();
        }

    }

    //FIND ALUMNO POR EMAIL
    public void findAlumnoByEmail(String correo){
        //creo el filtro de busqueda-->TODOS DEVUELVEN UN BSON
        Bson filtrado = Filters.eq("email",correo);
        //ACCEDO A LA COLECCION
        MongoCollection<Alumno> collection = new MongoDBconnection().getAlumnosCollecion();
        //PASO POR PARAMETRO EL FILTRO DE BUSQUEDA AL METODO FIND
            //AÑADO FIRST PARA QUE ME DE EL PRIMERO QUE ENCUENTRE
                //ALAMACENO TODO EN UN OBJ ALUMNO
        Alumno alumno = collection.find(filtrado).first();
        //SI ALUMNO NO ESTA VACIO-->MUESTRA LA INFO DEL ALUMNO
        if (alumno != null){

            System.out.println("Nombre: " + alumno.getName());
            System.out.println("Edad: " + alumno.getAge());
            System.out.println("Calificacion: " + alumno.getRating());
            System.out.println("Grado: " + alumno.getHigher_grade());
            System.out.println("Email: " + alumno.getEmail());
            System.out.println("Telefono: " + alumno.getPhone());


        }else{
            System.out.println("No se ha podido encontrar un alumno que tenga el correo: " +correo);
        }

    }

    //DELETE A LOS ALUMNOS CON MENOS DE UN 5 DE RATING
    public void deleteAlumnLt5(){
        int nota = 5;
        //documento filtrado
        Bson filtro = Filters.lt("rating",nota);

        MongoCollection<Alumno> collection = new MongoDBconnection().getAlumnosCollecion();
        collection.deleteMany(filtro);

    }
}
