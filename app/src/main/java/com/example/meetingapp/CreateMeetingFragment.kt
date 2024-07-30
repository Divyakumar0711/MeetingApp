package com.example.meetingapp

import AllMeetingsFragment
import Meeting
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.DatePicker
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Spinner

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [CreateMeetingFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CreateMeetingFragment : Fragment() {

    private lateinit var editTextPersonName: EditText
    private lateinit var radioGroupMeetingType: RadioGroup
    private lateinit var datePicker: DatePicker
    private lateinit var spinnerTimeSlot: Spinner
    private lateinit var buttonAddMeeting: Button

    private val timeSlots = List(9 * 60 + 1) { index ->
        val hour = index / 60 + 9
        val minute = index % 60
        String.format("%02d:%02d", hour, minute)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_create_meeting, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        editTextPersonName = view.findViewById(R.id.editTextPersonName)
        radioGroupMeetingType = view.findViewById(R.id.radioGroupMeetingType)
        datePicker = view.findViewById(R.id.datePicker)
        spinnerTimeSlot = view.findViewById(R.id.spinnerTimeSlot)
        buttonAddMeeting = view.findViewById(R.id.buttonAddMeeting)

        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, timeSlots)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerTimeSlot.adapter = adapter

        buttonAddMeeting.setOnClickListener {
            val name = editTextPersonName.text.toString()
            val type = if (view.findViewById<RadioButton>(R.id.radioOnline).isChecked) "Online" else "Offline"
            val date = "${datePicker.dayOfMonth}/${datePicker.month + 1}/${datePicker.year}"
            val time = spinnerTimeSlot.selectedItem.toString()

            // Store the meeting data in arguments or use a shared ViewModel
            val meeting = Meeting(name, type, date, time)
            (activity as MainActivity).addMeeting(meeting)

            // Navigate to the AllMeetingsFragment
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, AllMeetingsFragment())
                .addToBackStack(null)
                .commit()
        }
    }
}
