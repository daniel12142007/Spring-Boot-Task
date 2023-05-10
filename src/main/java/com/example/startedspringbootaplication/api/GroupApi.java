package com.example.startedspringbootaplication.api;

import com.example.startedspringbootaplication.dto.request.GroupRequest;
import com.example.startedspringbootaplication.dto.response.GroupResponse;
import com.example.startedspringbootaplication.service.GroupService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/group")
@RequiredArgsConstructor
public class GroupApi {
    private final GroupService groupService;


    @PostMapping("/save/group/{courseId}/{companyId}")
    @PreAuthorize("hasAnyAuthority('ADMIN','TEACHER')")
    @Operation(summary = "save group ", description = " group can only be saved to the admin and teacher")
    public ResponseEntity<String> save(@RequestBody GroupRequest request, @PathVariable Long courseId, @PathVariable Long companyId) {
        groupService.saveGroup(request, courseId, companyId);
        return ResponseEntity.ok().body("user with name:" + request.getGroupName() + " successfully save");
    }


    @GetMapping("get/group/{id}")   
    @PreAuthorize("hasAnyAuthority('ADMIN','TEACHER')")
    @Operation(summary = "get by id group ", description = " group can be taken by ID for teacher and admin")
    public GroupResponse getById(@PathVariable Long id) {
        return groupService.getByIdGroup(id);
    }

    @GetMapping("find/all")
    @PreAuthorize("hasAnyAuthority('ADMIN','TEACHER')")
    @Operation(summary = "find all teacher ", description = " group can only find all id to the admin and teacher ")
    public List<GroupResponse> findAll() {
        return groupService.findAllGroups();
    }

    @PostMapping("/delete/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN','TEACHER')")
    @Operation(summary = "save teacher ", description = " group can only be deleteByIdCourse to the admin and teacher")
    public ResponseEntity<String> save(@PathVariable Long id) {
        groupService.deleteByIdGroup(id);
        return ResponseEntity.ok().body("deleteByIdCourse");
    }
}
