package com.denis.API.Sistema.Liga.Nacional.de.Futebol.model.dto;

import java.util.List;

public record SumulaRequest(int golsMandante, int golsVisitante,Long idPartida, List<AmareloRequest> amarelos, List<VermelhoRequest> vermelhos, List<GolRequest> gols) {
}
