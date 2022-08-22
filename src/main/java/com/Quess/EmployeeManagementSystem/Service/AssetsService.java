package com.Quess.EmployeeManagementSystem.Service;

import com.Quess.EmployeeManagementSystem.Models.Assets.Assets;


import java.util.List;

public interface AssetsService {

    Assets saveAsset(Assets assets);

    List<Assets> getAllAssets();
    Assets getAssetById(int id);
    Assets updateAsset(Assets assets,int id);
    void deleteAsset(int id);
}
