package lms.pixel.backend.rowMapperStrategy;

import java.sql.ResultSet;
import java.sql.SQLException;

import lms.pixel.backend.model.Calendar;

public class CalendarMapper  extends RowMapperStrategy<Calendar> {
    @Override
    public Calendar mapRow(ResultSet rs, int rowNum) throws SQLException {
        Calendar calendarEventCourse = new Calendar();

        calendarEventCourse.setCourseTitle(rs.getString("course"));
        calendarEventCourse.setEventnumber(rs.getInt("eventnumber"));
        calendarEventCourse.setCourseid(rs.getInt("courseid"));
        calendarEventCourse.setTitle(rs.getString("title"));
        calendarEventCourse.setDescription(rs.getString("description"));
        calendarEventCourse.setStarttime(rs.getTimestamp("starttime"));
        calendarEventCourse.setEndtime(rs.getTimestamp("endtime"));

        return calendarEventCourse;
    }
}
