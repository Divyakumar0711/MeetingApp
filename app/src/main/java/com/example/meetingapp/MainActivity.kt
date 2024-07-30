package com.example.meetingapp

import AllMeetingsFragment
import Meeting
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat



class MainActivity : AppCompatActivity() {

    private val meetings = mutableListOf<Meeting>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, CreateMeetingFragment())
                .commit()
        }

        val buttonCreateMeeting = findViewById<Button>(R.id.button_create_meeting)
        val buttonAllMeetings = findViewById<Button>(R.id.button_all_meetings)

        buttonCreateMeeting.setOnClickListener {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, CreateMeetingFragment())
                .commit()
        }

        buttonAllMeetings.setOnClickListener {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, AllMeetingsFragment())
                .commit()
        }
    }

    fun addMeeting(meeting: Meeting) {
        meetings.add(meeting)
    }

    fun getMeetings(): List<Meeting> = meetings
}

