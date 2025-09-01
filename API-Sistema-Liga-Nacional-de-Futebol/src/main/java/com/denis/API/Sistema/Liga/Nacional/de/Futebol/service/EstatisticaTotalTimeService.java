package com.denis.API.Sistema.Liga.Nacional.de.Futebol.service;

import com.denis.API.Sistema.Liga.Nacional.de.Futebol.excessoes.CadastroException;
import com.denis.API.Sistema.Liga.Nacional.de.Futebol.model.entity.EstatisticaTotalTime;
import com.denis.API.Sistema.Liga.Nacional.de.Futebol.model.entity.Time;
import com.denis.API.Sistema.Liga.Nacional.de.Futebol.repository.EstatisticaTotalTimeRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
public class EstatisticaTotalTimeService {

    private EstatisticaTotalTimeRepository estatisticaTotalTimeRepository;

    public EstatisticaTotalTimeService(EstatisticaTotalTimeRepository estatisticaTotalTimeRepository) {this.estatisticaTotalTimeRepository = estatisticaTotalTimeRepository;}

    public void cadastrarEstatisticaTotalTime(Time time){
        try {
            EstatisticaTotalTime estatisticaTotalTime = new EstatisticaTotalTime(time);

            estatisticaTotalTimeRepository.save(estatisticaTotalTime);
        } catch (DataIntegrityViolationException e){
            throw new CadastroException("Erro ao cadastrar Estatisticas Totais do Time: Dados Inválidos");
        } catch (Exception e) {
            throw new CadastroException("Erro inesperado ao cadastrar Estatisticas Totais do Time");
        }
    }
}
