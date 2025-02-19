package com.isi.school_management.user_service.controller;

import com.isi.school_management.user_service.dto.AdministrativeAgentDto;
import com.isi.school_management.user_service.service.AdministrativeAgentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/administrative-agents")
public class AdministrativeAgentController {

    @Autowired
    private AdministrativeAgentService administrativeAgentService;

    @PostMapping
    public ResponseEntity<AdministrativeAgentDto> createAdministrativeAgent(@Valid @RequestBody AdministrativeAgentDto administrativeAgentDto) {
        AdministrativeAgentDto createdAdministrativeAgent = administrativeAgentService.createAdministrativeAgent(administrativeAgentDto);
        return new ResponseEntity<>(createdAdministrativeAgent, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<AdministrativeAgentDto>> getAllAdministrativeAgents() {
        List<AdministrativeAgentDto> administrativeAgents = administrativeAgentService.getAllAdministrativeAgents();
        return ResponseEntity.ok(administrativeAgents);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AdministrativeAgentDto> getAdministrativeAgentById(@PathVariable Long id) {
        AdministrativeAgentDto administrativeAgent = administrativeAgentService.getAdministrativeAgentById(id);
        return ResponseEntity.ok(administrativeAgent);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AdministrativeAgentDto> updateAdministrativeAgent(@PathVariable Long id, @Valid @RequestBody AdministrativeAgentDto administrativeAgentDto) {
        AdministrativeAgentDto updatedAdministrativeAgent = administrativeAgentService.updateAdministrativeAgent(id, administrativeAgentDto);
        return ResponseEntity.ok(updatedAdministrativeAgent);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAdministrativeAgent(@PathVariable Long id) {
        administrativeAgentService.deleteAdministrativeAgent(id);
        return ResponseEntity.noContent().build();
    }
}