package com.isi.school_management.user_service.service;

import com.isi.school_management.user_service.dto.AdministrativeAgentDto;
import com.isi.school_management.user_service.entity.AdministrativeAgentEntity;
import com.isi.school_management.user_service.entity.UserEntity;
import com.isi.school_management.user_service.exception.ResourceNotFoundException;
import com.isi.school_management.user_service.mapper.AdministrativeAgentMapper;
import com.isi.school_management.user_service.repository.AdministrativeAgentRepository;
import com.isi.school_management.user_service.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class AdministrativeAgentService {

    @Autowired
    private AdministrativeAgentRepository administrativeAgentRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AdministrativeAgentMapper administrativeAgentMapper;

    @Transactional
    public AdministrativeAgentDto createAdministrativeAgent(AdministrativeAgentDto administrativeAgentDto) {
        log.info("Création d'un nouvel agent administratif");

        if (administrativeAgentDto.user() == null || administrativeAgentDto.user().emailPro() == null) {
            throw new IllegalArgumentException("Les informations utilisateur sont requises");
        }

        UserEntity userEntity = userRepository.findByEmailPro(administrativeAgentDto.user().emailPro());
        if (userEntity == null) {
            throw new ResourceNotFoundException("Utilisateur non trouvé avec l'email: " + administrativeAgentDto.user().emailPro());
        }

        if (administrativeAgentRepository.existsByUser(userEntity)) {
            throw new IllegalStateException("Cet utilisateur est déjà associé à un agent administratif");
        }

        AdministrativeAgentEntity administrativeAgentEntity = administrativeAgentMapper.administrativeAgentDtoToAdministrativeAgentEntity(administrativeAgentDto);
        administrativeAgentEntity.setUser(userEntity);

        try {
            AdministrativeAgentEntity savedEntity = administrativeAgentRepository.save(administrativeAgentEntity);
            return administrativeAgentMapper.administrativeAgentEntityToAdministrativeAgentDto(savedEntity);
        } catch (Exception e) {
            log.error("Erreur lors de la création de l'agent administratif", e);
            throw new RuntimeException("Erreur lors de la création de l'agent administratif");
        }
    }

    public List<AdministrativeAgentDto> getAllAdministrativeAgents() {
        return administrativeAgentRepository.findAll().stream()
                .map(administrativeAgentMapper::administrativeAgentEntityToAdministrativeAgentDto)
                .collect(Collectors.toList());
    }

    public AdministrativeAgentDto getAdministrativeAgentById(Long id) {
        AdministrativeAgentEntity administrativeAgentEntity = administrativeAgentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Agent administratif non trouvé avec l'id: " + id));
        return administrativeAgentMapper.administrativeAgentEntityToAdministrativeAgentDto(administrativeAgentEntity);
    }

    @Transactional
    public AdministrativeAgentDto updateAdministrativeAgent(Long id, AdministrativeAgentDto administrativeAgentDto) {
        AdministrativeAgentEntity existingAdministrativeAgent = administrativeAgentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Agent administratif non trouvé avec l'id: " + id));

        administrativeAgentMapper.updateAdministrativeAgentFromDto(administrativeAgentDto, existingAdministrativeAgent);
        AdministrativeAgentEntity updatedEntity = administrativeAgentRepository.save(existingAdministrativeAgent);
        return administrativeAgentMapper.administrativeAgentEntityToAdministrativeAgentDto(updatedEntity);
    }

    public void deleteAdministrativeAgent(Long id) {
        if (!administrativeAgentRepository.existsById(id)) {
            throw new ResourceNotFoundException("Agent administratif non trouvé avec l'id: " + id);
        }
        administrativeAgentRepository.deleteById(id);
    }
}