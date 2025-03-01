package com.mzu.expandablelistviewdemo;

import android.os.Bundle;
import android.widget.ExpandableListView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ExpandableListView expandableListView;
    private ExpandableListAdapter adapter;
    private List<String> listGroup;
    private HashMap<String, List<String>> listChild;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        expandableListView = findViewById(R.id.expandableListView);

        listGroup = new ArrayList<>();
        listChild = new HashMap<>();

        listGroup.add("Fruits");
        listGroup.add("Vegetables");

        List<String> fruits = new ArrayList<>();
        fruits.add("Apple");
        fruits.add("Banana");
        fruits.add("Mango");

        List<String> vegetables = new ArrayList<>();
        vegetables.add("Carrot");
        vegetables.add("Broccoli");
        vegetables.add("Spinach");

        listChild.put(listGroup.get(0), fruits);
        listChild.put(listGroup.get(1), vegetables);

        adapter = new ExpandableListAdapter(this, listGroup, listChild);
        expandableListView.setAdapter(adapter);
    }
}
