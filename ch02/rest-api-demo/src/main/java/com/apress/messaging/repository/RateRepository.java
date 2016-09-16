package com.apress.messaging.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.apress.messaging.domain.Rate;

@Repository
public interface RateRepository  extends JpaRepository<Rate,String>{
		List<Rate> findByDate(Date date);
}
