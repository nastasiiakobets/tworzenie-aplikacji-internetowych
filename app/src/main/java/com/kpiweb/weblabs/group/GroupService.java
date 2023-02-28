package com.kpiweb.weblabs.group;

import com.kpiweb.weblabs.exceptions.CRUDoperationException;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GroupService {
    private GroupRepository groupRepository;

    public enum SortFields {
        name
    }

    @Autowired
    public GroupService(GroupRepository groupRepository) {
        this.groupRepository = groupRepository;
    }

    public Page<Group> getGroups(
            @Min(value = 0, message = "Page is invalid!") Integer page,
            @Min(value = 1, message = "There can`t be less than 1 element per page!") Integer elementsPerPage,
            Sort.Direction sortDirection, GroupService.SortFields sortFields
    ) {
        Pageable pageable = PageRequest.of(page, elementsPerPage, Sort.by(sortDirection, sortFields.name()));
        return groupRepository.findAll(pageable);
    }

    public Group getOneById(Long id) {
        Optional<Group> result = groupRepository.findById(id);
        if (!result.isPresent()) {
            throw new CRUDoperationException("There`s no group with id: " + id,"id");
        }
        return result.orElse(null);
    }

    public void addNewGroup(@Valid Group group) {
        if (groupRepository.existsByNameAndDepartment(group.getName(), group.getDepartment())) {
            throw new CRUDoperationException("Group " + group.getName() + " already exists in department: " + group.getDepartment().getName()
                    , "name");
        }
        groupRepository.save(group);
    }

    public void deleteGroup(@Min(value = 1, message = "Id is invalid!") Long id) {
        if (!groupRepository.existsById(id)) {
            throw new CRUDoperationException("There is no Group with id: " + id, "id");
        }
        groupRepository.deleteById(id);
    }

    public List<Group> searchGroupByName(@NotNull String name) {
        return groupRepository.findAllByName(name);
    }

    public void updateGroup(@Valid Group group) {
        Optional<Group> GroupOptional = groupRepository.findById(group.getId());
        if (!GroupOptional.isPresent()) {
            throw new CRUDoperationException("There`s no Group with id: " + group.getId(), "id");
        }
        groupRepository.save(group);
    }

    public Group getById(@Min(value = 1, message = "Invalid id!") Long id) {
        Optional<Group> result = groupRepository.findById(id);
        return result.orElse(null);
    }
}