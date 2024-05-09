package com.aitcheikhahmed.secondarybabytalkmonitorapi.service;

import com.aitcheikhahmed.secondarybabytalkmonitorapi.factory.AiClientFactory;
import com.aitcheikhahmed.secondarybabytalkmonitorapi.model.TranscriptionResponse;
import com.aitcheikhahmed.secondarybabytalkmonitorapi.utility.linguistics.LinguisticAnalysis;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.assemblyai.api.AssemblyAI;
import com.assemblyai.api.resources.transcripts.types.Transcript;import com.assemblyai.api.AssemblyAI;
import com.assemblyai.api.resources.transcripts.types.Transcript;

import java.io.File;
import java.io.IOException;

@Service
public class TranscriptionService {

    private final String API_KEY = "7a965374cf5c496e88ad3905170fef3d";
    private AiClientFactory aiClientFactory;
    private AssemblyAI assemblyAiClient;

    public TranscriptionService(AiClientFactory aiClientFactory) {
        this.aiClientFactory = aiClientFactory;
    }

    public TranscriptionResponse transcribeAudio(File audioFile) throws IOException {
        AssemblyAI client = aiClientFactory.createAIClient();

        try {
            Transcript transcript = client.transcripts().transcribe(audioFile);
            LinguisticAnalysis linguisticAnalysis = new LinguisticAnalysis();
            return linguisticAnalysis.analyzeTranscription(transcript.toString());

        } catch (Exception e) {
            throw new IOException(e);
        }
    }
}