package hu.supercluster.overpasser.adapter;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.net.MalformedURLException;
import java.net.URL;

public class OverpassServiceProvider {
    private static OverpassService service;

    public static OverpassService get() {
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
                .baseUrl(new URL("http://overpass-api.de"))
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit.create(OverpassService.class);
    }
}