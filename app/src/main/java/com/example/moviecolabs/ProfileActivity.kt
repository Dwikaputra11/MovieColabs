package com.example.moviecolabs

import android.app.AlertDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.moviecolabs.databinding.ActivityProfileBinding

class ProfileActivity : AppCompatActivity() {

    lateinit var binding : ActivityProfileBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnLogout.setOnClickListener {
            alerDialog()
        }
    }
    fun alerDialog(){
        val builder = AlertDialog.Builder(this)

//            set tittle of alert dialog box
        builder.setTitle(R.string.title)
//            set message for dialog box
        builder.setMessage(R.string.dialogMsg)
//        builder.setIcon(R.drawable.ic_baseline_add_alert_24)

//            performing neutral action
        builder.setNegativeButton("Cancel"){dialogInterface, which ->
            Toast.makeText(this,"Cancel", Toast.LENGTH_LONG).show()
        }

        builder.setPositiveButton("Yes"){dialogInterface, which->
            val intentLogin = Intent(this, LoginActivity::class.java)
            startActivity(intentLogin)
            Toast.makeText(this,"Anda Logout", Toast.LENGTH_LONG).show()
        }
//            creating dialog box
        val alertDialog: AlertDialog = builder.create()
        alertDialog.setCancelable(false)
        alertDialog.show()


    }
}