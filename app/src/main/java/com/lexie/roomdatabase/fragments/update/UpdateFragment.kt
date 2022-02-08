package com.lexie.roomdatabase.fragments.update

import android.os.Bundle
import android.text.TextUtils
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.lexie.roomdatabase.R
import com.lexie.roomdatabase.model.User
import com.lexie.roomdatabase.viewmodel.UserViewModel


class UpdateFragment : Fragment() {

    private val args by navArgs<UpdateFragmentArgs>()

    private lateinit var mUserViewModel: UserViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val view = inflater.inflate(R.layout.fragment_update, container, false)

        mUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        view.findViewById<EditText>(R.id.updateFirstName).setText(args.currentItem.firstName)
        view.findViewById<EditText>(R.id.updateLastName).setText(args.currentItem.lastName)
        view.findViewById<EditText>(R.id.age).setText(args.currentItem.age.toString())

        view.findViewById<Button>(R.id.btnUpdate).setOnClickListener {
            updateItem()

        }
        //add menu
        setHasOptionsMenu(true)
        return view
    }
    private fun updateItem(){
        val firstName = view?.findViewById<EditText>(R.id.updateFirstName)?.text.toString()
        val lastName = view?.findViewById<EditText>(R.id.updateLastName)?.text.toString()
        val age = Integer.parseInt(view?.findViewById<EditText>(R.id.age)?.text.toString())


        if (inputCheck(firstName,lastName,age.toString())){
            //create user object
            val updateUser = User(args.currentItem.id,firstName,lastName,age)

            //update current user
            mUserViewModel.updateUser(updateUser)

            Toast.makeText(requireContext(),"updated successfully...",Toast.LENGTH_SHORT).show()

            //navigate
            findNavController().navigate(R.id.action_updateFragment_to_listFragment)
        }else{
            Toast.makeText(requireContext(),"fill out all thr fields...",Toast.LENGTH_SHORT).show()
        }
    }

    private fun inputCheck(firstName: String, lastName: String, age: String):Boolean {

        return !(TextUtils.isEmpty(firstName) && TextUtils.isEmpty(lastName) && age.isEmpty())
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_menu,menu)

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.menu_delete){
            deleteUser()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun deleteUser() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Yes"){_,_->
            mUserViewModel.deleteUser(args.currentItem)
            Toast.makeText(requireContext(),
                "deleted successfully: ${args.currentItem}"
                ,Toast.LENGTH_SHORT).show()

            findNavController().navigate(R.id.action_updateFragment_to_listFragment)
        }
        builder.setNegativeButton("No"){_,_->
        }
        builder.setTitle("Delete ${args.currentItem.firstName}")
        builder.setMessage("Are you sure you want delete ${args.currentItem.firstName}")
        builder.create().show()

    }


}