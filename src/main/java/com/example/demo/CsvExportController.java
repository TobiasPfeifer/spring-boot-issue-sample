package com.example.demo;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.ResponseStatusException;

@Controller
@RequestMapping("clients")
public class CsvExportController {

  @GetMapping(path = {"{id}"}, produces = {DemoApplication.MEDIA_TYPE_CSV})
  public ResponseEntity<String> getChangedProtocol(@PathVariable String id) {
    if ("12345".equalsIgnoreCase(id)) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }
    return new ResponseEntity<String>("a,dummy,csv,string", HttpStatus.OK);
  }
}
