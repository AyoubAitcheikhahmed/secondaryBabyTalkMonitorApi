package com.aitcheikhahmed.secondarybabytalkmonitorapi.utility.linguistics;

import com.aitcheikhahmed.secondarybabytalkmonitorapi.model.TranscriptionResponse;
import com.aitcheikhahmed.secondarybabytalkmonitorapi.service.TranscriptionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class LinguisticAnalysis {
    private static final Logger logger = LoggerFactory.getLogger(TranscriptionService.class);

    private TranscriptionResponse transcriptionResponse;
    private Set<String> geenVerkleinwoorden;
    private Set<String> nietzeggendewoorden;

    public LinguisticAnalysis() {
        this.geenVerkleinwoorden = loadWordsFromFile("/language/geen_verkleinwoorden.txt");
        this.nietzeggendewoorden = loadWordsFromFile("/language/nietszeggendewoorden.txt");
    }

    private Set<String> loadWordsFromFile(String filePath) {
        Set<String> wordsSet = new HashSet<>();
        try (InputStream is = getClass().getResourceAsStream(filePath);
             BufferedReader reader = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8))) {
            String line;
            while ((line = reader.readLine()) != null) {
                wordsSet.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return wordsSet;
    }
    public Set<String> verkleinwoorden(String text) {
        Set<String> verkleinwoordenSet = new HashSet<>();
        List<String> words = makeArrayWords(text);
        for (String word : words) {
            if (word.length() > 3 && !geenVerkleinwoorden.contains(word.toLowerCase()) &&
                    (word.endsWith("je") || word.endsWith("ke") || word.endsWith("kes") || word.endsWith("jes"))) {
                verkleinwoordenSet.add(word);
            }
        }

        return verkleinwoordenSet;
    }

    public Set<String> herhalendeZinnen(String text) {
        List<String> words = makeArrayWords(text);
        List<String> recentWords = new ArrayList<>();
        Set<String> repetition = new HashSet<>();

        for (String word : words) {
            if (recentWords.size() >= 25) {
                recentWords.remove(0);
            }
            if (!nietzeggendewoorden.contains(word)) {
                recentWords.add(word);
            }
        }

        Map<String, Integer> wordCounts = new HashMap<>();
        for (String word : recentWords) {
            wordCounts.put(word, wordCounts.getOrDefault(word, 0) + 1);
        }

        for (Map.Entry<String, Integer> entry : wordCounts.entrySet()) {
            if (entry.getValue() > 1) {
                repetition.add(entry.getKey());
            }
        }
        return repetition;
    }


    private List<String> makeArrayWords(String text) {
        text = text.toLowerCase().replaceAll("[^\\w\\s]", "").replaceAll("\\s{2,}", " ");
        return Arrays.asList(text.split("\\s+"));
    }

    public TranscriptionResponse analyzeTranscription(String text){
        Set<String> verkleinwoordenResult = verkleinwoorden(text);
        Set<String> herhalendeZinnenResult = herhalendeZinnen(text);


        TranscriptionResponse response = new TranscriptionResponse(
                true,
                text,
                verkleinwoordenResult,
                herhalendeZinnenResult);

        return response;
    }
}
