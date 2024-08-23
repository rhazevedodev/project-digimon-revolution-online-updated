package com.example.api.service;

import com.example.api.exception.DigimonSemVidaException;
import com.example.api.model.Atributos;
import com.example.api.model.ResultadoCombateInvasao;
import com.example.api.model.entity.Digimon;
import com.example.api.model.entity.Invasao;
import com.example.api.repository.DigimonRepository;
import com.example.api.repository.InvasaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CombateService {

    @Autowired
    private DigimonService digimonService;
    @Autowired
    private DigimonRepository digimonRepository;
    @Autowired
    private InvasaoService invasaoService;
    @Autowired
    private InvasaoRepository invasaoRepository;

    public ResultadoCombateInvasao iniciarCombate1vs1Invasao(Long idDigimonAtacante) {
        Digimon digimonAtacante = digimonRepository.findById(idDigimonAtacante).orElseThrow(() -> new IllegalArgumentException("Invalid Digimon ID: " + idDigimonAtacante));
        Digimon digimonDefensor = new Digimon();
        digimonDefensor.setNome("Invasor");
        Atributos atributos = new Atributos();
        digimonDefensor.setAtributos(atributos);
        digimonDefensor.getAtributos().setPontosVida(invasaoService.existInvasionActive().getEnergiaVital());
        digimonDefensor.getAtributos().setPontosForca((int) (digimonAtacante.getAtributos().getPontosForca()*1.5));
        digimonDefensor.getAtributos().setPontosInteligencia((int) (digimonAtacante.getAtributos().getPontosInteligencia()*1.5));
        digimonDefensor.getAtributos().setPontosConhecimento((int) (digimonAtacante.getAtributos().getPontosConhecimento()*1.5));
        digimonDefensor.getAtributos().setPontosAgilidade((int) (digimonAtacante.getAtributos().getPontosAgilidade()*1.5));

        String vencedor;
        StringBuilder log = new StringBuilder();

        Digimon primeiro = determinarPrimeiroAtacante(digimonAtacante, digimonDefensor);
        Digimon segundo = primeiro == digimonAtacante ? digimonDefensor : digimonAtacante;

        log.append("Combate iniciado entre ").append(digimonAtacante.getNome()).append(" e ").append(digimonDefensor.getNome()).append("!\n");
        log.append(primeiro.getNome()).append(" ataca primeiro!\n");

        log.append(digimonAtacante.getNome()).append(" começa com ").append(digimonAtacante.getAtributos().getPontosVida()).append(" pontos vitais.\n");
        log.append(digimonDefensor.getNome()).append(" começa com ").append(digimonDefensor.getAtributos().getPontosVida()).append(" pontos vitais.\n");

        int turno = 1;
        int totalDanoAtacante = 0;
        int totalDanoDefensor = 0;
        while (digimonAtacante.getAtributos().getPontosVida() > 0 && digimonDefensor.getAtributos().getPontosVida() > 0) {
            // First combatant attacks second combatant
            int physicalDamage = primeiro.getAtributos().getTotalAtaqueFisico() - segundo.getAtributos().getTotalDefesaFisica();
            int elementalDamage = primeiro.getAtributos().getTotalAtaqueElemental() - segundo.getAtributos().getTotalDefesaElemental();
            int totalDamage = Math.max(physicalDamage, 0) + Math.max(elementalDamage, 0);
            segundo.getAtributos().setPontosVida(segundo.getAtributos().getPontosVida() - totalDamage);

            log.append("Turno " + turno + ":\n");
            log.append(primeiro.getNome()).append(" ataca ").append(segundo.getNome()).append(" causando ").append(totalDamage).append(" de dano!\n");
            log.append(segundo.getNome()).append(" tem agora ").append(segundo.getAtributos().getPontosVida()).append(" de saúde.\n");
            totalDanoDefensor += totalDamage;

            if (segundo.getAtributos().getPontosVida() <= 0) {
                // o primeiro a atacar no caso da invasao vai ser sempre o invasor
                log.append(segundo.getNome()).append(" foi derrotado! ").append(primeiro.getNome()).append(" é o vencedor!\n");
                vencedor = primeiro.getNome();
                int vidaRestante = digimonAtacante.getAtributos().getPontosVida()-totalDanoDefensor;
                if(vidaRestante < 0 ){
                    vidaRestante = 0;
                }
                digimonAtacante.getAtributos().setPontosVida(vidaRestante);
                digimonRepository.save(segundo);
                digimonService.atualizarExpDigimon(digimonAtacante, 10);

                Invasao invasao = invasaoService.existInvasionActive();
                invasao.setEnergiaVital(invasao.getEnergiaVital()-totalDanoAtacante);
                invasao.setAtaquesSofridos(invasao.getAtaquesSofridos()+1);
                invasaoRepository.save(invasao);

                return new ResultadoCombateInvasao(log.toString(), primeiro, segundo, totalDanoAtacante, totalDanoDefensor,
                        digimonAtacante.getAtributos().getPontosVida(), digimonDefensor.getAtributos().getPontosVida());
            }
            turno++;

            // Second combatant attacks first combatant
            physicalDamage = segundo.getAtributos().getTotalAtaqueFisico() - primeiro.getAtributos().getTotalDefesaFisica();
            elementalDamage = segundo.getAtributos().getTotalAtaqueElemental() - segundo.getAtributos().getTotalDefesaElemental();
            totalDamage = Math.max(physicalDamage, 0) + Math.max(elementalDamage, 0);
            primeiro.getAtributos().setPontosVida(primeiro.getAtributos().getPontosVida() - totalDamage);
            totalDanoAtacante += totalDamage;

            log.append(segundo.getNome()).append(" ataca ").append(primeiro.getNome()).append(" causando ").append(totalDamage).append(" de dano!\n");
            log.append(primeiro.getNome()).append(" tem agora ").append(primeiro.getAtributos().getPontosVida()).append(" de saúde.\n");

            if (primeiro.getAtributos().getPontosVida() <= 0) {
                log.append(primeiro.getNome()).append(" foi derrotado! ").append(segundo.getNome()).append(" é o vencedor!\n");
                vencedor = segundo.getNome();
                return new ResultadoCombateInvasao(log.toString(), segundo, primeiro, totalDanoAtacante, totalDanoDefensor,
                        digimonAtacante.getAtributos().getPontosVida(), digimonDefensor.getAtributos().getPontosVida());
            }
        }
        return new ResultadoCombateInvasao(log.toString(), null, null, totalDanoAtacante, totalDanoDefensor,
                digimonAtacante.getAtributos().getPontosVida(), digimonDefensor.getAtributos().getPontosVida()); // Em caso de empate
    }

    public static Digimon determinarPrimeiroAtacante(Digimon digimon1, Digimon digimon2) {
        return digimon1.getAtributos().getPontosAgilidade() > digimon2.getAtributos().getPontosAgilidade() ? digimon1 : digimon2;
    }
}
