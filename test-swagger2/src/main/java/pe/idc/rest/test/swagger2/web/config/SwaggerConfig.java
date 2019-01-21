package pe.idc.rest.test.swagger2.web.config;

import com.google.common.collect.Lists;
import static com.google.common.collect.Lists.newArrayList;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.RequestMethod;
import springfox.documentation.builders.ApiInfoBuilder;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.Contact;
import springfox.documentation.service.SecurityScheme;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.ApiKeyVehicle;
import springfox.documentation.swagger.web.SecurityConfiguration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 *
 * @author j0s3
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    public static final String AUTHORIZATION_HEADER = "Authorization";
    public static final String DEFAULT_INCLUDE_PATTERN = "/api/.*";

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("pe.idc.rest.test.swagger2.web"))
                .paths(PathSelectors.any())
                .build()
                .securitySchemes(Lists.newArrayList(apiKey()))
                .apiInfo(apiInfo())
                .useDefaultResponseMessages(false)
                .globalResponseMessage(RequestMethod.GET, newArrayList(new ResponseMessageBuilder().code(500)
                        .message("500 message")
                        .build(),
                        new ResponseMessageBuilder().code(403)
                                .message("Forbidden!!!!!")
                                .build()));
    }

    @Bean
    public SecurityConfiguration security() {
        return new SecurityConfiguration("client", "secret", "", "", "Bearer access token", ApiKeyVehicle.HEADER, HttpHeaders.AUTHORIZATION, "");
    }

    @Bean
    SecurityScheme apiKey() {
        return new ApiKey("JWT", AUTHORIZATION_HEADER, "header");
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title("Test Sample Swagger 2").description("")
                .termsOfServiceUrl("https://www.example.com/api")
                .contact(new Contact("Jose Corcuera", "http://1ntr3p1d.blogspot.com", ""))
                .license("Open Source")
                .licenseUrl("\"https://www.apache.org/licenses/LICENSE-2.0")
                .version("1.0.0")
                .build();
    }
}
