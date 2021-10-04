package com.example.covidtracker.services;

import com.example.covidtracker.models.LatestStats;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.StringReader;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

@Service
public class CovidDataService {

    private static String COVID_DATA_URL = "https://raw.githubusercontent.com/CSSEGISandData/COVID-19/6a0f6b1ae79efc4fac2e3b31b72e8e9e51eb0ef4/csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_confirmed_global.csv";
    private List<LatestStats> allStats = new ArrayList<>();

    public List<LatestStats> getAllStats() {
        return allStats;
    }

    @PostConstruct
    @Scheduled(cron = "* * * * * *")
    public void fetchCovidData() throws IOException, InterruptedException {
        List<LatestStats> newStats = new ArrayList<>();
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest httpRequest = HttpRequest.newBuilder().uri(URI.create(COVID_DATA_URL)).build();
        HttpResponse<String> httpResponse = httpClient.send(httpRequest,HttpResponse.BodyHandlers.ofString());

        StringReader csvData = new StringReader(httpResponse.body());
        Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(csvData);
        for (CSVRecord record : records) {
            LatestStats latestStats = new LatestStats();
            latestStats.setState(record.get("Province/State"));
            latestStats.setCountry(record.get("Country/Region"));
            int totalCases = Integer.parseInt(record.get(record.size() -1));
            int newCases = Integer.parseInt(record.get(record.size() -2));
            latestStats.setLatestTotalCases(totalCases);
            latestStats.setDiffFromPrevDay(totalCases-newCases);
            newStats.add(latestStats);
        }
        this.allStats = newStats;


    }
}
