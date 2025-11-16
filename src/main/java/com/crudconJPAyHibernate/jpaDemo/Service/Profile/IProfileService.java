package com.crudconJPAyHibernate.jpaDemo.Service.Profile;

import com.crudconJPAyHibernate.jpaDemo.Model.Entity.Profile;

public interface IProfileService {

    public Profile getProfile(Long personId);
    public Profile createProfile(Profile profile);
    public Profile updateProfile(Profile profile);
    public void deleteProfile(Long profileId);
    public boolean existsByPersonId(Long personId);
}
