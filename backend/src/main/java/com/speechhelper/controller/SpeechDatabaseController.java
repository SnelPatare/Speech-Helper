package com.speechhelper.controller;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.speechhelper.databasemanager.SpeechEntity;
import com.speechhelper.databasemanager.SpeechRepository;
import com.speechhelper.speechtotext.Speech;

@RestController
public class SpeechDatabaseController {
	@Autowired
	private SpeechRepository speechRepository;
	
	@Transactional
	@PostMapping(path="/add_speech") // Map ONLY POST Requests
	public Long addNewSpeech (@RequestParam Long userId, @RequestParam Speech speech) {
		SpeechEntity n = new SpeechEntity();
		n.setUserId(userId);
		Calendar cal = Calendar.getInstance();
		n.setDateCreated(String.valueOf(cal.get(Calendar.YEAR)) + String.valueOf(cal.get(Calendar.MONTH)+1) + String.valueOf(cal.get(Calendar.DATE)));
//		n.setTranscribedSpeechText(speech.getText());
//		n.setConvertedSpeechText(speech.getInput());
		n = speechRepository.save(n);
		System.out.println("after saving speech"+n.getSpeechId());
		return n.getSpeechId();
	}
	
	@Transactional
	@GetMapping(path="/all/speech")
	public Iterable<SpeechEntity> getAllSpeech() {
	    // This returns a JSON or XML with the users
	    return speechRepository.findAll();
	}
	
	@Transactional
	@GetMapping(path = "/speech_user_id")
	public Map<String, String> findByUserId(@RequestParam Long userId){
		HashMap<String, String> map = new HashMap<>();
		List<SpeechEntity> speeches = speechRepository.findByUserId(userId);
		
		java.util.Iterator<SpeechEntity> it  = speeches.iterator();
		while (it.hasNext()) {
			SpeechEntity e = it.next();
			map.put(e.getSpeechId()+"", e.getDateCreated().toString()+"");
		}
		
		return map;
	}
	
	
	@Transactional
	@GetMapping(path = "/speech_id")
	public SpeechEntity findBySpeechId(@RequestParam Long speechId){
		return speechRepository.findBySpeechId(speechId);
	}
	
	
}
