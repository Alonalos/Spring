package com.cybertek.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenApi(){

        SecurityScheme securitySchemeItem=new SecurityScheme();
        securitySchemeItem.setType(SecurityScheme.Type.HTTP);
        securitySchemeItem.setScheme("bearer");//token
        securitySchemeItem.setBearerFormat("JWT");//what is the format?
        securitySchemeItem.setIn(SecurityScheme.In.HEADER);//where is the token? in the header
        securitySchemeItem.setName("Authorization");//what is the key in the header?
        io.swagger.v3.oas.models.info.Info infoVersion=
                new io.swagger.v3.oas.models.info.Info().title("Cybertek API").version("snapshot");//info object

        SecurityRequirement securityItem=new SecurityRequirement().addList("bearer-jwt", Arrays.asList("read","write"));

        return new OpenAPI()
                .components(new Components()
                .addSecuritySchemes("bearer-jwt",securitySchemeItem))
                .info(infoVersion)
                .addSecurityItem(securityItem);


    }
}
