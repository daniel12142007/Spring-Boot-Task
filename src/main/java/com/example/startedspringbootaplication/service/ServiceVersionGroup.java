package com.example.startedspringbootaplication.service;

import com.example.startedspringbootaplication.dto.request.GroupsRequest;
import com.example.startedspringbootaplication.dto.response.GroupResponse;
import com.example.startedspringbootaplication.model.Groups;
import com.example.startedspringbootaplication.repository.GroupsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ServiceVersionGroup {
    private final GroupsRepository companyRepository;

    public ResponseEntity<String> saveGroup(GroupsRequest request) {
        Groups company = new Groups();
        company.setEmail(request.getEmail());
        companyRepository.save(company);
        return ResponseEntity.ok().build();
    }

    public GroupResponse getbyid(Long id) {
        Groups groupResponse;
        groupResponse = companyRepository.findById(id).get();
        if (groupResponse.getId() == null) {
            return null;
        }
        GroupResponse response = new GroupResponse();
        response.setEmail(groupResponse.getEmail());
        response.setCompanyId(String.valueOf(groupResponse.getId()));
        return response;
    }
}