package di.uoa.gr.dira.models.sprint;

import di.uoa.gr.dira.models.IModel;

import di.uoa.gr.dira.models.issue.IssueModel;
import di.uoa.gr.dira.models.project.ProjectModel;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
public class SprintModel implements IModel<Long> {
    @NotNull
    @Positive
    private Long id;

    @NotNull
    private ProjectModel project;

    private List<IssueModel> issueModels;

    @NotNull
    private Date startDate;

    @NotNull
    private Date dueDate;

    @NotNull
    private boolean isActive;

}
