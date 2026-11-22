package tn.esprit.autoloc.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.autoloc.domain.Agence;
import tn.esprit.autoloc.repository.AgenceRepository;
import tn.esprit.autoloc.web.dto.AgenceDto;
import tn.esprit.autoloc.web.mapper.AgenceMapper;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AgenceServiceImpl implements IAgenceService {
    private final AgenceRepository agenceRepository;
    private final AgenceMapper agenceMapper;

    @Override
    public AgenceDto addAgence(AgenceDto dto) {
        Agence agence = agenceMapper.toEntity(dto);
        return agenceMapper.toDto(agenceRepository.save(agence));
    }

    @Override
    public List<AgenceDto> getAllAgences() {
        return agenceRepository.findAll().stream().map(agenceMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public AgenceDto getAgenceById(Long id) {
        return agenceRepository.findById(id).map(agenceMapper::toDto).orElse(null);
    }

    @Override
    public AgenceDto updateAgence(Long id, AgenceDto dto) {
        if (agenceRepository.existsById(id)) {
            Agence agence = agenceMapper.toEntity(dto);
            agence.setIdAgence(id);
            return agenceMapper.toDto(agenceRepository.save(agence));
        }
        return null;
    }

    @Override
    public void deleteAgence(Long id) {
        agenceRepository.deleteById(id);
    }
}