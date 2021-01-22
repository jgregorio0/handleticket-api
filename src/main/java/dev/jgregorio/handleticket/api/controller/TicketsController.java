package dev.jgregorio.handleticket.api.controller;

import com.google.cloud.vision.v1.EntityAnnotation;
import dev.jgregorio.handleticket.api.model.NormalizedText;
import dev.jgregorio.handleticket.api.service.GoogleVisionService;
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
            e.printStackTrace();//TODO add log
        }
        return new ResponseEntity<>(texts, HttpStatus.OK);
    }
}