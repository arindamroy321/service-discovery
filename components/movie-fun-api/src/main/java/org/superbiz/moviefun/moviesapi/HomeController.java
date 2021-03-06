package org.superbiz.moviefun.moviesapi;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


import java.util.Map;

@Controller
public class HomeController {

    private final MoviesClient moviesClient;
    private final AlbumsClient albumsClient;
    private final MovieFixtures movieFixtures;
    private final AlbumFixtures albumFixtures;

    public HomeController(MoviesClient moviesClient, AlbumsClient albumsclient, MovieFixtures movieFixtures, AlbumFixtures albumFixtures) {
        this.moviesClient = moviesClient;
        this.albumsClient = albumsclient;
        this.movieFixtures = movieFixtures;
        this.albumFixtures = albumFixtures;
    }

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/setup")
    public String setup(Map<String, Object> model) {
        for (MovieInfo movie : movieFixtures.load()) {
            moviesClient.addMovie(movie);
        }

        for (AlbumInfo album : albumFixtures.load()) {
            albumsClient.addAlbum(album);

        }

        model.put("movies", moviesClient.getMovies());
        model.put("albums", albumsClient.getAlbums());

        return "setup";
    }
}
