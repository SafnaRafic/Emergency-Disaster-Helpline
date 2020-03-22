package org.SafnaRafic.emergencydisasterhelpline.models.data;

import org.SafnaRafic.emergencydisasterhelpline.models.DaysAvailability;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DaysAvailabilityRepository extends CrudRepository<DaysAvailability,Integer> {
}