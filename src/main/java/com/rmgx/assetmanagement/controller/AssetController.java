package com.rmgx.assetmanagement.controller;

import com.rmgx.assetmanagement.entity.Asset;
import com.rmgx.assetmanagement.entity.Employee;
import com.rmgx.assetmanagement.service.AssetService;
import com.rmgx.assetmanagement.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/assets")
public class AssetController {

    @Autowired
    private AssetService assetService;

    @Autowired
    private EmployeeService employeeService;

    // Add Asset
    @PostMapping
    public ResponseEntity<Asset> addAsset(@RequestBody Asset asset) {
        Asset savedAsset = assetService.addAsset(asset);
        return ResponseEntity.ok(savedAsset);
    }

    // Get All Assets
    @GetMapping
    public ResponseEntity<List<Asset>> getAllAssets() {
        List<Asset> assets = assetService.getAllAssets();
        return ResponseEntity.ok(assets);
    }

    // Get Asset by ID
    @GetMapping("/{id}")
    public ResponseEntity<Asset> getAssetById(@PathVariable Long id) {
        Optional<Asset> assetOpt = assetService.getAssetById(id);
        return assetOpt.map(ResponseEntity::ok)
                       .orElse(ResponseEntity.notFound().build());
    }

    // Update Asset
    @PutMapping("/{id}")
    public ResponseEntity<?> updateAsset(@PathVariable Long id, @RequestBody Asset updatedAsset) {
        try {
            Optional<Asset> existingAssetOpt = assetService.getAssetById(id);
            if (existingAssetOpt.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Asset not found");
            }

            Asset existingAsset = existingAssetOpt.get();

            // Update fields
            existingAsset.setAssetName(updatedAsset.getAssetName());
            existingAsset.setPurchaseDate(updatedAsset.getPurchaseDate());
            existingAsset.setConditionNotes(updatedAsset.getConditionNotes());
            existingAsset.setAssignmentStatus(updatedAsset.getAssignmentStatus());
            existingAsset.setCategory(updatedAsset.getCategory());
            existingAsset.setAssignedTo(updatedAsset.getAssignedTo());

            Asset savedAsset = assetService.updateAssetById(id,existingAsset);
            return ResponseEntity.ok(savedAsset);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error updating asset: " + e.getMessage());
        }
    }

    // Delete Asset
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAsset(@PathVariable Long id) {
        try {
            assetService.deleteAsset(id);
            return ResponseEntity.ok("Asset deleted successfully");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // Search by name
    @GetMapping("/search")
    public List<Asset> searchAssetsByName(@RequestParam String name) {
        return assetService.searchAssetsByName(name);
    }

    // Assign Asset to Employee
    @PutMapping("/{assetId}/assign/{employeeId}")
    public ResponseEntity<?> assignAsset(@PathVariable Long assetId, @PathVariable Long employeeId) {
        try {
            Employee employee = employeeService.getEmployeeById(employeeId)
                                   .orElseThrow(() -> new RuntimeException("Employee not found"));
            Asset updatedAsset = assetService.assignAssetToEmployee(assetId, employee);
            return ResponseEntity.ok(updatedAsset);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    // Recover Asset from Employee
    @PutMapping("/{assetId}/recover")
    public ResponseEntity<?> recoverAsset(@PathVariable Long assetId) {
        try {
            Asset updatedAsset = assetService.recoverAssetFromEmployee(assetId);
            return ResponseEntity.ok(updatedAsset);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
