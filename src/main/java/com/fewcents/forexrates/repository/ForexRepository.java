package com.fewcents.forexrates.repository;

import com.fewcents.forexrates.models.ForexRates;
import com.fewcents.forexrates.models.Key;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ForexRepository extends JpaRepository<ForexRates, Long> {

  ForexRates findByKey(Key key);

}
