package reactor.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import reactor.entities.ScheduleEvent;

import java.util.List;
import java.util.Optional;

public interface ScheduleEventRepository extends JpaRepository<ScheduleEvent, Integer> {

    Optional<List<ScheduleEvent>> findScheduleEventsByMondayOrderByHourAscMinuteAscSecondAsc(boolean day);

    Optional<List<ScheduleEvent>> findScheduleEventsByTuesdayOrderByHourAscMinuteAscSecondAsc(boolean day);

    Optional<List<ScheduleEvent>> findScheduleEventsByWednesdayOrderByHourAscMinuteAscSecondAsc(boolean day);

    Optional<List<ScheduleEvent>> findScheduleEventsByThursdayOrderByHourAscMinuteAscSecondAsc(boolean day);

    Optional<List<ScheduleEvent>> findScheduleEventsByFridayOrderByHourAscMinuteAscSecondAsc(boolean day);

    Optional<List<ScheduleEvent>> findScheduleEventsBySaturdayOrderByHourAscMinuteAscSecondAsc(boolean day);

    Optional<List<ScheduleEvent>> findScheduleEventsBySundayOrderByHourAscMinuteAscSecondAsc(boolean day);

    Optional<List<ScheduleEvent>> findScheduleEventsByDeviceId(Integer deviceIdd);

    Optional<List<ScheduleEvent>> findScheduleEventsBySecondAfter(Integer second);
}
