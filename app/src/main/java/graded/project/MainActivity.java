package graded.project;
/*  This application was made by Loreleï NOIRAUD (V4)
    -----------------------------------------------------------------------------------------
    For the Graded Exercise of Basic of android programming :
    I wanted at first to do a Habit tracker. After two weeks of trying to figure out how to
    create a different view in the Java code (make a dynamic view, which load more than one
    element depending on what's the user is doing) I decided to finally do a "to do" list.
    I still have the view of my only try that worked "old_activity_main.xml".
    I was just setting the visibility of the concerned layout in VISIBLE, but there was a
    limit of 7 habits tracked (the one that I implemented, it worked, but was really messy
    and had too much limits).

    I learned a lot during this class. Thanks !
    -----------------------------------------------------------------------------------------

*/

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<String> tasks;
    private ArrayAdapter<String> tasksAdapter;
    private ListView listView; // listView from the view
    private Button button; // Button from the view

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); // Set up the view

        listView = findViewById(R.id.listView); // Get the id of the listView
        button = findViewById(R.id.button); // Get the id of the button

        button.setOnClickListener(new View.OnClickListener() { // If we click on the button
            @Override
            public void onClick(View v) {
                addItem();
            }
        });

        // Set up the list of tasks
        tasks = new ArrayList<>();
        tasksAdapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1, tasks);
        listView.setAdapter(tasksAdapter);
        setUpListViewListener(); // If we click on the list
    }

    private void setUpListViewListener() { // If we click on the list
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) { // If we make a long click on one element of the list
                Context context = getApplicationContext();
                Toast.makeText(context,"Task Removed !", Toast.LENGTH_LONG).show(); // Print a message on the device

                // Remove the task :
                tasks.remove(position);
                tasksAdapter.notifyDataSetChanged();
                return true;
            }
        });
    }

    private void addItem() { // Add a task in the list
        EditText input = findViewById(R.id.text_input);
        String itemText = input.getText().toString();

        if(!(itemText.equals(""))){ // If the EditText test is not empty :
            tasksAdapter.add(itemText); // Add the task in the list
            input.setText(""); // Put "" in the editText so we can write again without delete the previous text
        }else{
            Toast.makeText(getApplicationContext(),"Please enter text", Toast.LENGTH_LONG).show(); // Print a message on the device
        }
    }
}

