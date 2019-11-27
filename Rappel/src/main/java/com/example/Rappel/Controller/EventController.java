/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.Rappel.Controller;

import com.example.Rappel.Exception.ResourceNotFoundException;
import com.example.Rappel.Model.Event;
import com.example.Rappel.Repository.EventRepository;
import java.util.List;
import javax.transaction.Transactional;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Sniper Elite
 */
@RestController
@Transactional
@RequestMapping("/api")
@CrossOrigin(origins = "localhost:3000", maxAge = 3600)
public class EventController {
     @Autowired
    EventRepository eventRepository;

    // Get All Events
@GetMapping("/event")
public List<Event> getEvents() {
    return (List<Event>) eventRepository.findAll();
}
// Create a new Event
@PostMapping("/events")
public Event createEvent(@Valid @RequestBody Event event) {
    return eventRepository.save(event);
}

// Get a Single Event
@GetMapping("/events/{id}")
public Event getEventById(@PathVariable(value = "id") Long eventId) {
    return eventRepository.findById(eventId)
            .orElseThrow(() -> new ResourceNotFoundException("Event", "id", eventId));
}
   
// Delete a Event
@DeleteMapping("/events/{id}")
public ResponseEntity<?> deleteEvent(@PathVariable(value = "id") Long eventId) {
    Event event = eventRepository.findById(eventId)
            .orElseThrow(() -> new ResourceNotFoundException("Event", "id", eventId));

    eventRepository.delete(event);

    return ResponseEntity.ok().build();
}
}

