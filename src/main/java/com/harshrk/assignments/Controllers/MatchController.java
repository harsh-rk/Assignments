package com.harshrk.assignments.Controllers;

import com.harshrk.assignments.ServiceObjects.Match;
import com.harshrk.assignments.MatchObjects.Scorecard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MatchController {

    @Autowired
    private Match match;

    @RequestMapping("/newMatch")
    public Scorecard newMatch() {
        return match.simulateMatch();
    }
}
