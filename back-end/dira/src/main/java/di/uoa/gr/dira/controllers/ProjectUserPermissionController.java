package di.uoa.gr.dira.controllers;


import di.uoa.gr.dira.models.project.ProjectUserPermissionModel;
import di.uoa.gr.dira.services.permissionService.IPermissionService;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Validated
@RestController
@RequestMapping("projects/{projectId}/users/permissions")
public class ProjectUserPermissionController {
    private final IPermissionService service;

    public ProjectUserPermissionController(IPermissionService service) {
        this.service = service;
    }

    @ApiOperation(
            value = "Retrieves all the user permissions for the project with the given id",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @GetMapping
    public List<@Valid ProjectUserPermissionModel> getProjectPermissionsForUsers(@PathVariable Long projectId) {
        return service.getProjectPermissionsForUsers(projectId);
    }

    @ApiOperation(
            value = "Creates a new user permission for the project with the given id",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @PostMapping
    public void createUserPermission(@PathVariable Long projectId, @Valid @RequestBody ProjectUserPermissionModel userPermissionModel) {
        service.createUserPermission(projectId, userPermissionModel);
    }

    @ApiOperation(
            value = "Updates the user permission for the project with the given id",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @PutMapping("{permissionId}")
    public @Valid ProjectUserPermissionModel updateUserPermission(@PathVariable Long projectId,
                                                                  @PathVariable Long permissionId,
                                                                  @Valid @RequestBody ProjectUserPermissionModel userPermissionModel) {
        return service.updateUserPermission(projectId, permissionId, userPermissionModel);
    }

    @ApiOperation(
            value = "Deletes the user's permission for the project with the given id"
    )
    @DeleteMapping("{permissionId}")
    public void deleteUserPermission(@PathVariable Long projectId, @PathVariable Long permissionId) {
        service.deleteUserPermission(projectId, permissionId);
    }
}
