package com.rmgx.assetmanagement.service;

import com.rmgx.assetmanagement.entity.Asset;
import com.rmgx.assetmanagement.entity.Category;
import com.rmgx.assetmanagement.entity.Employee;
import com.rmgx.assetmanagement.repository.AssetRepository;
import com.rmgx.assetmanagement.repository.CategoryRepository;
import com.rmgx.assetmanagement.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AssetServiceImpl implements AssetService {

    @Autowired
    private AssetRepository assetRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public Asset addAsset(Asset asset) {
        //Load full Category entity from DB
        if (asset.getCategory() != null && asset.getCategory().getCategoryId() != null) {
            Category category = categoryRepository.findById(asset.getCategory().getCategoryId())
                    .orElseThrow(() -> new RuntimeException("Category not found"));
            asset.setCategory(category);
        }

        //Load full Employee entity from DB
        if (asset.getAssignedTo() != null && asset.getAssignedTo().getId() != null) {
            Employee employee = employeeRepository.findById(asset.getAssignedTo().getId())
                    .orElseThrow(() -> new RuntimeException("Employee not found"));
            asset.setAssignedTo(employee);
        }

        return assetRepository.save(asset);
    }

    @Override
    public List<Asset> getAllAssets() {
        return assetRepository.findAll();
    }

    @Override
    public Optional<Asset> getAssetById(Long id) {
        return assetRepository.findById(id);
    }
    @Override
    public Asset updateAssetById(Long id, Asset updatedAsset) {
        Asset existingAsset = assetRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Asset not found"));

        existingAsset.setAssetName(updatedAsset.getAssetName());
        existingAsset.setPurchaseDate(updatedAsset.getPurchaseDate());
        existingAsset.setConditionNotes(updatedAsset.getConditionNotes());
        existingAsset.setAssignmentStatus(updatedAsset.getAssignmentStatus());

        // Fetch full category from DB
        if (updatedAsset.getCategory() != null && updatedAsset.getCategory().getCategoryId() != null) {
            Long catId = updatedAsset.getCategory().getCategoryId();
            Category fullCategory = categoryRepository.findById(catId)
                    .orElseThrow(() -> new RuntimeException("Category not found"));
            existingAsset.setCategory(fullCategory);
        }

        existingAsset.setAssignedTo(updatedAsset.getAssignedTo());

        return assetRepository.save(existingAsset);
    }



    @Override
    public void deleteAsset(Long id) throws Exception {
        Optional<Asset> assetOpt = assetRepository.findById(id);
        if (assetOpt.isPresent()) {
            Asset asset = assetOpt.get();
            if ("Assigned".equalsIgnoreCase(asset.getAssignmentStatus())) {
                throw new Exception("Assigned asset cannot be deleted");
            }
            assetRepository.delete(asset);
        } else {
            throw new Exception("Asset not found");
        }
    }

    @Override
    public List<Asset> searchAssetsByName(String name) {
        return assetRepository.findByAssetNameContainingIgnoreCase(name);
    }

    @Override
    public Asset assignAssetToEmployee(Long assetId, Employee employee) {
        Optional<Asset> assetOpt = assetRepository.findById(assetId);
        if (assetOpt.isEmpty()) {
            throw new RuntimeException("Asset not found with id: " + assetId);
        }
        Asset asset = assetOpt.get();

        if ("Assigned".equalsIgnoreCase(asset.getAssignmentStatus())) {
            throw new RuntimeException("Asset is already assigned");
        }

        // Load full employee from DB
        Employee fullEmployee = employeeRepository.findById(employee.getId())
                .orElseThrow(() -> new RuntimeException("Employee not found"));

        asset.setAssignmentStatus("Assigned");
        asset.setAssignedTo(fullEmployee);

        return assetRepository.save(asset);
    }

    @Override
    public Asset recoverAssetFromEmployee(Long assetId) {
        Optional<Asset> assetOpt = assetRepository.findById(assetId);
        if (assetOpt.isEmpty()) {
            throw new RuntimeException("Asset not found with id: " + assetId);
        }
        Asset asset = assetOpt.get();

        if (!"Assigned".equalsIgnoreCase(asset.getAssignmentStatus())) {
            throw new RuntimeException("Asset is not currently assigned");
        }

        asset.setAssignmentStatus("Recovered");
        asset.setAssignedTo(null);

        return assetRepository.save(asset);
    }
}
