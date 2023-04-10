package com.example.startedspringbootaplication.service;

import com.example.startedspringbootaplication.dto.request.GroupsRequest;
import com.example.startedspringbootaplication.dto.response.GroupResponse;
import com.example.startedspringbootaplication.model.Groups;
import com.example.startedspringbootaplication.repository.GroupsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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

    public List<GroupResponse> findAll() {
        List<Groups> list = companyRepository.findAll();
        List<GroupResponse> getList = new ArrayList<>();
        for (Groups sudent : list) {
            getList.add(getbyid(sudent.getId()));
        }
        return getList;
    }

    public ResponseEntity<String> delete(Long id) {
        companyRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}