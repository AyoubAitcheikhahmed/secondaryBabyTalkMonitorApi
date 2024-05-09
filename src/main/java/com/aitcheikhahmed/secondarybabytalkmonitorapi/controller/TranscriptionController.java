package com.aitcheikhahmed.secondarybabytalkmonitorapi.controller;

import com.aitcheikhahmed.secondarybabytalkmonitorapi.model.TranscriptionResponse;
import com.aitcheikhahmed.secondarybabytalkmonitorapi.service.TranscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Set;

@RestController
@RequestMapping("/api")
public class TranscriptionController {

    private final TranscriptionService transcriptionService;

    @Autowired
    public TranscriptionController(TranscriptionService transcriptionService) {
        this.transcriptionService = transcriptionService;
    }

    @PostMapping(path = "/upload", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TranscriptionResponse> handleFileUpload(@RequestPart("file") File file) {
        try {
            TranscriptionResponse response = transcriptionService.transcribeAudio(file);
            return ResponseEntity.ok(response);
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError()
                    .body(
                            new TranscriptionResponse(
                                    false,
                                    "Failed to transcribe",
                                    Set.of("Failed to transcribe"),
                                    Set.of("Failed to transcribe")
                            ));
        }
    }




    @PostMapping(path = "/uploadTest", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> handleFileUploadTest(@RequestParam("file") MultipartFile file) {

        return ResponseEntity.ok("YES >>>>>>>>>>>>");
    }
}

