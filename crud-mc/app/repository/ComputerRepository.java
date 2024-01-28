package repository;

import io.ebean.Ebean;
import io.ebean.EbeanServer;
import models.Computer;
import play.db.ebean.EbeanConfig;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

import static java.util.concurrent.CompletableFuture.supplyAsync;

/**
 * A repository that executes database operations in a different
 * execution context.
 */
public class ComputerRepository {

    private final EbeanServer ebeanServer;
    private final DatabaseExecutionContext executionContext;


    @Inject
    public ComputerRepository(EbeanConfig ebeanConfig, DatabaseExecutionContext executionContext) {
        this.ebeanServer = Ebean.getServer(ebeanConfig.defaultServer());
        this.executionContext = executionContext;

    }


    public CompletionStage<Long> insert(Computer computer) {
        return CompletableFuture.supplyAsync(() -> {
             //computer.id = System.currentTimeMillis(); // not ideal, but it works
             ebeanServer.insert(computer);
             return computer.id;
        }, executionContext);
    }
}
