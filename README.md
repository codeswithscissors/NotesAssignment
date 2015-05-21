# NotesAssignment
Small RESTful Notes API

The web service can be started by running mvn spring-boot:run in the root directory. The service will then be accessible
at 

http://localhost:8080/api/notes

To add a note, use the following command:

curl -i -H "Content-Type: application/json" -X POST -d '{"body" : "YOUR NOTE HERE"}' http://localhost:8080/api/notes

You can access any particular note (once added) by its ID at

http://localhost:8080/api/notes/noteId (e.g., http://localhost:8080/api/notes/3)

To retrieve the entire list of notes, access

http://localhost:8080/api/notes

To search note bodies for a string, use

http://localhost:8080/api/notes?query=searchString (e.g., http://localhost:8080/api/notes?query=milk)
