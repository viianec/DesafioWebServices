package com.example.marvel.comic.view

import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.ImageView
import androidx.fragment.app.DialogFragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.marvel.R
import com.squareup.picasso.Picasso

class HqExpandedFragment : DialogFragment(){
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_hq_expanded, container, false)
        val image = view.findViewById<ImageView>(R.id.imgHqExpanded)
        val coverThumbnail = arguments?.getString(HqFragment.HQ_IMAGE)
        Picasso.get().load(coverThumbnail).into(image)
        close(view)

        return view
    }
    private fun close(view: View) {
        view.findViewById<ImageView>(R.id.icClose).setOnClickListener {
            findNavController().navigateUp()
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog =  super.onCreateDialog(savedInstanceState)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        return dialog
    }

}

