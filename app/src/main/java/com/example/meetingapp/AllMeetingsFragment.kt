import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.fragment.app.Fragment
import com.example.meetingapp.MainActivity
import com.example.meetingapp.R

class AllMeetingsFragment : Fragment() {

    private lateinit var listViewMeetings: ListView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_all_meetings, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        listViewMeetings = view.findViewById(R.id.listViewMeetings)

        // Retrieve and display meetings
        val meetings = (activity as MainActivity).getMeetings()
        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, meetings.map { it.toString() })
        listViewMeetings.adapter = adapter
    }
}
