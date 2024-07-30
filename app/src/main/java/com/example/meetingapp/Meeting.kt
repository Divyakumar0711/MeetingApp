data class Meeting(
    val personName: String,
    val meetingType: String,
    val date: String,
    val timeSlot: String
) {
    override fun toString(): String {
        return "$personName, $meetingType, $date, $timeSlot"
    }
}
