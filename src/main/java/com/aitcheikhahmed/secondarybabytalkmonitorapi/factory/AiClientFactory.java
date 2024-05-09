package com.aitcheikhahmed.secondarybabytalkmonitorapi.factory;

import com.assemblyai.api.AssemblyAI;
import org.springframework.stereotype.Component;

@Component
public class AiClientFactory {
    private  static final String API_KEY = "7a965374cf5c496e88ad3905170fef3d";



    public AssemblyAI createAIClient(){
        return AssemblyAI.builder()
                .apiKey(System.getenv(API_KEY))
                .build();
    }
}
