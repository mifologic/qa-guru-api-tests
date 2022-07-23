import config.CredentialsConfig;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.aeonbits.owner.ConfigFactory;

import static io.restassured.RestAssured.with;

public class Specs {

    static CredentialsConfig config = ConfigFactory.create(CredentialsConfig.class);

    public static RequestSpecification request = with()
            .baseUri(config.baseUrl())
            .basePath(config.basePath())
            .log().all()
            .contentType(ContentType.JSON);

    public static ResponseSpecification response = new ResponseSpecBuilder()
            .expectStatusCode(200)
            .build();
}
