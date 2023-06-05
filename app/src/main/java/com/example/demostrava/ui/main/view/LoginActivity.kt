package com.example.demostrava.ui.main.view

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import com.example.demostrava.ui.main.viewmodel.ViewModelProviders
import com.example.demostrava.R
import com.example.demostrava.data.api.ApiLoginHelper
import com.example.demostrava.data.model.input.InputGetAccessToken
import com.example.demostrava.ui.base.ViewModelLoginFactory
import com.example.demostrava.ui.main.viewmodel.LoginViewModel
import com.example.demostrava.utils.Status

class LoginActivity : AppCompatActivity() {
    private val clientId = "108172"
    private val clientSecret = "9a0526dd16cb83c2d98b200e4e1863d983787333"
    private val grantType = "authorization_code"
    private var code = ""
    private var scope = ""
    private lateinit var viewModel: LoginViewModel
    private lateinit var button: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        setupViewModel()

        button = findViewById<View>(R.id.btnLogin)
        button.setOnClickListener {
            val intentUri = Uri.parse("https://www.strava.com/oauth/mobile/authorize")
                .buildUpon()
                .appendQueryParameter("client_id", "108172")
                .appendQueryParameter("redirect_uri", "myapp://developers.strava.com")
                .appendQueryParameter("response_type", "code")
                .appendQueryParameter("approval_prompt", "auto")
//                .appendQueryParameter("scope", "activity:write,read")
                .appendQueryParameter("scope", "activity:read_all,profile:read_all")
                .build()

            val intent = Intent(Intent.ACTION_VIEW, intentUri)
            startActivity(intent);
        }
    }

    private fun setupViewModel() {
        viewModel = ViewModelProviders.of(
            this,
            ViewModelLoginFactory(ApiLoginHelper())
        ).get(LoginViewModel::class.java)
    }

    private fun setupObservers() {
        var inputGetAccessToken = InputGetAccessToken()
        inputGetAccessToken.client_id = clientId
        inputGetAccessToken.client_secret = clientSecret
        inputGetAccessToken.code = code
        inputGetAccessToken.grant_type = grantType
        viewModel.getAccessToken(inputGetAccessToken).observe(this, Observer {
            it?.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        resource.data?.let { output ->
                            var intent = Intent(this, ActivityActivity::class.java)
                            intent.putExtra("accessToken", output.access_token)
                            startActivity(intent)
                        }
                    }
                    Status.ERROR -> {
                        Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                    }
                    Status.LOADING -> {
                    }
                }
            }
        })
    }

    override fun onResume() {
        super.onResume()

        val uri = intent.data
        if (uri != null && uri.toString().startsWith("myapp://developers.strava.com")) {
            code = uri.getQueryParameter("code").toString()
            scope = uri.getQueryParameter("scope").toString()
            setupObservers()
        }
    }
}