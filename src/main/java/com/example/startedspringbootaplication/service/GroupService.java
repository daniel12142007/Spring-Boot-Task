package com.example.startedspringbootaplication.service;

import com.example.startedspringbootaplication.dto.request.GroupRequest;
import com.example.startedspringbootaplication.dto.response.GroupResponse;
import com.example.startedspringbootaplication.model.Group;
import com.example.startedspringbootaplication.repository.CompanyRepository;
import com.example.startedspringbootaplication.repository.CourseRepository;
import com.example.startedspringbootaplication.repository.GroupRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GroupService {
    private final GroupRepository companyRepository;
    private final CourseRepository courseRepository;
    private final CompanyRepository companies;

    public ResponseEntity<String> saveGroup(GroupRequest request, Long courseId, Long companyId) {
        Group group = new Group();
        group.setCompany(companies.getById(companyId));
        group.setCourses(List.of(courseRepository.getById(courseId)));
        group.setEmail(request.getGroupName());
        companyRepository.save(group);
        return ResponseEntity.ok().build();
    }

    public GroupResponse getByIdGroup(Long id) {
        try {
            Group groupResponse = companyRepository.findById(id).get();
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

    public List<GroupResponse> findAllGroups() {
        List<Group> list = companyRepository.findAll();
        List<GroupResponse> getList = new ArrayList<>();
        for (Group group : list) {
            getList.add(getByIdGroup(group.getId()));
        }
        return getList;
    }

    public ResponseEntity<String> deleteByIdGroup(Long id) {
        try {
            companyRepository.deleteById(id);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            throw new RuntimeException("-----------------------------------------------------not fount id-----------------------------------------------------");
        }
    }
}