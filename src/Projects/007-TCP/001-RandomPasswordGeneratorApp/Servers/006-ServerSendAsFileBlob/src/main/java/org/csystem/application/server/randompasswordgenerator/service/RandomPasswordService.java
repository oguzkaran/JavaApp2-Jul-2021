package org.csystem.application.server.randompasswordgenerator.service;

import org.csystem.application.server.randompasswordgenerator.data.dal.RandomPasswordHelper;
import org.csystem.application.server.randompasswordgenerator.data.dto.ClientDTO;
import org.csystem.application.server.randompasswordgenerator.mapper.IClientMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.csystem.util.data.DatabaseUtil.doWorkForService;

@Service
public class RandomPasswordService {
    private final RandomPasswordHelper m_randomPasswordHelper;
    private final IClientMapper m_clientMapper;

    private ClientDTO saveClientCallback(ClientDTO clientDTO)
    {
        var client = m_randomPasswordHelper.saveClient(m_clientMapper.toClient(clientDTO));
        clientDTO.setId(client.id);

        return clientDTO;
    }

    private Optional<ClientDTO> findClientByIdCallback(long id)
    {
        var clientOpt = m_randomPasswordHelper.findClientById(id);

        return clientOpt.isEmpty() ? Optional.empty() : Optional.of(m_clientMapper.toClientDTO(clientOpt.get()));
    }

    public RandomPasswordService(RandomPasswordHelper randomPasswordHelper, IClientMapper clientMapper)
    {
        m_randomPasswordHelper = randomPasswordHelper;
        m_clientMapper = clientMapper;
    }

    public ClientDTO saveClient(ClientDTO clientDTO)
    {
        return doWorkForService(() -> saveClientCallback(clientDTO), "RandomPasswordService.saveClient");
    }

    @Transactional
    public Optional<ClientDTO> findClientById(long id)
    {
        return doWorkForService(() -> findClientByIdCallback(id), "RandomPasswordService.findClientById");
    }

    //...
}
