package com.speechhelper.databasemanager;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface SpeechRepository extends JpaRepository<SpeechEntity, Long>{
	
	List<SpeechEntity> findByUserId(Long userId);
	SpeechEntity findBySpeechId(long speechId);
	List<SpeechEntity> findAll();
}
