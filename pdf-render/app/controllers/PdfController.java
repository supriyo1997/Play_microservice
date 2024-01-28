package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.google.inject.Inject;
import com.itextpdf.text.DocumentException;
import models.Computer;
import models.PdfStore;
import play.db.ebean.EbeanConfig;
import play.libs.Json;
import play.libs.concurrent.HttpExecutionContext;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;
import play.mvc.Results;
import repository.ComputerRepository;
import repository.PdfRepository;
import util.PdfGenerator;

import java.sql.SQLException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.ExecutionException;
//import io.ebean.types.Blob;

public class PdfController extends Controller {

     private final ComputerRepository compRep;
     private final PdfRepository pdfrep;
    private final HttpExecutionContext httpExecutionContext;
    private final EbeanConfig ebeanConfig;

    private PdfStore p;
    private byte[] pdfContent;
    @Inject
    public PdfController(ComputerRepository compRep, PdfRepository pdfrep, HttpExecutionContext httpExecutionContext, EbeanConfig ebeanConfig, PdfStore p) {
        this.compRep = compRep;
        this.pdfrep = pdfrep;
        this.httpExecutionContext = httpExecutionContext;
        this.ebeanConfig = ebeanConfig;
        this.p=p;
    }

    public CompletionStage<Result> generateAndStorePdf(Http.Request request)
            throws SQLException, ExecutionException, InterruptedException {

        JsonNode jsonNode = request.body().asJson();

        Computer computer= Json.fromJson(jsonNode, Computer.class);


            try {
                pdfContent = PdfGenerator.generatePdfFromData(computer);
                System.out.println("supriyo1  "+pdfContent);

                String filename = computer.id.toString()+".pdf"; // Provide a meaningful filename

                // Blob b=new SerialBlob(pdfContent);
                p.setId(1L);
                p.setContent( pdfContent);
                p.setFilename(filename);
            } catch (DocumentException e) {
                throw new RuntimeException(e);
            }
           return pdfrep.insert(p).thenApplyAsync(data -> {
               // This is the HTTP rendering thread context
               System.out.println(data);
               return ok("success");
           }, httpExecutionContext.current());




    }
    public CompletableFuture<Result> downloadPdf(Long id) {
        // Asynchronously retrieve the PdfModel from the database


        return pdfrep.lookup(id).thenApplyAsync(optionalPdfModel -> {
            // Check if the PdfModel exists
            if (optionalPdfModel.isPresent()) {
                PdfStore pdfModel = optionalPdfModel.get();


                // Stream the content directly to the response
                return Results.ok(pdfModel.getContent()).withHeader("Content-Type", "application/pdf")
                        .withHeader("Content-Disposition", "attachment; filename=example.pdf");;
            } else {
                // PdfModel not found, return a 404 Not Found result
                return null;
            }
        }, httpExecutionContext.current());
    }
}