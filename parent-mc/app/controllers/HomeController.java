package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.itextpdf.text.DocumentException;
import models.Computer;
import play.data.Form;
import play.data.FormFactory;
import play.i18n.MessagesApi;
import play.libs.Json;
import play.libs.concurrent.HttpExecutionContext;
import play.libs.ws.WSClient;
import play.libs.ws.WSRequest;
import play.libs.ws.WSResponse;
import play.mvc.*;
import repository.CompanyRepository;
import repository.ComputerRepository;

import javax.inject.Inject;
import java.sql.SQLException;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.ExecutionException;

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

    public CompletionStage<Result> list() {
        // Run a db operation in another thread (using DatabaseExecutionContext)


        return computerRepository.page().thenApplyAsync(comp -> {
            // This is the HTTP rendering thread context
            return ok(Json.toJson(comp));
        }, httpExecutionContext.current());
    }

    public CompletionStage<Result> delete(Long id) {
        // Run delete db operation, then redirect
        return computerRepository.delete(id).thenApplyAsync(v -> {
            // This is the HTTP rendering thread context
            return ok("success", "Computer has been deleted");
        }, httpExecutionContext.current());
    }

    public CompletionStage<Result> saveComputer(Http.Request request) {
        Form<Computer> computerForm = formFactory.form(Computer.class).bindFromRequest(request);


        Computer computer = computerForm.get();
        // Run insert db operation, then redirect

        return computerRepository.insertCrud(computer);




    }

    public CompletionStage<Result> pdfCreateUpload(Long id)
            throws SQLException, ExecutionException, InterruptedException {

        CompletableFuture<Optional<Computer>> comp =computerRepository.lookup(id);
        return comp.thenApply(c-> {
            Computer com = c.get();

            String url = "http://localhost:9002/createPdf";


            // Create a WSRequest object
            WSRequest req = ws.url(url);
            JsonNode jsonNode = Json.toJson(com);

            // Make a POST request with JSON payload
            CompletionStage<WSResponse> responsePromise = req
                    .addHeader("Content-Type", "application/json")
                    .post(jsonNode);

            // Handle the response asynchronously
            return responsePromise.thenApply(response -> {
                // Process the response here
                String responseBody = response.getBody();
                return ok(responseBody);
            });
        }).thenApply(c->ok("sccess"));
    }

    public CompletionStage<Result> pdfDownload(Long id)
            throws SQLException, ExecutionException, InterruptedException {




            String url = "http://localhost:9002/downloadPdf/"+id.toString();


            // Create a WSRequest object
            WSRequest req = ws.url(url);


            // Make a GET request
            CompletionStage<WSResponse> responsePromise = req.get();


            // Handle the response asynchronously
            return responsePromise.thenApply(response -> {
                 System.out.println("rdhthghghg "+response.getBody().toString());
                return ok(response.getBody());
            });

    }
    public Result index() {
        return ok(views.html.index.render());
    }

}
