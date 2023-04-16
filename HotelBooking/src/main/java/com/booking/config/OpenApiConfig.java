package com.booking.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;

@Configuration
public class OpenApiConfig {


    @Bean
    public OpenAPI myOpenAPI() {
        Server devServer = new Server();
        devServer.setUrl("http://localhost:8080");
        devServer.setDescription("API exposes endpoint for Managing all the Hotel Bookings. \n" +
                "\n 1. Get all the Hotel rooms for booking\n" +
                "\n 2. Create Booking\n" +
                "\n 3. Update booking \n" +
                "\n 4. Delete Booking \n" +
                "\n 5. Implemented pageble interface for managing 100's of bookings\n"
            );

        Info info = new Info()
                .title("HotelBooking API")
                .version("1.0")
                .description("API exposes endpoint for Managing all the Hotel Bookings.");

        return new OpenAPI().info(info).servers(List.of(devServer));
    }
}