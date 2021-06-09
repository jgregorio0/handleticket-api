package dev.jgregorio.handleticket.api.ticket;

import com.google.cloud.vision.v1.EntityAnnotation;
import dev.jgregorio.handleticket.api.ticket.model.NormalizedText;
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
@RequestMapping("/v1/api/tickets")
public class TicketController {

    Logger logger = LoggerFactory.getLogger(TicketController.class);

    @GetMapping(produces = {"application/json"})
    public ResponseEntity<String> get() {
        return ResponseEntity.ok().body("OK");
    }

    @PostMapping(produces = "application/json")
    public ResponseEntity<List<NormalizedText>> handleFileUpload(@RequestParam("file") MultipartFile file) {
        ResponseEntity response = null;
        try {
            List<EntityAnnotation> annotations = GoogleVisionService.getAnnotations(file.getInputStream());
            List<NormalizedText> texts = annotations.stream().map(annotation -> new NormalizedText(annotation)).collect(Collectors.toList());
            response = new ResponseEntity<>(texts, HttpStatus.OK);
        } catch (IOException e) {
            logger.error("GoogleVisionService failed", e);
            response = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return response;
    }
}