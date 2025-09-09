package com.denis.API.Sistema.Liga.Nacional.de.Futebol.service;

import com.denis.API.Sistema.Liga.Nacional.de.Futebol.excessoes.CadastroException;
import com.denis.API.Sistema.Liga.Nacional.de.Futebol.model.dto.CartaoRequest;
import com.denis.API.Sistema.Liga.Nacional.de.Futebol.model.dto.CartaoResponse;
import com.denis.API.Sistema.Liga.Nacional.de.Futebol.model.entity.Amarelo;
import com.denis.API.Sistema.Liga.Nacional.de.Futebol.model.entity.Cartao;
import com.denis.API.Sistema.Liga.Nacional.de.Futebol.model.entity.Vermelho;
import com.denis.API.Sistema.Liga.Nacional.de.Futebol.repository.AmareloRepository;
import com.denis.API.Sistema.Liga.Nacional.de.Futebol.repository.AtletaRepository;
import com.denis.API.Sistema.Liga.Nacional.de.Futebol.repository.PartidaRepository;
import com.denis.API.Sistema.Liga.Nacional.de.Futebol.repository.VermelhoRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
public class CartaoService {

    private AmareloRepository amareloRepository;
    private VermelhoRepository vermelhoRepository;
    private PartidaRepository partidaRepository;
    private AtletaRepository atletaRepository;

    public CartaoService(AmareloRepository amareloRepository, VermelhoRepository vermelhoRepository,  PartidaRepository partidaRepository, AtletaRepository atletaRepository) {
        this.amareloRepository = amareloRepository;
        this.vermelhoRepository = vermelhoRepository;
        this.partidaRepository = partidaRepository;
        this.atletaRepository = atletaRepository;
    }

    public CartaoResponse cadastrarCartao(CartaoRequest dto) {
        try {
            Cartao cartao;
            if (dto.tipo().equals("AMARELO")) {
                cartao = new Amarelo();
                cartao.setMinuto(dto.minuto());
                cartao.setPartida(partidaRepository.findById(dto.idPartida()).orElseThrow());
                cartao.setAtleta(atletaRepository.findById(dto.idAtleta()).orElseThrow());

                Amarelo salvo = amareloRepository.save((Amarelo) cartao);

                return new CartaoResponse(salvo.getId(), salvo.getMinuto(), salvo.getAtleta().getNome(), salvo.getAtleta().getTime().getNome(), salvo.getClass().getSimpleName());
            } else if (dto.tipo().equals("VERMELHO")) {
                cartao = new Vermelho();
                cartao.setMinuto(dto.minuto());
                cartao.setPartida(partidaRepository.findById(dto.idPartida()).orElseThrow());
                cartao.setAtleta(atletaRepository.findById(dto.idAtleta()).orElseThrow());

                Vermelho salvo = vermelhoRepository.save((Vermelho) cartao);

                return new CartaoResponse(salvo.getId(), salvo.getMinuto(), salvo.getAtleta().getNome(), salvo.getAtleta().getTime().getNome(), salvo.getClass().getSimpleName());
            } else {
                throw new CadastroException("Tipo Inválido de Cartão");
            }
        } catch (DataIntegrityViolationException e) {
            throw new CadastroException("Erro ao cadastrar Cartão: Dados Inválidos");
        } catch (Exception e) {
            throw new CadastroException("Erro inesperado ao cadastrar Cartão");
        }
    }
}
