package com.denis.API.Sistema.Liga.Nacional.de.Futebol.service;

import com.denis.API.Sistema.Liga.Nacional.de.Futebol.excessoes.CadastroException;
import com.denis.API.Sistema.Liga.Nacional.de.Futebol.model.dto.*;
import com.denis.API.Sistema.Liga.Nacional.de.Futebol.model.entity.Time;
import com.denis.API.Sistema.Liga.Nacional.de.Futebol.repository.TimeRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
public class TimeService {

    private TimeRepository timeRepository;

    public TimeService(TimeRepository timeRepository) {this.timeRepository = timeRepository;}

    public TimeResponse cadastrarTime (TimeRequest dto){
        try {
            Time time = new Time();
            BeanUtils.copyProperties(dto, time);

            Time salvo = timeRepository.save(time);

            return new TimeResponse(salvo.getId(), salvo.getNome(), salvo.getEstadio(), salvo.getNomeTreinador(), salvo.getCampeonato().getNome());
        } catch (DataIntegrityViolationException e){
            throw new CadastroException("Erro ao cadastrar Time: Dados Inválidos");
        } catch (Exception e) {
            throw new CadastroException("Erro inesperado ao cadastrar Time");
        }
    }
}
