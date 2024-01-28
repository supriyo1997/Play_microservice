package repository;

import io.ebean.Ebean;
import io.ebean.EbeanServer;
import models.PdfStore;
import play.db.ebean.EbeanConfig;

import javax.inject.Inject;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

import static java.util.concurrent.CompletableFuture.supplyAsync;

public class PdfRepository {
    private final EbeanServer ebeanServer;
    private final DatabaseExecutionContext executionContext;
    private final EbeanConfig ebeanConfig;


    @Inject
    public PdfRepository(EbeanConfig ebeanConfig, DatabaseExecutionContext executionContext, EbeanConfig ebeanConfig1) {
        this.ebeanServer = Ebean.getServer(ebeanConfig.defaultServer());
        this.executionContext = executionContext;

        this.ebeanConfig = ebeanConfig1;
    }

    public CompletionStage<Long> insert(PdfStore pdfStore) {
        return CompletableFuture.supplyAsync(() -> {
            //computer.id = System.currentTimeMillis(); // not ideal, but it works
            ebeanServer.insert(pdfStore);
            return pdfStore.id;
        }, executionContext);
    }

    public CompletableFuture<Optional<PdfStore>> lookup(Long id) {
        return supplyAsync(() -> Optional.ofNullable(ebeanServer.find(PdfStore.class).setId(id).findOne()), executionContext);
    }
}
