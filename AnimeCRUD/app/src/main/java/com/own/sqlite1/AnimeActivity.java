package com.own.sqlite1;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.own.sqlite1.adapter.AnimeAdapter;
import com.own.sqlite1.model.Anime;
import com.own.sqlite1.sqlite.DBOperations;

import java.util.ArrayList;
import java.util.List;

public class AnimeActivity extends AppCompatActivity {
    private EditText edtName;
    private EditText edtCreator;
    private EditText edtGender;
    private EditText edtYear;
    private EditText edtChapters;
    private Button btnSaveAnime;
    private DBOperations operations;
    private Anime anime = new Anime();
    private RecyclerView rcvAnime;
    private List<Anime> animes = new ArrayList<>();
    private AnimeAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anime);
        operations = DBOperations.getDBOperations(getApplicationContext());
        anime.setIdAnime("");
        initComponents();
    }

    private void initComponents(){
        rcvAnime = (RecyclerView)findViewById(R.id.rcv_list);
        rcvAnime.setHasFixedSize(true);
        LinearLayoutManager layoutManeger = new LinearLayoutManager(this);
        rcvAnime.setLayoutManager(layoutManeger);
        getAnimes();
        adapter = new AnimeAdapter(animes);

        adapter.setListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()){
                    case R.id.btn_delete:
                        operations.deleteAnime(animes.get(rcvAnime.getChildPosition((View)v.getParent().getParent())).getIdAnime());
                        getAnimes();
                        adapter.notifyDataSetChanged();
                        break;
                    case R.id.btn_edit:
                        Toast.makeText(getApplicationContext(),"Edit anime.",Toast.LENGTH_SHORT).show();
                        Cursor c = operations.getAnimeById(animes.get(rcvAnime.getChildPosition((View)v.getParent().getParent())).getIdAnime());
                            if (c!=null){
                                if (c.moveToFirst()){
                                    anime = new Anime(c.getString(1),c.getString(2),c.getString(3),c.getString(4), c.getString(4), c.getString(5));
                                }
                                edtName.setText(anime.getName());
                                edtCreator.setText(anime.getCreator());
                                edtGender.setText(anime.getGender());
                                edtYear.setText(anime.getYear());
                                edtYear.setText(String.valueOf(anime.getYear()));
                                edtChapters.setText(String.valueOf(anime.getChapters()));
                            }else{
                                Toast.makeText(getApplicationContext(),"Record not found.",Toast.LENGTH_SHORT).show();
                            }
                        break;
                }
            }
        });

        rcvAnime.setAdapter(adapter);
        edtName = (EditText) findViewById(R.id.edt_name);
        edtCreator = (EditText) findViewById(R.id.edt_creator);
        edtGender = (EditText) findViewById(R.id.edt_gender);
        edtYear = (EditText) findViewById(R.id.edt_year);
        edtChapters = (EditText) findViewById(R.id.edt_chapters);
        btnSaveAnime = (Button) findViewById(R.id.btn_save_song);

        btnSaveAnime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!anime.getIdAnime().equals("")){
                    anime.setName(edtName.getText().toString());
                    anime.setCreator(edtCreator.getText().toString());
                    anime.setGender(edtGender.getText().toString());
                    anime.setYear(edtYear.getText().toString());
                    anime.setChapters(edtChapters.getText().toString());
                    operations.updateAnime(anime);
                }else {
                    anime = new Anime("", edtName.getText().toString(), edtCreator.getText().toString(), edtGender.getText().toString(),
                            edtYear.getText().toString(), edtChapters.getText().toString());
                    operations.insertAnime(anime);
                }
                getAnimes();
                clearData();
                adapter.notifyDataSetChanged();
            }
        });
    }

    private void getAnimes(){
        Cursor c = operations.getAnimes();
        animes.clear();
        if(c!=null){
            while (c.moveToNext()){
                animes.add(new Anime(c.getString(1), c.getString(2), c.getString(3), c.getString(4), c.getString(5), c.getString(6)));
            }
        }
    }

    private void clearData(){
        edtName.setText("");
        edtCreator.setText("");
        edtGender.setText("");
        edtYear.setText("");
        edtChapters.setText("");
        anime = null;
        anime = new Anime();
        anime.setIdAnime("");
    }

}//End
