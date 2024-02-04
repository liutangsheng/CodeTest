第一题
fun canAttendMeetings(intervals: Array<IntArray>): Boolean {
    if (intervals.size <= 1) {
        return true
    }
    
    val sortedIntervals = intervals.sortedBy { it[0] }
    
    for (i in 0 until sortedIntervals.size - 1) {
        val currentEnd = sortedIntervals[i][1]
        val nextStart = sortedIntervals[i+1][0]
        
        if (currentEnd > nextStart) {
            return false
        }
    }
    
    return true
}
