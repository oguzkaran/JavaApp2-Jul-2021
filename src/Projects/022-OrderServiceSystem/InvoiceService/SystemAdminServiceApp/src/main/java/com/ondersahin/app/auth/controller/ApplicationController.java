package com.ondersahin.app.auth.controller;

import com.ondersahin.app.auth.controller.dto.ApplicationUserRoleSaveRequestDTO;
import com.ondersahin.app.auth.service.ApplicationUserService;
import com.ondersahin.app.auth.controller.dto.ApplicationUserSaveRequestDTO;
import com.ondersahin.app.auth.controller.mapper.ApplicationUserMapper;
import com.ondersahin.app.auth.controller.mapper.ApplicationUserRoleMapper;
import com.ondersahin.app.pdb.api.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.RolesAllowed;

/**
 * @author onder sahin
 */

@RestController
@RequestMapping("api/users")
public class ApplicationController {

    private ApplicationUserService applicationUserService;
    private ApplicationUserMapper applicationUserMapper;
    private ApplicationUserRoleMapper applicationUserRoleMapper;

    public ApplicationController(ApplicationUserService applicationUserService, ApplicationUserMapper applicationUserMapper, ApplicationUserRoleMapper applicationUserRoleMapper)
    {
        this.applicationUserService = applicationUserService;
        this.applicationUserMapper = applicationUserMapper;
        this.applicationUserRoleMapper = applicationUserRoleMapper;
    }

    @PostMapping
    @RolesAllowed("SYSTEM_ADMIN")
    public ResponseEntity<ApiResponse<Integer>> saveUser(@RequestBody ApplicationUserSaveRequestDTO userSaveRequestDTO)
    {
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<>(applicationUserService.saveApplicationUser(applicationUserMapper.toEntity(userSaveRequestDTO)).getId()));
    }


    @PostMapping("roles")
    @RolesAllowed("SYSTEM_ADMIN")
    public ResponseEntity<ApiResponse<Integer>> saveUserRole(@RequestBody ApplicationUserRoleSaveRequestDTO userRoleSaveRequestDTO)
    {
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<>(applicationUserService.saveApplicationUserRole(applicationUserRoleMapper.toEntity(userRoleSaveRequestDTO)).getId()));
    }

}
