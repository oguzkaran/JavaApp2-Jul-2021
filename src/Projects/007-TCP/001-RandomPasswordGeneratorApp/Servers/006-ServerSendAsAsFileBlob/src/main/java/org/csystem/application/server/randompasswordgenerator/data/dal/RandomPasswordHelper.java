package org.csystem.application.server.randompasswordgenerator.data.dal;

import org.csystem.application.server.randompasswordgenerator.data.entity.Client;
import org.csystem.application.server.randompasswordgenerator.data.repository.IClientRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.OptionalInt;

import static org.csystem.util.data.DatabaseUtil.doWorkForRepository;

@Component
public class RandomPasswordHelper {
    private final IClientRepository m_clientRepository;

    public RandomPasswordHelper(IClientRepository clientRepository)
    {
        m_clientRepository = clientRepository;
    }

    public Client saveClient(Client client)
    {
        return doWorkForRepository(() -> m_clientRepository.save(client), "RandomPasswordDAL.saveClient");
    }

    public Optional<Client> findClientById(long id)
    {
        return doWorkForRepository(() -> m_clientRepository.findById(id), "RandomPasswordDAL.findClientById");
    }
    //...
}
