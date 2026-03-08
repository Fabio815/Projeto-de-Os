package br.com.config;

import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;
//Isso serve para ligar minha api REST ao servidor, pois herda do application. Basicamente fala que meu proj tem endpoints.
@ApplicationPath("/api")
public class RestConfig extends Application {
}
