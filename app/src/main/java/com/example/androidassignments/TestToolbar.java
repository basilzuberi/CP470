package com.example.androidassignments;

import android.content.DialogInterface;
import android.os.Bundle;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class TestToolbar extends AppCompatActivity {

    String mSetToastText;
    EditText txtSetText;
    View v;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_toolbar);
        Toolbar toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        mSetToastText = "You Selected Option 1";

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, R.string.string_thanks, Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }
    public boolean onCreateOptionsMenu(Menu m){
        getMenuInflater().inflate(R.menu.toolbar_menu,m);


        return true;
    }

    public boolean onOptionsItemSelected(MenuItem mi){
        int id = mi.getItemId();

        switch(id){

            case R.id.action_one:
                Log.d("ToolBar","Option 1 Selected");

                Snackbar.make(getWindow().getDecorView().findViewById(R.id.toolbar), mSetToastText, Snackbar.LENGTH_LONG).show();
                break;
            case R.id.action_two:
                Log.d("ToolBar","Option 2 Selected");

                AlertDialog.Builder builder = new AlertDialog.Builder(TestToolbar.this);
                builder.setTitle(R.string.alertdialog_title);
                builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                });

                builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //do nothing...
                    }
                });

                AlertDialog dialog = builder.create();
                dialog.show();
                break;
            case R.id.action_three:
                Log.d("ToolBar","Option 3 Selected");

                AlertDialog.Builder customBuilder = new AlertDialog.Builder(TestToolbar.this);
                customBuilder.setTitle(R.string.customdialog_title);
                LayoutInflater inflater = this.getLayoutInflater();
                v = inflater.inflate(R.layout.custom_dialog_testtoolbar,null);

                customBuilder.setView(v)
                        .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        txtSetText = v.findViewById(R.id.custom_dialog_text);
                                        mSetToastText = txtSetText.getText().toString();

                                    }
                                })

                    .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            //nothing
                        }
                    });


                AlertDialog customDialog = customBuilder.create();
                customDialog.show();

                break;
            default:
                Log.d("ToolBar","About Me Selected");
                Toast toast = Toast.makeText(this, "Version 1.0 by Basil Zuberi", Toast.LENGTH_LONG);
                toast.show();
        }
        return false;
    }
}

