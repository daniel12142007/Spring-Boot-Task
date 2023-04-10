package com.example.startedspringbootaplication.api.GroupsApi;

import com.example.startedspringbootaplication.dto.auth.AuthRequest;
import com.example.startedspringbootaplication.dto.auth.AuthResponse;
import com.example.startedspringbootaplication.dto.request.GroupsRequest;
import com.example.startedspringbootaplication.dto.response.CourserResponse;
import com.example.startedspringbootaplication.dto.response.GroupResponse;
import com.example.startedspringbootaplication.service.ServiceVersionGroup;
import com.example.startedspringbootaplication.service.auth.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.PermitAll;
import java.util.List;

@RestController
@RequestMapping("api/v1/group")
@RequiredArgsConstructor
public class V3 {
    private final ServiceVersionGroup company;
    private final AuthService authService;


    @PostMapping("/save/group/{courseId}/{companyId}")
    @PreAuthorize("hasAnyAuthority('ADMIN','TEACHER')")
    @Operation(summary = "save group ", description = " group can only be saved to the admin and teacher")
    public ResponseEntity<String> save(@RequestBody GroupsRequest request, @PathVariable Long courseId, @PathVariable Long companyId) {
        company.saveGroup(request, courseId, companyId);
        return ResponseEntity.ok().body("user with name:" + request.getEmail() + " successfully save");
    }


    @GetMapping("get/group/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN','TEACHER')")
    @Operation(summary = "get by id group ", description = " group can only be get by id to the admin and teacher")
    public GroupResponse getbyid(@PathVariable Long id) {
        return company.getbyid(id);
    }

    @GetMapping("find/all")
    @PreAuthorize("hasAnyAuthority('ADMIN','TEACHER')")
    @Operation(summary = "find all teacher ", description = " group can only find all id to the admin and teacher ")
    public List<GroupResponse> findAll() {
        return company.findAll();
    }

    @PostMapping("/delete/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN','TEACHER')")
    @Operation(summary = "save teacher ", description = " group can only be delete to the admin and teacher")
    public ResponseEntity<String> save(@PathVariable Long id) {
        company.delete(id);
        return ResponseEntity.ok().body("delete");
    }
}
