public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn = findViewById(R.id.clickButton);
        btn.setOnClickListener(view -> {
            Toast.makeText(this, "Button Clicked!", Toast.LENGTH_SHORT).show();
        });
    }
}