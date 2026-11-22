package tn.esprit.autoloc.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.autoloc.domain.Client;
import tn.esprit.autoloc.repository.ClientRepository;
import tn.esprit.autoloc.web.dto.ClientDto;
import tn.esprit.autoloc.web.mapper.ClientMapper;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements IClientService {
    private final ClientRepository clientRepository;
    private final ClientMapper clientMapper;

    @Override
    public ClientDto addClient(ClientDto dto) {
        Client client = clientMapper.toEntity(dto);
        return clientMapper.toDto(clientRepository.save(client));
    }

    @Override
    public List<ClientDto> getAllClients() {
        return clientRepository.findAll().stream().map(clientMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public ClientDto getClientById(Long id) {
        return clientRepository.findById(id).map(clientMapper::toDto).orElse(null);
    }

    @Override
    public ClientDto updateClient(Long id, ClientDto dto) {
        if (clientRepository.existsById(id)) {
            Client client = clientMapper.toEntity(dto);
            client.setIdClient(id);
            return clientMapper.toDto(clientRepository.save(client));
        }
        return null;
    }

    @Override
    public void deleteClient(Long id) {
        clientRepository.deleteById(id);
    }
}