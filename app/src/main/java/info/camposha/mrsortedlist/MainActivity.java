package info.camposha.mrsortedlist;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    SortedListAdapter adapter;
    private Boolean ASC = true;
    Spinner sp;
    String SELECTED_PROPERTY = "NAME";

    private List<Star> generateData(){
        List<Star> starList = new ArrayList<>();
        Star s=new Star("Rigel",98,92,9800);
        starList.add(s);
        s=new Star("Arcturus",73,83,7803);
        starList.add(s);
        s=new Star("Deneb",27,37,4283);
        starList.add(s);
        s=new Star("Wezen",36,39,3703);
        starList.add(s);
        s=new Star("Betelgeuse",89,85,9734);
        starList.add(s);
        s=new Star("Eta Carina",84,91,9242);
        starList.add(s);
        s=new Star("Aldebaran",87,83,8604);
        starList.add(s);
        s=new Star("Canopus",83,72,7937);
        starList.add(s);
        s=new Star("Regulus",75,72,6704);
        starList.add(s);
        s=new Star("Sirius",49,57,5294);
        starList.add(s);
        s=new Star("Trappist A",48,46,4635);
        starList.add(s);
        s=new Star("Proxima Centauri",94,92,9252);
        starList.add(s);
        s=new Star("Tau Ceti",15,25,2573);
        starList.add(s);
        s=new Star("Chara",24,28,3108);
        starList.add(s);
        s=new Star("Vega",46,58,5863);
        starList.add(s);
        s=new Star("Alpha Pegasi",57,62,6348);
        starList.add(s);
        s=new Star("Bellatrix",24,35,3628);
        starList.add(s);
        s=new Star("Naos",31,34,1635);
        starList.add(s);
        s=new Star("Hamal",11,14,1023);
        starList.add(s);
        s=new Star("Polaris",63,68,4592);
        starList.add(s);
        s=new Star("Enif",25,23,1292);
        starList.add(s);
        s=new Star("VY Canis Majoris",93,97,9262);
        starList.add(s);
        s=new Star("UY Scuti",76,91,8924);
        starList.add(s);
        s=new Star("Pollux",15,17,1364);
        starList.add(s);
        s=new Star("Archernar",25,24,2734);
        starList.add(s);
        return starList;

    }

    private void prepareSpinner(){
        final String[] properties = {"NAME","COMMENTS","FAVORITES","VIEWS"};
        sp=findViewById(R.id.propertySpinner);
        sp.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_dropdown_item_1line,
                properties));
        sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                SELECTED_PROPERTY=properties[position];
                adapter.sort(ASC,SELECTED_PROPERTY);
                adapter.addAll(generateData());
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    private void toggleButtons(){
        final Button ascBtn = findViewById(R.id.ascendigBtn);
        final Button descBtn = findViewById(R.id.sortByCommentsBtn);

        ascBtn.setBackgroundColor(getResources().getColor(android.R.color.holo_red_light));
        descBtn.setBackgroundColor(getResources().getColor(R.color.colorAccent));

        ascBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ASC = true;
                ascBtn.setBackgroundColor(getResources().getColor(android.R.color.holo_red_light));
                descBtn.setBackgroundColor(getResources().getColor(R.color.colorAccent));

                recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));

                adapter = new SortedListAdapter(R.layout.model);
                recyclerView.setAdapter(adapter);

                adapter.sort(true,SELECTED_PROPERTY);
                adapter.addAll(generateData());
                adapter.notifyDataSetChanged();
            }
        });
        descBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ASC = false;
                ascBtn.setBackgroundColor(getResources().getColor(R.color.colorAccent));
                descBtn.setBackgroundColor(getResources().getColor(android.R.color.holo_red_light));

                recyclerView.setLayoutManager(new GridLayoutManager(MainActivity.this,2));
                adapter = new SortedListAdapter(R.layout.model_grid);
                recyclerView.setAdapter(adapter);

                adapter.sort(false,SELECTED_PROPERTY);
                adapter.addAll(generateData());
                adapter.notifyDataSetChanged();

            }
        });

    }

    private void bindData(){
        recyclerView = findViewById(R.id.myRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter = new SortedListAdapter(R.layout.model);
        recyclerView.setAdapter(adapter);


        adapter.addAll(generateData());


    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bindData();
        prepareSpinner();
        toggleButtons();
    }
}
//end