package com.sanvalero.animalfluxclient;

import com.sanvalero.animalfluxclient.domain.Animal;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

import java.util.concurrent.Executors;

/**
 * Creado por @ author: Pedro Orós
 * el 12/05/2021
 */

public class  Application {

    public static void main(String[] args) {
        WebClient webClient = WebClient.create("http://localhost:8080");
        Flux<Animal> animalsFlux = webClient.get()
                .uri("/animals")
                .retrieve()
                .bodyToFlux(Animal.class);

        animalsFlux.doOnError((System.out::println))
                .subscribeOn(Schedulers.fromExecutor(Executors.newCachedThreadPool()))
                .doOnComplete(() -> System.out.println("Terminado"))
                .subscribe((animal) -> {

                    try {
                        System.out.println("Haciendo algo con " + animal.getName() + " . . .");
                        Thread.sleep(5000);
                    } catch (InterruptedException ie) {
                        ie.printStackTrace();
                    }

                    System.out.println("Consumidor 1: " + animal.getName());
                });

        animalsFlux.doOnError((System.out::println))
                .subscribeOn(Schedulers.fromExecutor(Executors.newCachedThreadPool()))
                .doOnComplete(() -> System.out.println("Terminado"))
                .subscribe((animal) -> System.out.println("Consumidor 2: " + animal.getName()));

        System.out.println(animalsFlux.count().block());

        System.out.println("Listo");
    }
}
