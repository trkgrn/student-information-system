package com.trkgrn.studentinformationsystem.api.service;

import com.trkgrn.studentinformationsystem.api.model.entity.Branch;
import com.trkgrn.studentinformationsystem.api.repository.BranchRepository;
import org.springframework.stereotype.Service;

@Service
public class BranchService {

    private final BranchRepository branchRepository;

    public BranchService(BranchRepository branchRepository) {
        this.branchRepository = branchRepository;
    }

    public Branch saveBranch(Branch branch) {
        return branchRepository.save(branch);
    }

    public Branch getBranchById(Long id) {
        return branchRepository.findById(id).orElse(null);
    }

    public void deleteBranchById(Long id) {
        branchRepository.deleteById(id);
    }

    public Branch updateBranch(Branch branch, Long id) {
        Branch branchOptional = branchRepository.findById(id).orElse(null);

        if (branchOptional != null) {
            Branch updatedBranch = branchOptional;
            updatedBranch.setBranchId(id);
            updatedBranch.setName(branch.getName());
            updatedBranch.setFaculty(branch.getFaculty());
            return branchRepository.save(updatedBranch);
        }

        return null;
    }

    public Iterable<Branch> getAllBranches() {
        return branchRepository.findAll();
    }

}
