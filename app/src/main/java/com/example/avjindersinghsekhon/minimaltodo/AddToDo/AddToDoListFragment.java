package com.example.avjindersinghsekhon.minimaltodo.AddToDo;

import static android.content.Context.INPUT_METHOD_SERVICE;
import static android.content.Context.MODE_PRIVATE;

import android.app.Activity;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.avjindersinghsekhon.minimaltodo.Analytics.AnalyticsApplication;
import com.example.avjindersinghsekhon.minimaltodo.AppDefault.AppDefaultFragment;
import com.example.avjindersinghsekhon.minimaltodo.Main.MainActivity;
import com.example.avjindersinghsekhon.minimaltodo.Main.MainFragment;
import com.example.avjindersinghsekhon.minimaltodo.R;
import com.example.avjindersinghsekhon.minimaltodo.Utility.ScreenSlidePageAdapter;

import java.util.Calendar;

public class AddToDoListFragment extends AppDefaultFragment{
    private static final String TAG = "AddToDoListFragment";
    private static ScreenSlidePageAdapter adapter;
    private String theme;
    private EditText mToDoListEditText;
    private FloatingActionButton mMakeToDoListFAB;
    private String mUserEnteredText;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        theme = getActivity().getSharedPreferences(MainFragment.THEME_PREFERENCES, MODE_PRIVATE).getString(MainFragment.THEME_SAVED, MainFragment.LIGHTTHEME);
        if (theme.equals(MainFragment.LIGHTTHEME)) {
            getActivity().setTheme(R.style.CustomStyle_LightTheme);
            Log.d("OskarSchindler", "Light Theme");
        } else {
            getActivity().setTheme(R.style.CustomStyle_DarkTheme);
        }
        mUserEnteredText = "";
        mMakeToDoListFAB = (FloatingActionButton) view.findViewById(R.id.makeToDoListFloatingActionButton);

        mToDoListEditText = (EditText) view.findViewById(R.id.userToDoListEditText);
        mToDoListEditText.requestFocus();

        mToDoListEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mUserEnteredText = s.toString();
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        mMakeToDoListFAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                makeResult(MainFragment.REQUEST_ID_TODO_LIST);
                //crear nueva tab y abrirla
                //setear el nombre como mUserEnteredText
                Toast.makeText(getContext(),"Toque el boton", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void makeResult(int result) {
        Intent i = new Intent();
        if (mUserEnteredText.length() > 0) {

            String capitalizedString = Character.toUpperCase(mUserEnteredText.charAt(0)) + mUserEnteredText.substring(1);
            i.putExtra(MainFragment.TODOLIST, capitalizedString);
            Log.d(TAG, "Title: " + mUserEnteredText);
        } else {
            Log.d(TAG, "Titulo: " + "Lista ");
        }
        getActivity().setResult(Activity.RESULT_OK, i);
        getActivity().finish();
       /*Log.d(TAG, "makeResult - ok : in");
        Intent i = new Intent(getContext(),MainActivity.class);
        if (mUserEnteredText.length() > 0) {

            String capitalizedString = Character.toUpperCase(mUserEnteredText.charAt(0)) + mUserEnteredText.substring(1);
            i.putExtra(MainFragment.TODOLIST, capitalizedString);
            Log.d(TAG, "Title: " + mUserEnteredText);
        } else {
            Log.d(TAG, "Titulo: " + "Lista ");
        }
//        mUserToDoItem.setLastEdited(mLastEdited);

        i.putExtra(AddToDoListActivity.TODOADAPTER, (Parcelable) adapter);
        getActivity().setResult(result, i);
        startActivity(i);*/
    }
    @Override
    protected int layoutRes() {
        return R.layout.fragment_add_to_do_list;
    }

    public static AddToDoListFragment newInstance(ScreenSlidePageAdapter a) {
        adapter = a;
        return new AddToDoListFragment();
    }
}
