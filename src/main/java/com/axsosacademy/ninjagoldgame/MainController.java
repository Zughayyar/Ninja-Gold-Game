package com.axsosacademy.ninjagoldgame;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

@Controller
public class MainController {
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(HttpSession session) {
        if (session.getAttribute("totalGold") == null) {
            session.setAttribute("totalGold", 0);
            session.setAttribute("activities", new ArrayList<>());
            session.setAttribute("numPlay", 0);
        }
        return "index";
    }

    @RequestMapping(value = "/process_money", method = RequestMethod.POST)
    public String processMoney(HttpSession session, @RequestParam("building") String building) {
        Random rand = new Random();
        int goldEarned;
        String message;
        Date currentDate = new Date();
        switch (building) {
            case "farm":
                goldEarned = rand.nextInt(20 - 10 + 1) + 10;
                session.setAttribute("goldEarned", goldEarned);
                message = String.format("You entered a %s and Earned %d gold. %s", building, goldEarned, currentDate);
                addActivityMessage(session, message);
                addColorClass(session, goldEarned);
                break;
            case "cave":
                goldEarned = rand.nextInt(10 - 5 + 1) + 5;
                session.setAttribute("goldEarned", goldEarned);
                message = String.format("You entered a %s and Earned %d gold. %s", building, goldEarned, currentDate);
                addActivityMessage(session, message);
                addColorClass(session, goldEarned);
                break;
            case "house":
                goldEarned = rand.nextInt(5 - 2 + 1) + 2;
                session.setAttribute("goldEarned", goldEarned);
                message = String.format("You entered a %s and Earned %d gold. %s", building, goldEarned, currentDate);
                addActivityMessage(session, message);
                addColorClass(session, goldEarned);
                break;
            case "casino":
                goldEarned = rand.nextInt(50 - (-50) + 1) - 50;
                session.setAttribute("goldEarned", goldEarned);
                message = String.format("You entered a %s and Earned %d gold. %s", building, goldEarned, currentDate);
                addActivityMessage(session, message);
                addColorClass(session, goldEarned);
                break;
            default:
                return "redirect:/";
        }

        updateTotalGold(session, goldEarned);
        updateNumPlay(session);
        return "redirect:/";
    }

    private void addActivityMessage(HttpSession session, String message) {
        ArrayList<String> activities = (ArrayList<String>) session.getAttribute("activities");
        activities.addFirst(message);
        session.setAttribute("activities", activities);
    }

    private void addColorClass(HttpSession session, int goldEarned) {
        ArrayList<String> colorClasses = (ArrayList<String>) session.getAttribute("colorClass");

        if (colorClasses == null) {
            colorClasses = new ArrayList<>();
            session.setAttribute("colorClass", colorClasses);
        }

        if (goldEarned > 0) {
            colorClasses.addFirst("green-message");
        } else {
            colorClasses.addFirst("red-message");
        }
    }


    private void updateTotalGold(HttpSession session, int goldEarned) {
        int totalGold = (int) session.getAttribute("totalGold");
        session.setAttribute("totalGold", totalGold + goldEarned);
    }

    private void updateNumPlay(HttpSession session) {
        int numPlay = (int) session.getAttribute("numPlay");
        session.setAttribute("numPlay", numPlay + 1);
    }

    @RequestMapping(value = "/destroy_session", method = RequestMethod.POST)
    public String destroySession(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }
}