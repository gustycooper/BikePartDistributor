package bike;

import basicStuff.LoginAccount;
import basicStuff.Person;

/**
 * Created by gusty on 5/4/17.
 */
public class OfficeManager extends LoginAccount {

    public OfficeManager(String fn, String ln, String em, String un, String pw) {
        super(new Person(fn, ln, em), un, pw);
    }
}
