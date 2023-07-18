package com.example.demostrava.ui.main.view

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import com.example.demostrava.ui.main.viewmodel.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.demostrava.R
import com.example.demostrava.data.api.ApiActivityHelper
import com.example.demostrava.data.model.input.InputListAthleteActivity
import com.example.demostrava.data.model.output.ActivityModel
import com.example.demostrava.ui.base.ViewModelActivityFactory
import com.example.demostrava.ui.main.adapter.ActivityAdapter
import com.example.demostrava.ui.main.adapter.CallToAction
import com.example.demostrava.ui.main.viewmodel.ActivityViewModel
import com.example.demostrava.utils.Status.ERROR
import com.example.demostrava.utils.Status.LOADING
import com.example.demostrava.utils.Status.SUCCESS

class ActivityActivity : AppCompatActivity() {

    private var accessToken = ""
    private lateinit var viewModel: ActivityViewModel
    private lateinit var adapter: ActivityAdapter
    private lateinit var btnProfile: ImageView
    private lateinit var recyclerView: RecyclerView
    private lateinit var progressBar: ProgressBar
    private lateinit var tvProfile: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_activity)
        initValue()
        setControl()
        setupViewModel()
        setupUI()
        setupObserversListActivity()
        setupObserversProfile()
        initBtnProfile()
    }

    private fun setControl(){
        tvProfile = findViewById(R.id.tvProfile)
        recyclerView = findViewById(R.id.recyclerView)
        btnProfile = findViewById(R.id.btnProfile)
        progressBar = findViewById(R.id.progressBar)
    }

    private fun initBtnProfile(){

    }

    private fun initValue(){
        intent.extras?.let {
            accessToken = it.getString("accessToken").toString()
        }
    }

    private fun setupViewModel() {
        viewModel = ViewModelProviders.of(
            this,
            ViewModelActivityFactory(ApiActivityHelper(accessToken))
        ).get(ActivityViewModel::class.java)
    }

    private fun setupUI() {
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = ActivityAdapter(
            this@ActivityActivity,
            arrayListOf(),
            object : CallToAction {
                override fun action(bundle: Bundle?) {
                    var intent = Intent(this@ActivityActivity,DetailActivityActivity::class.java)
                    intent.putExtra("summaryPolyline",bundle?.getString("summaryPolyline"))
                    intent.putExtra("name",bundle?.getString("name"))
                    intent.putExtra("accessToken", accessToken)
                    intent.putExtra("idActivity", bundle?.getString("idActivity"))
                    startActivity(intent)
                }
            }
        )
        recyclerView.addItemDecoration(
            DividerItemDecoration(
                recyclerView.context,
                (recyclerView.layoutManager as LinearLayoutManager).orientation
            )
        )
        recyclerView.adapter = adapter
    }


    private fun setupObserversListActivity() {
        var input = InputListAthleteActivity()
//        input.before = 0
//        input.after = 0
        input.page = 1
        input.per_page = 30
        viewModel.getListAthleteActivity(input).observe(this, Observer {
            it?.let { resource ->
                when (resource.status) {
                    SUCCESS -> {
                        recyclerView.visibility = View.VISIBLE
                        progressBar.visibility = View.GONE
                        resource.data?.let { list ->
                            retrieveList(list)
                        }
                    }
                    ERROR -> {
                        recyclerView.visibility = View.VISIBLE
                        progressBar.visibility = View.GONE
                        Toast.makeText(this, it.message + "list activity", Toast.LENGTH_LONG).show()
                    }
                    LOADING -> {
                        progressBar.visibility = View.VISIBLE
                        recyclerView.visibility = View.GONE
                    }
                }
            }
        })
    }

    private fun setupObserversProfile() {

        viewModel.getProfile().observe(this, Observer {
            it?.let { resource ->
                when (resource.status) {
                    SUCCESS -> {
                        resource.data?.let { profile ->
                            Glide.with(btnProfile.context)
                                .load(profile.profileMedium)
                                .into(btnProfile)
                            tvProfile.text = "Xin chào, " + profile.firstname + " " + profile.lastname

                        }
                    }
                    ERROR -> {
                        Toast.makeText(this, it.message + "profile", Toast.LENGTH_LONG).show()
                    }
                    LOADING -> {
                    }
                }
            }
        })
    }

    private fun retrieveList(activities: List<ActivityModel>) {
        adapter.apply {
            addActivities(activities)
            notifyDataSetChanged()
        }
    }
}
