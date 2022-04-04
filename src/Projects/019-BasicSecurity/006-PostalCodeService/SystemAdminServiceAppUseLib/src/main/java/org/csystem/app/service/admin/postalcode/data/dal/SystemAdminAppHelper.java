package org.csystem.app.service.admin.postalcode.data.dal;


import org.csystem.util.security.web.data.entity.Authority;
import org.csystem.util.security.web.data.entity.User;
import org.csystem.util.security.web.data.repository.IAuthorityRepository;
import org.csystem.util.security.web.data.repository.IUserRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

import static org.csystem.util.data.DatabaseUtil.doWorkForRepository;
import static org.csystem.util.data.DatabaseUtil.doWorkForRepositoryRunnable;


@Component
public class SystemAdminAppHelper {
    private final IUserRepository m_userRepository;
    private final IAuthorityRepository m_authorityRepository;

    private User saveUserCallback(User user)
    {
        m_userRepository.save(user);

        return user;
    }

    private void saveAuthorityCallback(Authority authority)
    {
        m_authorityRepository.save(authority);
    }

    public SystemAdminAppHelper(IUserRepository userRepository, IAuthorityRepository userRoleRepository)
    {
        m_userRepository = userRepository;
        m_authorityRepository = userRoleRepository;
    }

    public Optional<User> findUserById(String username)
    {
        return doWorkForRepository(() -> m_userRepository.findById(username), "SystemAdminAppHelper.findUserById");
    }

    public User saveUser(User user)
    {
        return doWorkForRepository(() -> saveUserCallback(user), "SystemAdminAppHelper.saveUser");
    }

    public void saveAuthority(Authority authority)
    {
        doWorkForRepositoryRunnable(() -> saveAuthorityCallback(authority), "SystemAdminAppHelper.saveAuthority");
    }
    //...
}
