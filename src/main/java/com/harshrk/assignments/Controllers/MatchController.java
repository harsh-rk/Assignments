package com.harshrk.assignments.Controllers;

import com.harshrk.assignments.Services.Match;
import com.harshrk.assignments.Beans.Scorecard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MatchController {

    @Autowired
    private Match match;

    @RequestMapping("/matches")
    public Scorecard match() {
        return match.simulateMatch();
    }
}
