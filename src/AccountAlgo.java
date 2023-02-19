public class AccountAlgo {
    private String saltChars = "1234567890ABCDEFGHIJKLMNOPQRSTUVWXYZ!@#$%^&*()abcdefghijklmnopqrstuvwxyz"; // 72 length
    private String userPassesList[], idList[];

    public AccountAlgo() {
    }

    public AccountAlgo(String userList[], String passesList[]) {
        setUserPasses(userList, passesList);
    }

    public void setIds(String ids[]) {
        idList = new String[ids.length];
        for (int i = 0; i < ids.length; i++)
            idList[i] = ids[i];
    }

    private void setUserPasses(String userList[], String passesList[]) {
        userList = clearNull(userList);
        passesList = clearNull(passesList);
        userPassesList = new String[Math.min(userList.length, passesList.length)];
        for (int i = 0; i < userPassesList.length; i++)
            userPassesList[i] = userList[i] + " " + passesList[i]; // making the user and password pairs.
    }

    private String[] clearNull(String list[]) {
        int count = 0;
        for (String i : list)
            if (!i.isEmpty())
                count++;

        String ret[] = new String[count];

        count = 0;
        for (int i = 0; i < ret.length; count++)
            if (!list[count].isEmpty())
                ret[i++] = list[count];

        return ret;
    }

    public String encrypt(String pass) {
        String enc = "";
        for (int i = 0; i < pass.length() / 2; i++)
            enc += (char) ((pass.charAt(i) + pass.charAt(pass.length() / 2 + i)) / 2);
        return enc;
    }

    public String insertSalt(String user, String pass, String salt) {
        String salted = ""; // encrypted value
        for (int i = 0, x = -3; i < pass.length(); i++, x++) {
            if (x % 3 == 0) { // Put the salt every 3 characters.
                salted += salt;
                salt = getSalt(user, salt, (pass + " user:" + user).length() / 2);
            }
            salted += pass.charAt(i);
        }
        salted += getSalt(user, salt, (pass + " user:" + user).length() / 2);
        return salted;
    }

    public String getEncryption(String user, String pass) {
        String userPass = pass + " user:" + user; // preparing the user and password pair.
        pass = insertSalt(user, pass, getSalt(user, pass, userPass.length() / 2));
        pass = encrypt(pass);
        return pass;
    }

    public boolean tryLogin(String user, String pass) {
        pass = getEncryption(user, pass);
        return contained(user + " " + pass);
    }

    // This will check if the pair of user and encrypted password is in the
    // userPassesList
    private boolean contained(String check) {
        for (String i : userPassesList) {
            if (i.equals(check))
                return true;
        }
        return false;
    }

    // This is an important function...
    public String getSalt(String user, String pass, int length) {
        String s = pass + " user:" + user; // preparing the pair.
        String salt = "";
        int x = 0;
        for (int i = 0; i < s.length(); i++) {
            x = (x << 4) | (s.charAt(i) >> 3);
            if (x > 72) {
                salt += saltChars.charAt(x % 72);
                x %= 72;
            }
            if (salt.length() >= length)
                break;
        }
        return salt.equals("") ? "Aj#@e(c" : salt;
    }
}