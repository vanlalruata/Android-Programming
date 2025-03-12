package com.mzu.wordguess;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private TextView tvScrambledWord, tvResult;
    private EditText etUserGuess;
    private Button btnSubmit, btnNewWord;
    private String originalWord;
    private List<String> wordList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        // Initialize UI elements
        tvScrambledWord = findViewById(R.id.tvScrambledWord);
        tvResult = findViewById(R.id.tvResult);
        etUserGuess = findViewById(R.id.etUserGuess);
        btnSubmit = findViewById(R.id.btnSubmit);
        btnNewWord = findViewById(R.id.btnNewWord);

        // Sample words list
        wordList.add("Android");
        wordList.add("Java");
        wordList.add("Kotlin");
        wordList.add("Programming");
        wordList.add("Mobile");
        wordList.add("Development");
        wordList.add("Challenge");
        wordList.add("Puzzle");
        wordList.add("Scramble");
        wordList.add("Game");
        wordList.add("Algorithm");
        wordList.add("Binary");
        wordList.add("Compile");
        wordList.add("Database");
        wordList.add("Debug");
        wordList.add("Encryption");
        wordList.add("Framework");
        wordList.add("Function");
        wordList.add("Hardware");
        wordList.add("Integration");
        wordList.add("Iteration");
        wordList.add("Javascript");
        wordList.add("Library");
        wordList.add("Machine");
        wordList.add("Network");
        wordList.add("Object");
        wordList.add("Package");
        wordList.add("Query");
        wordList.add("Repository");
        wordList.add("Software");
        wordList.add("Technology");
        wordList.add("Variable");
        wordList.add("Widget");
        wordList.add("Interface");
        wordList.add("Application");
        wordList.add("Cybersecurity");
        wordList.add("Automation");
        wordList.add("Optimization");
        wordList.add("Compilation");
        wordList.add("Versioning");
        wordList.add("Streaming");
        wordList.add("Analytics");
        wordList.add("Bandwidth");
        wordList.add("Character");
        wordList.add("Command");
        wordList.add("Dependency");
        wordList.add("Encryption");
        wordList.add("Firmware");
        wordList.add("Hyperlink");
        wordList.add("Instruction");
        wordList.add("Keyboard");
        wordList.add("Latency");
        wordList.add("Memory");
        wordList.add("Nexus");
        wordList.add("Operation");
        wordList.add("Parallel");
        wordList.add("Quantum");
        wordList.add("Runtime");
        wordList.add("Syntax");
        wordList.add("Thread");
        wordList.add("Utility");
        wordList.add("Virtual");
        wordList.add("Workflow");
        wordList.add("XML");
        wordList.add("Yield");
        wordList.add("Zebra");
        wordList.add("Syntax");
        wordList.add("Hexadecimal");
        wordList.add("Boolean");
        wordList.add("Compiler");
        wordList.add("Constructor");
        wordList.add("Encapsulation");
        wordList.add("Garbage");
        wordList.add("Exception");
        wordList.add("Functionality");
        wordList.add("Hierarchy");
        wordList.add("Identifier");
        wordList.add("Javadoc");
        wordList.add("Keyword");
        wordList.add("Lambda");
        wordList.add("Multithreading");
        wordList.add("Namespace");
        wordList.add("Overloading");
        wordList.add("Polymorphism");
        wordList.add("Queue");
        wordList.add("Recursion");
        wordList.add("Singleton");
        wordList.add("Traversal");
        wordList.add("Utility");
        wordList.add("Virtualization");
        wordList.add("Whitespace");
        wordList.add("Yielding");
        wordList.add("Zipfile");
        wordList.add("Assembler");
        wordList.add("Bootloader");
        wordList.add("Cache");
        wordList.add("Daemon");
        wordList.add("Execution");
        wordList.add("Frontend");
        wordList.add("Backend");
        wordList.add("Cloud");
        wordList.add("DevOps");
        wordList.add("Database");
        wordList.add("Entity");
        wordList.add("Foreign");
        wordList.add("Gateway");
        wordList.add("Hashing");
        wordList.add("Indexing");
        wordList.add("Join");
        wordList.add("Kernel");
        wordList.add("Latency");
        wordList.add("Metadata");
        wordList.add("Normalization");
        wordList.add("Optimization");
        wordList.add("Protocol");
        wordList.add("Querying");
        wordList.add("Replication");
        wordList.add("Scalability");
        wordList.add("Transaction");
        wordList.add("Uptime");
        wordList.add("Visualization");
        wordList.add("Workflow");
        wordList.add("XOR");
        wordList.add("YAML");
        wordList.add("Zip");
        wordList.add("Authentication");
        wordList.add("Browser");
        wordList.add("Cookies");
        wordList.add("DNS");
        wordList.add("Email");
        wordList.add("Firewall");
        wordList.add("Geolocation");
        wordList.add("HTTPS");
        wordList.add("IP Address");
        wordList.add("JavaScript");
        wordList.add("Keyboard");
        wordList.add("Load Balancer");
        wordList.add("Metadata");
        wordList.add("Node.js");
        wordList.add("Operating System");
        wordList.add("Python");
        wordList.add("Queue");
        wordList.add("Router");
        wordList.add("Server");
        wordList.add("Tokenization");
        wordList.add("User Interface");
        wordList.add("Version Control");
        wordList.add("Web Development");
        wordList.add("XML Parsing");
        wordList.add("YAML Configuration");
        wordList.add("Zero Trust");
        wordList.add("Agile");
        wordList.add("Big Data");
        wordList.add("Cloud Computing");
        wordList.add("Data Science");
        wordList.add("Edge Computing");
        wordList.add("Full Stack");
        wordList.add("GraphQL");
        wordList.add("Hybrid Cloud");
        wordList.add("Internet of Things");
        wordList.add("Javascript Framework");
        wordList.add("Kubernetes");
        wordList.add("Load Testing");
        wordList.add("Microservices");
        wordList.add("NoSQL Database");
        wordList.add("Open Source");
        wordList.add("Predictive Analytics");
        wordList.add("Quantum Computing");
        wordList.add("Remote Work");
        wordList.add("Serverless");
        wordList.add("Tech Stack");
        wordList.add("Unit Testing");
        wordList.add("Versioning");
        wordList.add("Web Scraping");
        wordList.add("Xamarin");
        wordList.add("Yield Curve");
        wordList.add("Zero-day Attack");

        // Set initial word
        newScrambledWord();

        // Set click listeners
        btnSubmit.setOnClickListener(v -> checkAnswer());
        btnNewWord.setOnClickListener(v -> newScrambledWord());

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void newScrambledWord() {
        // Get random word from list
        Random random = new Random();
        originalWord = wordList.get(random.nextInt(wordList.size()));

        // Scramble the word
        String scrambled = scrambleWord(originalWord.toLowerCase());
        tvScrambledWord.setText(scrambled);
        tvResult.setText("");
        etUserGuess.setText("");
        etUserGuess.requestFocus();
    }

    private String scrambleWord(String word) {
        List<Character> characters = new ArrayList<>();
        for (char c : word.toCharArray()) {
            characters.add(c);
        }

        // Shuffle characters
        Collections.shuffle(characters);

        // Convert back to string
        StringBuilder sb = new StringBuilder();
        for (Character c : characters) {
            sb.append(c);
        }

        // Ensure scrambled word is different from original
        String result = sb.toString();
        if (result.equalsIgnoreCase(word)) {
            return scrambleWord(word); // Recursively scramble again if needed
        }
        return result;
    }

    private void checkAnswer() {
        String userGuess = etUserGuess.getText().toString().trim();

        if (userGuess.isEmpty()) {
            Toast.makeText(this, "Please enter a guess", Toast.LENGTH_SHORT).show();
            return;
        }

        if (userGuess.equalsIgnoreCase(originalWord)) {
            tvResult.setText(R.string.correct_well_done);
            tvResult.setTextColor(ContextCompat.getColor(this, R.color.correct_color));
        } else {
            tvResult.setText(R.string.wrong_try_again);
            tvResult.setTextColor(ContextCompat.getColor(this, R.color.wrong_color));
        }
    }
}