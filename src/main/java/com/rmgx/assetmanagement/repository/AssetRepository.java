package com.rmgx.assetmanagement.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rmgx.assetmanagement.entity.Asset;

@Repository
public interface AssetRepository extends JpaRepository<Asset, Long> {

    List<Asset> findByAssetNameContainingIgnoreCase(String assetName);

}
