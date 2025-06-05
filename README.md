# PROYECTO 6

## MES 06 SPRING BOOT DESDE CERO - ARQUITECTURA PROFESIONAL PARA APLICACIONES BACKEND

### RETO 06 - PROYECTO GESTION DE LIBRERIA

#### CONTENIDO
- Saludo
- Modelos
- Peticiones de la aplicación
- Repositorio
- Conclusiones

# Saludo
Cordial saludo, familia Dev Senior! Espero que se encuentren muy bien. Me da el grato gusto de presentarles este proyecto que fue bastante divertido y muy emotivo al realizarlo. Espero que les gusten y que sigamos creciendo más en esta bella profesión.
# Modelos
Para este proyecto se utilizaron dos tipos de modelos (DTO, entities) donde el DTO se utiliza para la interacción con el usuario para evitar tipos de manipulación a tipos de datos de integridad explicita que ayuda al modelo entities para llevar esta comunicación y así manejar la integracion de datos.
Para este proyecto llevamos el modelo:
* Libro - modelo entity
* LibroDTO - modelo dto
* Estado - modelo dto tipo Enum que nos ayuda a manejar el comportamiento del libro

# Peticiones de la aplicación 
Los controladores que usamos en este proyecto fueron: 
**Class LibroController(localhost:8080/api/libros)**

**GET searchBooks(localhost:8080/api/libros/buscar)** 

Donde nos va a devolver una lista de libros, aplica tambien para la busqueda tanto titulo, auto o isnb dependiendo el tipo de dato que le solicitemos, si no va dado el caso, nos consulta a todos sin excepciones. 
Para poder aplicar los filtros lo podemos realizar de la siguiente manera: 
- localhost:8080/api/libros/buscar (de esta manera traes todos los libros)
- localhost:8080/api/libros/buscar?titulo={titulo-consultador}
- localhost:8080/api/libros/buscar?autor={autor-consultador}
- localhost:8080/api/libros/buscar?isbn={isbn-consultador}

**GET getBookById(localhost:8080/api/libros/{id})** 
Donde nos va a devolver un Libro por un ID, esta consulta solo recibirá el parametro del ID 

**POST saveBook(localhost:8080/api/libros)** 
Acá tenemos la opcion de poder guardar un libro en la base de datos, el cuerpo del libro es el siguiente: 
{
   "titulo" : "{Titulo del libro}",
   "autor" : "{Autor del libro}",
   "isbn" : "{ISBN del libro}",
   "añoPublicado" : {Año Publicado},
   "genero" : "{Genero del libro}"
}

**POST changeStatusBook(localhost:8080/api/libros/{id}/estado)**
Esta petición POST nos ayuda a manejar el comportamiento del libro, es decir, cambiar su estado.
Los parametros requeridos son:
- ID - El id del libro
- estado - Las opciones del estado son {DISPONIBLE, RESERVADO, OCUPADO, AGOTADO}. Si no aplicas los indicados anteriormente, te va a generar un error (409), los puedes digitar como una cadena

**PUT editBook(localhost:8080/api/libros/{id})**
Esta petición PUT nos va a editar el libro que solicitemos por medio del ID, el cuerpo que necesitamos es:
{
   "titulo" : "{Titulo del libro}",
   "autor" : "{Autor del libro}",
   "isbn" : "{ISBN del libro}",
   "añoPublicado" : {Año Publicado},
   "genero" : "{Genero del libro}"
}
Si el ID no se encuentra en la base de datos, va a arrojar un error 404

**DEL deleteBook(localhost:8080/api/libros/{id})**
Esta petición lo que nos va a realizar es eliminar el libro que le solicitemos con el ID de la base de datos.
Si el ID no se encuentra en la base de datos, va a arrojar un error 404

# Repositorio
