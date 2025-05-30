package com.rmgx.assetmanagement.service;

import com.rmgx.assetmanagement.entity.Asset;
import com.rmgx.assetmanagement.entity.Employee;

import java.util.List;
import java.util.Optional;

public interface AssetService {

    Asset addAsset(Asset asset);

    List<Asset> getAllAssets();

    Optional<Asset> getAssetById(Long id);


    void deleteAsset(Long id) throws Exception;

    List<Asset> searchAssetsByName(String name);

    Asset assignAssetToEmployee(Long assetId, Employee employee);

    Asset recoverAssetFromEmployee(Long assetId);

	Asset updateAssetById(Long id, Asset updatedAsset);

}
