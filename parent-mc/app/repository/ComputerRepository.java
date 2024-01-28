package repository;

import com.fasterxml.jackson.databind.JsonNode;
import io.ebean.Ebean;
import io.ebean.EbeanServer;
import io.ebean.Model;
import models.Computer;
import play.db.ebean.EbeanConfig;
import play.libs.Json;
import play.libs.ws.WSClient;
import play.libs.ws.WSRequest;
import play.libs.ws.WSResponse;
import play.mvc.Result;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

import static java.util.concurrent.CompletableFuture.supplyAsync;
import static play.mvc.Results.ok;

/**
 * A repository that executes database operations in a different
 * execution context.
 */
public class ComputerRepository {

    private final EbeanServer ebeanServer;
    private final DatabaseExecutionContext executionContext;
    private final WSClient ws;


    @Inject
    public ComputerRepository(EbeanConfig ebeanConfig, DatabaseExecutionContext executionContext ,WSClient ws) {
        this.ebeanServer = Ebean.getServer(ebeanConfig.defaultServer());
        this.executionContext = executionContext;
        this.ws=ws;

    }



    public CompletionStage<List<Computer>> page() {
        return supplyAsync(() ->
                ebeanServer.find(Computer.class)
                        .findList(), executionContext).thenApply(list -> {
            List<Computer> options = new ArrayList<>();
            for (Computer c : list) {
                options.add(c);
            }
            return options;
        });
    }

    public CompletionStage<Optional<Long>>  delete(Long id) {
        return supplyAsync(() -> {
            try {
                final Optional<Computer> computerOptional = Optional.ofNullable(ebeanServer.find(Computer.class).setId(id).findOne());
                computerOptional.ifPresent(Model::delete);
                return computerOptional.map(c -> c.id);
            } catch (Exception e) {
                return Optional.empty();
            }
        }, executionContext);
    }


 public CompletableFuture<Optional<Computer>> lookup(Long id) {
        return supplyAsync(() -> Optional.ofNullable(ebeanServer.find(Computer.class).setId(id).findOne()), executionContext);
    }
 /*
    public CompletionStage<Optional<Long>> update(Long id, Computer newComputerData) {
        return supplyAsync(() -> {
            Transaction txn = ebeanServer.beginTransaction();
            Optional<Long> value = Optional.empty();
            try {
                Computer savedComputer = ebeanServer.find(Computer.class).setId(id).findOne();
                if (savedComputer != null) {
                    savedComputer.company = newComputerData.company;
                    savedComputer.discontinued = newComputerData.discontinued;
                    savedComputer.introduced = newComputerData.introduced;
                    savedComputer.name = newComputerData.name;

                    savedComputer.update();
                    txn.commit();
                    value = Optional.of(id);
                }
            } finally {
                txn.end();
            }
            return value;
        }, executionContext);
    }

    public CompletionStage<Optional<Long>>  delete(Long id) {
        return supplyAsync(() -> {
            try {
                final Optional<Computer> computerOptional = Optional.ofNullable(ebeanServer.find(Computer.class).setId(id).findOne());
                computerOptional.ifPresent(Model::delete);
                return computerOptional.map(c -> c.id);
            } catch (Exception e) {
                return Optional.empty();
            }
        }, executionContext);
    }*/

    public CompletionStage<Result> insertCrud(Computer computer)
    {
        String url = "http://localhost:9001/create";



        // Create a WSRequest object
        WSRequest req = ws.url(url);
        JsonNode jsonNode = Json.toJson(computer);

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
    }
    public CompletionStage<Long> insert(Computer computer) {
        return CompletableFuture.supplyAsync(() -> {
             //computer.id = System.currentTimeMillis(); // not ideal, but it works
             ebeanServer.insert(computer);
             return computer.id;
        }, executionContext);
    }
}
