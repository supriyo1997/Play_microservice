package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import models.Computer;
import play.data.FormFactory;
import play.i18n.MessagesApi;
import play.libs.Json;
import play.libs.concurrent.HttpExecutionContext;
import play.libs.ws.WSClient;
import play.mvc.*;
import repository.CompanyRepository;
import repository.ComputerRepository;

import javax.inject.Inject;
import java.util.concurrent.CompletionStage;

/**
 * This controller contains an action to handle HTTP requests
 * to the application's home page.
 */
public class HomeController extends Controller {

    private final ComputerRepository computerRepository;
    private final CompanyRepository companyRepository;
    private final FormFactory formFactory;
    private final HttpExecutionContext httpExecutionContext;
    private final MessagesApi messagesApi;
    private final WSClient ws;

    @Inject
    public HomeController(FormFactory formFactory,
                          ComputerRepository computerRepository,
                          CompanyRepository companyRepository,
                          HttpExecutionContext httpExecutionContext,
                          MessagesApi messagesApi, WSClient ws) {
        this.computerRepository = computerRepository;
        this.formFactory = formFactory;
        this.companyRepository = companyRepository;
        this.httpExecutionContext = httpExecutionContext;
        this.messagesApi = messagesApi;
        this.ws = ws;
    }


    public CompletionStage<Result> save(Http.Request request) {

        JsonNode jsonNode = request.body().asJson();

        Computer computer= Json.fromJson(jsonNode, Computer.class);


        return computerRepository.insert(computer).thenApplyAsync(data -> {
            // This is the HTTP rendering thread context
            System.out.println(data);
            return ok("success");
        }, httpExecutionContext.current());
    }
    public Result index() {
        return ok(views.html.index.render());
    }

}
