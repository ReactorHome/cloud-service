package reactor.schedule;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import reactor.entities.ScheduleEvent;
import reactor.repositories.ScheduleEventRepository;

import java.util.*;
import java.util.concurrent.ConcurrentLinkedQueue;

@Component
public class ScheduledTasks {

    @Autowired
    private ScheduleEventRepository scheduleEventRepository;

    private Queue<ScheduleEvent> eventQueue = new ConcurrentLinkedQueue<>();

    @Scheduled(fixedRate = 5000)
    public void getDueEvents() {
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);

        Optional<List<ScheduleEvent>> optional = null;
        switch (dayOfWeek){
            case 1:
                optional = scheduleEventRepository.findScheduleEventsBySundayOrderByHourAscMinuteAscSecondAsc(true);
                break;
            case 2:
                optional = scheduleEventRepository.findScheduleEventsByMondayOrderByHourAscMinuteAscSecondAsc(true);
                break;
            case 3:
                optional = scheduleEventRepository.findScheduleEventsByTuesdayOrderByHourAscMinuteAscSecondAsc(true);
                break;
            case 4:
                optional = scheduleEventRepository.findScheduleEventsByWednesdayOrderByHourAscMinuteAscSecondAsc(true);
                break;
            case 5:
                optional = scheduleEventRepository.findScheduleEventsByThursdayOrderByHourAscMinuteAscSecondAsc(true);
                break;
            case 6:
                optional = scheduleEventRepository.findScheduleEventsByFridayOrderByHourAscMinuteAscSecondAsc(true);
                break;
            case 7:
                optional = scheduleEventRepository.findScheduleEventsBySaturdayOrderByHourAscMinuteAscSecondAsc(true);
                break;
            default:
        }

        optional.ifPresent(scheduleEvents -> eventQueue.addAll(scheduleEvents));
    }

    @Scheduled(fixedRate = 5000)
    public void executeEvents() {
        while (!eventQueue.isEmpty()){
            ScheduleEvent event = eventQueue.remove();

            // execute event
            System.out.println(event.getAttribute_name());
        }
    }
}