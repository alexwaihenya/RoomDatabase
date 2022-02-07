package com.lexie.roomdatabase.fragments.add

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.lexie.roomdatabase.R
import com.lexie.roomdatabase.viewmodel.UserViewModel
import com.lexie.roomdatabase.model.User

class AddFragment : Fragment() {


    private lateinit var mUserViewModel: UserViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_add, container, false)

        mUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        val btnAdd = view.findViewById<Button>(R.id.btnAdd)
        btnAdd.setOnClickListener {
            insertDataToDatabase()
        }

        return view
    }

    private fun insertDataToDatabase() {
        val firstName = view?.findViewById<EditText>(R.id.firstName)?.text.toString()
        val lastName = view?.findViewById<EditText>(R.id.lastName)?.text.toString()
        val age = view?.findViewById<EditText>(R.id.age)?.text


        if (inputCheck(firstName,lastName,age.toString())){
            // Create User Object
            val user = User(0,firstName,lastName,Integer.parseInt(age.toString()))

            //Add Data to Database
            mUserViewModel.addUser(user)

            Toast.makeText(requireContext(),"Successfully Added...",Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_addFragment_to_listFragment)
        }else{
            Toast.makeText(requireContext(),"All fields are required...",Toast.LENGTH_SHORT).show()
        }


    }

    private fun inputCheck(firstName: String, lastName: String, age: String):Boolean {

        return !(TextUtils.isEmpty(firstName) && TextUtils.isEmpty(lastName) && age.isEmpty())
    }

}