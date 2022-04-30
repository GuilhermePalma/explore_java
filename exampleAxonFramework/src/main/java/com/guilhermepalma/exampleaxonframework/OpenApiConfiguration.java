package com.guilhermepalma.exampleAxonFramework;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(
        info = @Info(
                title = "Example Axon Framework, DDD, CQRS, Event Sourcing",
                description = "Essa API foi criada com o Objetivo de Explorar e Desenvolver uma REST API utilizando do " +
                        "Axon Framework, DDD (Domain-Drive Design) e Event Sourcing",
                contact = @Contact(
                        name = "Guilherme Palma",
                        url = "https://www.linkedin.com/in/guilherme-peres-lins-da-palma/",
                        email = "guippalma@gmail.com"
                )
        ),
        servers = @Server(url = "http://localhost:8080")
)
class OpenAPIConfiguration {
}
