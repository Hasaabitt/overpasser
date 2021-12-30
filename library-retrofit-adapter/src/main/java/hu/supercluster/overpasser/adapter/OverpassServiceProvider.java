package hu.supercluster.overpasser.adapter;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.net.MalformedURLException;
import java.net.URL;

public class OverpassServiceProvider {
    private static OverpassService service;
    private static URL url;

    public static void initialize() {
        try {
            initialize("http://overpass-api.de");
        } catch (MalformedURLException ignored) {
            // Will not happen
        }
    }

    public static void initialize(String endpoint) throws MalformedURLException {
        if (url == null) {
            url = new URL(endpoint);
        }
    }

    public static OverpassService get() {
        if (url == null) initialize();
        if (service == null) {
            try {
                service = createService();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }

        return service;
    }

    private static OverpassService createService() throws MalformedURLException {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit.create(OverpassService.class);
    }
}