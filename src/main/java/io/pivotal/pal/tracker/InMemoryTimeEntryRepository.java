package io.pivotal.pal.tracker;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.springframework.http.ResponseEntity.ok;

public class InMemoryTimeEntryRepository implements TimeEntryRepository{
    private HashMap<Long, TimeEntry> timeEntries = new HashMap<>();
    Long currentId = 1L;



    @Override
    public TimeEntry create(TimeEntry timeEntryToCreate) {
        Long id = currentId++;
        TimeEntry createdTimeEntry = new TimeEntry(
                id,
                timeEntryToCreate.getProjectId(),
                timeEntryToCreate.getUserId(),
                timeEntryToCreate.getDate(),
                timeEntryToCreate.getHours()
        );
        timeEntries.put(id, createdTimeEntry);
        return createdTimeEntry;

    }

    @Override
    public TimeEntry find(long timeEntryId) {
        return timeEntries.get(timeEntryId);
    }

    @Override
    public List<TimeEntry> list() {
        return new ArrayList<>(timeEntries.values());

    }

    @Override
    public TimeEntry update(long timeEntryId, TimeEntry newTimeEntry) {
        if (find(timeEntryId) == null)
            return null;
        TimeEntry updatedTimeEntry = new TimeEntry(
                timeEntryId,
                newTimeEntry.getProjectId(),
                newTimeEntry.getUserId(),
                newTimeEntry.getDate(),
                newTimeEntry.getHours()
        );

        timeEntries.replace(timeEntryId, updatedTimeEntry);
        return updatedTimeEntry;
    }

    @Override
    public void delete(long timeEntryId) {
        timeEntries.remove(timeEntryId);
    }
}
