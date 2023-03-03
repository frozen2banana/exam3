package com.exam3.transfer3.repositories;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import com.exam3.transfer3.models.TransferForm;


@Repository
public class DetailsRepository {

	@Autowired @Qualifier("bank")
	private RedisTemplate<String, String> redisTemplate;

	public void save(TransferForm transfer) {
		redisTemplate.opsForValue().set(
				transfer.getTransId(), transfer.toJSON().toString()
		);
	}

	public Optional<TransferForm> get(String transId) {
		String json = redisTemplate.opsForValue().get(transId);

		if ((null == json) || (json.trim().length() <= 0))
			return Optional.empty();

		return Optional.of(TransferForm.create(json));
	}

}
