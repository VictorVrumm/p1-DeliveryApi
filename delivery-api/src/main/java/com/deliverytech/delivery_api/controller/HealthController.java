package com.deliverytech.delivery_api.controller;

// Imports do Spring Boot
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

// Imports do Swagger/OpenAPI
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;

// Imports do Java
import java.time.LocalDateTime;
import java.util.Map;

// O Controller
@RestController
@Tag(name = "Health Check", description = "Verifica o status da API e exibe informações do sistema")
public class HealthController {

    // --- ENDPOINT: GET /health ---
    
    @Operation(
        summary = "Verifica o status da API",
        description = "Retorna informações básicas como status, timestamp, versão do Java e nome do serviço."
    )
    @ApiResponse(
        responseCode = "200",
        description = "API em funcionamento",
        content = @Content(mediaType = "application/json") // Indica que a resposta é um JSON
    )
    @GetMapping("/health")
    public Map<String, String> health() {
        return Map.of(
            "status", "UP",
            "timestamp", LocalDateTime.now().toString(),
            "service", "Delivery API",
            "javaVersion", System.getProperty("java.version")
        );
    }

    // --- ENDPOINT: GET /info (já completo no seu código) ---

    @Operation(
        summary = "Informações da aplicação",
        description = "Retorna informações detalhadas da aplicação como nome, versão, desenvolvedor e frameworks."
    )
    @ApiResponse(
        responseCode = "200",
        description = "Informações da aplicação retornadas com sucesso",
        content = @Content(
            mediaType = "application/json",
            schema = @Schema(implementation = AppInfo.class) // Garante que o Swagger entenda o formato da resposta
        )
    )
    @GetMapping("/info")
    public AppInfo info() {
        return new AppInfo(
            "Delivery Tech API",
            "1.0.0",
            "Anderson Buenos",
            "JDK 21",
            "Spring Boot 3.5.3"
        );
    }

    // O Record/Model (representa a estrutura da resposta JSON)
    public record AppInfo(
        String application,
        String version,
        String developer,
        String javaVersion,
        String framework
    ) {}
}