package edu.wat.pl.timlabki;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private Button getRandomNumbersButton;
    private EditText getRandomNumbersInput;
    private ListView randomNumbersContainerList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getRandomNumbersButton = (Button) findViewById(R.id.getRandomNumbersButton);
        getRandomNumbersInput = (EditText) findViewById(R.id.getRandomNumbersInput);
        randomNumbersContainerList = (ListView) findViewById(R.id.RandomNumbersContainerList);

        Retrofit retrofit = new Retrofit.Builder()
                            .baseUrl(getString(R.string.BaseUrl))
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();

        final RandomNumberService randomNumberService = retrofit.create(RandomNumberService.class);

        final ArrayAdapter<Integer> itemsAdapter =
                new ArrayAdapter<Integer>(this, android.R.layout.simple_list_item_1, new ArrayList<Integer>());

        itemsAdapter.setNotifyOnChange(true);

        randomNumbersContainerList.setAdapter(itemsAdapter);

        getRandomNumbersButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String amount = getRandomNumbersInput.getText().toString();

                Call<List<Integer>> numbers = randomNumberService.listNumbers(amount);

                numbers.enqueue(new Callback<List<Integer>>() {
                    @Override
                    public void onResponse(Call<List<Integer>> call, Response<List<Integer>> response) {
                        itemsAdapter.clear();

                        itemsAdapter.addAll(response.body());
                    }

                    @Override
                    public void onFailure(Call<List<Integer>> call, Throwable t) {

                    }
                });
            }
        });

    }
}
