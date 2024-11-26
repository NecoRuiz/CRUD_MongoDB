package DataBase;

import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import model.Alumno;
import model.Profesor;
import org.bson.codecs.configuration.CodecProvider;
import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;

public class MongoDBconnection {
    private String connectionString = "mongodb+srv://%s:%s@cluster0.4ih5a.mongodb.net/?retryWrites=true&w=majority&appName=Cluster0";

    private MongoClient mongoClient;

    // Aquí inicializamos las variables de clase
    private CodecProvider codecProvider;
    private CodecRegistry codecRegistry;

    public MongoDBconnection() {
        // Conectar al cliente MongoDB
        mongoClient = MongoClients.create(String.format(connectionString, SchemaDB.USER, SchemaDB.PASS));

        // Registrar el codec del POJO (para que mapee automáticamente las clases)
        CodecRegistry pojoCodecRegistry = CodecRegistries.fromProviders(PojoCodecProvider.builder().automatic(true).build());

        // Usar el codecRegistry estándar junto con el del POJO
        codecRegistry = CodecRegistries.fromRegistries(
                MongoClientSettings.getDefaultCodecRegistry(),
                pojoCodecRegistry
        );
    }

    // Método para obtener la colección de alumnos
    public MongoCollection<Alumno> getAlumnosCollecion() {
        MongoDatabase database = mongoClient.getDatabase("centro_estudios").withCodecRegistry(codecRegistry);
        return database.getCollection("alumnos", Alumno.class);
    }

    // Método para obtener la colección de profesores
    public MongoCollection<Profesor> getProfesoresCollection() {
        // Obtener la colección con el codec correcto
        MongoDatabase database = mongoClient.getDatabase("centro_estudios").withCodecRegistry(codecRegistry);
        return database.getCollection("profesores", Profesor.class);
    }
}

