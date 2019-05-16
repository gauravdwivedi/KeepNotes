package com.focustowardsfuture.gaurav.keepnotes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Toast;

public class AddEditNoteActivity extends AppCompatActivity {
    public static final String EXTRA_ID =
            " com.focustowardsfuture.gaurav.keepnotes.EXTRA_ID";
    public static final String EXTRA_TITLE =
            " com.focustowardsfuture.gaurav.keepnotes.EXTRA_TITLE";
    public static final String EXTRA_DESCRIPTION =
            " com.focustowardsfuture.gaurav.keepnotes.EXTRA_DESCRIPTION";

    public static final String EXTRA_PRIORITY =
            " com.focustowardsfuture.gaurav.keepnotes.EXTRA_PRIORITY";
private EditText edittextTitle, editTextDescription;
private NumberPicker numberPickerPriority;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);

        edittextTitle = findViewById(R.id.edit_text_title);
        editTextDescription = findViewById(R.id.edit_text_description);
        numberPickerPriority = findViewById(R.id.number_picker_priority);

        numberPickerPriority.setMinValue(1);
        numberPickerPriority.setMaxValue(10);


        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close);

        Intent intent = getIntent();
        if (intent.hasExtra(EXTRA_ID)) {

            setTitle("Edit Note");
            edittextTitle.setText(intent.getStringExtra(EXTRA_TITLE));
            editTextDescription.setText(intent.getStringExtra(EXTRA_DESCRIPTION));
            numberPickerPriority.setValue(intent.getIntExtra(EXTRA_PRIORITY,1));
        } else {


            setTitle("Add Note");
        }
    }
    private void saveNote(){
        String title =edittextTitle.getText().toString();
        String description =editTextDescription.getText().toString();
        int priority = numberPickerPriority.getValue();

        if(title.trim().isEmpty()||description.trim().isEmpty()){

            Toast.makeText(this, "Title and Description Cannot be Empty", Toast.LENGTH_SHORT).show();

        }else {

            Intent data = new Intent();
            data.putExtra(EXTRA_TITLE, title);
            data.putExtra(EXTRA_DESCRIPTION, description);
            data.putExtra(EXTRA_PRIORITY, priority);

            int id = getIntent().getIntExtra(EXTRA_ID, -1);
            if (id != -1) {
                data.putExtra(EXTRA_ID, id);
            }

            setResult(RESULT_OK, data);
            finish();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater =getMenuInflater();
        menuInflater.inflate(R.menu.menu_main,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.save_note:
                saveNote();
                return true;
                default:
                    return super.onOptionsItemSelected(item);
        }

    }
}
