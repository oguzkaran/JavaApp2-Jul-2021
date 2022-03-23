package org.csystem.app.api.customer.service;

import org.csystem.app.api.customer.domain.Phone;
import org.csystem.app.api.customer.domain.PhoneType;
import org.csystem.app.api.customer.repository.IPhoneRepository;
import org.csystem.app.api.customer.repository.IPhoneTypeRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static org.csystem.util.data.DatabaseUtil.doWorkForRepository;
import static org.csystem.util.data.DatabaseUtil.doWorkForService;

/**
 * @author onder sahin
 */
@Service
public class PhoneService {

    private final IPhoneRepository phoneRepository;
    private final IPhoneTypeRepository phoneTypeRepository;

    public PhoneService(IPhoneRepository phoneRepository, IPhoneTypeRepository phoneTypeRepository)
    {
        this.phoneRepository = phoneRepository;
        this.phoneTypeRepository = phoneTypeRepository;
    }

    public Optional<Phone> findPhoneById(int id)
    {
        return doWorkForRepository(() -> phoneRepository.findById(id), "PhoneService.findPhoneById");
    }

    public Optional<PhoneType> findPhoneTypeById(int id)
    {
        return doWorkForRepository(() -> phoneTypeRepository.findById(id), "PhoneService.findPhoneTypeById");
    }

    private Phone savePhoneCallback(Phone phone)
    {
        return phoneRepository.save(phone);
    }

    private Phone updatePhoneCallback(Phone phone)
    {
        return phoneRepository.save(phone);
    }

    private Phone deletePhoneCallback(Phone phone)
    {
        phoneRepository.deleteById(phone.getId());
        return phone;
    }

    public Phone savePhone(Phone phone)
    {
        return doWorkForService(() -> savePhoneCallback(phone), "PhoneService.savePhone");
    }

    public Phone updatePhone(Phone phone)
    {
        return doWorkForService(() -> updatePhoneCallback(phone), "PhoneService.updatePhone");
    }


    public Phone deletePhone(Phone phone)
    {
        return doWorkForService(() -> deletePhoneCallback(phone), "PhoneService.deletePhone");
    }

}
