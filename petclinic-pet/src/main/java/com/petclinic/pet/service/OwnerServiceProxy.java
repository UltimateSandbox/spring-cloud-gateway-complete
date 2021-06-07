package com.petclinic.pet.service;

import com.petclinic.pet.models.Owner;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="owner-service", fallback = Fallback.class)
public interface OwnerServiceProxy {

    @GetMapping("ownerapi/owner/getById/{id}")
    Owner getOwnerById(@PathVariable("id") long id);
}

@Component
class Fallback implements OwnerServiceProxy{

    @Override
    public Owner getOwnerById(long id) {
        return null;
    }
}

