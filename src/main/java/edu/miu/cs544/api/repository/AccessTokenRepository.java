package edu.miu.cs544.api.repository;

import edu.miu.cs544.api.entity.AccessToken;
import org.springframework.data.repository.CrudRepository;

public interface AccessTokenRepository extends CrudRepository <AccessToken, String>{
}
