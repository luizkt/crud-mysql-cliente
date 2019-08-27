package br.com.fiap.service;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexService {
    @GetMapping("/")
    public String index() {
        return "Hello there! I'm running.";
    }
}