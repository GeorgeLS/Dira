package di.uoa.gr.dira.controllers;

import di.uoa.gr.dira.models.issue.IssueModel;
import di.uoa.gr.dira.models.project.ProjectIssueModel;
import di.uoa.gr.dira.services.issueService.IssueService;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("project/{projectId}/issues")
public class IssueController {
    private final IssueService service;

    public IssueController(IssueService service) {
        this.service = service;
    }

    @GetMapping
    @ResponseBody
    public ProjectIssueModel getAllIssuesWithProjectId(@PathVariable("projectId") Long projectId) {
        return service.findAllIssuesByProjectId(projectId);
    }

    @PostMapping
    @ResponseBody
    public void createIssueWithProjectId(@PathVariable("projectId") Long projectId, @RequestBody IssueModel issueModel) {
        service.createIssueToProject(projectId, issueModel);
    }

    @GetMapping("{id}")
    @ResponseBody
    public IssueModel retrieveIssueWithProjectId(@PathVariable Long id) {
        return service.findIssueWithProjectId(id);
    }
}
