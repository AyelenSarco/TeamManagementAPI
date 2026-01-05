package com.crudconJPAyHibernate.jpaDemo.Service.Profile;

import com.crudconJPAyHibernate.jpaDemo.Dto.Profile.ProfileBaseDTO;
import com.crudconJPAyHibernate.jpaDemo.Model.Entity.Profile;

public interface IProfileService {

    public Profile getProfile(Long personId);
    public Profile createProfile(ProfileBaseDTO profileBaseDTO,Long personId);
    public Profile updateProfile(ProfileBaseDTO profileBaseDTO,Long personId);
    public void deleteProfile(Long profileId);
    public boolean existsByPersonId(Long personId);
}
