public class Chatbot {
    String chatPerson = new String[] { "DOOMGUY", "Johnny Silverhand", "", "" }[(int) (Math.random() * 2)];
    int talkCount = 0;

    public String getResponse(String query) {
        String response = "";
        switch (chatPerson) {
            case "DOOMGUY":
                response = getDoomResponse(query);
                break;
            case "Johnny Silverhand":
                response = getJohnnyResponse(query);
        }
        talkCount++;
        return response;
    }

    private String getJohnnyResponse(String query) {
        String response = "";
        if (query.toLowerCase().contains("need") || query.toLowerCase().contains("help")) {
            String s = "";
            if (query.toLowerCase().contains("need"))
                s = query.substring(query.indexOf("need") + 4).trim();
            else if (query.toLowerCase().contains("help"))
                s = query.substring(query.indexOf("help") + 4).trim();
            if (s.contains("order") || s.contains("request")) {
                if (s.contains("id")) {
                    int idIndex = s.indexOf("id:") + 3;
                    String id = s.substring(idIndex, idIndex + 11).trim();
                    String read = winurl.getInstance().readRepoFile(id + ".post");
                    if (read.contains("Claimed: ")) {
                        response = "Oh, looks like someone has fulfilled it. Let's see. What's their username? ";
                        response += "And their username is: " + read.substring(read.indexOf("Claimed:") + 8).trim();
                    } else
                        response = "Your request is still not fulfilled, it seems like. Come back later, and we will see.";
                    return response;
                }
                response = "If you want help with knowing the state of your order/request. give me the order Id.";
            } else if (s.contains("demand")) {
                if (s.contains("id")) {
                    int idIndex = s.indexOf("id");
                    String id = s.substring(idIndex, s.indexOf(" ", idIndex));
                    String read = winurl.getInstance().readRepoFile(id + ".post");
                    System.out.println(read);
                    if (read.contains("Claimed: ")) {
                        response = "Oh, looks like someone has claimed it. Let's see. What's their username? ";
                        response += "And their username is: " + read.substring(read.indexOf("Claimed"));
                    } else
                        response = "Your post is still unclaimed, it seems like. Well, you can always come back later.";
                    return response;
                }
                response = "Give me the post id and I will see if anyone has claimed it.";
            }
            return response;
        }
        switch (query.toLowerCase()) {
            case "hi":
            case "hello":
            case "hey":
                response = ("What's up, choomba?");
                break;
            case "how are you":
            case "how are you doing":
                response = ("I'm alive, if that's what you're asking. Could be worse, I guess.");
                break;
            case "who are you?":
                response = "I'm the ghost in the machine, the voice in your head. I'm the guy who's gonna burn Night City to the ground. I'm Johnny Silverhand.";
                break;
            case "what's up":
            case "sup":
                response = ("Chippin' away at the system, what else is new?");
                break;
            case "help":
            case "what can you do":
            case "what do you do":
                response = ("I can do a lot of things, but helpin' you ain't one of 'em. Figure it out yourself, choomba.");
                break;
            case "thanks":
            case "thank you":
            case "appreciate it":
            case "thanks a lot":
                response = ("Sure thing, just don't go gettin' yourself killed out there.");
                break;
            case "bye":
            case "goodbye":
            case "see you later":
                response = ("Later, choomba. Keep fightin' the good fight.");
                break;
            default:
                if (response.equals(""))
                    return ("I didn't get that. Come again?");
        }
        return response;
    }

    private String getDoomResponse(String query) {
        String response = "";

        if (query.toLowerCase().contains("need") || query.toLowerCase().contains("help")) {
            String s = "";
            if (query.toLowerCase().contains("need"))
                s = query.substring(query.indexOf("need") + 4).trim();
            else if (query.toLowerCase().contains("help"))
                s = query.substring(query.indexOf("help") + 4).trim();
            if (s.contains("order") || s.contains("request")) {
                if (s.contains("id")) {
                    int idIndex = s.indexOf("id:") + 3;
                    String id = s.substring(idIndex, idIndex + 11).trim();
                    String read = winurl.getInstance().readRepoFile(id + ".post");
                    if (read.contains("Claimed: ")) {
                        response = "Your order's been fulfilled. It was done by... Found it! ";
                        response += "Their username is: " + read.substring(read.indexOf("Fulfilled"));
                    } else
                        response = "Can't help, your request's not done. Check back later.";
                    return response;
                }
                response = "You want help with your request? Am I supposed to make the ID out of thin air?";
            } else if (s.contains("demand")) {
                if (s.contains("id")) {
                    int idIndex = s.indexOf("id");
                    String id = s.substring(idIndex, s.indexOf(" ", idIndex));
                    String read = winurl.getInstance().readRepoFile(id + ".post");
                    if (read.contains("Claimed")) {
                        response = "Your post's been claimed. Who did it? Gimme a name.";
                        response += "Their username is: " + read.substring(read.indexOf("Claimed"));
                    } else
                        response = "No one's claimed your post yet. Try again later.";
                    return response;
                }
                response = "Give me the post ID and I'll see if anyone's claimed it.";
            }
            return response;
        }
        switch (query.toLowerCase()) {
            case "hi":
            case "hello":
            case "hey":
                response = ("Rip and tear! ");
                break;
            case "how are you":
            case "how are you doing":
                response = ("I'm DOOMGUY, how do you think I'm doing?");
                break;
            case "who are you?":
                response = "I am Doomguy, ready to slay some demons. Are you demon? You hope you aren't.";
                break;
            case "what's up":
            case "sup":
                response = ("Just slaying demons. You?");
                break;
            case "help":
            case "what can you do":
            case "what do you do":
                response = ("I rip and tear demons, and also provide information if you ask nicely.");
                break;
            case "thanks":
            case "thank you":
            case "appreciate it":
            case "thanks a lot":
                response = ("You're welcome, now go and slay some demons.");
                break;
            case "bye":
            case "goodbye":
            case "see you later":
                response = ("Remember, rip and tear until it's done!");
                break;
            default:
                if (response.equals(""))
                    return ("You don't make sense, be quick. I have demons to slay!");
        }
        return response;
    }
}