package com.sample.recyclerviewbrownbag.util;

import com.sample.recyclerviewbrownbag.model.Box;
import com.sample.recyclerviewbrownbag.model.Email;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

/**
 * Created by S. Reyes on 2/1/16.
 */
public class DummyDataGenerator {

    private static SimpleDateFormat sdf;
    private static String[] names = {"Edwardo Armes",
            "Brandon Crisman",
            "Claude Ruvolo",
            "Sheldon Macomber",
            "David Fortin",
            "Brain Hauger",
            "Vernon Birkholz",
            "Alejandro Aker",
            "Brendan Trimpe",
            "Britt Burd",
            "Erwin Anzaldua",
            "Errol Torrain",
            "Carson Shrewsbury",
            "Loyd Boudreaux",
            "Mohammed Linebaugh",
            "Jesse Keever",
            "Florentino Herron",
            "Chris Devereux",
            "Ivan Lassen",
            "Jerrod Cienfuegos",
            "Margaretta Braz",
            "Youlanda Fitzpatrick",
            "Kimi Dollinger",
            "Nerissa Tanguay",
            "Anjelica Pickard",
            "Tena Milardo",
            "Kimberlee Wines",
            "Elicia Pigeon",
            "Lavonna Haver",
            "Naomi Gulick",
            "Sacha Sorrentino",
            "Tasia Lavin",
            "Ava Portalatin",
            "Vernice Cephus",
            "Cinthia Amundsen",
            "Estelle Sundstrom",
            "Cira Manzanares",
            "Digna Cohn",
            "Clemmie Auger",
            "Melany Wagers"};
    private static String[] subjects = {
            "3 Ways To Be More Productive Today",
                    "Evernote Team",
                    "Google Calendar",
                    "Spotify",
                    "Dropbox",
                    "Google",
                    "Your Social Media Job Is Dead: Now What?",
                    "Epic Battle: Creativity vs. Discipline in Social",
                    "Can’t Keep up? 21 Ways to Simplify Your Search for New Clients",
                    "Extended for a day! Get Free shipping through Friday.",
                    "12 Pinterest Boards You Can’t Live Without, # 3 Is My Bible!",
                    "Enjoy this Special Offer at Our New Location",
                    "Ends Today! 20% Off Friends & Family",
                    "Yours in 2 days (free shipping!) + 40% off",
                    "Last chance to get 50% off!",
                    "Don’t Let These Get Away",
                    "Last chance! Be sure to grab the best price!",
                    "Temperatures Fall, Style Rises ",
                    "Party Like it’s 1999 Aged Cabernet Special",
                    "Facebook"
    };
    private static String[] contents = {
            "Lorem ipsum dolor sit amet, consectetur adipiscing elit.",
                    "Mauris aliquam odio dui, et condimentum neque facilisis non.",
                    "Etiam ultrices facilisis lacus. Donec diam felis, sodales in auctor quis, blandit nec enim.",
                    "Cras tincidunt scelerisque dui ultricies suscipit. Sed ac ligula odio.",
                    "Donec vel tortor nulla. Pellentesque hendrerit tincidunt consectetur.",
                    "Pellentesque tempus iaculis augue sed luctus.",
                    "Pellentesque gravida, nisl posuere consequat volutpat, massa orci accumsan mi, vel luctus nulla est vel ipsum.",
                    "Mauris elementum metus eget nibh accumsan, eleifend dapibus tellus euismod.",
                    "Ut sollicitudin metus mauris, pulvinar molestie nibh vehicula quis."
    };

    static {
        sdf = new SimpleDateFormat("h:mm:ss a");
    }

    public static List<Box> generateBoxes(int numOfBoxes) {
        List<Box> boxes = new ArrayList<>();
        for (int i = 0; i < numOfBoxes; i++) {
            Box box = new Box(i);
            boxes.add(box);
        }
        return boxes;
    }

    public static List<Email> generateDummmyDatas(int numberOfDatas) {
        List<Email> mails = new ArrayList<>();
        for (int i = 0; i < numberOfDatas; i++) {
            Email mail = generateEmail(Email.Status.Read);
            mails.add(mail);
        }
        return mails;
    }

    public static Email generateEmail(Email.Status status) {
        Random r = new Random();
        Email mail = new Email();
        int randomSenderIdx = r.nextInt((names.length));
        int randomSubjIdx = r.nextInt((subjects.length));
        int randomContentIdx = r.nextInt((contents.length));
        mail.setSender(names[randomSenderIdx]);
        mail.setStatus(status);
        mail.setContent(contents[randomContentIdx]);
        mail.setDate(sdf.format(Calendar.getInstance().getTime()));
        mail.setSubj(subjects[randomSubjIdx]);
        return mail;
    }
}
