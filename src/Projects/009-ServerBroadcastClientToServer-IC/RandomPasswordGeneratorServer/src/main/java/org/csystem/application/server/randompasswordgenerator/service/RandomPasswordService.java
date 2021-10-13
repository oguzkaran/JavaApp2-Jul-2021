package org.csystem.application.server.randompasswordgenerator.service;

import org.csystem.application.server.randompasswordgenerator.data.dal.RandomPasswordHelper;
import org.csystem.application.server.randompasswordgenerator.data.dto.ClientDTO;
import org.csystem.application.server.randompasswordgenerator.mapper.IClientMapper;
import org.springframework.stereotype.Service;

import static org.csystem.util.data.DatabaseUtil.doWorkForService;

@Service
public class RandomPasswordService {
    private final RandomPasswordHelper m_randomPasswordHelper;
    private final IClientMapper m_clientMapper;

    private ClientDTO saveClientCallback(ClientDTO clientDTO)
    {
        var client = m_randomPasswordHelper.saveClient(m_clientMapper.toClient(clientDTO));

        return m_clientMapper.toClientDTO(client);
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

    //...
}
