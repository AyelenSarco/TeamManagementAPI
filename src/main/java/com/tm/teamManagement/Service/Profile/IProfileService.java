package com.tm.teamManagement.Service.Profile;

import com.tm.teamManagement.Dto.Profile.ProfileBaseDTO;
import com.tm.teamManagement.Model.Entity.Profile;

public interface IProfileService {

    public Profile getProfile(Long personId);
    public Profile createProfile(ProfileBaseDTO profileBaseDTO,Long personId);
    public Profile updateProfile(ProfileBaseDTO profileBaseDTO,Long personId);
    public void deleteProfile(Long profileId);
    public boolean existsByPersonId(Long personId);
}
