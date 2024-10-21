package uz.ilmnajot.organisation_task.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.ilmnajot.organisation_task.service.RegionService;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/regions")
public class RegionController {

    private final RegionService regionService;
}
