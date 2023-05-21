package edu.miu.cs544.repository;

import edu.miu.cs544.entity.AccessToken;
import org.springframework.data.repository.CrudRepository;

public interface AccessTokenRepository extends CrudRepository <AccessToken, String>{
}
