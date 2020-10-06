package com.example.service_novigrad.ui.register;
import com.example.service_novigrad.*;
import android.app.Activity;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import android.content.Intent;

import com.example.service_novigrad.R;
import com.example.service_novigrad.accounts.CustomerAccount;
import com.example.service_novigrad.accounts.EmployeeAccount;
import com.example.service_novigrad.ui.login.LoginViewModel;
import com.example.service_novigrad.ui.login.LoginViewModelFactory;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity{



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //Find the objects in the xml files
        final EditText firstNameEditText = findViewById(R.id.firstname);
        final EditText lastNameEditText = findViewById(R.id.lastname);
        final EditText usernameEditText = findViewById(R.id.username);
        final EditText passwordEditText = findViewById(R.id.password);
        final EditText branchIDEditText = findViewById(R.id.branchid);
        final RadioButton employeeAccountTypeRadioButton = findViewById(R.id.employee);
        final RadioButton customerAccountTypeRadioButton = findViewById(R.id.customer);
        final Button submitButton = findViewById(R.id.submit);

        submitButton.setOnClickListener(new View.OnClickListener() { //this submits the information the user inputs and stores it in the firebase database
            public void onClick(View view) {

                //initialize and fill in the values of the data we found from the users
                String firstName, lastName, username, password;
                int branchID;
                firstName = firstNameEditText.getText().toString();
                lastName = lastNameEditText.getText().toString();
                username = usernameEditText.getText().toString();
                password = passwordEditText.getText().toString();


                FirebaseDatabase database = FirebaseDatabase.getInstance(); //creates an instance so you can read and write to it



                if (employeeAccountTypeRadioButton.isChecked()){

                    branchID = Integer.parseInt(branchIDEditText.getText().toString());
                    //Create references that take the form similar to a json file this is a template basically to show the format and how stuff will be shown
                    DatabaseReference newEmployeeAccount = database.getReference("Employee Accounts/");


                    EmployeeAccount newEmployee = new EmployeeAccount(username, password, firstName, lastName, branchID);
                    newEmployeeAccount.push().setValue(newEmployee);


                }else if (customerAccountTypeRadioButton.isChecked()){
                    DatabaseReference newCustomerAccount = database.getReference("Customer Accounts/");


                    CustomerAccount newCustomer = new CustomerAccount(username,password,firstName,lastName);

                    newCustomerAccount.push().setValue(newCustomer);

                }

                finish();



            }
        });


    }


}

