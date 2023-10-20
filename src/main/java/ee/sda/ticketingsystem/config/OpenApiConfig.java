package ee.sda.ticketingsystem.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.Operation;
import io.swagger.v3.oas.models.PathItem;
import io.swagger.v3.oas.models.Paths;
import io.swagger.v3.oas.models.media.Content;
import io.swagger.v3.oas.models.media.MediaType;
import io.swagger.v3.oas.models.media.Schema;
import io.swagger.v3.oas.models.parameters.RequestBody;
import io.swagger.v3.oas.models.responses.ApiResponses;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class OpenApiConfig {
    @Bean
    public OpenAPI myOpenAPI() {
        final OpenAPI openapi = new OpenAPI();

        final var stringSchema = new Schema<String>().type("string");

        Map<String, Schema> formProps = new HashMap<>();  // Use raw types
        formProps.put("username", stringSchema);
        formProps.put("password", stringSchema);

        Schema rawFormSchema = new Schema<>().type("object"); // Use raw types
        rawFormSchema.setProperties(formProps);
        Schema<Object> formSchema = (Schema<Object>) rawFormSchema; // Cast it to Schema<Object>


        final MediaType formUrlEncodedMediaType = new MediaType().schema(formSchema);

        final RequestBody requestBody = new RequestBody()
                .description("User credentials")
                .content(new Content()
                        .addMediaType("application/x-www-form-urlencoded", formUrlEncodedMediaType));

        final Operation postLoginOperation = new Operation()
                .addTagsItem("login")
                .summary("Logs in a user")
                .operationId("login")
                .requestBody(requestBody)
                .responses(new ApiResponses()); // You can set up responses here

        Paths paths = new Paths();
        paths.addPathItem("/api/v1/user/login", new PathItem().post(postLoginOperation));

        openapi.paths(paths);

        return openapi;
    }


}
