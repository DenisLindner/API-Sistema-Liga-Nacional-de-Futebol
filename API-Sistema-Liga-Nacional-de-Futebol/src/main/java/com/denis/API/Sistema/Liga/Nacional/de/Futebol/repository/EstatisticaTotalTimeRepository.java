package com.denis.API.Sistema.Liga.Nacional.de.Futebol.repository;

import com.denis.API.Sistema.Liga.Nacional.de.Futebol.model.entity.EstatisticaTotalTime;
import com.denis.API.Sistema.Liga.Nacional.de.Futebol.model.entity.Time;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EstatisticaTotalTimeRepository extends JpaRepository<EstatisticaTotalTime,Long> {

    EstatisticaTotalTime findByTime(Time time) throws Exception;
}
