package tn.esprit.autoloc.service;

import tn.esprit.autoloc.web.dto.ClientDto;
import java.util.List;

public interface IClientService {
    ClientDto addClient(ClientDto clientDto);
    List<ClientDto> getAllClients();
    ClientDto getClientById(Long id);
    ClientDto updateClient(Long id, ClientDto clientDto);
    void deleteClient(Long id);
}