package com.example.myapplication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.RatingBar
import androidx.fragment.app.Fragment

class EvaluateFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_evaluate, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val ratingBar = view.findViewById<RatingBar>(R.id.ratingBar)
        val checkBox  = view.findViewById<CheckBox>(R.id.checkBoxAgree)
        val btnSend   = view.findViewById<Button>(R.id.buttonSend)

        fun updateButtonState() {
            btnSend.isEnabled = ratingBar.rating > 0f && checkBox.isChecked
        }

        ratingBar.setOnRatingBarChangeListener { _, _, _ -> updateButtonState() }
        checkBox.setOnCheckedChangeListener { _, _ -> updateButtonState() }

        btnSend.setOnClickListener {
            // fecha a EvaluateActivity e volta para a ResultActivity
            activity?.finish()
        }

        updateButtonState()
    }
}
