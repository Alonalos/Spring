package com.cybertek.controller;


import com.cybertek.entity.Genre;
import com.cybertek.entity.MovieCinema;
import com.cybertek.repository.GenreRepository;
import com.cybertek.repository.MovieCinemaRepository;
import com.cybertek.repository.MovieRepository;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class WebFluxController {

    private WebClient webClient= WebClient.builder().baseUrl("http://localhost:8080").build(); //we consume own API

    private MovieCinemaRepository movieCinemaRepository;
    private GenreRepository genreRepository;

    public WebFluxController(MovieCinemaRepository movieCinemaRepository, GenreRepository genreRepository) {
        this.movieCinemaRepository = movieCinemaRepository;
        this.genreRepository = genreRepository;
    }

    @GetMapping("/flux-movie-cinemas")
    public Flux<MovieCinema> readAllCinemasFlux(){
        return Flux.fromIterable(movieCinemaRepository.findAll());
    }

    @GetMapping("/mono-movie-cinema/{id}")
    public Mono<MovieCinema> readById(@PathVariable("id") Long id){
        return Mono.just(movieCinemaRepository.findById(id).get());
    }

    @GetMapping("/mono-movie-cinema")
    public Mono<MovieCinema> readByIdRequestParam(@RequestParam("id") Long id){
        return Mono.just(movieCinemaRepository.findById(id).get());
    }

    @PostMapping("/create-genre")
    public Mono<Genre> createGenre(@RequestBody Genre genre){
        Genre createdGenre=genreRepository.save(genre);
        return Mono.just(createdGenre);
        //return Mono.just(genreRepository.save(genre));
    }

    @PutMapping ("/update")
    public Mono<Genre> updateGenre(@RequestBody Genre genre){
        Genre updatedGenre=genreRepository.save(genre);
        return Mono.just(updatedGenre);
    }

    @DeleteMapping("/delete-genre/{id}")
    public Mono<Void> deleteGenre(@PathVariable("id") Long id){
        genreRepository.deleteById(id);
        return Mono.empty();
    }

    //----------- Web Client Example-----------


    @GetMapping("/flux") //"/flux" endpoint will consume "/flux-movie-cinemas" API
    public Flux<MovieCinema> readWithWebClient(){
        return webClient
                .get()
                .uri("/flux-movie-cinemas") //we specify the specific endpoint (API that we created earlier) since the base URI is already declared
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .retrieve()
                .bodyToFlux(MovieCinema.class);
        //we are calling another service
    }

    @GetMapping("/mono/{id}")
    public Mono<MovieCinema> readMonoWithWebClient(@PathVariable("id") Long id){
        return  webClient
                .get()
                .uri("/mono-movie-cinema/{id}",id)
                .retrieve()
                .bodyToMono(MovieCinema.class);

    }

    @GetMapping("/mono-rp")
    public Mono<MovieCinema> readMonoWithWebclientRequestParam(@RequestParam("id") Long id){
        return webClient
                .get()
                .uri(uriBuilder ->
                        uriBuilder
                                .path("/mono-movie-cinema")
                                .queryParam("id",id)
                                .build()
                )
                .retrieve()
                .bodyToMono(MovieCinema.class);
    }
    @PostMapping("/create")
    public Mono<Genre> createWebClient(@RequestBody Genre genre){
        return webClient
                .post()
                .uri("/create-genre")
                .body(Mono.just(genre),Genre.class)
                .retrieve()
                .bodyToMono(Genre.class);
    }

//    @DeleteMapping("/delete/{id}")
//    public Mono<Void> deleteWebClient(@PathVariable("id") Long id){
//        return webClient.delete()
//                .uri("/delete-genre/{id}",id)
//                .retrieve()
//                .bodyToMono(Void.class);
//    }
//
    @DeleteMapping("/delete/{id}")
    public Mono<Void> deleteWebClient(@PathVariable("id") Long id) throws Exception {

        Integer countGenres = genreRepository.countGenresNativeQuery(id);
        if (countGenres > 0) {
            throw new Exception("Genre can't be deleted, is linked by a movie");
        }

        return webClient.delete()
                .uri("/delete-genre/{id}",id)
                .retrieve()
                .bodyToMono(Void.class);
    }
}

