package com.example.startedspringbootaplication.service;

import com.example.startedspringbootaplication.dto.request.GroupsRequest;
import com.example.startedspringbootaplication.dto.response.GroupResponse;
import com.example.startedspringbootaplication.model.Groups;
import com.example.startedspringbootaplication.repository.CompanyRepository;
import com.example.startedspringbootaplication.repository.CourserRepository;
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
    private final CourserRepository courserRepository;
    private final CompanyRepository companys;

    public ResponseEntity<String> saveGroup(GroupsRequest request, Long courseId, Long companyId) {
        Groups company = new Groups();
        company.setCompany(companys.getById(companyId));
        company.setCourses(List.of(courserRepository.getById(courseId)));
        company.setEmail(request.getGroupName());
        companyRepository.save(company);
        return ResponseEntity.ok().build();
    }

    public GroupResponse getById(Long id) {
        try {
            Groups groupResponse = companyRepository.findById(id).get();
            if (groupResponse.getId() == null) {
                return null;
            }
            GroupResponse response = new GroupResponse();
            response.setGroupName(groupResponse.getEmail());
            response.setCompanyId(String.valueOf(groupResponse.getId()));
            return response;
        } catch (RuntimeException e) {
            throw new RuntimeException("-----------------------there is no such:\" + id + \" please enter an ID that exists-----------------------");
        }
    }

    public List<GroupResponse> findAll() {
        List<Groups> list = companyRepository.findAll();
        List<GroupResponse> getList = new ArrayList<>();
        for (Groups group : list) {
            getList.add(getById(group.getId()));
        }
        return getList;
    }

    public ResponseEntity<String> delete(Long id) {
        try {
            companyRepository.deleteById(id);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            throw new RuntimeException("-----------------------------------------------------not fount id-----------------------------------------------------");
        }
    }
}