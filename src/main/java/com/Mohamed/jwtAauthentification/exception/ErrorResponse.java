package com.Mohamed.jwtAauthentification.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ErrorResponse {

    private LocalDateTime timestamp;
    private HttpStatus status;
    private String message;

    //@Builder.Default pour initialiser la propriété errors avec une instance d'ArrayList par défaut.
    @Builder.Default
    private List<String> errors = new ArrayList<>();

}
/*
*  l'annotation @Builder. Cette annotation est utilisée pour générer automatiquement un constructeur de style constructeur de type de construction (builder pattern) pour la classe. Cela permet de créer des instances de la classe ErrorDto de manière plus concise et lisible en utilisant le constructeur de type constructeur de type construction, plutôt qu'en utilisant un constructeur avec de nombreux paramètres.*/