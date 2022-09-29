package Repository;

import Model.Movie;
import io.quarkus.mongodb.panache.PanacheMongoRepository;
import io.quarkus.panache.common.Sort;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;


@ApplicationScoped
public class MovieRepository implements PanacheMongoRepository<Movie> {
    public Movie findByTitle(String title){
        return find("title", title).firstResult();
    }

    public List<Movie> allMovies(){
        return  listAll(Sort.by("title"));
    }

    public Movie findByAuthor(String author){
        return find("author", author).firstResult();
    }

}
