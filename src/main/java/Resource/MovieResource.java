package Resource;

import Model.Movie;
import Repository.MovieRepository;
import org.bson.types.ObjectId;
import org.jboss.resteasy.annotations.jaxrs.PathParam;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;


@Path("/movies")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class MovieResource {

    @Inject MovieRepository movieRepository;


    @POST
    @Path("/post")
    public Response createMovie(Movie newMovie){
        movieRepository.persist(newMovie);
        return  Response.created(URI.create("/movies" + newMovie.getId().toString())).build();
    }
//GET BY ID
    @GET
    @Path("/id/{id}")
    public Response getByID(@PathParam("id") String id) {
        Movie movie = movieRepository.findById(new ObjectId(id));
        return Response.ok(movie).build();
    }
//GET ALL
    @GET
    public Response getAll(){
    return Response.ok(movieRepository.listAll()).build();
    }
//GET BY AUTHOR
    @GET
    @Path("/author/{author}")
    public Response getByAuthor(@PathParam("author") String author){
        Movie movie = movieRepository.findByAuthor(author);
        return  Response.ok(movie).build();
    }
//GET BY TITLE
    @GET
    @Path("/title/{title}")
    public Response getByTitle(@PathParam("title") String title){
        Movie movie = movieRepository.findByTitle(title);
        return  Response.ok(movie).build();
    }


    @DELETE
    @Transactional
    @Path("/delete/{id}")
    public  void deleteMovieByID(@PathParam("id") String id){
        Movie movie = movieRepository.findById(new ObjectId(id));
        movieRepository.delete(movie);
    }

    @PUT
    @Path("/put/{id}")
    @Transactional
    public Response updateMovie(@PathParam("id") String id, Movie updateMovie){
        updateMovie.setId(new ObjectId(id));
        movieRepository.update(updateMovie);
        return Response.ok(updateMovie).build();
    }
}
