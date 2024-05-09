package com.aitcheikhahmed.secondarybabytalkmonitorapi.model;

import com.assemblyai.api.AssemblyAI;
import com.assemblyai.api.resources.transcripts.types.Transcript;

import java.util.Set;

public class TranscriptionResponse {

    private boolean success;
    private String fullTranscription;
    private Set<String> verkleinwoorden;
    private Set<String> nietzeggendewoorden;

    public TranscriptionResponse(boolean success, String fullTranscription, Set<String> verkleinwoorden, Set<String> nietzeggendewoorden) {
        this.success = success;
        this.fullTranscription = fullTranscription;
        this.verkleinwoorden = verkleinwoorden;
        this.nietzeggendewoorden = nietzeggendewoorden;
    }

    @Override
    public String toString() {
        return "TranscriptionResponse{" +
                "success=" + success +
                ", fullTranscription='" + fullTranscription + '\'' +
                ", verkleinwoorden=" + verkleinwoorden +
                ", nietzeggendewoorden=" + nietzeggendewoorden +
                '}';
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getFullTranscription() {
        return fullTranscription;
    }

    public void setFullTranscription(String fullTranscription) {
        this.fullTranscription = fullTranscription;
    }

    public Set<String> getVerkleinwoorden() {
        return verkleinwoorden;
    }

    public void setVerkleinwoorden(Set<String> verkleinwoorden) {
        this.verkleinwoorden = verkleinwoorden;
    }

    public Set<String> getNietzeggendewoorden() {
        return nietzeggendewoorden;
    }

    public void setNietzeggendewoorden(Set<String> nietzeggendewoorden) {
        this.nietzeggendewoorden = nietzeggendewoorden;
    }
}
