package com.example.androidassignments;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class ChatWindow extends AppCompatActivity {
    Button mBtnSend;
    EditText mTxtMessage;
    ListView mChatView;
    ArrayList<String> mMessageArray;
    ChatAdapter messageAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_window);
        mBtnSend = findViewById(R.id.btnSendMessage);
        mTxtMessage = findViewById(R.id.txtMessage);
        mChatView = findViewById(R.id.chatView);

        mMessageArray = new ArrayList<>();

        messageAdapter = new ChatAdapter(this);
        mChatView.setAdapter(messageAdapter);

        mBtnSend.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                mMessageArray.add(mTxtMessage.getText().toString());
                messageAdapter.notifyDataSetChanged();
                mTxtMessage.setText("");
            }
        });





    }

    private class ChatAdapter extends ArrayAdapter<String>{

        public ChatAdapter(@NonNull Context context) {
            super(context, 0);
        }

        public int getCount(){

            return mMessageArray.size();
        }

        public String getItem(int position){
            return mMessageArray.get(position);
        }

        public View getView(int position, View convertView, ViewGroup parent){
            LayoutInflater inflater = ChatWindow.this.getLayoutInflater();

            View result;

            if(position%2==0){
                result = inflater.inflate(R.layout.chat_row_incoming,null);
            }
            else{
                result = inflater.inflate(R.layout.chat_row_outgoing,null);
            }

            TextView message = result.findViewById(R.id.message_text);

            message.setText(getItem(position));
            return result;

        }

    }
}