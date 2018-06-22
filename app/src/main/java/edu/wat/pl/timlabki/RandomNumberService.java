package edu.wat.pl.timlabki;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Aleksander on 22.06.2018.
 * Wojskowa Akademia Techniczna im. Jarosława Dąbrowskiego, Warszawa 2017r.
 */
public interface RandomNumberService {

    @GET("/random/onlynumbers/{amount}")
    Call<List<Integer>> listNumbers(@Path("amount") String amount);
}
