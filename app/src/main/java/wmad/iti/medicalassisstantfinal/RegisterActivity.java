package wmad.iti.medicalassisstantfinal;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import wmad.iti.medicalassisstantfinal.urls.Urls;

public class RegisterActivity extends Activity implements BirthdateInterface,AdapterView.OnItemSelectedListener {


    // birthday EditText
    EditText birthdayEditText;
    //object form Activity
    static RegisterActivity registerActivity;
    // Calendar Birthday ..
    Calendar birthday;
    int gender;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        birthdayEditText = (EditText) findViewById(R.id.birthdayEditText);

        // get spinner object
        Spinner genderSpinner = (Spinner) findViewById(R.id.genderSpinner);

        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> genderAdapter = ArrayAdapter.createFromResource(this,R.array.gender,android.R.layout.simple_spinner_item);

        // Specify the layout to use when the list of choices appears
        genderAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Apply the adapter to the spinner
        genderSpinner.setAdapter(genderAdapter);

        //add listener to genderSpinner
        genderSpinner.setOnItemSelectedListener(this);




        birthdayEditText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                DialogFragment dialogFragment =  new MyDatePickerFragment();
                dialogFragment.show(getFragmentManager(),"datePicker");
            }
        });


        //DatePicker datePicker = (DatePicker) findViewById( R.id.datePicker);



    }

    /**
     *  @author Atef
     * This method used to update the date from datePicker ..
     * @param year
     * @param month
     * @param day
     */
    @Override
    public void changeDate(int year, int month, int day) {

       // birthdayEditText = (EditText) findViewById(R.id.birthdayEditText);

        Calendar calendar= Calendar.getInstance();
        calendar.set(year,month,day);
        calendar.set(year,month,day);
        // get date object from Calendar to print date
        Date date= calendar.getTime();
        this.birthday=calendar;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd");
       Log.i("DATE .....",date.toString());
        birthdayEditText.setText(simpleDateFormat.format(date));
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {


            if(parent.getItemAtPosition(pos).toString().equals("Male")){

                //male
                gender=1;


            }else{

                // female
                gender=0;
            }




    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }


    /**
     * @author  Atef
     * this  class used to show the datePicker dialog when i choose birthday
     */
    public static class MyDatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener{

        BirthdateInterface birthdateInterface;



        @Override
        public void onDateSet(DatePicker datePicker, int year, int month, int day) {

            birthdateInterface.changeDate(year, month, day);


        }

        @Override
        public void onAttach(Activity activity) {
            super.onAttach(activity);
            birthdateInterface =(BirthdateInterface) activity;
        }

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the current date as the default date in the picker
            final Calendar calendar= Calendar.getInstance();
            int year = calendar.get(Calendar.YEAR);
            int month=calendar.get(Calendar.MONTH);
            int day = calendar.get(Calendar.DAY_OF_MONTH);




            // Create a new instance of DatePickerDialog and return it

            return new DatePickerDialog(getActivity(),this,year,month,day);
        }
    }// end of datepicker class



    public boolean registerWeb(){

        try {
            URL registerUrl = new URL(Urls.REGISTER_URL);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return true;
    }


}
