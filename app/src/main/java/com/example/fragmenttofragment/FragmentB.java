package com.example.fragmenttofragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class FragmentB extends Fragment {
    private  FragmenBListener listener;
    private EditText editText;
    private Button buttonOk;

    public interface FragmenBListener{
        void onInputBSent(CharSequence input);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_b, container, false);

        editText = v.findViewById(R.id.edit_text);
        buttonOk  = v.findViewById(R.id.button_ok);

        buttonOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CharSequence input = editText.getText();
                listener.onInputBSent(input);
            }
        });
        return v;
    }

    public  void updateEditText(CharSequence newText){
        editText.setText(newText);
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if(context instanceof FragmenBListener){
            listener = (FragmenBListener) context; //if activity(MainActivity) implements fragmentAListener
        } else{
            throw new RuntimeException(context.toString()
                    + "must implement FragmenBListener"); // if activity doesn't implement fragment listener throw exception
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }
}
