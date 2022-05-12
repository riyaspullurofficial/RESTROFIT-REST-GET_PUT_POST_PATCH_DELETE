package com.riyaspullur.learningrestapimethods

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(),RecyclerViewAdapter.OnItemClickListener {
    lateinit var reclerViewAdapter:RecyclerViewAdapter
    lateinit var viewModel:MainActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initRecycleView()
        initViewModel()
        searchUser()
        createUserButton.setOnClickListener { startActivity(Intent(this,CreateNewUserActivity::class.java)) }
    }

    private fun searchUser() {
        search_button.setOnClickListener {
            if (!TextUtils.isEmpty(searchEditText.toString())){
                    viewModel.searchUserList(searchEditText.text.toString())
            }else{
                viewModel.getUserList()
            }
        }

    }

    private fun initRecycleView() {
        recyclerView.apply {
            layoutManager=LinearLayoutManager(this@MainActivity)
            val decoration=DividerItemDecoration(this@MainActivity,DividerItemDecoration.VERTICAL)
            addItemDecoration(decoration)
            reclerViewAdapter= RecyclerViewAdapter(this@MainActivity)
            adapter=reclerViewAdapter
        }
    }
    fun initViewModel(){
       viewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)
        viewModel.getUserListObservable().observe(this, Observer<UserList>{
            if (it==null){
                Toast.makeText(this,"no result found...",Toast.LENGTH_LONG).show()

            }else{
                reclerViewAdapter.userList=it.data.toMutableList()
                reclerViewAdapter.notifyDataSetChanged()
            }
        })
        viewModel.getUserList()
    }

    override fun onItemEditClick(user: User) {
        val intent=Intent(this,CreateNewUserActivity::class.java)
        intent.putExtra("user_id",user.id)
        startActivityForResult(intent,1000)
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode==1000){
            viewModel.getUserList()
        }
    }
}