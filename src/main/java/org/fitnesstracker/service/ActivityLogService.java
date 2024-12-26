package org.fitnesstracker.service;

import lombok.RequiredArgsConstructor;
import org.fitnesstracker.model.ActivityLog;
import org.fitnesstracker.repository.ActivityLogRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ActivityLogService
{
    private final ActivityLogRepository activityLogRepository;

    public ActivityLog saveActivityLog( ActivityLog log )
    {
        return activityLogRepository.save( log );
    }

    public List<ActivityLog> getActivityLogs()
    {
        return activityLogRepository.findAll();
    }

    public Optional<ActivityLog> getActivityLogById( Long id )
    {
        return activityLogRepository.findById( id );
    }

    public void deleteActivityLog( Long id )
    {
        activityLogRepository.deleteById( id );
    }
}
