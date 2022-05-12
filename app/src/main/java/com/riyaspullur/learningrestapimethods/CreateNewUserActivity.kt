package com.riyaspullur.learningrestapimethods

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.activity_create_new_user.*

class CreateNewUserActivity : AppCompatActivity() {
    lateinit var viewModel: CreateNewUserViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_new_user)


        var user_id=intent.getStringExtra("user_id")
        initViewModel()
        createUserObservable()

        if (user_id!=null){
            loadUserData(user_id)
        }
        var aa=1111
        createButton.setOnClickListener {
            aa++
            user_id="$aa"
            createUser(user_id!!)
        }

        deleteButton.setOnClickListener {
            deleteUser(user_id)
        }
    }
    private fun deleteUser(user_id:String?){
        viewModel.getdeleteUserObservable().observe(this, Observer <UserResponse?>{
            if (it==null){
                Toast.makeText(this,"Failed to Delete",Toast.LENGTH_LONG).show()
            }else{
                Toast.makeText(this,"Successfully Deleted  User",Toast.LENGTH_LONG).show()
                finish()
            }

            //Toast.makeText(this,"Failed to create",Toast.LENGTH_LONG).show()
        })
        viewModel.deleteUser(user_id!!)
    }
    private fun loadUserData(user_id:String?){
        viewModel.getLoadUserObservable().observe(this, Observer <UserResponse?>{

            if (it!=null){
                editTextName.setText(it.data?.name)
                editTextEmail.setText(it.data?.email)
                editTextGender.setText(it.data?.gender)
                createButton.setText("Update")
                deleteButton.visibility=View.VISIBLE


            }

            //Toast.makeText(this,"Failed to create",Toast.LENGTH_LONG).show()
        })
        viewModel.getUserData(user_id)

    }

    private fun createUser(user_id:String) {
        val user=User("",editTextName.text.toString(),editTextEmail.text.toString(),editTextGender.text.toString(),"Active")
       if (user_id==null)
           viewModel.createUser(user)
        else
           viewModel.updateUser(user_id,user)
    }

    private fun initViewModel() {
        viewModel=ViewModelProvider(this).get(CreateNewUserViewModel::class.java)

    }
    private fun createUserObservable(){
        viewModel.getCreateNewUserObservable().observe(this, Observer <UserResponse?>{

            if (it==null){
                Toast.makeText(this,"Failed to create / Update",Toast.LENGTH_LONG).show()
            }else{
                Toast.makeText(this,"Succesfully created / Updated  User",Toast.LENGTH_LONG).show()
                finish()
            }
        })
    }
}