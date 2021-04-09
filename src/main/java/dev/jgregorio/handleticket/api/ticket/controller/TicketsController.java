package dev.jgregorio.handleticket.api.ticket.controller;

import com.google.cloud.vision.v1.EntityAnnotation;
import dev.jgregorio.handleticket.api.ticket.model.NormalizedText;
import dev.jgregorio.handleticket.api.ticket.service.GoogleVisionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/tickets")
public class TicketsController {

    Logger logger = LoggerFactory.getLogger(TicketsController.class);

    @GetMapping(produces = {"application/json"})
    public ResponseEntity<String> get() {
        return ResponseEntity.ok().body("OK");
    }

    @PostMapping(produces = "application/json")
    public ResponseEntity<List<NormalizedText>> handleFileUpload(@RequestParam("file") MultipartFile file) {
        List<NormalizedText> texts = null;
        try {
            List<EntityAnnotation> annotations = GoogleVisionService.getAnnotations(file.getInputStream());
            texts = annotations.stream().map(annotation -> new NormalizedText(annotation)).collect(Collectors.toList());
        } catch (IOException e) {
            logger.error("GoogleVisionService failed", e);
        }
        return new ResponseEntity<>(texts, HttpStatus.OK);
    }
}